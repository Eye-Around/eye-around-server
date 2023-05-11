package hongik.eyearoundserver.service;

import hongik.eyearoundserver.domain.User;
import hongik.eyearoundserver.dto.*;

public interface UserService {
    UserResponseDTO signUp(UserRequestDTO requestDTO);
    UserResponseDTO login(LoginRequestDTO requestDTO);
    void changePassword(PasswordRequestDto requestDto, User user);
//    ProfileResponseDTO getProfile(User user);
}
