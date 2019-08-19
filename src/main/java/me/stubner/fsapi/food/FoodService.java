/**
 * 
 */
package me.stubner.fsapi.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.FatsecretService;

/**
 * @author ralf
 *
 */
@Service
public class FoodService implements FoodServiceInterface {

	@Autowired
	private FatsecretService service;

	@Override
	public List<CompactFood> search(String query) {
		return service.searchFoods(query).getResults();
	}

	@Override
	public Food getOne(Long id) {
		return service.getFood(id);
	}
}
