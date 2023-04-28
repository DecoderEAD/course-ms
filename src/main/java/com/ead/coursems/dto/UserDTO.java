package com.ead.coursems.dto;

import com.ead.coursems.enums.UserStatusEnum;
import com.ead.coursems.enums.UserTypeEnum;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Data
public class UserDTO {

    private UUID userId;
    private String userName;
    private String email;
    private String fullName;
    private UserStatusEnum status;
    private UserTypeEnum userType;
    private String phoneNumber;
    private String cpf;
    private String imageUrl;

}
