package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.Exercise;
import hongik.eyearoundserver.domain.enums.Direction;
import hongik.eyearoundserver.domain.enums.ExerciseType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseRequestDto {
    private String title;
    private List<String> tags;
    private ExerciseType type;
    private Direction direction;
    private String message;

    public Exercise toEntity() {
        return Exercise.builder()
                .title(title)
                .tags(tags)
                .type(type)
                .direction(direction)
                .message(message)
                .build();
    }
}
