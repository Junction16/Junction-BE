package junction.domain.voca.presentation.dto.res;

import junction.domain.voca.domain.entity.Voca;

public record VocaRes(

        String word, // 단어 원문

        String exampleSentence, // 예시 문장

        String synonym, // 유의어

        String pronunciation, // 발음

        String meaning // 해석
) {
    public static VocaRes of(
            Voca voca) {
        return new VocaRes(voca.getWord(), voca.getExampleSentence(),
                voca.getSynonym(), voca.getPronunciation(), voca.getMeaning());
    }

}
