package junction.domain.user.presentation.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import junction.domain.user.application.service.UserService;

import junction.domain.user.presentation.dto.res.GetMyPageRes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User", description = "닉네임 수정 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "사용자 정보 조회", description = "사용자 정보 데이터를 줍니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 사용자 정보를 반환"),
            @ApiResponse(responseCode = "401", description = "인증 실패 (로그인 필요)"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/mypage")
    public ResponseEntity<GetMyPageRes> getMyPage(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(userService.getMyPage(userId));
    }

}

