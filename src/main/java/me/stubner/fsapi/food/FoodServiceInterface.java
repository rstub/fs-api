package me.stubner.fsapi.food;

import java.util.List;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;

public interface FoodServiceInterface {

	List<CompactFood> search(String query);

	Food getOne(Long id);

}