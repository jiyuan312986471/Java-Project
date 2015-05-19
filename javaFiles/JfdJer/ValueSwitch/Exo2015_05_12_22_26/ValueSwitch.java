package JfdJer.ValueSwitch.Exo2015_05_12_22_26;

public class ValueSwitch {

	public static void main(String[] args) {
		switchValue(100, 200);
	}
	
	private static void switchValue(int a, int b) {
		// display before switch
		System.out.println("Before switch: a=" + a + " b=" + b);
		
		// algorithm to fulfill
a = a^b;
b = a^b;
a = a^b;
		// display
		System.out.println("After switch:  a=" + a + " b=" + b);
	}
}