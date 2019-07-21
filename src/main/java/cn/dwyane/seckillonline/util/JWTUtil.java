package cn.dwyane.seckillonline.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class JWTUtil {
    private static final String TOKEN_SECRET = "tokensecret123";

    static class AlgorithmHolder{
        static final Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        public static Algorithm getInstance(){
            return algorithm;
        }
    }

    public static <T> String sign(T object ,long expireTime){
        Date now = new Date();
        now.setTime(now.getTime()+expireTime);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now.getTime()+expireTime);
        calendar.getTime();

        Map<String,Object> headerClaims = new HashMap<>();
        headerClaims.put("alg","HMAC256");
        headerClaims.put("type","JWT");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
        return JWT.create().withHeader(headerClaims).withClaim("payload",json).withExpiresAt(now).sign(AlgorithmHolder.getInstance());
    }

    public static boolean verify(String token){
        JWTVerifier jwtVerifier = JWT.require(AlgorithmHolder.getInstance()).build();
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
        }catch (JWTVerificationException e){
            return false;
        }
        return true;
    }

    public static void main(String[] args){
//        Map<String,Object> map = new HashMap<>();
//        map.put("name","test123123123");
//        map.put("feature","poor");
//        //String token = sign(map,1000*60*1);
//        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJwYXlsb2FkIjoie1wiZmVhdHVyZVwiOlwicG9vclwiLFwibmFtZVwiOlwidGVzdDEyMzEyMzEyM1wifSIsImV4cCI6MTU2MzU5MzkyNX0.mefpaO5iesiE5OUeJvtXGT7hMlDTH0ls6leOThLIY0I\n";
//        System.out.println(token);
//
//        boolean verify = verify(token);
//
//        System.out.println(verify);
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        for(int i=0;i<1;i++){
            concurrentLinkedQueue.offer(i);
        }
    }

}
