package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDTO {

    @NotBlank
    private String name;
    @Email @NotBlank
    private String email;
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
