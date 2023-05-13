package hongik.eyearoundserver.service;

import hongik.eyearoundserver.dto.ExerciseRequestDto;
import hongik.eyearoundserver.dto.ExerciseResponseDTO;
import hongik.eyearoundserver.dto.GuideResponseDTO;

import java.util.List;

public interface GuideService {
    List<GuideResponseDTO> getGuideList();
    GuideResponseDTO getGuide(Long guideId);
}
