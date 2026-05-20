package com.healthcare.webapp.feign;

import com.healthcare.webapp.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@FeignClient(name = "user-service")
public interface UserClient {

    @PostMapping("/users")
    UserModel create(@RequestBody UserModel user);

    @GetMapping("/users/username/{username}")
    Optional<UserModel> findByUsername(@PathVariable String username);
}
