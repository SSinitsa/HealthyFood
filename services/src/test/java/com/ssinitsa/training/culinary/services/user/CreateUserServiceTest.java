package com.ssinitsa.training.culinary.services.user;

import java.sql.Timestamp;
import java.util.Date;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.datamodel.UserRole;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IUserService;

public class CreateUserServiceTest extends AbstractTest {

	@Inject
	private IUserService service;
	
	private Integer savedUserId;
	
	@After
	public void deleteCreatedUser(){
		service.delete(savedUserId);
	}

	@Test
	public void createTest() {
		User user = new User();
		user.setLogin("new login");
		user.setPassword("new password");
		user.setRegistrated(new Timestamp(new Date().getTime()));
		user.setFirstName("new FN");
		user.setLastName("new LN");
		user.setEmail ("new e-mail");
		user.setRole(UserRole.user);
		
		service.save(user);

		savedUserId = user.getId();
		User userFromDb = service.get(savedUserId);
		
		System.out.println (userFromDb.toString());

		Assert.notNull(userFromDb, "user must be saved");

		/*Assert.notNull(ingredientFromDb.getCalories(), "calories column must not me empty");

		Assert.isTrue(ingredientFromDb.getCalories().equals(ingredient.getCalories()), "calories must be equals");

		Assert.notNull(ingredientFromDb.getFats(), "fats column must not me empty");

		Assert.isTrue(ingredientFromDb.getFats() == (ingredient.getFats()), "fats must be equals");

		Assert.notNull(ingredientFromDb.getProteins(), "proteins column must not me empty");

		Assert.isTrue(ingredientFromDb.getProteins() == (ingredient.getProteins()), "proteins must be equals");

		Assert.notNull(ingredientFromDb.getCarbohydrates(), "carbohydrates column must not me empty");

		Assert.isTrue(ingredientFromDb.getCarbohydrates() == (ingredient.getCarbohydrates()),
				"carbohydrates must be equals");

		Assert.notNull(ingredientFromDb.getCategory(), "category column must not me empty");

		Assert.isTrue(ingredientFromDb.getCategory().equals(ingredient.getCategory()), "category must be equals");*/

	}
	
	

}
