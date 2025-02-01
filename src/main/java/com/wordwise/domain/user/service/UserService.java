package com.wordwise.domain.user.service;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.user.entity.User;
import com.wordwise.domain.user.repository.UserRepository;
import com.wordwise.domain.user.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserProfileResponse getUserProfile(AuthUser authUser) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(()-> new ApiException(ErrorStatus._USER_NOT_FOUND));

        return UserProfileResponse.of(
                user.getName(),
                user.getEmail(),
                user.getTier()
        );
    }
}
