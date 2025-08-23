package junction.domain.record.domain.entity;

import jakarta.persistence.*;
import junction.domain.s3.domain.entity.S3Video;
import junction.domain.user.domain.entity.User;
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
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")   // FK 컬럼명
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s3_video_id")   // FK 컬럼명
    private S3Video s3Video;

    @Builder
    public Note(User user, S3Video s3Video){
        this.user= user;
        this.s3Video= s3Video;
    }
}