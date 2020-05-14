package org.kithsiri.rest.controller;

import org.kithsiri.rest.repo.ShoppingCartRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ShoppingCartController {

	private ShoppingCartRepo shoppingCartRepo;
	
	public ShoppingCartController(final ShoppingCartRepo shoppingCartRepo) {
		this.shoppingCartRepo = shoppingCartRepo;
	}
	
	@GetMapping("/cart")
	void getShoppingCart(@RequestBody String shoppingCartId) {
		this.shoppingCartRepo.findById(Long.parseLong(shoppingCartId));
	}
	
}
