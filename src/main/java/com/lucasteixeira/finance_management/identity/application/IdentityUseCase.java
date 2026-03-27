package com.lucasteixeira.finance_management.identity.application;

import com.lucasteixeira.finance_management.identity.domain.Identity;
import com.lucasteixeira.finance_management.identity.domain.commands.LoginUserCommand;
import com.lucasteixeira.finance_management.identity.domain.commands.createUserCommand;
import com.lucasteixeira.finance_management.identity.domain.factory.IdentityFactory;
import com.lucasteixeira.finance_management.identity.infrastructure.IdentityRepositoryPort;
import com.lucasteixeira.finance_management.identity.infrastructure.security.JwtUtil;
import org.springframework.modulith.NamedInterface;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@NamedInterface("api")
@Service
public class IdentityUseCase {

    private final IdentityRepositoryPort repository;
    private final IdentityFactory identityFactory;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil  jwtUtil;

    public IdentityUseCase(IdentityRepositoryPort repository, IdentityFactory identityFactory, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repository = repository;
        this.identityFactory = identityFactory;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public void createUser(createUserCommand createUserCommand) {
        if (repository.findByEmail(createUserCommand.email()).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        String encondedPassword = passwordEncoder.encode(createUserCommand.password());

        var user = identityFactory.createUser(
                createUserCommand.name(),
                createUserCommand.cpf(),
                createUserCommand.email(),
                encondedPassword,
                createUserCommand.phone()
        );
        repository.save(user);
    }

    @Transactional
    public LoginResponsePayload loginUser(LoginUserCommand loginUserCommand) {

        Identity identity = repository.findByEmail(loginUserCommand.email()).orElseThrow(
                () -> new BadCredentialsException("Bad Credentials")
        );

        if (!passwordEncoder.matches(loginUserCommand.password(), identity.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        return new LoginResponsePayload(jwtUtil.generateToken(loginUserCommand.email()));
    }

    public UserResponsePayload getUsers(String email){
        Identity identity = repository.findByEmail(email).orElseThrow(
                () -> new BadCredentialsException("Bad Credentials")
        );

        return new UserResponsePayload(
                identity.getName(),
                identity.getEmail(),
                identity.getCpf(),
                identity.getPhone(),
                identity.getAccess(),
                identity.getCreatedAt()
        );
    }


}
