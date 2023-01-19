package com.ead.coursems.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class SubscriptionDTO {

    @NotNull
    private UUID userId;
}
