package junction.domain.user.application.service;


import junction.domain.user.presentation.dto.req.NicknameReq;
import junction.domain.user.presentation.dto.res.GetMyPageRes;
import junction.domain.user.presentation.dto.res.GetNicknameRes;

public interface UserService {

    void updateNickname(String userId, NicknameReq req);

    GetNicknameRes getUser(String userId);


//    GetMyPageRes getMyPage(String userId);


}