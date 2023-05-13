package hongik.eyearoundserver.service;

import hongik.eyearoundserver.domain.Exercise;
import hongik.eyearoundserver.domain.ExerciseGuide;
import hongik.eyearoundserver.domain.Guide;
import hongik.eyearoundserver.dto.GuideResponseDTO;
import hongik.eyearoundserver.exception.CustomException;
import hongik.eyearoundserver.exception.ErrorCode;
import hongik.eyearoundserver.repository.ExerciseGuideRepository;
import hongik.eyearoundserver.repository.ExerciseRepository;
import hongik.eyearoundserver.repository.GuideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuideServiceImpl implements GuideService {

    private final GuideRepository guideRepository;
    private final ExerciseGuideRepository exerciseGuideRepository;

    @Override
    public List<GuideResponseDTO> getGuideList() {
        List<Guide> guides = guideRepository.findAll();

        return guides.stream().map(guide -> {
                    List<ExerciseGuide> exerciseGuides = exerciseGuideRepository.findByGuideId(guide.getId());
                    return new GuideResponseDTO(guide, exerciseGuides);
                })
                .collect(Collectors.toList());
    }

    @Override
    public GuideResponseDTO getGuide(Long guideId) {
        Guide guide = guideRepository.findById(guideId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_GUIDE));
        List<ExerciseGuide> exerciseGuides = exerciseGuideRepository.findByGuideId(guide.getId());

        return new GuideResponseDTO(guide, exerciseGuides);
    }
}
