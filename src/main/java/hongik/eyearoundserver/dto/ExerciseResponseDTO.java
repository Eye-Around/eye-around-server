package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.Exercise;
import hongik.eyearoundserver.domain.enums.Direction;
import hongik.eyearoundserver.domain.enums.ExerciseType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ExerciseResponseDTO {
    private Long id;
    private String title;
    private List<String> tags;
    private ExerciseType type;
    private Direction direction;
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
