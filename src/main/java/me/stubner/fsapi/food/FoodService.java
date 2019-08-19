/**
 * 
 */
package me.stubner.fsapi.food;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.FatsecretService;

/**
 * @author ralf
 *
 */
@Service
public class FoodService {

	@Value("${FATSECRET_CONSUMER_KEY}")
	private String key;
	@Value("${FATSECRET_CONSUMER_SECRET}")
	private String secret;

	private FatsecretService service;

	@PostConstruct
	private void init() {
		service = new FatsecretService(key, secret);
	}

	public List<CompactFood> search(String query) {
		return service.searchFoods(query).getResults();
	}

	public Food getOne(Long id) {
		return service.getFood(id);
	}
}
