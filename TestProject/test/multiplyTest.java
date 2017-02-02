package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class multiplyTest {

	@Test
	public void testMultiply() {


		Junit test = new Junit();
		int result = test.multiply(2, 3);
		assertEquals(6, result);
	}

}
