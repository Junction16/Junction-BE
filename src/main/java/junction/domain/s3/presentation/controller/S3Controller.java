package junction.domain.s3.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import junction.domain.s3.domain.entity.VideoType;
import junction.domain.s3.presentation.application.S3Service;
import junction.domain.s3.presentation.dto.req.PostVideoReq;
import junction.domain.s3.presentation.dto.res.ClipRes;
import junction.domain.s3.presentation.dto.res.RandomHomeRes;
import junction.domain.s3.presentation.dto.res.RandomQuiz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
@Slf4j
public class S3Controller {
    private final S3Service s3Service;

    @Operation(summary = "영어 영상 업로드", description = "MultipartFile로 영상과 관련 정보를 업로드합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "영상 업로드 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패 (로그인 필요)"),
            @ApiResponse(responseCode = "404", description = "대상 리소스를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping
    public String s3Image(@RequestPart("video") MultipartFile video,
                          @RequestPart("name") String name,
                          @RequestPart("compareWord") String compareWord,
                          @RequestPart("successWord") String successWord,
                          @RequestPart("caption") String caption,
                          @RequestPart("videoType") VideoType videoType,
                          @RequestPart("sentence") String sentence) throws IOException {

        return s3Service.storeFile(video, name, compareWord, successWord, videoType, caption, sentence,"");
    }

    @Operation(summary = "퀴즈 랜덤 조회", description = "videoType별 퀴즈를 랜덤으로 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "퀴즈 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패 (로그인 필요)"),
            @ApiResponse(responseCode = "404", description = "해당 퀴즈 영상을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/quiz")
    public ResponseEntity<RandomQuiz> randomQuiz(@RequestParam VideoType videoType) {
        return ResponseEntity.ok(s3Service.randomQuizSelect(videoType));
    }

    @Operation(summary = "홈 화면 랜덤 영상 조회", description = "홈 화면에 노출할 영상을 랜덤으로 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "랜덤 영상 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패 (로그인 필요)"),
            @ApiResponse(responseCode = "404", description = "영상을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/home")
    public ResponseEntity<List<RandomHomeRes>> randomHome(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(s3Service.randomHome(userId));
    }

    @Operation(summary = "클립 조회", description = "현재 로그인한 사용자의 클립 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "클립 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패 (로그인 필요)"),
            @ApiResponse(responseCode = "404", description = "클립 데이터를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/clip")
    public ResponseEntity<ClipRes> getClip(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(s3Service.clipSelect(userId));
    }

    @Operation(summary = "영어 영상 저장", description = "사용자가 특정 영상을 저장(PostVideoReq 기반)합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "영상 저장 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패 (로그인 필요)"),
            @ApiResponse(responseCode = "404", description = "해당 영상이나 사용자를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/video")
    public void postVideo(@AuthenticationPrincipal String userId, @RequestBody PostVideoReq req) {
        s3Service.postVideo(userId, req);
    }
}
