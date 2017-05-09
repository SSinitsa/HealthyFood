package com.ssinitsa.training.culinary.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({ IngredientServiceTest.class, UserServiceTest.class, RecipeServiceTest.class,
		RecipeDetailsServiceTest.class, FeedbackServiceTest.class, RatingServiceTest.class })
@RunWith(Suite.class)
public class AllServiceTest {

}
