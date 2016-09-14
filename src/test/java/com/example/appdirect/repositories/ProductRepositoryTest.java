package com.example.appdirect.repositories;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.appdirect.configuration.RepositoryConfiguration;
import com.example.appdirect.domain.Product;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={RepositoryConfiguration.class})
public class ProductRepositoryTest {
	
	@Autowired private ProductRepository productRepository;

	@Test
	public void shouldAutowire() {
		Assert.assertNotNull(productRepository);
	}
	
	@Test
	public void shouldSaveProduct() {
		// Setup 
		Product p = new Product();
		p.setDescription("test description");
		p.setPrice(new BigDecimal("18.99"));
		p.setProductId("1223");
				
		// execute
		Assert.assertNull("Product's Id is not null before saving" , p.getId());
		productRepository.save(p);
		Assert.assertNotNull("Product's Id is null after saving" , p.getId());
		
		// verify
		final Product fetchedProduct = productRepository.findOne(p.getId());
		
		Assert.assertNotNull("Fetched Product is null", fetchedProduct);
		
		Assert.assertEquals("Product Id mismatch in Save", p.getId(), fetchedProduct.getId());
		Assert.assertEquals("Product Description mismatch in Save", p.getDescription(), fetchedProduct.getDescription());
	}
	
	@Test
	public void shouldUpdateProduct() {
		// setup
		Product p = new Product();
		p.setDescription("old description");
		p.setPrice(new BigDecimal("19.99"));
		p.setProductId("1220");
		
		// execute
		productRepository.save(p);
		
		final Integer productId = p.getId();
		
		p.setDescription("new description");
		productRepository.save(p);
		
		final Product updatedProduct = productRepository.findOne(productId);
		
		// verify
		Assert.assertNotNull(updatedProduct);
		Assert.assertEquals("Wrong Product Id updated", productId, updatedProduct.getId());
		Assert.assertEquals("Product Description mismatch", "new description", updatedProduct.getDescription());
	}
}
