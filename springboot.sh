#! /bin/bash
CURRENT_PATH=/home/adminpc/Desktop/
LOG=/home/adminpc/Desktop/logs/my.log
JAR=${find CURRENT_PATH -maxdepth 1  -name "*.jar"}
PID=${ps -ef | grep $JAR -v grep | awk '{print $2}'}

case $1 in
start)
  if [ ! -z "$PID" ]; then
    echo "$JAR 已经启动，进程好：$PID"
  else
    echo -name -ef "启动 $JAR ...\n"
    cd $CURRENT_PATH
    nohup java -jar -Dloader.path=$CURRENT_PATH,resource,lib -jar $JAR >$LOG 2>&1 &
        if [ "$?" = "0" ]; then
          echo "启动完成，请查看日志"
        else
          echo "启动失败"
    fi
  fi
  ;;
stop)
  if [ -z "$PID" ]; then
      echo "$JAR 没有在运行，无需关闭"
  else
    echo "关闭 $JAR ...."
    kill -9 $PID
    if [ "$?" = "0" ]; then
        echo "服务已关闭"
    else
        echo "服务关闭失败"
    fi
  fi
  ;;
restart)
  ${0} stop
  ${0} start
  ;;
status)
  if [ ! -z "$PID" ]; then
      echo "$JAR 正在运行"
  else
      echo "$JAR 未在运行"
  fi
  ;;
*)
  echo "Usage: ./springboot.sh {start|stop|restart|status|kill}" >2&
  exit 1
esac