package com.github.sbugat.nqueens.solvers.experimental;

import java.util.ArrayList;
import java.util.List;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BackTrackingNQueensSolver extends GenericNQueensSolver {

	/** Chessboard represented by a list of lists. */
	private List<List<Integer>> chessboard;
	/** Array to count queens on each column. */
	private List<Integer> columnCounts;
	/** Array to count queens on ascending diagonals, diagonal number = x + y. */
	private List<Integer> ascendingDiagonalCounts;
	/** Array to count queens on descending diagonals, diagonal number = x + chess board size - 1 - y. */
	private List<Integer> descendingDiagonalCounts;

	public BackTrackingNQueensSolver(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new ArrayList<>();
		columnCounts = new ArrayList<>();
		for (int x = 0; x < chessboardSizeArg; x++) {
			final List<Integer> lineList = new ArrayList<>();
			for (int y = 0; y < chessboardSizeArg; y++) {
				lineList.add(Integer.valueOf(0));
			}
			chessboard.add(lineList);
			columnCounts.add(Integer.valueOf(0));
		}

		ascendingDiagonalCounts = new ArrayList<>();
		descendingDiagonalCounts = new ArrayList<>();

		for (int i = 0; i < chessboardSizeArg * 2 - 1; i++) {
			ascendingDiagonalCounts.add(Integer.valueOf(0));
			descendingDiagonalCounts.add(Integer.valueOf(0));
		}
	}

	@Override
	public long solve() {

		// Start the algorithm at the first line
		solve(0);

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * Solving recursive method, do a brute-force algorithm by testing all combinations.
	 * 
	 * @param y line position on the chessboard
	 */
	private void solve(final int y) {

		for (int x = 0; x < chessboardSize; x++) {

			if (columnCounts.get(x).intValue() == 0) {
				final int ascendingDiagnonal = x + y;
				final int descendingDiagnonal = x + chessboardSize - 1 - y;
				if (ascendingDiagonalCounts.get(ascendingDiagnonal).intValue() == 0 && descendingDiagonalCounts.get(descendingDiagnonal).intValue() == 0) {
					// Put a queen on the current position
					chessboard.get(x).set(y, Integer.valueOf(1));
					columnCounts.set(x, Integer.valueOf(columnCounts.get(x).intValue() + 1));
					ascendingDiagonalCounts.set(ascendingDiagnonal, Integer.valueOf(ascendingDiagonalCounts.get(ascendingDiagnonal).intValue() + 1));
					descendingDiagonalCounts.set(descendingDiagnonal, Integer.valueOf(descendingDiagonalCounts.get(descendingDiagnonal).intValue() + 1));

					// Last line: all queens are sets then a solution may be present
					if (y + 1 >= chessboardSize) {
						if (checkSolutionChessboard()) {
							solutionCount++;
							print();
						}
					}
					else {
						// Go to the next line
						solve(y + 1);
					}

					// Remove the placed queen
					ascendingDiagonalCounts.set(ascendingDiagnonal, Integer.valueOf(ascendingDiagonalCounts.get(ascendingDiagnonal).intValue() - 1));
					descendingDiagonalCounts.set(descendingDiagnonal, Integer.valueOf(descendingDiagonalCounts.get(descendingDiagnonal).intValue() - 1));
					columnCounts.set(x, Integer.valueOf(columnCounts.get(x).intValue() - 1));
					chessboard.get(x).set(y, Integer.valueOf(0));
				}
			}
		}
	}

	/**
	 * Check if a chessboard with N queens is a solution (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard contain a solution, false otherwise
	 */
	private boolean checkSolutionChessboard() {

		// Check if 2 queens are on the same line and column
		for (final Integer value : columnCounts) {
			if (value.intValue() > 1) {
				return false;
			}
		}

		// Check if 2 queens are on the same column
		for (final Integer value : ascendingDiagonalCounts) {
			if (value.intValue() > 1) {
				return false;
			}
		}
		for (final Integer value : descendingDiagonalCounts) {
			if (value.intValue() > 1) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void reset() {

		super.reset();

		if (chessboard.size() != chessboardSize) {
			chessboard = new ArrayList<>();
			columnCounts = new ArrayList<>();
			for (int x = 0; x < chessboardSize; x++) {
				final List<Integer> lineList = new ArrayList<>();
				for (int y = 0; y < chessboardSize; y++) {
					lineList.add(Integer.valueOf(0));
				}
				chessboard.add(lineList);
				columnCounts.add(Integer.valueOf(0));
			}

			ascendingDiagonalCounts = new ArrayList<>();
			descendingDiagonalCounts = new ArrayList<>();

			for (int i = 0; i < chessboardSize * 2 - 1; i++) {
				ascendingDiagonalCounts.add(Integer.valueOf(0));
				descendingDiagonalCounts.add(Integer.valueOf(0));
			}
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return 1 == chessboard.get(x).get(y).intValue();
	}
}
