package junction.domain.voca.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import junction.domain.voca.application.service.VocaService;
import junction.domain.voca.presentation.dto.res.VocaAIRes;
import junction.domain.voca.presentation.dto.res.VocaRes;
import junction.domain.voca.presentation.dto.res.VocaSelectRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class VocaController {

    private final VocaService vocaService;

    @Operation(description = "영어 단어 AI 등록 및 반환")
    @GetMapping("/voca/ai")
    public ResponseEntity<List<VocaAIRes>> vocaAI(@AuthenticationPrincipal String userId,
                                                    @RequestParam String vocaType) {
        return ResponseEntity.ok(vocaService.vocaAI(userId, vocaType));
    }

    @Operation(description = "영어 단어 조회")
    @GetMapping("/voca")
    public ResponseEntity<VocaSelectRes> vocaSelect(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(vocaService.vocaSelect(userId));
    }
}
