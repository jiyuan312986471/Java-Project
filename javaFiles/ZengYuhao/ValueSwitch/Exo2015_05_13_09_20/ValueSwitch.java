package ZengYuhao.ValueSwitch.Exo2015_05_13_09_20;

public class ValueSwitch {

	public static void main(String[] args) {
		switchValue(100, 200);
	}
	
	private static void switchValue(int a, int b) {
		// display before switch
		System.out.println("Before switch: a=" + a + " b=" + b);
		
		// algorithm to fulfill
              int t;
               t=a;
a=b;
b=t;
             
		// display
		System.out.println("After switch:  a=" + a + " b=" + b);
	}
}