package kw.kng.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import kw.kng.dto.UserDto;
import kw.kng.entities.Users;

@Mapper
public interface AutoUserMapper 
{
	
	//We use the below Mapping instance to call the below methods:
	AutoUserMapper MAPPER =Mappers.getMapper(AutoUserMapper.class);
	
	//@Mapping(source="email", target="emailAddress")
	UserDto mapToUserDto(Users user);
	
	
	Users mapToUser(UserDto userDto);

}

/*
 
1.  NOTE: The field name has to be same in DTO and in ENTITY Class.
 2. Even if the field names vary in DTO and ENTITY class. MapStruct provides @Mapping annotation to handle such scenarios.
 
 
 3. If the field names are different in `Source` and `Target`, then this is how to proceed:
 E.g:
 
 Code:
 
  @Mapping(source = "email", target = "emailAddress")
    @Mapping(source = "name", target = "fullName")
    UserDto mapToUserDto(Users user);
 
 Explaination:
   
  @Mapping(source = "email", target = "emailAddress"): 
  This means that when converting from Users to UserDto, the email field of Users is mapped to the emailAddress field of UserDto.

	@Mapping(source = "name", target = "fullName"): 
	Similarly, the name field of Users is mapped to the fullName field of UserDto.  
    
    
 4. MapStruct provides an implementation for this interface during compilation time.  
 
 5. //We use the below Mapping instance to call the below methods:
	AutoUserMapper MAPPER =Mappers.getMapper(AutoUserMapper.class);
 
 
 */