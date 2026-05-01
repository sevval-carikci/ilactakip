package com.ilactakip.starter;

import com.ilactakip.entity.Role;
import com.ilactakip.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder; // EKLENDİ
import org.springframework.transaction.annotation.Transactional;
import com.ilactakip.entity.User;
import com.ilactakip.entity.Medicine;
import com.ilactakip.repository.MedicineRepository;
import com.ilactakip.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@ComponentScan(basePackages = "com.ilactakip") 
@EntityScan(basePackages = "com.ilactakip.entity")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Transactional
   
    public CommandLineRunner dataInitializer(MedicineRepository medicineRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            /* ================= ROLES ================= */

            Role userRole = roleRepository.findByName("USER");
            if (userRole == null) {
                userRole = new Role();
                userRole.setName("USER");
                roleRepository.save(userRole);
            }

            Role adminRole = roleRepository.findByName("ADMIN");
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setName("ADMIN");
                roleRepository.save(adminRole);
            }

            Role managerRole = roleRepository.findByName("MANAGER");
            if (managerRole == null) {
                managerRole = new Role();
                managerRole.setName("MANAGER");
                roleRepository.save(managerRole);
            }


            /* ================= USERS ================= */

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            Set<Role> managerRoles = new HashSet<>();
            managerRoles.add(managerRole);

            User user1 = new User();
            user1.setUsername("user");
            user1.setPassword(passwordEncoder.encode("password"));
            user1.setRoles(userRoles);

            User user2 = new User();
            user2.setUsername("admin");
            user2.setPassword(passwordEncoder.encode("admin"));
            user2.setRoles(adminRoles);

            User user3 = new User();
            user3.setUsername("manager");
            user3.setPassword(passwordEncoder.encode("manager"));
            user3.setRoles(managerRoles);

            if (userRepository.findByUsername("user") == null) {
                userRepository.save(user1);
            }

            if (userRepository.findByUsername("admin") == null) {
                userRepository.save(user2);
            }

            if (userRepository.findByUsername("manager") == null) {
                userRepository.save(user3);
            }




            // Örnek ilaçlar
            Medicine med1 = new Medicine();
            med1.setName("Paracetamol");
            med1.setDosage("500mg");
            med1.setFrequency("Günde 2 kez");
            med1.setStockQuantity(20);
            med1.setReminderTimes("09:00,21:00");
            med1.setUser(user1);
            medicineRepository.save(med1);

            Medicine med2 = new Medicine();
            med2.setName("Aspirin");
            med2.setDosage("100mg");
            med2.setFrequency("Günde 1 kez");
            med2.setStockQuantity(10);
            med2.setReminderTimes("08:00");
            med2.setUser(user1);
            medicineRepository.save(med2);

            System.out.println(">>> Başlangıç verileri eklendi. Toplam ilaç sayısı: " + medicineRepository.count());
        };
    }
}