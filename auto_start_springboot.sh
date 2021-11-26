src="/home/adminpc/Desktop/"
log="/home/adminpc/Desktop/logs/my.log"
fileType="jar"
/usr/bin/inotifywait -mr --timefmt '%d/%m/%y %H:%M' --format '%T %w %f' -e close_write $src | while  read DATE TIME DIR FILE; do
  FILECHANGE=${DIR}${FILE}

  if [[ $FILECHANE =~ $fileType ]]; then
      echo "At ${TIME} on ${TIME}, file $FILECHANGE was change" >> $log
      /home/adminpc/Desktop/springboot.sh restart >> $log

  fi
    
done
