package com.yicen.flutter_service_demo.services;

import com.yicen.flutter_service_demo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component("UserService")
public interface UserService {
    public List<User> queryAll();
    public User test();
    public User add(User user);
    public User queryByUsername(String username);
    public User queryById(Integer id);
    public User queryByPhone(String mobile);
    public User modifyAvatar(String username,String url);
    public User modifyUsername(String username,String nickname);
    public User modifyGender(String username,Boolean gender);
    public User modifyRealName(String username,String realName);
    public User modifyBirthday(String username,String birthday);
    public User modifyPhone(String username,String phone);
    public User modifyEmail(String username,String email);
    public User modifyPwd(String username,String pwd);
    public String getInviteCode(long id);
}
