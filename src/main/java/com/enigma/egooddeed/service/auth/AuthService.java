package com.enigma.egooddeed.service.auth;

import com.enigma.egooddeed.entity.Admin;
import com.enigma.egooddeed.exception.UnauthorizedException;
import com.enigma.egooddeed.model.request.AuthRequest;
import com.enigma.egooddeed.service.IAdminService;
import com.enigma.egooddeed.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService implements IAuthService{

    private final List<String > tokenStorage = new ArrayList<>();

    @Autowired
    private IAdminService adminService;
    final JwtUtil jwtUtil;

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(AuthRequest authRequest) {
        Admin cek = adminService.findByUsername(authRequest.getUsername());
        if(authRequest.getUsername().equalsIgnoreCase(cek.getUsername()) && authRequest.getPassword().equals(cek.getPassword())){
            String token = jwtUtil.generateToken("GoodDeed");
            tokenStorage.add(token);
            return token;
        }else {
            throw new UnauthorizedException("Invalid username or password");
        }    }

    @Override
    public boolean validateToken(String token) {
        String existingToken = null;
        for(String sToken : tokenStorage){
            if(sToken.equals(token)){
                existingToken = sToken;
                break;
            }
        }
        if(existingToken == null){
            throw new UnauthorizedException("Token not exist");
        }

        if(jwtUtil.validateToken(existingToken)){
            return true;
        }else {
            throw new UnauthorizedException("Token invalid");
        }
    }

    @Override
    public boolean logout(String token) {
        return tokenStorage.remove(token);
    }
}
