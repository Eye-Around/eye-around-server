package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDTO {

    @NotBlank
    @Schema(description = "사용자의 이름", defaultValue = "루피")
    private String name;
    @Email @NotBlank
    @Schema(description = "사용자의 이메일", defaultValue = "루피@hongik.com")
    private String email;
    @Schema(description = "사용자의 비밀번호", defaultValue = "password")
    @NotBlank
    private String password;

    public User toEntity(String password) {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
