public class restaurantNotFoundException extends Throwable {
	public restaurantNotFoundException(String restaurantName) {
		System.out.println(restaurantName);
		// super(restaurantName);-- we can customize the message as above
	}
}
