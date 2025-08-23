package junction.domain.voca.presentation.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "사용자의 단어 목록 응답 DTO")
public record VocaSelectRes(

        @Schema(description = "저장된 단어 개수", example = "5")
        Long vocaSize,

        @Schema(description = "단어 응답 리스트")
        List<VocaRes> vocaResList
) {
    public static VocaSelectRes of(Long vocaSize, List<VocaRes> vocaResList) {
        return new VocaSelectRes(vocaSize, vocaResList);
    }
}
