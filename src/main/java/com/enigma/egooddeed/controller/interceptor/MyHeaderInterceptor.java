package com.enigma.egooddeed.controller.interceptor;

import com.enigma.egooddeed.controller.UrlMappings;
import com.enigma.egooddeed.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHeaderInterceptor implements HandlerInterceptor {

//    @Autowired
//    JwtUtil jwtUtil;

    @Autowired
    RestTemplate restTemplate;

    @Value("${service.authentication}")
    String authServiceUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().contains(UrlMappings.AUTH_URL) || request.getRequestURI().contains(UrlMappings.ADMIN_URL+"/create")){
            return true;
        }
//
//        return jwtUtil.validateToken(request.getHeader("my-header"));

        try {
            String tokenHeader = request.getHeader("Authorization");
            String[] tokenBearer = tokenHeader.split(" ");
            restTemplate.getForEntity(authServiceUrl+"?token="+tokenBearer[1],String.class);
            return true;
        } catch (HttpClientErrorException ee){
            if (ee.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
                throw new UnauthorizedException(ee.getMessage());
            }
            throw new RuntimeException(ee.getMessage());
        }catch (RestClientException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
