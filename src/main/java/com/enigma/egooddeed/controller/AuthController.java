package com.enigma.egooddeed.controller;

import com.enigma.egooddeed.model.request.AuthRequest;
import com.enigma.egooddeed.service.auth.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMappings.AUTH_URL)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity doAuthentication(@RequestBody AuthRequest authRequest){
        String token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/token-validation")
    public ResponseEntity doTokenValidation(@RequestParam String token){
        if (authService.validateToken(token)){
            return ResponseEntity.ok("Token is valid");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity doLogout(@RequestParam String token){
        if (authService.logout(token)){
            return ResponseEntity.ok("Logout success");
        }else {
            return ResponseEntity.ok("Token not found");
        }
    }
}
