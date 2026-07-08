package com.farmacia.v1.security.seeder;

import com.farmacia.v1.entity.RoleEntity;
import com.farmacia.v1.entity.UserEntity;
import com.farmacia.v1.entity.UserRoleEntity;
import com.farmacia.v1.repository.RoleRepository;
import com.farmacia.v1.repository.UserRepository;
import com.farmacia.v1.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepo;
    private final UserRoleRepository userRoleRepository;

    public DataSeeder(PasswordEncoder passwordEncoder,
                      UserRepository userRepository,
                      RoleRepository roleRepo,
                      UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepo = roleRepo;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) {
        RoleEntity admin = getOrCreateRole("ADMIN");
        RoleEntity administrativo = getOrCreateRole("ADMINISTRATIVO");

        UserEntity u1 = getOrCreateUser(
                "admin",
                "Secret123$",
                "administrator@redsalud.gob.cl",
                "Admin"
        );

        UserEntity u2 = getOrCreateUser(
                "operador",
                "Secret123$",
                "administrative@redsalud.gob.cl",
                "Operador"
        );

        assignRoleIfMissing(u1, admin);
        assignRoleIfMissing(u2, administrativo);
    }

    private RoleEntity getOrCreateRole(String name) {
        return roleRepo.findByName(name)
                .orElseGet(() -> roleRepo.save(RoleEntity.builder().name(name).build()));
    }

    private UserEntity getOrCreateUser(String username, String plainPassword, String email, String firstName) {
        return userRepository.findByUsername(username)
                .orElseGet(() -> userRepository.save(UserEntity.builder()
                        .username(username)
                        .password(passwordEncoder.encode(plainPassword))
                        .email(email)
                        .firstName(firstName)
                        .build()));
    }

    private void assignRoleIfMissing(UserEntity user, RoleEntity role) {
        userRoleRepository.findByUserIdAndRoleId(user.getId(), role.getId())
                .orElseGet(() -> userRoleRepository.save(UserRoleEntity.builder()
                        .user(user)
                        .role(role)
                        .build()));
    }
}
