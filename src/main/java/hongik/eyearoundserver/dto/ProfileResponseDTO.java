package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileResponseDTO {
    @Schema(description = "user 의 id", defaultValue = "1")
    private Long id;
    @Schema(description = "사용자의 이름", defaultValue = "루피")
    private String name;
    @Schema(description = "사용자의 이메일", defaultValue = "루피@hongik.com")
    private String email;
    @Schema(description = "사용자가 오늘 눈 운동을 했는지 여부를 나타내는 플래그입니다.", defaultValue = "false")
    private Boolean state;
    @Schema(description = "사용자가 이번주에 총 몇 번의 눈 운동을 했는지 알려주는 값입니다.", defaultValue = "0")
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
