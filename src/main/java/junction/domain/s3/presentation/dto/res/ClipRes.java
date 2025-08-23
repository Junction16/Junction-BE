package junction.domain.s3.presentation.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "클립 목록 응답 DTO")
public record ClipRes(

        @Schema(description = "클립에 저장된 영상 개수", example = "3")
        Long videoCnt,

        @Schema(description = "클립에 저장된 영상 리스트")
        List<ClipVideoRes> clipVideoResList
) {
    public static ClipRes of(Long videoCnt, List<ClipVideoRes> clipVideoResList) {
        return new ClipRes(videoCnt, clipVideoResList);
    }
}
