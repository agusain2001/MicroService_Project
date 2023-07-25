package com.load.gateway.models;

import lombok.*;

import java.util.Collection;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String userId;
    private String accessToken;
    private String refreshToken;
    private Long expireAt;

    private Collection<String> authories;








}
