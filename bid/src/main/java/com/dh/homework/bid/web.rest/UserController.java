package com.dh.homework.bid.web.rest;

import com.dh.homework.bid.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping
    public UserDto getCurrentUser() {
        // todo
        return null;
    }
}
