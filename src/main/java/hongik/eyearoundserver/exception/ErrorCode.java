package hongik.eyearoundserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 유저의 이메일입니다."),
    USER_NAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "해당 이름의 유저가 이미 존재합니다."),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "올바르지 않은 비밀번호입니다."),
    PASSWORD_DUPLICATE(HttpStatus.CONFLICT, "기존 비밀번호와 일치합니다. 다른 비밀번호를 입력해주세요.");

    private final HttpStatus httpStatus;
    private final String message;
}
