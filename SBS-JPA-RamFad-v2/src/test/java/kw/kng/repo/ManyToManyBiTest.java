package kw.kng.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Role;
import kw.kng.entities.User;

@SpringBootTest
public class ManyToManyBiTest 
{
	/* ----------------------------------------------------- MANY TO MANY  BI-DIRECTIONAL MAPPING ----------------------------------------------------- */
	
	
	@Autowired
	private RoleRepo rrepo;
	
	@Test
	void saveRole()
	{
		User u = new User();
		u.setFirstName("ramesh");
		u.setLastName("fadatare");
		u.setEmail("ramesh@gmail.com");
		u.setPassword("secret");
		
		User a = new User();
		a.setFirstName("admin");
		a.setLastName("admin");
		a.setEmail("admin@gmail.com");
		a.setPassword("admin");
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		
		roleAdmin.getUsers().add(u);//Add the  2 users in Target.
		roleAdmin.getUsers().add(a);
		
		u.getRoles().add(roleAdmin);//Add the role for the user `u` in Source.
		a.getRoles().add(roleAdmin);//Add the role for the user `a` in Source.
		
		rrepo.save(roleAdmin);
	}

	@Test
	void fetchRole()
	{
		List<Role> rl=rrepo.findAll();
		rl.forEach((r) -> 
		{
			System.out.println(r.getName());
			r.getUsers().forEach((u) ->{
				System.out.println(u.getFirstName());
			});
		});
	}
	
	@Test
	void updateRole()
	{
		Long id=1L;
		Role rl = rrepo.findById(id).orElseThrow(() -> new RuntimeException("Role with "+id+" not found"));
		rl.setName("ROLE_SUPER_ADMIN");
		rrepo.save(rl);
	}
	
	@Test
	void deleteRole()
	{
		Long id=1L;
		rrepo.deleteById(id);
		System.out.println("Role with id= "+id+" is deleted successfully from DB !!!");
	}
}
