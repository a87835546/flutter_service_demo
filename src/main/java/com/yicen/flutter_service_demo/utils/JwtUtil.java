package com.yicen.flutter_service_demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yicen.flutter_service_demo.entity.User;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class JwtUtil {

    private static final long EXPIRE_DATE = 30 * 24 * 60 * 60 * 100000;//一个月
    private static final String TOKEN_SALT = "1231223jsadkajsjhbn";//一个月

    public static String getToken(User user) {
        String token = "";
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SALT);
            Map<String, Object> map = new HashMap<>();
            map.put("type", "jwt");
            map.put("alg", "hmac256");
            token = JWT.create().withHeader(map).withClaim("userinfo", JSONObject.fromObject(user)).withExpiresAt(date).sign(algorithm);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    public static boolean verity(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SALT);
            JWTVerifier verifier  = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
//            log.info("jwt" + jwt.getToken());
            Claim s = jwt.getClaim("userinfo");
            User user = (User)JSONObject.toBean(JSONObject.fromObject(s.asMap()),User.class);
//            log.info("验证 token是否有效 ----- >>>>>>> " + user);
            return user!=null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static User getUser(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SALT);
            JWTVerifier verifier  = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Claim s = jwt.getClaim("userinfo");
            User user = (User)JSONObject.toBean(JSONObject.fromObject(s.asMap()),User.class);
//            log.info("验证 token是否有效 ----- >>>>>>> " + user);
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static User getUserByRequestServlet(HttpServletRequest servletRequest){
        try{
            User user = JwtUtil.getUser(servletRequest.getHeader("token"));
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
