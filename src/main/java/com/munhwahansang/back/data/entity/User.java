package com.munhwahansang.back.data.entity;

import com.munhwahansang.back.data.dto.user.request.LoginOrRegisterRequest;
import com.munhwahansang.back.data.dto.user.request.UserModifyRequest;
import com.munhwahansang.back.data.dto.user.response.FindUserResponse;
import com.munhwahansang.back.util.Utils;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "users")
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long provideId;

    private String name;

    private Integer identifier;

    private LocalDateTime createdAt;

    private Boolean isAdmin;

    private Boolean isDeleted;

    private String imagePath;


    private User(String name, Long provideId) {
        this.name = name;
        this.provideId = provideId;
        this.createdAt = LocalDateTime.now();
        this.identifier = Utils.generateIdentifier();
        this.isAdmin = false;
        this.isDeleted = false;
        this.imagePath = null;
    }

    public Integer getUserId() {
        return id;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    /**
     * -유저: id(pk), 이름, 비번(카카오로그인만제공하면필요없을듯), 프로필이미지, 닉네임, 지역, 계정생성날짜시간
     * -음식점: id, 이름, 주소, 음식점종류(한식,양식이런거), 별점, 오픈시간, 연락처, 링크?
     */


    public static User of(LoginOrRegisterRequest request) {
        return new User(request.name(), request.provideId());
    }

    public FindUserResponse convertToFindUserResponse() {
        return new FindUserResponse(imagePath, name, isAdmin);
    }

    public void deleteAccount() {
        this.isDeleted = true;
    }

    public void modifyName(UserModifyRequest request) {
        this.name = request.name();
    }


}
