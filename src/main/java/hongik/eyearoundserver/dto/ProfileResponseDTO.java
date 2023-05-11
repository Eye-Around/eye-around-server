package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Boolean state;
    private Integer weekCount;

    @Builder
    public ProfileResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.state = user.getState();
        this.weekCount = user.getWeekCount();
    }
}
