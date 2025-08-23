package junction.domain.s3.presentation.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "홈 화면 랜덤 영상 응답 DTO")
public record RandomHomeRes(

        @Schema(description = "영상 URL", example = "https://bucket.s3.ap-northeast-2.amazonaws.com/video1.mp4")
        String videoUrl,

        @Schema(description = "영상과 관련된 대화/스크립트", example = "Hello, how are you?")
        String chat,

        @Schema(description = "최승호", example = "최승호")
        String name,
        @Schema(description = "프로필 이미지", example = "~~~~.jpg")
        String profile

) {
    public static RandomHomeRes of(String videoUrl, String chat, String name, String profile) {

        return new RandomHomeRes(videoUrl, chat, name, profile);
    }
}
