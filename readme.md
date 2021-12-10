#关于Ubuntu20 上的mysql8.0以后版本连接一直报错问题的解决
###首先确保用户的host是不是localhost，如果是就需要修改()
    use mysql;
    update use set host='localhost' where use'root';
    flush privileges;
    如果到这来，使用workbench 或者其他的第三方仍然打不开，就需要修改查看Firewalls 是不是已经开放了3306端口

##查看springboot 后台的日志打印
    nohup java -jar xxx.jar > mylog.log 2>&1 

    上面的2 和 1 的意思如下:
    
    0 标准输入（一般是键盘）
    
    1 标准输出（一般是显示屏，是用户终端控制台）
    
    2 标准错误（错误信息输出）
    tail -f mylog.log | grep --line-buffered 你所需要查询的方法

#swagger2 404的问题
    在WebMvcConfigurationSupport配置文件中添加 这三条记录
    registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

#fastdfs 使用记录 [fastdfs-client-java 介绍](https://www.cnblogs.com/binghe001/p/13751723.html)
    1.在docker里面安装fastdfs的镜像，并且配置相对应的端口。
    2.在客服端使用fastdfs-client-java 这个库
    