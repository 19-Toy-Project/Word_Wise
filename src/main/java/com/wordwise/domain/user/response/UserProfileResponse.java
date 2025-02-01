package com.wordwise.domain.user.response;

import com.wordwise.common.enums.UserTier;
import lombok.Getter;
import org.hibernate.usertype.UserType;

@Getter
public class UserProfileResponse {

    private final String userName;
    private final String userEmail;
    private final UserTier userTier;

    private UserProfileResponse(
            String userName,
            String userEmail,
            UserTier userTier
    ) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userTier = userTier;
    }

    public static UserProfileResponse of(
            String userName,
            String userEmail,
            UserTier userTier
    ){
        return new UserProfileResponse(
                userName,
                userEmail,
                userTier
        );
    }
}
