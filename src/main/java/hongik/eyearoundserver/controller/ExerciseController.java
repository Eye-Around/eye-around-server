package hongik.eyearoundserver.controller;

import hongik.eyearoundserver.domain.User;
import hongik.eyearoundserver.dto.ExerciseRequestDto;
import hongik.eyearoundserver.dto.ExerciseResponseDTO;
import hongik.eyearoundserver.exception.ErrorCode;
import hongik.eyearoundserver.exception.ErrorResponse;
import hongik.eyearoundserver.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
@Slf4j
@Tag(name = "Exercise", description = "눈 운동 API")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseResponseDTO> createExercise(@RequestBody ExerciseRequestDto requestDto) {
        log.info("눈 운동 생성 - title = {}", requestDto.getTitle());
        return new ResponseEntity<>(exerciseService.createExercise(requestDto), HttpStatus.CREATED);
    }

    @Operation(summary = "눈 운동 리스트 조회 API", description = "눈 운동 리스트 전체를 조회하여 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExerciseResponseDTO.class)))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰입니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public List<ExerciseResponseDTO> getExerciseList() {
        log.info("눈 운동 리스트 조회");
        return exerciseService.getExerciseList();
    }

    @Operation(summary = "눈 운동 상세 조회 API", description = "사용자가 눈 운동 리스트 중 하나를 클릭할 시, 눈 운동 정보를 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ExerciseResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰입니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "요청하신 눈 운동을 찾을 수 없습니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{exerciseId}")
    public ResponseEntity<ExerciseResponseDTO> getExercise(@PathVariable Long exerciseId) {
        log.info("눈 운동 조회 - id = {}", exerciseId);
        return new ResponseEntity<>(exerciseService.getExercise(exerciseId), HttpStatus.OK);
    }

    @Operation(summary = "오늘의 눈 운동 완료 API", description = "사용자가 운동 시작을 클릭할 시 요청되어야 할 API 입니다. 하루에 한 번 이상 운동을 할 경우 이번주 운동 횟수를 갱신합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰입니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/check")
    public ResponseEntity changeTodayState(@AuthenticationPrincipal User user) {
        log.info("오늘의 운동 완료 - user.name = {}", user.getName());
        exerciseService.changeTodayState(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
