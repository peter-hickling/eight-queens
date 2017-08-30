package uk.co.codera;

import java.util.Map;
import java.util.TreeMap;

public class Chessboard {
	private Map<Integer, Integer> coordinates;

	public Chessboard() {
		coordinates = new TreeMap<Integer, Integer>();
	}

	public Map<Integer, Integer> placedQueens() {
		return coordinates;
	}

	public void addQueen(int horizontal, int vertical) {
		coordinates.put(horizontal, vertical);
	}

	public void removeQueen(int index) {
		coordinates.remove(coordinates.keySet().toArray()[index]);
	}

	public void empty() {
		coordinates.clear();
	}
}
