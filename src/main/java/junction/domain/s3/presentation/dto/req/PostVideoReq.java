package junction.domain.s3.presentation.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "영상 저장 요청 DTO")
public record PostVideoReq(

        @Schema(description = "저장할 영상 ID", example = "1")
        Long videoId
) {
}
