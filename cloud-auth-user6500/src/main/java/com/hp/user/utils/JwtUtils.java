package com.hp.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-24 22:02
 */
public class JwtUtils {
    /**
     * 签发人
     */
    private static final String ISS_USER = "ds2dash";

    /**
     * 过期时间
     */
    private static  final long TOKEN_EXPIRE_TIME = 5  * 60 * 1000;

    /**
     * 秘钥
     */
    private static final String KEY = "wer21w3e2r904923";



    /**
     * 生成令牌
     * @return
     */
    public static String token(){


        Date now = new Date();
        // key 用来加密数据签名秘钥
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        // 1. 创建JWT
        return JWT.create()
                //签发人
                .withIssuer(ISS_USER)
                // 签发时间
                .withIssuedAt(now)
                // 过期时间
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME))
                .sign(algorithm);

    }


    /**
     * 验证令牌
     * @return
     */
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            // 1. 验证令牌
            JWTVerifier verifier = JWT.require(algorithm)
                    // 签发人
                    .withIssuer(ISS_USER)
                    .build();

            // 如果校验有问题会抛出异常
            verifier.verify(token);
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            e.printStackTrace();
        }

        return false;

    }


    public static void main(String[] args) {
        String token = token();

        boolean verify = verify(token);
        System.out.println(verify);

    }
}
