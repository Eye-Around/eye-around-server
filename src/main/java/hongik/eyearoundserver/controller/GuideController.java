package hongik.eyearoundserver.controller;

import hongik.eyearoundserver.dto.GuideResponseDTO;
import hongik.eyearoundserver.service.GuideService;
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
public class GuideController {

    private final GuideService guideService;

    @GetMapping
    public List<GuideResponseDTO> getGuideList() {
        log.info("눈 운동 가이드 리스트 조회");
        return guideService.getGuideList();
    }

    @GetMapping("/{guideId}")
    public ResponseEntity<GuideResponseDTO> getGuide(@PathVariable Long guideId) {
        log.info("눈 운동 조회 - id = {}", guideId);
        return new ResponseEntity<>(guideService.getGuide(guideId), HttpStatus.OK);
    }
}
