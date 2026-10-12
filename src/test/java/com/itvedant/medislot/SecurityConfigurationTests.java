package com.itvedant.medislot;

import com.itvedant.medislot.models.User;
import com.itvedant.medislot.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SecurityConfigurationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;

    @Test
    void registeredUserCanReadDoctorsWithEmailBasicAuth() throws Exception {
        String email = "security-read-" + System.nanoTime() + "@example.com";
        String password = "Password@123";
        saveUser(email, password, List.of("ROLE_USER"));

        mockMvc.perform(get("/doctors")
                        .with(httpBasic(email, password)))
                .andExpect(status().isOk());
    }

    @Test
    void normalUserCannotCreateDoctor() throws Exception {
        String email = "security-write-" + System.nanoTime() + "@example.com";
        String password = "Password@123";
        saveUser(email, password, List.of("ROLE_USER"));

        mockMvc.perform(post("/doctors")
                        .with(httpBasic(email, password))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Test Doctor",
                                  "email": "test-doctor@example.com",
                                  "specialization": "General",
                                  "mobile": "9999999999",
                                  "consultationFee": 500
                                }
                                """))
                .andExpect(status().isForbidden());
    }

    private void saveUser(String email, String password, List<String> roles) {
        User user = new User();
        user.setUsername(email.substring(0, email.indexOf("@")));
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setMobile("9999999999");
        user.setRoles(roles);

        userRepository.saveAndFlush(user);
        entityManager.clear();
    }
}
