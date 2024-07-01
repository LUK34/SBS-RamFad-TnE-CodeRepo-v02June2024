package kw.kng.service;

import java.util.List;

import kw.kng.dto.UserDto;

public interface UserService 
{
	UserDto createUser(UserDto userDto);
	List<UserDto> createUserList(List<UserDto> userDtos);
	UserDto getUserById(Long userId);
	List<UserDto> getAllUser();
	UserDto updateUser(Long userId, UserDto updateUsers);
	void deleteUserByid(Long userId);

}
