package sshukla.executor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sshukla.executor.service.UserService;

import java.util.concurrent.CompletableFuture;

/**
 * @author 'Seemant Shukla' on '28/10/2022'
 */

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    public ResponseEntity<String> saveUsers(MultipartFile[] usersMultipartFile) {
        for (MultipartFile file : usersMultipartFile)
            userService.saveUsers(file);
        return new ResponseEntity<>("Users list saved in DB", HttpStatus.CREATED);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public CompletableFuture<Void> findAllUsers() {
        return userService.findAllUser().thenAccept(ResponseEntity::ok);
    }

}
