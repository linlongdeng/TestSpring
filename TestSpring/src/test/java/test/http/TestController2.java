package test.http;

import org.junit.Test;

public class TestController2 {

	
	
	@Test
	public void testController(){
		String actionPath="/test/testExampleException";
		String param ="flag=true";
		String result = HttpRequest.sendGet(actionPath, param);
		System.out.println(result);
	}
}
