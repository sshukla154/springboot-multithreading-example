package sshukla.executor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sshukla.executor.entity.User;
import sshukla.executor.repo.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * @author 'Seemant Shukla' on '28/10/2022'
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Async
    public CompletableFuture<List<User>> saveUser(MultipartFile multipartFile) {
        long startTime = System.currentTimeMillis();
        //Utility Method to parse CSV
        List<User> userList = parseUserCSVFile(multipartFile);
        log.info("Parsed All Users - " + Thread.currentThread().getName());
        userRepository.saveAll(userList);
        log.info("Saved All Users - " + Thread.currentThread().getName());
        long endTime = System.currentTimeMillis();
        log.info("Total Time - {}, Thread Name - {} ", (endTime - startTime), Thread.currentThread().getName());
        return CompletableFuture.completedFuture(userList);
    }

    @Async
    public CompletableFuture<List<User>> findAllUser() {
        log.info("List of users by thread - " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(userRepository.findAll());
    }

    private List<User> parseUserCSVFile(MultipartFile multipartFile) {
        List<User> userList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            while (bufferedReader.readLine() != null) {
                String[] data = bufferedReader.readLine().split(",");
                User user = User.builder()
                        .name(data[0])
                        .email(data[1])
                        .gender(data[2])
                        .build();
                userList.add(user);
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception occurred while parsing CSV file");
        }
        return userList;
    }

}
