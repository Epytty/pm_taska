package com.taska.pm.dto.user;

import lombok.Data;

@Data
public class UserUpdateDto {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String telegramUsername;
    private Boolean notificationAgreement;
}
