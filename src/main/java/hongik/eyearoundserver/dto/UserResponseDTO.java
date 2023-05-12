package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDTO {
    @Schema(description = "user 의 id", defaultValue = "1")
    private Long id;
    @Schema(description = "사용자의 이름", defaultValue = "루피")
    private String name;
    @Schema(description = "사용자의 이메일", defaultValue = "루피@hongik.com")
    private String email;
    @Schema(description = "로그인 성공 후 발급된 token 입니다. 해당 token 을 flutter 내에 저장하여 회원가입과 로그인을 제외한 모든 API 요청 시, header 의 'Authorization' 에 토큰을 담아 보내야 합니다. 토큰의 유효기간은 1시간입니다.")
    private String token;

    @Builder
    public UserResponseDTO(User user, String token) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.token = token;
    }
}
