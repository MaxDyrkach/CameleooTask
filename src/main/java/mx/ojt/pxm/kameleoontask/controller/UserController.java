package mx.ojt.pxm.kameleoontask.controller;

import jakarta.validation.Valid;
import mx.ojt.pxm.kameleoontask.model.dto.UserDto;
import mx.ojt.pxm.kameleoontask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{uname}")
    public ResponseEntity<?> getUser(@PathVariable String uname) {
        return ResponseEntity.ok(userService.getUser(uname));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody  UserDto uDto) {
        return ResponseEntity.ok(userService.createUser(uDto));
    }
}
