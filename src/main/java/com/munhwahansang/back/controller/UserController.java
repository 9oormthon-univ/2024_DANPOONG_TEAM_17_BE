package com.munhwahansang.back.controller;

import com.munhwahansang.back.data.dto.jwt.response.TokenCreateResponse;
import com.munhwahansang.back.data.dto.user.request.LoginOrRegisterRequest;
import com.munhwahansang.back.data.dto.user.request.UserDeleteRequest;
import com.munhwahansang.back.data.dto.user.request.UserFindRequest;
import com.munhwahansang.back.data.dto.user.request.UserModifyRequest;
import com.munhwahansang.back.data.dto.user.response.FindUserResponse;
import com.munhwahansang.back.data.entity.User;
import com.munhwahansang.back.service.TokenService;
import com.munhwahansang.back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final TokenService tokenService;


    @PostMapping
    public ResponseEntity<TokenCreateResponse> loginOrRegister(@RequestBody LoginOrRegisterRequest request) {

        User user = userService.loginOrRegister(request);

        TokenCreateResponse response = tokenService.createToken(user.getUserId(), user.getAdmin());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@RequestHeader("Authorization") String token) {

        token = token.replace("Bearer ", "");
        Integer userId = tokenService.getUserId(token);
        userService.deleteUser(new UserDeleteRequest(userId));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void modifyName(@RequestHeader("Authorization") String token, @RequestBody UserModifyRequest request) {
        token = token.replace("Bearer ", "");
        Integer userId = tokenService.getUserId(token);
        userService.modifyName(request, userId);
    }

    @GetMapping
    public ResponseEntity<FindUserResponse> findUser(@RequestHeader("Authorization") String token) {

        token = token.replace("Bearer ", "");
        Integer userId = tokenService.getUserId(token);
        FindUserResponse response = userService.findUser(new UserFindRequest(userId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


