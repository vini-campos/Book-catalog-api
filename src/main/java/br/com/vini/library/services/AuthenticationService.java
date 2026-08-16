package br.com.vini.library.services;

import br.com.vini.library.config.TokenProviderConfiguration;
import br.com.vini.library.database.models.CustomersEntity;
import br.com.vini.library.database.models.RolesEntity;
import br.com.vini.library.database.repositories.ICustomersRepository;
import br.com.vini.library.database.repositories.IRolesRepository;
import br.com.vini.library.dtos.requests.LoginRequestDto;
import br.com.vini.library.dtos.requests.RegisterRequestDto;
import br.com.vini.library.dtos.responses.TokenResponseDto;
import br.com.vini.library.enums.RoleTypeEnum;
import br.com.vini.library.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ICustomersRepository customersRepository;
    private final IRolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProviderConfiguration tokenProviderConfiguration;

    @Value("${JWT_EXPIRATION}")
    private long expirationTime;

    public void register(RegisterRequestDto registerRequestDto) throws BadRequestException {
        if (customersRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new BadRequestException("A customer with this  email already exists");
        }

        RolesEntity role = rolesRepository.findByName(RoleTypeEnum.ROLE_ALUNO.name())
                .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                        .name(RoleTypeEnum.ROLE_ALUNO.name())
                        .build()));

        CustomersEntity customer = CustomersEntity.builder()
                .name(registerRequestDto.getName())
                .email(registerRequestDto.getEmail())
                .birthDate(registerRequestDto.getBirthDate())
                .roles(Set.of(role))
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .build();

        customersRepository.save(customer);
    }

    public TokenResponseDto login(LoginRequestDto loginRequestDto) {
        try {
            // authenticationProvider -> userDetailsServiceImpl -> passwordEncoder.matches() -> authenticated
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
            String token = tokenProviderConfiguration.generateToken(auth);

            return new TokenResponseDto(token, expirationTime);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Credentials do not match");
        }
        catch (Exception e) {
            throw e;
        }
    }
}
