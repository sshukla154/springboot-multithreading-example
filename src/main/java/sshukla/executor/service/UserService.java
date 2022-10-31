package sshukla.executor.service;

import org.springframework.web.multipart.MultipartFile;
import sshukla.executor.entity.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author 'Seemant Shukla' on '28/10/2022'
 */

public interface UserService {

    CompletableFuture<List<User>> saveUsers(MultipartFile multipartFile);

    CompletableFuture<List<User>> findAllUser();

}
