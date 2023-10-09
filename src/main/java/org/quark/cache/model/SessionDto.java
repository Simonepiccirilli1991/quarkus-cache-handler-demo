package org.quark.cache.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionDto {

    private String email;
    private LocalDateTime sessionStart;
    private String scope;
    private Boolean isValid;
}
