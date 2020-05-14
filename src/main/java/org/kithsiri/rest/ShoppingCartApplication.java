package org.kithsiri.rest;

import java.util.HashSet;
import java.util.Set;

import org.kithsiri.rest.model.Item;
import org.kithsiri.rest.model.ShoppingCart;
import org.kithsiri.rest.repo.ShoppingCartRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

    @Bean
    CommandLineRunner init(ShoppingCartRepo shoppingCartRepo) {
        return args -> {
        	Item item1 = new Item();
        	item1.setItemName("Pudding");
        	item1.setItemPrice(12);
        	
        	Item item2 = new Item();
        	item2.setItemName("Kiribath");
        	item2.setItemPrice(10);
        	
        	Set<Item> items = new HashSet<>(0);
        	items.add(item1);
        	items.add(item2);
        	
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setItems(items);
            
            shoppingCartRepo.save(shoppingCart);

            //shoppingCartRepo.findAll().forEach(System.out::println);
        };
    }
}
