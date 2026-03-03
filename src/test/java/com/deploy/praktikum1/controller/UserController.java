package com.deploy.praktikum1.controller;

import com.deploy.praktikum1.model.dto.UserAddRequest;
import com.deploy.praktikum1.model.dto.UserDto;
import com.deploy.praktikum1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> AddUser(@RequestBody UserAddRequest request) {
        UserDto result = userService.AddUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "User created successfully",
                "data", result
        ));
    }
}

@GetMapping(
        path = "/api/users",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Map<String, Object>> getAllUser() {
    List<UserDto> result = UserService.GetAllUser();
    return ResponseEntity.ok(Map.of(
            "message", "Get all user successfully",
            "data", result
    ));
}

@PutMapping(
        path = "/api/users/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Map<String, Object>> updateUser(
        @PathVariable String id,
        @RequestBody UserAddRequest request
    ) {
    UserDto result = UserService.UpdateUser(id, request);
    return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "status", "success",
            "data", result
    ));

    @DeleteMapping(
        path = "/api/users/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> DeleteUser(@PathVariable("id") String id){
        UserService.DeleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success delete user with id " + id
        ));
    }

}
