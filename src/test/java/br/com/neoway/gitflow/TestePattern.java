package br.com.neoway.gitflow;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class TestePattern {

	@Test
	public void teste() throws IOException{
		 ParseTeste mock = mock(ParseTeste.class);
		    Mockito.doCallRealMethod().when(mock).testeParttern("[a-z]");
		    assertTrue(mock.testeParttern("[a-zA]"));
	}
	
}
