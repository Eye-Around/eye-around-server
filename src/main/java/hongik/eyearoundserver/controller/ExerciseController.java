package hongik.eyearoundserver.controller;

import hongik.eyearoundserver.dto.ExerciseRequestDto;
import hongik.eyearoundserver.dto.ExerciseResponseDTO;
import hongik.eyearoundserver.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercises")
@Slf4j
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseResponseDTO> createExercise(@RequestBody ExerciseRequestDto requestDto) {
        log.info("눈 운동 생성 - title = {}", requestDto.getTitle());
        return new ResponseEntity<>(exerciseService.createExercise(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponseDTO>> getExerciseList() {
        log.info("눈 운동 리스트 조회");
        return new ResponseEntity<>(exerciseService.getExerciseList(), HttpStatus.OK);
    }
}
