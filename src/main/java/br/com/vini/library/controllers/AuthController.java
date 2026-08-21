package br.com.vini.library.controllers;

import br.com.vini.library.dtos.requests.LoginRequestDto;
import br.com.vini.library.dtos.requests.RegisterRequestDto;
import br.com.vini.library.dtos.responses.TokenResponseDto;
import br.com.vini.library.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        authenticationService.register(registerRequestDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return authenticationService.login(loginRequestDto);
    }
}
