package junction.domain.voca.presentation.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "AI가 생성한 단어 응답 DTO")
public record VocaAIRes(

        @Schema(description = "단어 원문", example = "vulnerable")
        String word,

        @Schema(description = "예시 문장 (줄바꿈으로 3개)", example = "to be vulnerable to attack\nShe looked very vulnerable standing there on her own.\nIn cases of food poisoning, young children are especially vulnerable")
        String exampleSentence,

        @Schema(description = "유의어 (줄바꿈으로 3개)", example = "helpless\nendangered\ndefenceless")
        String synonym,

        @Schema(description = "발음", example = "[ˈvʌlnərəbl]")
        String pronunciation,

        @Schema(description = "한국어 해석", example = "연약한, 취약한 (신체적·정서적으로 상처받기 쉬움을 나타냄)")
        String meaning,

        @Schema(description = "단어 유형", example = "adjective")
        String vocaType
) {
    public static VocaAIRes of(
            String word,
            String exampleSentence,
            String synonym,
            String pronunciation,
            String meaning,
            String vocaType) {
        return new VocaAIRes(word, exampleSentence, synonym, pronunciation, meaning, vocaType);
    }
}
