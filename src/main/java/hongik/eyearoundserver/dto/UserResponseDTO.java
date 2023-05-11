package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String token;

    @Builder
    public UserResponseDTO(User user, String token) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.token = token;
    }
}
