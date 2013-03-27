package br.com.maven3.tutorial;

import org.junit.Test;



public class HelloWorldTest
{
   @Test
   public void testGetText() {
      HelloWorld fixture = new HelloWorld();
      assert "Hello World!".equals(fixture.getText());
   }
}
