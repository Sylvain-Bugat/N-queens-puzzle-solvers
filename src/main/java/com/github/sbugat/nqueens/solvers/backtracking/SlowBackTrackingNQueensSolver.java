package com.github.sbugat.nqueens.solvers.backtracking;

import java.util.ArrayList;
import java.util.List;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class SlowBackTrackingNQueensSolver extends GenericNQueensSolver {

	/** Chessboard represented by a list of lists. */
	private List<List<Integer>> chessboard;

	public SlowBackTrackingNQueensSolver(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new ArrayList<>();
		for (int x = 0; x < chessboardSizeArg; x++) {
			final List<Integer> lineList = new ArrayList<>();
			for (int y = 0; y < chessboardSizeArg; y++) {
				lineList.add(Integer.valueOf(0));
			}
			chessboard.add(lineList);
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

			// Put a queen on the current position
			chessboard.get(x).set(y, Integer.valueOf(1));

			if (checkValidChessboard()) {
				// Last line: all queens are sets then a solution is found present
				if (y + 1 >= chessboardSize) {
					solutionCount++;
					print();
				}
				else {
					// Go to the next line
					solve(y + 1);
				}
			}

			// Remove the current queen
			chessboard.get(x).set(y, Integer.valueOf(0));
		}
	}

	/**
	 * Check if the current chessboard is valid (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard is valid, false otherwise
	 */
	private boolean checkValidChessboard() {

		// Check if 2 queens are on the same line
		for (int y = 0; y < chessboardSize; y++) {

			boolean usedLine = false;
			for (int x = 0; x < chessboardSize; x++) {

				if (1 == chessboard.get(x).get(y).intValue()) {
					if (usedLine) {
						return false;
					}
					usedLine = true;
				}
			}
		}

		// Check if 2 queens are on the same column
		for (int x = 0; x < chessboardSize; x++) {

			boolean usedColumn = false;
			for (int y = 0; y < chessboardSize; y++) {

				if (1 == chessboard.get(x).get(y).intValue()) {
					if (usedColumn) {
						return false;
					}
					usedColumn = true;
				}
			}
		}

		// Check if 2 queens are on the same descending diagonal
		for (int diagonal = 0; diagonal < chessboardSize * 2 - 1; diagonal++) {

			boolean usedDiagonal = false;

			for (int y = 0; y < chessboardSize; y++) {

				final int x = diagonal - y;

				if (x >= 0 && x < chessboardSize) {

					if (1 == chessboard.get(x).get(y).intValue()) {
						if (usedDiagonal) {
							return false;
						}
						usedDiagonal = true;
					}
				}
			}
		}

		// Check if 2 queens are on the same ascending diagonal
		for (int diagonal = 0; diagonal < chessboardSize * 2 - 1; diagonal++) {

			boolean usedDiagonal = false;

			for (int y = 0; y < chessboardSize; y++) {

				final int x = diagonal - chessboardSize + 1 + y;

				if (x >= 0 && x < chessboardSize) {

					if (1 == chessboard.get(x).get(y).intValue()) {
						if (usedDiagonal) {
							return false;
						}
						usedDiagonal = true;
					}
				}
			}
		}

		return true;
	}

	@Override
	public void reset() {

		super.reset();

		if (chessboard.size() != chessboardSize) {
			chessboard = new ArrayList<>();
			for (int x = 0; x < chessboardSize; x++) {
				final List<Integer> lineList = new ArrayList<>();
				for (int y = 0; y < chessboardSize; y++) {
					lineList.add(Integer.valueOf(0));
				}
				chessboard.add(lineList);
			}
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return 1 == chessboard.get(x).get(y).intValue();
	}
}
