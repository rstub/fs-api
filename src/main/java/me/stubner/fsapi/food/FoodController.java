/**
 * 
 */
package me.stubner.fsapi.food;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;

/**
 * @author ralf
 *
 */

@RestController
public class FoodController {

    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

	private final FoodServiceInterface service;

	@Autowired
	FoodController(FoodServiceInterface service) {
		this.service = service;
    }
	
    @GetMapping("/api/v1/food")
    public List<CompactFood> search(@RequestParam(value="q") String query) {
    	logger.info("Search for '{}'", query);
    	return service.search(query); 
    }
    
    @GetMapping("/api/v1/food/{id}")
    public Food getOne(@PathVariable Long id) {
    	logger.info("Request for id {}", id);
    	return service.getOne(id);
    }

}
