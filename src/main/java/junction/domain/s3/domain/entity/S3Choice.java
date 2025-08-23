package junction.domain.s3.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class S3Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s3_video_id")   // FK 컬럼명
    private S3Video s3Video;

    private String dummyWord;

    @Builder
    public S3Choice(S3Video s3Video, String dummyWord){
        this.s3Video = s3Video;
        this.dummyWord = dummyWord;
    }
}