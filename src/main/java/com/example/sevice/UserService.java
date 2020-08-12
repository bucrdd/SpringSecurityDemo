package com.example.sevice;

import com.example.domain.UserInfo;
import com.example.dto.UserUpdateDto;

public interface UserService {

  UserInfo signOn(UserUpdateDto param);
}
