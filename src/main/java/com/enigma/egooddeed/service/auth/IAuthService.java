package com.enigma.egooddeed.service.auth;

import com.enigma.egooddeed.model.request.AuthRequest;

public interface IAuthService {
    String login(AuthRequest authRequest);

    boolean validateToken(String token);

    boolean logout(String token);
}
