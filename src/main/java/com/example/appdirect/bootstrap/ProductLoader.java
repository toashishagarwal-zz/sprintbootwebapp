package com.example.appdirect.bootstrap;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.appdirect.domain.Product;
import com.example.appdirect.repositories.ProductRepository;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired private ProductRepository productRepository;
	private Logger logger = Logger.getLogger(ProductLoader.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		final Product shirt = new Product();
		shirt.setDescription("Cotton wrinkle free white colored");
		shirt.setProductId("1231321312");
		shirt.setPrice(new BigDecimal("7.99"));
		productRepository.save(shirt);
		
		logger.info("Saved shirt. Id -" + shirt.getId());
		
		final Product cup = new Product();
		cup.setDescription("Bone China Cup");
		cup.setProductId("4642222111");
		cup.setPrice(new BigDecimal("3.99"));
		productRepository.save(cup);
		
		logger.info("Saved Cup. Id -" + cup.getId());
	}

}
