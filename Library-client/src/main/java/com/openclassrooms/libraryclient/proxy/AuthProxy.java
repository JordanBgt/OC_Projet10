package com.openclassrooms.libraryclient.proxy;

import com.openclassrooms.libraryclient.model.JwtResponse;
import com.openclassrooms.libraryclient.model.LoginForm;
import com.openclassrooms.libraryclient.model.RegisterForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign client to request the API Library authentication endpoints
 */
@FeignClient(url = "${app.apiUrl}/auth", name = "auth-api")
public interface AuthProxy {

    @PostMapping("/signin")
    ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginForm loginRequest);

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@RequestBody RegisterForm signupRequest);

}
