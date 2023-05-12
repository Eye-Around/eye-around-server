package hongik.eyearoundserver.service;

import hongik.eyearoundserver.domain.User;
import hongik.eyearoundserver.dto.*;

public interface UserService {
    UserResponseDTO signUp(UserRequestDTO requestDTO);
    UserResponseDTO login(LoginRequestDTO requestDTO);
    void checkPassword(PasswordRequestDto requestDto, User user);
    void changePassword(PasswordRequestDto requestDto, User user);
}
