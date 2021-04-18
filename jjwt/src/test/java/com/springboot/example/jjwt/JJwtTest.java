package com.springboot.example.jjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName JJwtTest.java
 * @createTime 2021年04月18日 10:42:00
 */
@SpringBootTest
public class JJwtTest {

    @Test
    public void testJwtBuilder() {
        final long now = System.currentTimeMillis();
        // 过期时间 1min
        final long exp = now + 60 * 1000;
        final String token = Jwts
                .builder()
                // jti jwt唯一标识
                .setId("888")
                // sub 签发用户
                .setSubject("jwt")
                // iat 签发时间
                .setIssuedAt(new Date())
                // 签名算法, secret
                .signWith(SignatureAlgorithm.HS256, "demo")
                // 过期时间
                .setExpiration(new Date(exp))
                // 自定义声明 map
//                .addClaims()
                .claim("username", "admin")
                .claim("password", "123456")
                .compact();
        System.out.println(token);

        final String[] split = token.split("\\.");
        System.out.println("header: " + TextCodec.BASE64.decodeToString(split[0]));
        System.out.println("payload: " + TextCodec.BASE64.decodeToString(split[1]));
        System.out.println("signature: " + TextCodec.BASE64.decodeToString(split[2]));
    }

    @Test
    public void testParseToken() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJqd3QiLCJpYXQiOjE2MTg3MTY4ODksImV4cCI6MTYxODcxNjk0OCwidXNlcm5hbWUiOiJhZG1pbiIsInBhc3N3b3JkIjoiMTIzNDU2In0.Jh41Ocn3FalSrE6B81mlVRQ_WZ1PyKPRnBsHnpTj_d0";
        final Jws<Claims> claimsJws = Jwts.parser().setSigningKey("demo").parseClaimsJws(jwt);
        // payload
        final Claims jwsBody = claimsJws.getBody();
        System.out.println("jti= " + jwsBody.getId());
        System.out.println("sub= " + jwsBody.getSubject());
        System.out.println("iat= " + jwsBody.getIssuedAt());
        System.out.println("exp= " + jwsBody.getExpiration());
        // 自定义声明
        System.out.println("username= " + jwsBody.get("username", String.class));
        System.out.println("password= " + jwsBody.get("password", String.class));
    }


}
