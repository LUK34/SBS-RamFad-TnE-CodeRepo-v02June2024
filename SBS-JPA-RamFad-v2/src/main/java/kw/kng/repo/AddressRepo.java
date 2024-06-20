package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.Address;

public interface AddressRepo extends JpaRepository<Address, Long> 
{

}
