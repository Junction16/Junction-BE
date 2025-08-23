package junction.domain.voca.presentation.dto.res;

public record VocaAIRes(


        String word, // 단어 원문

        String exampleSentence, // 예시 문장

        String synonym, // 유의어

        String pronunciation, // 발음

        String meaning // 해석
) {
    public static VocaAIRes of(
            String word,
            String exampleSentence,
            String synonym,
            String pronunciation,
            String meaning) {
        return new VocaAIRes(word, exampleSentence, synonym, pronunciation, meaning);
    }

}
