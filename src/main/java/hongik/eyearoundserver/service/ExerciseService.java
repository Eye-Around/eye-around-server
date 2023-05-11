package hongik.eyearoundserver.service;

import hongik.eyearoundserver.domain.User;
import hongik.eyearoundserver.dto.ExerciseRequestDto;
import hongik.eyearoundserver.dto.ExerciseResponseDTO;

import java.util.List;

public interface ExerciseService {
    ExerciseResponseDTO createExercise(ExerciseRequestDto requestDto);
    List<ExerciseResponseDTO> getExerciseList();
    ExerciseResponseDTO getExercise(Long exerciseId);
    // 운동 시작
    void changeTodayState(User user);
}
