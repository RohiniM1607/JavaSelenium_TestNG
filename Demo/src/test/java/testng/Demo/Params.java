package testng.Demo;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Params {


		@Test
		
		@Parameters({"val1", "val2"})
		public void Sum(int v1, int v2) {
			int finalSum = v1+v2;
			System.out.println("The final sum of the given values is "+finalSum);
		}
		
		@Test
		
		@Parameters({"val1", "val2"})
		public void Sum1(int v1, int v2) {
			int finaldiff = v1-v2;
			System.out.println("The final difference of the given values is "+finaldiff);
		}
	

}
