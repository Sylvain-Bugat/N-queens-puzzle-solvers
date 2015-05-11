package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverColumnBitFlags extends GenericNQueensSolver {

	/** Chessboard represented by a 2 dimensional array. */
	private boolean[][] chessboard;
	/** Bit-flags to set queens on each column. */
	private int columnFlags;
	/** Array to count queens on ascending diagonals, diagonal number = x + y. */
	private int[] ascendingDiagonalCounts;
	/** Array to count queens on descending diagonals, diagonal number = x + chess board size - 1 - y. */
	private int[] descendingDiagonalCounts;

	public BruteForceNQueensSolverColumnBitFlags(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new boolean[chessboardSizeArg][chessboardSizeArg];
		ascendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
		descendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
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
			chessboard[y][x] = true;
			final int saveColumnFlags = columnFlags;
			columnFlags |= 1 << x;
			final int ascendingDiagnonal = x + y;
			final int descendingDiagnonal = x + chessboardSize - 1 - y;
			ascendingDiagonalCounts[ascendingDiagnonal]++;
			descendingDiagonalCounts[descendingDiagnonal]++;

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
			ascendingDiagonalCounts[ascendingDiagnonal]--;
			descendingDiagonalCounts[descendingDiagnonal]--;
			columnFlags = saveColumnFlags;
			chessboard[y][x] = false;
		}
	}

	/**
	 * Check if a chessboard with N queens is a solution (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard contain a solution, false otherwise
	 */
	private boolean checkSolutionChessboard() {

		// Check if 2 queens are on the same line and column
		for (int x = 0; x < chessboardSize; x++) {

			if ((columnFlags & 1 << x) == 0) {
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
			columnFlags = 0;
			ascendingDiagonalCounts = new int[chessboardSize * 2 - 1];
			descendingDiagonalCounts = new int[chessboardSize * 2 - 1];
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard[y][x];
	}
}
