package junction.domain.voca.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import junction.domain.voca.application.service.VocaService;
import junction.domain.voca.presentation.dto.res.VocaAIRes;
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

    @Operation(summary = "영어 단어 AI 등록 및 반환", description = "선택한 단어 유형(vocaType)에 따라 AI가 단어 5개를 생성 및 DB에 저장 후 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "단어 생성 및 반환 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패 (로그인 필요)"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 및 json 실패")
    })
    @GetMapping("/voca/ai")
    public ResponseEntity<List<VocaAIRes>> vocaAI(@AuthenticationPrincipal String userId,
                                                  @RequestParam String vocaType) {
        return ResponseEntity.ok(vocaService.vocaAI(userId, vocaType));
    }

    @Operation(summary = "영어 단어 조회 마이페이지", description = "현재 로그인한 사용자의 저장된 단어 목록을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "단어 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패 (로그인 필요)"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/voca")
    public ResponseEntity<VocaSelectRes> vocaSelect(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(vocaService.vocaSelect(userId));
    }
}
