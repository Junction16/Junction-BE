package junction.domain.voca.presentation.dto.res;

import java.util.List;

public record VocaSelectRes(
        Long vocaSize,
        List<VocaRes> vocaResList
) {
    public static VocaSelectRes of(Long vocaSize, List<VocaRes> vocaResList){
        return new VocaSelectRes(vocaSize, vocaResList);
    }
}
