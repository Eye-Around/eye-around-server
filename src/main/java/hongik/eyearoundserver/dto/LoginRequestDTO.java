package hongik.eyearoundserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @Email @NotBlank
    @Schema(description = "이메일", defaultValue = "루피@hongik.com")
    private String email;
    @NotBlank
    @Schema(description = "비밀번호", defaultValue = "password")
    private String password;
}
