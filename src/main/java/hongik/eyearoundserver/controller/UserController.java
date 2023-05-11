package hongik.eyearoundserver.controller;

import hongik.eyearoundserver.dto.LoginRequestDTO;
import hongik.eyearoundserver.dto.UserRequestDTO;
import hongik.eyearoundserver.dto.UserResponseDTO;
import hongik.eyearoundserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/signup")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody UserRequestDTO requestDTO) {
        log.info("회원 가입 - email = {}", requestDTO.getEmail());
        return new ResponseEntity<>(userService.signUp(requestDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        log.info("로그인 - email = {}", requestDTO.getEmail());
        return new ResponseEntity<>(userService.login(requestDTO), HttpStatus.CREATED);
    }
}
