/**
 * 
 */
package me.stubner.fsapi.food;

import java.util.List;

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

	private final FoodService service;

	@Autowired
	FoodController(FoodService service) {
		this.service = service;
    }
	
    @GetMapping("/api/v1/food")
    public List<CompactFood> search(@RequestParam(value="q") String query) {
    	return service.search(query); 
    }
    
    @GetMapping("/api/v1/food/{id}")
    public Food getOne(@PathVariable Long id) {
    	return service.getOne(id);
    }

}
