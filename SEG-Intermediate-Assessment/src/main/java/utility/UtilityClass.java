package utility;

public class UtilityClass {

	public static long getRandomNumber() {
		return (long) Math.floor(Math.random() * 900000000L) + 10000000L;
	}
}
