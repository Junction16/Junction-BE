package junction.domain.user.application.service;

import junction.domain.user.presentation.dto.res.GetMyPageRes;


public interface UserService {


    GetMyPageRes getMyPage(String userId);


}