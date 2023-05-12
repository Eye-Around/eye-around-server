package hongik.eyearoundserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequestDto {
    @NotBlank
    @Schema(description = "변경할 비밀번호", defaultValue = "anotherPassword")
    private String password;
}
