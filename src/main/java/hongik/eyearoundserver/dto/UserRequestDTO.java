package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDTO {

    private String name;
    private String email;
    private String password;

    public User toEntity(String password) {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
