package junction.domain.user.presentation.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import junction.domain.user.domain.entity.User;

@Schema(description = "마이페이지 응답 DTO")
public record GetMyPageRes(

        @Schema(description = "사용자 이름", example = "최승호")
        String name,

        @Schema(description = "사용자 프로필 이미지 URL", example = "https://bucket.s3.ap-northeast-2.amazonaws.com/profile1.png")
        String profile,

        @Schema(description = "사용자 이메일", example = "test@example.com")
        String email
) {
    public static GetMyPageRes of(User user) {
        return new GetMyPageRes(user.getName(), user.getProfile(), user.getEmail());
    }
}
