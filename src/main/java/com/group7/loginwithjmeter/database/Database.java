package com.group7.loginwithjmeter.database;

import com.group7.loginwithjmeter.model.User;
import com.group7.loginwithjmeter.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                User userA = new User("thayhoangdepzai", "1");
                System.out.println("insert info: " + userRepository.save(userA));
            }
        };
    }
}
