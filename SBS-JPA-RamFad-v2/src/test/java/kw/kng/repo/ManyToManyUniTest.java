package kw.kng.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Role;
import kw.kng.entities.User;

@SpringBootTest
public class ManyToManyUniTest 
{
	/* ----------------------------------------------------- MANY TO MANY  UNI-DIRECTIONAL MAPPING ----------------------------------------------------- */
	
	
	@Autowired
	private UserRepo urepo;
	
	@Test
	void saveUser()
	{
		User u = new User();
		u.setFirstName("ramesh");
		u.setLastName("fadatare");
		u.setEmail("ramesh@gmail.com");
		u.setPassword("secret");
		
		Role admin = new Role();
		admin.setName("ROLE_ADMIN");
		
		Role customer = new Role();
		customer.setName("ROLE_CUSTOMER");
		
		u.getRoles().add(admin);
		u.getRoles().add(customer);
		
		urepo.save(u);
	}
	
	@Test
	void updateUser()
	{
		User user= urepo.findById(1L).get();
		user.setFirstName("ram");
		user.setEmail("ram@gmail.com");
		
		Role ru=new Role();
		ru.setName("ROLE_USER");
		
		user.getRoles().add(ru);
		urepo.save(user);
	}
	
	@Test
	void fetchUser()
	{
		User u= urepo.findById(1L).get();
		System.out.println(u.getEmail());
		u.getRoles().forEach((r) ->{
					System.out.println(r.getName());
		});
	}
	
	@Test
	void deleteUser()
	{
		Long id=1L;
		urepo.deleteById(id);
		System.out.println("User with id= "+id+" is deleted successfully from DB !!!");
	}

}
