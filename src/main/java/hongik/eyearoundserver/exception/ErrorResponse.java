package hongik.eyearoundserver.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class ErrorResponse {

    @Schema(description = "에러 상태코드", defaultValue = "409")
    private final int status;
    @Schema(description = "상태코드의 이름", defaultValue = "CONFLICT")
    private final String error;
    @Schema(description = "에러 설명", defaultValue = "USER_ALREADY_EXISTS")
    private final String code;
    @Schema(description = "에러 메세지", defaultValue = "이미 존재하는 유저의 이메일입니다.")
    private final String message;

    public static ErrorResponse toEntity(ErrorCode e) {
        return ErrorResponse.builder()
                .status(e.getHttpStatus().value())
                .error(e.getHttpStatus().name())
                .code(e.name())
                .message(e.getMessage())
                .build();
    }
}
