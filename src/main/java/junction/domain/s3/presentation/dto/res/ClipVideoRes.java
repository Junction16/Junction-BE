package junction.domain.s3.presentation.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "클립 영상 응답 DTO")
public record ClipVideoRes(

        @Schema(description = "영상 URL", example = "https://bucket.s3.ap-northeast-2.amazonaws.com/video1.mp4")
        String videoUrl
) {
    public static ClipVideoRes of(String videoUrl) {
        return new ClipVideoRes(videoUrl);
    }
}
