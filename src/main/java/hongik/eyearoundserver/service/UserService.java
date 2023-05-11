package hongik.eyearoundserver.service;

import hongik.eyearoundserver.domain.User;
import hongik.eyearoundserver.dto.LoginRequestDTO;
import hongik.eyearoundserver.dto.PasswordRequestDto;
import hongik.eyearoundserver.dto.UserRequestDTO;
import hongik.eyearoundserver.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO signUp(UserRequestDTO requestDTO);
    UserResponseDTO login(LoginRequestDTO requestDTO);
    void changePassword(PasswordRequestDto requestDto, User user);
}
