package junction.domain.s3.presentation.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "퀴즈 보기에 사용되는 선택지 응답 DTO")
public record ChoiceRes(

        @Schema(description = "단어 선택지 즉 오답애들", example = "banana")
        String dummyWord
) {
    public static ChoiceRes of(String dummyWord) {
        return new ChoiceRes(dummyWord);
    }
}
