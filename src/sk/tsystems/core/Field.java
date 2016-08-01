package sk.tsystems.core;

import java.util.*;

/**
 * Field represents playing field and game logic.
 */
public class Field {
	/**
	 * Playing field tiles.
	 */
	private final Tile[][] tiles;

	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	/**
	 * Mine count.
	 */
	private final int mineCount;

	/**
	 * Game state.
	 */

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	private GameState state = GameState.PLAYING;

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	/**
	 * Constructor.
	 *
	 * @param rowCount
	 *            row count
	 * @param columnCount
	 *            column count
	 * @param mineCount
	 *            mine count
	 */
	public Field(int rowCount, int columnCount, int mineCount) {
		
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
		tiles = new Tile[rowCount][columnCount];

		// generate the field content
		generate();
	}

	/**
	 * Opens tile at specified indeces.
	 *
	 * @param row
	 *            row number
	 * @param column
	 *            column number
	 */
	public void openTile(int row, int column) {
		
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.OPEN);
			if (tile instanceof Mine) {
				state = GameState.FAILED;
				return;
			}

			if (isSolved()) {
				state = GameState.SOLVED;
				return;
			}

			Tile tempTile = tiles[row][column];
			Clue tempClue = (Clue) tempTile;
			tempClue.getValue();

			if (tempClue.getValue() == 0) {
				openAdjacentTiles(row, column);

			}

		}
	}

	/**
	 * Marks tile at specified indeces.
	 *
	 * @param row
	 *            row number
	 * @param column
	 *            column number
	 */
	public void markTile(int row, int column) {
		
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.MARKED);
		} else {
			tile.setState(Tile.State.CLOSED);
		}

	}

	/**
	 * Generates playing field.
	 */
	private void generate() {

		int i = 0;
		while (i < getMineCount()) {

			Random randomNumber1 = new Random();
			Random randomNumber = new Random();
			int row = randomNumber.nextInt(getRowCount());
			int column = randomNumber1.nextInt(getColumnCount());

			if (tiles[row][column] == null) {
				tiles[row][column] = new Mine();
				i++;
			}
		}

		for (int k = 0; k < getRowCount(); k++) {
			for (int j = 0; j < getColumnCount(); j++) {

				int row = k;
				int column = j;

				if (tiles[row][column] == null) {
					tiles[row][column] = new Clue(countAdjacentMines(row, column));
				}
			}
		}
	}

	/**
	 * Returns true if game is solved, false otherwise.
	 *
	 * @return true if game is solved, false otherwise
	 */
	public boolean isSolved() {
		if ((getRowCount() * getColumnCount()) - getNumberOf(Tile.State.OPEN) == getMineCount()) {
			return true;
		} else {
			return false;
		}
	}

	private int getNumberOf(Tile.State state) {

		int helpMe = 0;

		for (int row = 0; row < getRowCount(); row++) {
			for (int column = 0; column < getColumnCount(); column++) {

				if (getTile(row, column).getState() == state) {
					helpMe++;
				}
			}
		}
		return helpMe;
	}

	/**
	 * Returns number of adjacent mines for a tile at specified position in the
	 * field.
	 *
	 * @param row
	 *            row number.
	 * @param column
	 *            column number.
	 * @return number of adjacent mines.
	 */
	private int countAdjacentMines(int row, int column) {
		
		int count = 0;
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < rowCount) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < columnCount) {
						if (tiles[actRow][actColumn] instanceof Mine) {
							count++;
						}
					}
				}
			}
		}
		return count;
		
	}

	private void openAdjacentTiles(int row, int column) {
		
		for (int adjecentRow = -1; adjecentRow < 2; adjecentRow++) {
			for (int adjecentColumn = -1; adjecentColumn < 2; adjecentColumn++) {
				if ((row + adjecentRow) > -1 && (row + adjecentRow) < getRowCount() && (column + adjecentColumn) > -1
						&& (column + adjecentColumn) < getColumnCount())
					openTile(row + adjecentRow, column + adjecentColumn);
			}
		
		}
	}

	public int getRemainingMineCount() {

		int remainingMineCount = getMineCount() - getNumberOf(Tile.State.MARKED);

		return remainingMineCount;
	}

}
