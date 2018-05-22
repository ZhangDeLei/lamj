package com.coderfamily.lamj.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 用于token工具
 *
 * @author ZhangDL
 * @date 2018/1/26 12:02
 */
public class TokenUtil {
    // 过期时间24小时
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        JsonObject json = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .withClaim("secret", secret)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            String payload = jwt.getPayload();
            byte[] base64 = Base64.decodeBase64(payload);
            String jsonStr = new String(base64);
            json = new Gson().fromJson(jsonStr, JsonObject.class);
            long expTime = Long.parseLong(json.get("exp").toString() + "000");
            long curTime = TimeUtils.getCurrentTimeStamp();
            String un = json.get("username").toString().replace("\"", "");
            String sc = json.get("secret").toString().replace("\"", "");
            //验证用户名密码以及token有效期
            if (username.equals(un) && secret.equals(sc) && expTime > curTime) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取token中保存的secret信息
     *
     * @param token
     * @return
     */
    public static String getPassword(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("secret").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,24小时后过期
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("secret", secret)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
