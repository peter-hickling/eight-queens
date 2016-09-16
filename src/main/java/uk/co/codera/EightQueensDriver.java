package uk.co.codera;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class EightQueensDriver {
	private int numberOfQueensToRemove;
	private Queen queen;
	private Set<String> allSolutions;

	public EightQueensDriver(Queen queen) {
		this.queen = queen;
		numberOfQueensToRemove = 0;
		allSolutions = new HashSet<String>();
	}

	public Map<Integer, Integer> getQueens() {
		return queen.getChessboard().placedQueens();
	}

	public int getNumberOfRemovedQueens() {
		return numberOfQueensToRemove;
	}

	public Map<Integer, Integer> placeEightQueens() {
		int numberOfAttempts = 0;

		while (queen.getChessboard().placedQueens().size() < 8) {
			Random random = new Random();
			numberOfAttempts++;

			if (queen.placeQueen(random.nextInt(8) + 1, random.nextInt(8) + 1)) {
				numberOfAttempts = 0;
			}

			if (numberOfAttempts > 50) {
				numberOfQueensToRemove++;

				if (numberOfQueensToRemove < queen.getChessboard().placedQueens().size()) {
					queen.removeRandomQueen(numberOfQueensToRemove);
				}

				else {
					numberOfQueensToRemove = 0;
				}
				numberOfAttempts = 0;
			}
		}
		return queen.getChessboard().placedQueens();
	}
	
	public Set<String> getAllSolutions() {
		
		while (allSolutions.size() < 92) {
			allSolutions.add(placeEightQueens().toString());
			queen.removeAllQueens();
		}
		
		return allSolutions;
	}

}
