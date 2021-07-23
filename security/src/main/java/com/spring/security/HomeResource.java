package com.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home(){
//        throw new IllegalArgumentException("Lỗi");
        return ("<h1>Anonymus</h1>");
    }

    @GetMapping("/user")
    public String user(){
        return ("<h1>User</h1>");
    }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Admin</h1>");
    }

}
