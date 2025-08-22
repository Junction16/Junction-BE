package junction.domain.user.presentation.dto.res;

import junction.domain.user.domain.entity.User;

public record GetNicknameRes (
        String nickName
){
    public static GetNicknameRes of(User user){
        return new GetNicknameRes(user.getName());
    }
}
