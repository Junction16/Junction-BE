package junction.domain.voca.domain.entity;

import jakarta.persistence.*;
import junction.domain.user.domain.entity.User;
import junction.domain.voca.presentation.dto.res.VocaAIRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")   // FK 컬럼명
    private User user;

    private String word; // 단어 원문

    private String exampleSentence; // 예시 문장

    private String synonym; // 유의어

    private String pronunciation; // 발음

    private String meaning; // 해석

    private String vocaType; // 영어 단어 타입

    private Voca(User user, String word,
                 String exampleSentence, String synonym,
                 String pronunciation, String meaning, String vocaType) {
        this.user = user;
        this.word = word;
        this.exampleSentence = exampleSentence;
        this.synonym = synonym;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
        this.vocaType=vocaType;
    }

    public static Voca of(User user, VocaAIRes dto) {
        return new Voca(
                user,
                dto.word(),
                dto.exampleSentence(),
                dto.synonym(),
                dto.pronunciation(),
                dto.meaning(),
                dto.vocaType()
        );
    }

}


