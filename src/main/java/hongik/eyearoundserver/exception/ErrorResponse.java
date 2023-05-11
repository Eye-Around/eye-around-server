package hongik.eyearoundserver.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class ErrorResponse {

    private final int status;
    private final String error;
    private final String code;
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
