package com.babar.geode.test.repository;

import java.util.Collection;

import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.gemfire.repository.Query;

import com.babar.geode.test.model.Product;

public interface ProductRepository extends GemfireRepository<Product, String> {

	Collection<Product> findAll();
	
	Product findById(String id);

	@Query("SELECT * FROM /Product p WHERE p.stockOnHand > 0")
	Collection<Product> findAllWithStock();

	@Query("SELECT * FROM /Product p WHERE p.brand = $1 and p.\"type\"= $2 and p.gender = $3")
	Collection<Product> findAllByBrandTypeGender(String brand, String type,
			String gender);

	@Query("SELECT * FROM /Product p   WHERE  p.brand = $1 and  p.\"type\" = $2 and  p.gender = $3 and  p.stockOnHand > 0")
	Collection<Product> findAllWithStockByBrandTypeGender(String brand,String type, String gender);

    @Query("SELECT * FROM /Product p WHERE p.\"type\" LIKE $1 and p.stockOnHand > 0")
    Collection<Product> findAllWithStockByBrand( String brand) ;

}
