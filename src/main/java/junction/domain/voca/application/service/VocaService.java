package junction.domain.voca.application.service;

import junction.domain.voca.presentation.dto.res.VocaAIRes;
import junction.domain.voca.presentation.dto.res.VocaRes;
import junction.domain.voca.presentation.dto.res.VocaSelectRes;

import java.util.List;

public interface VocaService {

    List<VocaAIRes> vocaAI(String userId, String voteType);

    VocaSelectRes vocaSelect(String userId);
}
