package hongik.eyearoundserver.controller;

import hongik.eyearoundserver.domain.User;
import hongik.eyearoundserver.dto.LoginRequestDTO;
import hongik.eyearoundserver.dto.PasswordRequestDto;
import hongik.eyearoundserver.dto.UserRequestDTO;
import hongik.eyearoundserver.dto.UserResponseDTO;
import hongik.eyearoundserver.service.UserService;
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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/signup")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody @Valid UserRequestDTO requestDTO) {
        log.info("회원 가입 - email = {}", requestDTO.getEmail());
        return new ResponseEntity<>(userService.signUp(requestDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody @Valid LoginRequestDTO requestDTO) {
        log.info("로그인 - email = {}", requestDTO.getEmail());
        return new ResponseEntity<>(userService.login(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/password")
    public ResponseEntity changePassword(
            @RequestBody @Valid PasswordRequestDto requestDto,
            @AuthenticationPrincipal User user) {
        log.info("비밀번호 변경 - email = {}", user.getEmail());
        userService.changePassword(requestDto, user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
