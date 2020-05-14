package org.kithsiri.rest.repo;

import org.kithsiri.rest.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepo extends CrudRepository<ShoppingCart, Long>{

}
