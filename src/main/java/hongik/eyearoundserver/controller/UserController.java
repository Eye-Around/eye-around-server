package hongik.eyearoundserver.controller;

import hongik.eyearoundserver.domain.User;
import hongik.eyearoundserver.dto.*;
import hongik.eyearoundserver.exception.ErrorResponse;
import hongik.eyearoundserver.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User", description = "사용자 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입 API", description = "사용자에게 정보를 받아 회원가입을 완료합니다. 회원가입 성공 시, token 을 발급하여 따로 로그인이 필요하지 않습니다. 발급된 token 은 flutter 내에 저장하여 회원가입과 로그인을 제외한 모든 API 요청 시, header 의 'Authorization' 에 토큰을 담아 보내야 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 형식의 요청입니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "이미 가입되어 있는 사용자의 이메일 혹은 이름입니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/signup")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody @Valid UserRequestDTO requestDTO) {
        log.info("회원 가입 - email = {}", requestDTO.getEmail());
        return new ResponseEntity<>(userService.signUp(requestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "로그인 API", description = "이메일, 비밀번호를 받아 로그인을 완료합니다. 로그인 성공 시, 발급된 token 은 flutter 내에 저장하여 회원가입과 로그인을 제외한 모든 API 요청 시, header 의 'Authorization' 에 토큰을 담아 보내야 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "로그인 성공", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 형식의 요청이거나 혹은 비밀번호가 일치하지 않습니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "가입되어 있지 않은 사용자입니다. 회원가입을 시도해주세요.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody @Valid LoginRequestDTO requestDTO) {
        log.info("로그인 - email = {}", requestDTO.getEmail());
        return new ResponseEntity<>(userService.login(requestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "비밀번호 확인 API", description = "비밀번호 변경 전 비밀번호를 받아 사용자 본인인지 검증합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "잘못된 형식의 요청입니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰입니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/password/check")
    public ResponseEntity checkPassword(
            @RequestBody @Valid PasswordRequestDto requestDto,
            @AuthenticationPrincipal User user) {
        log.info("비밀번호 검증 - email = {}", user.getEmail());
        userService.checkPassword(requestDto, user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "비밀번호 변경 API", description = "변경할 비밀번호를 받아 변경을 완료합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "업데이트 성공", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "잘못된 형식의 요청입니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰입니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "기존 비밀번호와 동일한 비밀번호입니다. 다시 시도해주세요.")
    })
    @PutMapping(value = "/password/update")
    public ResponseEntity changePassword(
            @RequestBody @Valid PasswordRequestDto requestDto,
            @AuthenticationPrincipal User user) {
        log.info("비밀번호 변경 - email = {}", user.getEmail());
        userService.changePassword(requestDto, user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "프로필(마이페이지) 조회 API", description = "마이페이지 및 홈화면에서 필요한 유저의 프로필 정보와 눈 운동 통계값을 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ProfileResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰입니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDTO> getProfile(@AuthenticationPrincipal User user) {
        log.info("프로필 조회 (마이페이지) - name = {}", user.getName());
        return new ResponseEntity<>(new ProfileResponseDTO(user), HttpStatus.OK);
    }
}
