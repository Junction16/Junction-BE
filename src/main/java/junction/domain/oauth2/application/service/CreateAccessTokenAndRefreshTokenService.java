package junction.domain.oauth2.application.service;

import junction.domain.user.domain.entity.Role;

import java.util.Map;

public interface CreateAccessTokenAndRefreshTokenService {
    Map<String, String> createAccessTokenAndRefreshToken(String userId, Role role, String email);
}
