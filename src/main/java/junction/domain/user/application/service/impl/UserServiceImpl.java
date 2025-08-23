package junction.domain.user.application.service.impl;


import junction.domain.user.application.service.UserService;
import junction.domain.user.domain.entity.User;
import junction.domain.user.domain.repository.UserRepository;

import junction.domain.user.presentation.dto.res.GetMyPageRes;
import junction.global.infra.exception.error.JunctionException;
import junction.global.infra.exception.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public GetMyPageRes getMyPage(String userId) {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) throw new JunctionException(ErrorCode.USER_NOT_EXIST);
        return GetMyPageRes.of(user);
    }
}
