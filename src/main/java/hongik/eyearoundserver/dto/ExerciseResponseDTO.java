package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.Exercise;
import hongik.eyearoundserver.domain.enums.Direction;
import hongik.eyearoundserver.domain.enums.ExerciseType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ExerciseResponseDTO {
    @Schema(description = "exercise 의 id", defaultValue = "1")
    private Long id;
    @Schema(description = "눈 운동 제목", defaultValue = "건조한 눈을 위한 눈 운동")
    private String title;
    @Schema(description = "눈 운동 태그", defaultValue = "눈건조")
    private List<String> tags;
    @Schema(description = "눈 운동 타입", defaultValue = "BLINKING")
    private ExerciseType type;
    @Schema(description = "눈 운동 방향", defaultValue = "UP")
    private Direction direction;
    @Schema(description = "눈 운동 시, 화면에 나타나는 가이드 메세지", defaultValue = "눈을 깜빡이세요")
    private String message;

    @Builder
    public ExerciseResponseDTO(Exercise exercise) {
        this.id = exercise.getId();
        this.title = exercise.getTitle();
        this.tags = exercise.getTags();
        this.type = exercise.getType();
        this.direction = exercise.getDirection();
        this.message = exercise.getMessage();
    }
}
