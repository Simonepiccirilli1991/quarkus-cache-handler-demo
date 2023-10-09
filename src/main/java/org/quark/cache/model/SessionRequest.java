package org.quark.cache.model;

import lombok.Data;

@Data
public class SessionRequest {

    private String email;
    private String scope;
}
