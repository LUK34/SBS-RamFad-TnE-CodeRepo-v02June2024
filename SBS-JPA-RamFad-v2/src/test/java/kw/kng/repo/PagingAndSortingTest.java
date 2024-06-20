package kw.kng.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import kw.kng.entities.Product;

@SpringBootTest
public class PagingAndSortingTest
{
	@Autowired
	private ProductRepo prepo;
	
	@Test
	void pagination()
	{
		int pageNo=0;
		int pageSize=5;
		
		//create pageable object
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		//findALL method and pass pageable instance
		Page<Product> page =prepo.findAll(pageable);
		
		List<Product> products = page.getContent();
		
		products.forEach((p) ->{
			System.out.println(p);
		});
		
		//Total pages
		int totalPage= page.getTotalPages();
		//Total Elemets
		long totalElements= page.getTotalElements();
		//Numbe of Elements
		int numberOfElements= page.getNumberOfElements();
		//Size
		int size = page.getSize();
		//lost
		boolean isLast = page.isLast();
		//first
		boolean isFirst= page.isFirst();
		System.out.println("Total pages ->"+totalPage);
		System.out.println("Total Elements ->"+totalElements);
		System.out.println("Number of Elements ->"+numberOfElements);
		System.out.println("Size ->"+size);
		System.out.println("isLast -> "+isLast);
		System.out.println("isFirst -> "+isFirst);
	}

	@Test
	void sorting()
	{
		String sortBy = "price";
		String sortDir = "desc";
		String sortByDescription="description";
		
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Sorting in Descending Order: ");
		System.out.println("--------------------------");
		List<Product> pros= prepo.findAll(Sort.by(sortBy).descending());
		pros.forEach((p) ->{
			System.out.println(p);
		});
		
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Sorting in Ascending Order: ");
		System.out.println("--------------------------");
		List<Product> pros1= prepo.findAll(Sort.by(sortBy).ascending());
		pros1.forEach((p) ->{
			System.out.println(p);
		});
		
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Sorting by ternary: ");
		System.out.println("--------------------------");
		Sort sort=sortDir.equalsIgnoreCase( Sort.Direction.ASC.name()) ?
						Sort.by(sortBy).ascending() :
						Sort.by(sortBy).descending();
		
		List<Product> prosT= prepo.findAll(Sort.by(sortBy));
		prosT.forEach((p) ->{
			System.out.println(p);
		});
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Sorting by Multiple Values: ");
		System.out.println("--------------------------");
		Sort sortPrice = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
									Sort.by(sortBy).ascending():
									Sort.by(sortBy).descending();	
		
		Sort sortDescription_1 = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
								  Sort.by(sortByDescription).ascending():
								  Sort.by(sortByDescription).descending();	
		
		//Sorting on multiple columns or group by sort,
		Sort groupBySort = sortPrice.and(sortDescription_1);
		
		List<Product> sortedProducts = prepo.findAll(groupBySort);
		sortedProducts.forEach((p) ->{
			System.out.println(p);
		});
		
	}
	
	 @Test
	  void paginationAndSortingTogether()
	 {
		 
	        String sortByPrice = "price";
	        String sortByDirection = "desc";
	        String sortByDescription = "description";
	        int pageNo = 1;
	        int pageSize = 5;

	        System.out.println("---------------------------------------------------------------------------------");
			System.out.println("Paging and Sorting together: ");
			System.out.println("--------------------------");
	        
	        // Sort object
	        Sort sortPrice = sortByDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?
	                					Sort.by(sortByPrice).ascending():
	                					Sort.by(sortByPrice).descending();

	        // Pageable object
	        Pageable pageable = PageRequest.of(pageNo, pageSize, sortPrice); //Here we add paging and sorting
	        Page<Product> page = prepo.findAll(pageable);
	        List<Product> products = page.getContent();

	        products.forEach((p) ->{
	            System.out.println(p);
	        });

	        // total pages
	        int totalPage = page.getTotalPages();
	        // total elements
	        long totalElements = page.getTotalElements();
	        // number of elements
	        int numberOfElements = page.getNumberOfElements();
	        // size
	        int size = page.getSize();
	        // last
	        boolean isLast = page.isLast();
	        // first
	        boolean isFirst = page.isFirst();
	        
	        System.out.println("total page -> " + totalPage);
	        System.out.println("totalElements -> " + totalElements);
	        System.out.println("numberOfElements -> " + numberOfElements);
	        System.out.println(" size -> " + size);
	        System.out.println(" isLast -> " + isLast);
	        System.out.println(" isFirst -> " + isFirst);
	    }
	
	
}
