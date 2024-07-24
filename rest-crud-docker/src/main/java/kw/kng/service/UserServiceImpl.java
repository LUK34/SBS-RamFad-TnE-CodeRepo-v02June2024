package kw.kng.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.dto.UserDto;
import kw.kng.entities.Users;
import kw.kng.exceptions.EmailAlreadyExistException;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService 
{
	private UserRepo urepo;
	private ModelMapper modelMapper;
	
	public UserServiceImpl(UserRepo urepo, ModelMapper modelMapper) 
	{
		this.urepo = urepo;
		this.modelMapper=modelMapper;
	}

	@Override
	public UserDto createUser(UserDto userDto)
	{
		//First check if the given email exist in the db or not. If exist -> throw EmailNotFoundException
		Optional<Users> optionalUser = urepo.findByEmail(userDto.getEmail());
		if(optionalUser.isPresent())
		{
			throw new EmailAlreadyExistException("Email: "+optionalUser.get().getEmail()+"  already exists in DB.");
		}
		
		//Transfer DTO to ENTITY
		Users user= modelMapper.map(userDto, Users.class);
		
		//Save the ENTITY details into PERSITANCE layer
		Users savedUser= urepo.save(user);
		
		//After saving to transfer ENTITY to DTO
		return modelMapper.map(savedUser, UserDto.class);
	}
	
	@Override
	public List<UserDto> createUserList(List<UserDto> userDto) 
	{
		
		//Transfer the LIST of USERS from DTO to ENTITY
		List<Users> userList= userDto.stream().map(dto -> modelMapper.map(dto, Users.class )).collect(Collectors.toList());
		
		// Check if any of the emails already exist in the database
		 List<String> emails = userList.stream().map(Users::getEmail).collect(Collectors.toList());

			for (String email : emails) 
			{
			Optional<Users> optionalUser = urepo.findByEmail(email);
				if (optionalUser.isPresent())
				{
					System.out.println("Email: " + optionalUser.get().getEmail());
					throw new EmailAlreadyExistException("Email: " + optionalUser.get().getEmail() + " already exists in DB.");
				}
			}
		
		
		//Save the List of USERS in ENTITY using PERSISTENCE
		List<Users> savedUserList = urepo.saveAll(userList);
		
		//Transfer the savedUserList to DTO
		return savedUserList.stream().map(u ->modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Long userId) 
	{
		//Search in DB if the data exist or not . If not throw `ResourceNotFoundException`
		Users user= urepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id=  "+userId+" not found in DB"));
		
		//Transfer ENTITY to DTO
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() 
	{
		//Find the list of user and save it in ENTITY as LIST
		List<Users> userList= urepo.findAll();
	
		//Transfer ENTITY to DTO
		return userList.stream().map((u) -> modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(Long userId, UserDto userDto) 
	{
		//Search in DB if the data exist or not . If not throw `ResourceNotFoundException`
		Users user= urepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id=  "+userId+" not found in DB"));
		
		//Transfer the DTO Details to ENTITY
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		
		//Save the ENTITY details to PERSISTANCE
		Users updatedUsers= urepo.save(user);
	
		return modelMapper.map(updatedUsers, UserDto.class);
	}

	@Override
	public void deleteUserByid(Long userId)
	{
		//Search in DB if the data exist or not . If not throw `ResourceNotFoundException`
		Users user= urepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id= "+userId+" not found in DB"));
			
		//Delete the user in PERSISTANCE
		urepo.deleteById(userId);
	}

	
	
	
	
	
	
	
	

}
