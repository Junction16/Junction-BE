package junction.domain.voca.application.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import junction.domain.openai.application.service.OpenAITextService;
import junction.domain.user.domain.entity.User;
import junction.domain.user.domain.repository.UserRepository;
import junction.domain.voca.application.service.VocaService;
import junction.domain.voca.domain.entity.Voca;
import junction.domain.voca.domain.repository.VocaRepository;
import junction.domain.voca.presentation.dto.res.VocaAIRes;
import junction.domain.voca.presentation.dto.res.VocaRes;
import junction.domain.voca.presentation.dto.res.VocaSelectRes;
import junction.global.infra.exception.error.ErrorCode;
import junction.global.infra.exception.error.JunctionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class VocaServiceImpl implements VocaService {

    private final VocaRepository vocaRepository;
    private final OpenAITextService openAITextService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserRepository userRepository;

    @Override
    public List<VocaAIRes> vocaAI(String userId, String vocaType) {
        // 1. user 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new JunctionException(ErrorCode.USER_NOT_EXIST));

        // 2. 프롬프트 생성
        String prompt = createdPrompt(vocaType);

        // 3. OpenAI API 호출 → JSON 문자열 응답 받기
        String jsonResponse = openAITextService.vocaAnalyze(prompt);

        log.info("OpenAI raw response = {}", jsonResponse);

        try {
            // 4. JSON 배열 부분만 추출
            String cleanedJson = extractJsonArray(jsonResponse);

            log.info("Cleaned JSON = {}", cleanedJson);

            // 5. JSON 문자열 → DTO 리스트 변환
            List<VocaAIRes> vocaList = objectMapper.readValue(
                    cleanedJson,
                    new TypeReference<List<VocaAIRes>>() {
                    }
            );

            // 6. DB 저장
            vocaList.forEach(v -> {
                Voca voca = Voca.of(user, v);
                vocaRepository.save(voca);
            });

            // 7. 그대로 반환
            return vocaList;

        } catch (Exception e) {
            throw new JunctionException(ErrorCode.JSON_PARSE_ERROR, e);
        }
    }

    @Override
    public VocaSelectRes vocaSelect(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new JunctionException(ErrorCode.USER_NOT_EXIST));

        List<Voca> vocaDB = vocaRepository.findByUser(user);
        List<VocaRes> result = new ArrayList<>();

        for (Voca voca : vocaDB) {
            result.add(VocaRes.of(voca));
        }

        return VocaSelectRes.of((long) result.size(), result);


    }

    /**
     * OpenAI 프롬프트 생성
     */
    private String createdPrompt(String vocaType) {
        return """
                You are an English vocabulary assistant. 
                Generate 5 vocabulary items related to the category: %s.
                        
                For each item, return the result in **valid JSON array format** like this:
                        
                [
                  {
                    "word": "vulnerable",
                    "pronunciation": "[ˈvʌlnərəbl]",
                    "synonym": "helpless\\nendangered\\ndefenceless",
                    "exampleSentence": "to be vulnerable to attack\\nShe looked very vulnerable standing there on her own.\\nIn cases of food poisoning, young children are especially vulnerable",
                    "meaning": "연약한, 취약한 (신체적·정서적으로 상처받기 쉬움을 나타냄)",
                    "vocaType": "%s"
                  },
                  ...
                ]
                        
                Requirements:
                - Exactly 5 vocabulary objects.
                - Each object must have: word, pronunciation, synonym, exampleSentence, meaning, vocaType.
                - `synonym` must contain exactly 3 synonyms separated by line breaks (`\\n`).
                - `exampleSentence` must contain exactly 3 example sentences separated by line breaks (`\\n`).
                - `meaning` should be in Korean.
                - `vocaType` should always be "%s".
                """.formatted(vocaType, vocaType, vocaType);
    }

    /**
     * OpenAI 응답에서 JSON 배열만 뽑아내기
     */
    private String extractJsonArray(String response) {
        int start = response.indexOf("[");
        int end = response.lastIndexOf("]");
        if (start != -1 && end != -1 && start < end) {
            return response.substring(start, end + 1).trim();
        }
        return response; // fallback
    }
}
