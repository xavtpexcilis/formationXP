package fr.excilys.formation.bowliwood;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ContinuousIntegrationTest {

	@Test
	public void testFailureLancerNum() {
	    BoardCount board = new BoardCount(2);
	    ByteArrayInputStream in = new ByteArrayInputStream("8\n".getBytes());
	    System.setIn(in);
	    Scanner sc = new Scanner(System.in);
	    assertEquals(board.lancerNum(1, 10), 8);
	    sc.close();
	}
	
	@Test
	public void testFailureLancerNum2() {
	    BoardCount board = new BoardCount(2);
	    //test ok si valeur incorrect puis correct cas "j\n8\n". Si seulement "j\n" échec. 
	    ByteArrayInputStream in = new ByteArrayInputStream("j\n8\n".getBytes());
	    System.setIn(in);
	    Scanner sc = new Scanner(System.in);
	    assertEquals(board.lancerNum(1, 10), 8);
	    sc.close();
	}
	
}
