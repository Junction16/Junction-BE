package junction.domain.s3.domain.entity;

import jakarta.persistence.*;
import junction.domain.record.domain.entity.Note;
import junction.domain.voca.domain.entity.Voca;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class S3Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "s3Video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<S3Choice>  s3Choices = new ArrayList<>();

    @OneToOne(mappedBy = "s3Video", cascade = CascadeType.ALL, orphanRemoval = true)
    private Note  notes;
    // 이름
    private String name;

    private String korea;

    // 예문
    private String sentence;

    // 정답 단어
    private String successWord;

    // 만약 유의어면 단어 있음, 빈칸 맞추기면 null
    private String compareWord;

    // 비디오 링크
    private String videoUrl;

    @Enumerated(EnumType.STRING)
    // 비디오 타입 어떤 유형인지
    private VideoType videoType;

    // 스크립트 테이블 안만들고 한번에 팍 저장할게요
    private String chat;


    @Builder
    public S3Video(String name, String sentence,String successWord,
                   String videoUrl, VideoType videoType, String chat, String korea){
        this.name= name;
        this.sentence= sentence;
        this.successWord = successWord;
        this.videoUrl = videoUrl;
        this.videoType = videoType;
        this.chat= chat;
        this.korea=korea;
    }
}