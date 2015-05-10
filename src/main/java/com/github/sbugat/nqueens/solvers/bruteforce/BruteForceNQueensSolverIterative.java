package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverIterative extends GenericNQueensSolver {

	/** Chessboard represented by a 2 dimensional array. */
	private boolean[][] chessboard;
	/** Array to count queens on each column. */
	private int[] columnCounts;
	/** Array to count queens on ascending diagonals, diagonal number = x + y. */
	private int[] ascendingDiagonalCounts;
	/** Array to count queens on descending diagonals, diagonal number = x + chess board size - 1 - y. */
	private int[] descendingDiagonalCounts;

	/** Stack for queens positions. */
	private int[] xStack;
	/** Stack for ascending diagonal numbers. */
	private int[] ascendingDiagonalStack;
	/** Stack for descending diagonal numbers. */
	private int[] descendingDiagonalStack;

	public BruteForceNQueensSolverIterative(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new boolean[chessboardSizeArg][chessboardSizeArg];
		columnCounts = new int[chessboardSizeArg];
		ascendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
		descendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];

		xStack = new int[chessboardSizeArg];
		ascendingDiagonalStack = new int[chessboardSizeArg];
		descendingDiagonalStack = new int[chessboardSizeArg];
	}

	@Override
	public long solve() {

		// Start the algorithm at the first line
		solveIterative();

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * Solving iterative method, do a brute-force algorithm by testing all combinations.
	 * 
	 */
	private void solveIterative() {

		int yStackLevel = 0;

		int x = -1;
		while (true) {

			// Test all position of the line
			x++;

			// Put a queen on the current position
			chessboard[yStackLevel][x] = true;
			columnCounts[x]++;
			final int ascendingDiagnonal = x + yStackLevel;
			final int descendingDiagnonal = x + chessboardSize - 1 - yStackLevel;
			ascendingDiagonalCounts[ascendingDiagnonal]++;
			descendingDiagonalCounts[descendingDiagnonal]++;

			// Stack a move
			xStack[yStackLevel] = x;
			ascendingDiagonalStack[yStackLevel] = ascendingDiagnonal;
			descendingDiagonalStack[yStackLevel] = descendingDiagnonal;

			yStackLevel++;

			// Last line: all queens are sets then a solution may be present
			if (yStackLevel >= chessboardSize) {

				if (checkSolutionChessboard()) {
					solutionCount++;
					print();
				}

				// Force unstack
				x = chessboardSize;
			}
			else {
				// Go to the next line
				x = -1;
			}

			// Back-tracking loop
			while (x >= chessboardSize - 1) {

				// Unstack if all line positation are tested
				yStackLevel--;
				if (yStackLevel >= 0) {
					ascendingDiagonalCounts[ascendingDiagonalStack[yStackLevel]]--;
					descendingDiagonalCounts[descendingDiagonalStack[yStackLevel]]--;
					x = xStack[yStackLevel];
					columnCounts[x]--;
					chessboard[yStackLevel][x] = false;
				}
				// Nothing to unstack, then exit
				else {
					return;
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
		for (int x = 0; x < columnCounts.length; x++) {

			if (columnCounts[x] > 1) {
				return false;
			}
		}

		// Check if 2 queens are on the same column
		for (int i = 0; i < ascendingDiagonalCounts.length; i++) {

			if (ascendingDiagonalCounts[i] > 1 || descendingDiagonalCounts[i] > 1) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void reset() {

		super.reset();

		if (chessboard.length != chessboardSize) {

			chessboard = new boolean[chessboardSize][chessboardSize];
			columnCounts = new int[chessboardSize];
			ascendingDiagonalCounts = new int[chessboardSize * 2 - 1];
			descendingDiagonalCounts = new int[chessboardSize * 2 - 1];

			xStack = new int[chessboardSize];
			ascendingDiagonalStack = new int[chessboardSize];
			descendingDiagonalStack = new int[chessboardSize];
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard[y][x];
	}
}
