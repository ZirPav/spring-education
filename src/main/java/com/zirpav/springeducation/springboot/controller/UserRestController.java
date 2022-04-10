package com.zirpav.springeducation.springboot.controller;

import com.zirpav.springeducation.springboot.service.api.UserService;
import com.zirpav.springeducation.springboot.model.dto.BankBookDto;
import com.zirpav.springeducation.springboot.model.dto.UserDto;
import com.zirpav.springeducation.springboot.validation.Create;
import com.zirpav.springeducation.springboot.validation.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok().body(userDto);
    }

    @Validated(Create.class)
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDto));
    }

    @Validated(Update.class)
    @PutMapping
    public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/bank-book/{id}")
    public ResponseEntity<List<BankBookDto>> getAllBankBookDto(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getAllBankBookDtos(id));
    }

}
