package cn.iocoder.springboot.lab01.springsecurity.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * JwtToken 工具类
 * @author Jaquez
 * @date 2021/09/25 19:34
 */
@Component
public class JwtTokenUtil {

    @Value("${key-store.type}")
    private String type;

    @Value("${key-store.alias}")
    private String alias;

    @Value("${key-store.password}")
    private String password;

    // 获取证书文件
    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks");
    // 私钥
    private static PrivateKey privateKey = null;
    // 公钥
    private static PublicKey publicKey = null;

    // 将证书文件里边的私钥公钥拿出来

    @PostConstruct
    private void before(){
        try {
            // java key store 固定常量
            KeyStore keyStore = KeyStore.getInstance(type);
            // 秘钥可以自定义
            keyStore.load(inputStream, password.toCharArray());
            // jwt 为 命令生成整数文件时的别名
            privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
            publicKey = keyStore.getCertificate(alias).getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成 token
     * @author Jaquez
     * @date 2021/09/25 19:42
     * @param subject
     * @param expirationSeconds
     * @param salt
     * @return java.lang.String
     */
    public static String generateToken(String subject, int expirationSeconds, String salt) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                // 不需要使用公钥私钥
                // .signWith(SignatureAlgorithm.HS512, salt)
                // 需要公钥、私钥配合使用才行
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 解析 token
     * @param token
     * @param salt
     * @return
     */
    public static String parseToken(String token, String salt) {
        String subject = null;
        try {
            Claims claims = Jwts.parser()
                    // 不需要使用公钥私钥
                    // .setSigningKey(salt)
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return subject;
    }

}
