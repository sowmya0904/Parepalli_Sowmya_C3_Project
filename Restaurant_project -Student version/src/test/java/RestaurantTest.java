import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import net.bytebuddy.asm.Advice.Local;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockitoSession;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {

	Restaurant restaurant;
	RestaurantService service = new RestaurantService();
	List<Item> list = new ArrayList<>();

	private void basicInfoaddToMenu() {
		restaurant.addToMenu("Sweet corn soup", 119);
		restaurant.addToMenu("Vegetable lasagne", 269);
	}

	@BeforeEach
	public void setUp() {
		LocalTime openingTime = LocalTime.parse("10:30:00");
		LocalTime closingTime = LocalTime.parse("20:00:59");
		restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
	}

	@Test
	public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {

		LocalTime current = LocalTime.parse("22:00:00");
		Restaurant restaurantTest = Mockito.spy(restaurant);
		Mockito.when(restaurantTest.getCurrentTime()).thenReturn(current);
		assertFalse(restaurantTest.isRestaurantOpen());

	}

	@Test
	public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
		Restaurant restaurantTest = Mockito.spy(restaurant);
		LocalTime current = LocalTime.parse("18:30:49");

		Mockito.when(restaurantTest.getCurrentTime()).thenReturn(current);
		assertTrue(restaurantTest.isRestaurantOpen());
	}

	@Test
	public void adding_item_to_menu_should_increase_menu_size_by_1() {
		basicInfoaddToMenu();
		int initialMenuSize = restaurant.getMenu().size();
		restaurant.addToMenu("Sizzling brownie", 319);
		assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
	}

	@Test
	public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
		basicInfoaddToMenu();
		int initialMenuSize = restaurant.getMenu().size();
		restaurant.removeFromMenu("Vegetable lasagne");
		assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
	}

	@Test
	public void removing_item_that_does_not_exist_should_throw_exception() {

		basicInfoaddToMenu();
		assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
	}

	@Test
	public void calculating_the_total_amount_for_the_selected_items() {

		basicInfoaddToMenu();
		list = restaurant.getMenu();
		int TotalAmount = restaurant.calculateTotalAmount(list);

	}

	

}