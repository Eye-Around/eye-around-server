package hongik.eyearoundserver.service;

import hongik.eyearoundserver.config.security.JwtProvider;
import hongik.eyearoundserver.domain.User;
import hongik.eyearoundserver.dto.LoginRequestDTO;
import hongik.eyearoundserver.dto.UserRequestDTO;
import hongik.eyearoundserver.dto.UserResponseDTO;
import hongik.eyearoundserver.exception.CustomException;
import hongik.eyearoundserver.exception.ErrorCode;
import hongik.eyearoundserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    @Override
    public UserResponseDTO signUp(UserRequestDTO requestDTO) {
        // email, name 검증
        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }
        if (userRepository.findByName(requestDTO.getName()).isPresent()) {
            throw new CustomException(ErrorCode.USER_NAME_ALREADY_EXISTS);
        }

        User user = requestDTO.toEntity(passwordEncoder.encode(requestDTO.getPassword()));
        User savedUser = userRepository.save(user);
        String token = jwtProvider.createToken(requestDTO.getEmail());

        return new UserResponseDTO(savedUser, token);
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO requestDTO) {
        User user = userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        }
        String token = jwtProvider.createToken(requestDTO.getEmail());
        return new UserResponseDTO(user, token);
    }
}
