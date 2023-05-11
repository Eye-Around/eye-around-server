package hongik.eyearoundserver.service;

import hongik.eyearoundserver.domain.Exercise;
import hongik.eyearoundserver.dto.ExerciseRequestDto;
import hongik.eyearoundserver.dto.ExerciseResponseDTO;
import hongik.eyearoundserver.exception.CustomException;
import hongik.eyearoundserver.exception.ErrorCode;
import hongik.eyearoundserver.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Override
    @Transactional
    public ExerciseResponseDTO createExercise(ExerciseRequestDto requestDto) {
        Exercise exercise = requestDto.toEntity();
        Exercise savedExercise = exerciseRepository.save(exercise);

        return new ExerciseResponseDTO(savedExercise);
    }

    @Override
    public List<ExerciseResponseDTO> getExerciseList() {
        List<Exercise> exercises = exerciseRepository.findAll();

        return exercises.stream().map(exercise -> new ExerciseResponseDTO(exercise))
                .collect(Collectors.toList());
    }

    @Override
    public ExerciseResponseDTO getExercise(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_EXERCISE));

        return new ExerciseResponseDTO(exercise);
    }
}
