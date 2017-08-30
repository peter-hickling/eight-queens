package uk.co.codera;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;


import org.junit.Test;

public class EightQueensDriverTest {

	@Test
	public void placeSomeQueens() {
		EightQueensDriver driver = new EightQueensDriver(new Queen(new Chessboard()));
		driver.placeEightQueens();
		
		assertThat(driver.getQueens().size(), is(not(0)));
		
	}
	
	@Test
	public void removesIfTooManyAttempts() {
		EightQueensDriver driver = new EightQueensDriver(new Queen(new Chessboard()));
		driver.placeEightQueens();
		
		assertThat(driver.getNumberOfRemovedQueens(), is(not(0)));
		
	}
	
	@Test
	public void eightQueensPlaced() {
		EightQueensDriver driver = new EightQueensDriver(new Queen(new Chessboard()));
		driver.placeEightQueens();
		
		assertThat(driver.getQueens().size(), is(equalTo(8)));
	}
	
	@Test
	public void allSolutionsFound() {
		EightQueensDriver driver = new EightQueensDriver(new Queen(new Chessboard()));
		
		assertThat(driver.getAllSolutions().size(), is(equalTo(92)));
	}
	
	

}
