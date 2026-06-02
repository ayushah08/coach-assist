package com.coachassist.backend.admin.config;


import com.coachassist.backend.admin.entity.Admin;
import com.coachassist.backend.admin.repository.AdminRepository;
import com.coachassist.backend.security.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (adminRepository.findByUsername("admin").isEmpty()) {
            Admin admin = new Admin();
            admin.setRole(Role.ROLE_ADMIN);
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));

            adminRepository.save(admin);

            System.out.println("Admin Created");
        }
    }
}
