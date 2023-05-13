package hongik.eyearoundserver.dto;

import hongik.eyearoundserver.domain.Exercise;
import hongik.eyearoundserver.domain.ExerciseGuide;
import hongik.eyearoundserver.domain.Guide;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GuideResponseDTO {
    @Schema(description = "guide 의 id", defaultValue = "1")
    private Long id;
    @Schema(description = "가이드 안내 화면의 운동 제목", defaultValue = "건조한 눈을 위한 눈 운동")
    private String title;
    @Schema(description = "가이드 안내 화면의 운동 효과", defaultValue = "눈물형성을 활성화 하여 안구 건조증 해소에 도움을 줍니다.")
    private String effect;
    @Schema(description = "해당 가이드에 속해있는 눈 운동 리스트입니다.")
    private List<ExerciseResponseDTO> exercises;

    @Builder
    public GuideResponseDTO(Guide guide, List<ExerciseGuide> exerciseGuides) {
        this.id = guide.getId();
        this.title = guide.getTitle();
        this.effect = guide.getEffect();

        List<ExerciseResponseDTO> responses = new ArrayList<>();
        for (ExerciseGuide e : exerciseGuides) {
            responses.add(new ExerciseResponseDTO(e.getExercise()));
        }
        this.exercises = responses;
    }
}
