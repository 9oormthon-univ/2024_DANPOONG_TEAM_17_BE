package com.munhwahansang.back.service;


import com.munhwahansang.back.data.dto.user.request.LoginOrRegisterRequest;
import com.munhwahansang.back.data.dto.user.request.UserDeleteRequest;
import com.munhwahansang.back.data.dto.user.request.UserFindRequest;
import com.munhwahansang.back.data.dto.user.request.UserModifyRequest;
import com.munhwahansang.back.data.dto.user.response.FindUserResponse;
import com.munhwahansang.back.data.entity.User;
import com.munhwahansang.back.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User loginOrRegister(LoginOrRegisterRequest request) {

        if (userRepository.existsByProvideId(request.provideId())) {
            return userRepository.findByProvideId(request.provideId());
        }

        User user = User.of(request);
        return userRepository.save(user);
    }

    public void deleteUser(UserDeleteRequest request) {
        User user = userRepository.queryById(request.id());
        user.deleteAccount();
    }

    public void modifyName(UserModifyRequest request, Integer userId) {

        User user = userRepository.queryById(userId);
        user.modifyName(request);
    }

    public FindUserResponse findUser(UserFindRequest request) {
        User user = userRepository.queryById(request.id());
        return user.convertToFindUserResponse();
    }

    public User findUserById(Integer id) {
        return userRepository.queryById(id);
    }


}
