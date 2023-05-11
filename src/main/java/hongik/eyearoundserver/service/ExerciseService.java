package hongik.eyearoundserver.service;

import hongik.eyearoundserver.dto.ExerciseRequestDto;
import hongik.eyearoundserver.dto.ExerciseResponseDTO;

import java.util.List;

public interface ExerciseService {
    ExerciseResponseDTO createExercise(ExerciseRequestDto requestDto);
    List<ExerciseResponseDTO> getExerciseList();
    ExerciseResponseDTO getExercise(Long exerciseId);
}
