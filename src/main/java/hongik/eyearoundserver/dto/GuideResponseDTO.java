package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.Exercise;
import hongik.eyearoundserver.domain.Guide;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GuideResponseDTO {
    private Long id;
    private List<ExerciseResponseDTO> exercises;
    private String effect;

    @Builder
    public GuideResponseDTO(Guide guide) {
        this.id = guide.getId();
        this.effect = guide.getEffect();

        List<ExerciseResponseDTO> exercises = new ArrayList<>();
        for (Exercise e : guide.getExercises()) {
            exercises.add(new ExerciseResponseDTO(e));
        }
        this.exercises = exercises;
    }
}
