package junction.domain.s3.presentation.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import junction.domain.s3.domain.entity.S3Video;
import junction.domain.s3.domain.entity.VideoType;

import java.util.List;

@Schema(description = "퀴즈 랜덤 조회 응답 DTO")
public record RandomQuiz(

        @Schema(description = "영상 ID", example = "101")
        Long id,

        @Schema(description = "퀴즈 문장", example = "She looked very vulnerable standing there on her own.")
        String sentence,

        @Schema(description = "정답 단어", example = "vulnerable")
        String successWord,

        @Schema(description = "영상 URL", example = "https://bucket.s3.ap-northeast-2.amazonaws.com/video1.mp4")
        String videoUrl,

        @Schema(description = "영상 타입", example = "BLANK, SYNONYM")
        VideoType videoType,

        @Schema(description = "영상과 관련된 대화/스크립트", example = "This is a sample chat text")
        String chat,

        @Schema(description = "비교 대상 단어", example = "weak")
        String compareWord,

        @Schema(description = "퀴즈 보기를 담은 리스트")
        List<ChoiceRes> choices
) {
    public static RandomQuiz of(S3Video s3Video, List<ChoiceRes> choices) {
        return new RandomQuiz(
                s3Video.getId(),
                s3Video.getSentence(),
                s3Video.getSuccessWord(),
                s3Video.getVideoUrl(),
                s3Video.getVideoType(),
                s3Video.getChat(),
                s3Video.getCompareWord(),
                choices
        );
    }
}
