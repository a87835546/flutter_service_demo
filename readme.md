#关于Ubuntu20 上的mysql8.0以后版本连接一直报错问题的解决
###首先确保用户的host是不是localhost，如果是就需要修改()
    use mysql;
    update use set host='localhost' where use'root';
    flush privileges;
    如果到这来，使用workbench 或者其他的第三方仍然打不开，就需要修改查看Firewalls 是不是已经开放了3306端口