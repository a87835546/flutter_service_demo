package com.yicen.flutter_service_demo.services.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.mysql.cj.util.StringUtils;
import com.yicen.flutter_service_demo.entity.TbUser;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.entity.vo.TbRegisterUserVo;
import com.yicen.flutter_service_demo.exception.DemoException;
import com.yicen.flutter_service_demo.mapper.TbUserDao;
import com.yicen.flutter_service_demo.mapper.UserMapper;
import com.yicen.flutter_service_demo.services.UserDaoService;
import com.yicen.flutter_service_demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDaoService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TbUserDao userDao;

    @Override
    public List<User> queryAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User test() {
        return userMapper.selectById("1");
    }

    /**
     * 生成邀请的盐值
     */
    private String kInviteCodeSlot = "ssjkdhasldahkDFHKJHJKHJ12312KASHD798";

    @Override
    public User add(User user) {
        user.setPoint(50);
        int i = userMapper.insert(user);
        int id = 0;
        if (i > 0) {
            id = user.getId();
        }
        log.info("insert user id ---- >>>>>>" + id);
        createCode(id);
        if(!StringUtils.isNullOrEmpty(user.getInviteCode())){
            long userIdByInviteCode = getUserIdByInviteCode(user.getInviteCode());
            User user1 = modifyPointById(userIdByInviteCode, 50);
            log.info( null != user1? "更新用户积分成功":"更新用户积分失败");
        }
        return userMapper.selectById(id);
    }


    public void createCode(long id){
        Hashids hashids = new Hashids(kInviteCodeSlot,6);
        try {
            User user = insertInviteCode(id, hashids.encode(id));
            if (null != user){
                log.info("生成邀请码成功----{}",user.getInviteCode());
            }else {
                createCode(id);
            }
        }catch (Exception err){
            log.error(err.getMessage());
            createCode(id);
        }

    }

    @Override
    public TbUser registerUser(TbRegisterUserVo vo) {
        if (vo.getRegisterCode().equals("000000")) { //是超级管理员
            boolean result = userDao.hasRootRole();
            if (!result) { // 如果表中没有超级管理员账号
                TbUser user = new TbUser();
                user.setName(vo.getName());
                user.setRole("[0]");
                user.setRoot(true);
                user.setStatus(Byte.valueOf("1"));
                user.setCreateTime(new Date());
                user.setNickname(vo.getUsername());
                int i = userDao.insert(user);
                Integer id = 0;
                if (i > 0) {
                    id = user.getId();
                }
                return userDao.selectByPrimaryKey(id);
            } else {
                throw new DemoException("无法绑定超级管理员账号");
            }
        } else {
            TbUser user = new TbUser();
            user.setName(vo.getName());
            user.setRole("[1]");
            user.setRoot(false);
            user.setStatus(Byte.valueOf("1"));
            user.setCreateTime(new Date());
            user.setNickname(vo.getUsername());
            int i = userDao.insert(user);
            Integer id = 0;
            if (i > 0) {
                id = user.getId();
            }
            log.info(userDao.selectByPrimaryKey(id).toString());
            return userDao.selectByPrimaryKey(id);
        }
    }

    @Override
    public User queryByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<User> users = userMapper.selectList(wrapper);
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public User queryByPhone(String mobile) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        List<User> users = userMapper.selectList(wrapper);
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public User modifyAvatar(String username, String url) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = new User();
        user.setAvatar(url);
        log.info(user.toString());
        int update = userMapper.update(user, wrapper);
        if (update > 0) {
            user = userMapper.selectOne(wrapper);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User modifyUsername(String username, String nickname) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = new User();
        user.setNickName(nickname);
        int update = userMapper.update(user, wrapper);
        if (update > 0) {
            user = userMapper.selectOne(wrapper);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User modifyGender(String username, Boolean gender) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = new User();
        user.setGender(gender);
        int update = userMapper.update(user, wrapper);
        if (update > 0) {
            user = userMapper.selectOne(wrapper);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User modifyRealName(String username, String realName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = new User();
        user.setRealName(realName);
        int update = userMapper.update(user, wrapper);
        if (update > 0) {
            user = userMapper.selectOne(wrapper);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User modifyBirthday(String username, String birthday) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = new User();
        user.setBirthday(birthday);
        int update = userMapper.update(user, wrapper);
        if (update > 0) {
            user = userMapper.selectOne(wrapper);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User modifyPhone(String username, String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = new User();
        user.setMobile(phone);
        int update = userMapper.update(user, wrapper);
        if (update > 0) {
            user = userMapper.selectOne(wrapper);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User modifyEmail(String username, String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = new User();
        user.setEmail(email);
        int update = userMapper.update(user, wrapper);
        if (update > 0) {
            user = userMapper.selectOne(wrapper);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User modifyPwd(String username, String pwd) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = new User();
        user.setPassword(pwd);
        int update = userMapper.update(user, wrapper);
        if (update > 0) {
            user = userMapper.selectOne(wrapper);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public String getInviteCode(long id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = userMapper.selectOne(wrapper);
        return  user.getInviteCode();
    }

    @Override
    public User queryById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 插入 邀请码
     * @param id
     * @param code
     * @return
     */
    public User insertInviteCode(long id, String code){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = new User();
        user.setInviteCode(code);
        int update = userMapper.update(user, wrapper);
        if (update > 0) {
            user = userMapper.selectOne(wrapper);
            return user;
        } else {
            return null;
        }
    }

    /**
     * 根据 邀请码解析会用户id
     * @param code
     * @return
     */
    public long getUserIdByInviteCode(String code){
        Hashids hashids = new Hashids(kInviteCodeSlot,6);
        try {
            return  hashids.decode(code)[0];
        }catch (Exception e){
            throw  new DemoException("使用邀请码，解析用户id失败");
        }
    }

    /**
     * 更新用户的积分 默认50分
     * @param id
     * @param value
     * @return
     */
    public User modifyPointById(long id,long value){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = new User();
        int point = user.getPoint();
        point += value;
        user.setPoint(point);
        int update = userMapper.update(user, wrapper);
        if (update > 0) {
            user = userMapper.selectOne(wrapper);
            return user;
        } else {
            return null;
        }
    }
}
