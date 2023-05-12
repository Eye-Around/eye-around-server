package hongik.eyearoundserver.controller;

import hongik.eyearoundserver.dto.ExerciseResponseDTO;
import hongik.eyearoundserver.dto.GuideResponseDTO;
import hongik.eyearoundserver.service.GuideService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guide")
@Slf4j
@Tag(name = "Guide", description = "눈 운동 가이드 API")
public class GuideController {

    private final GuideService guideService;

    @Operation(summary = "눈 운동 가이드 리스트 조회 API", description = "눈 운동 가이드 리스트 전체를 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = GuideResponseDTO.class)))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰입니다")
    })
    @GetMapping
    public List<GuideResponseDTO> getGuideList() {
        log.info("눈 운동 가이드 리스트 조회");
        return guideService.getGuideList();
    }

    @Operation(summary = "눈 운동 가이드 상세 조회 API", description = "사용자가 눈 운동 가이드 리스트 중 하나를 클릭할 시, 해당 눈 운동 가이드 정보를 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GuideResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰입니다"),
            @ApiResponse(responseCode = "404", description = "요청하신 가이드를 찾을 수 없습니다")

    })
    @GetMapping("/{guideId}")
    public ResponseEntity<GuideResponseDTO> getGuide(@PathVariable Long guideId) {
        log.info("눈 운동 조회 - id = {}", guideId);
        return new ResponseEntity<>(guideService.getGuide(guideId), HttpStatus.OK);
    }
}
