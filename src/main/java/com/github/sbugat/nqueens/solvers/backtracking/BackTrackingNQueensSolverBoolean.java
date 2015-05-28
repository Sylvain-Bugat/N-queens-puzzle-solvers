package com.github.sbugat.nqueens.solvers.backtracking;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Back-tracking algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BackTrackingNQueensSolverBoolean extends GenericNQueensSolver {

	/** Chessboard represented by a 2 dimensional array. */
	private boolean[][] chessboard;
	/** Array to count queens on each column. */
	private boolean[] columnCounts;
	/** Array to count queens on ascending diagonals, diagonal number = x + y. */
	private boolean[] ascendingDiagonalCounts;
	/** Array to count queens on descending diagonals, diagonal number = x + chessboard size - 1 - y. */
	private boolean[] descendingDiagonalCounts;

	public BackTrackingNQueensSolverBoolean(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new boolean[chessboardSizeArg][chessboardSizeArg];
		columnCounts = new boolean[chessboardSizeArg];
		ascendingDiagonalCounts = new boolean[chessboardSizeArg * 2 - 1];
		descendingDiagonalCounts = new boolean[chessboardSizeArg * 2 - 1];
	}

	@Override
	public long solve() {

		// Start the algorithm at the first line
		solve(0);

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * Solving recursive method, do a back-tracking algorithm by testing all valid combinations.
	 * 
	 * @param y line position on the chessboard
	 */
	private void solve(final int y) {

		for (int x = 0; x < chessboardSize; x++) {

			// Check if a queen is already placed on current column or diagonals
			if (!columnCounts[x] && !ascendingDiagonalCounts[x + y] && !descendingDiagonalCounts[x + chessboardSize - 1 - y]) {

				// Put a queen on the current position
				chessboard[y][x] = true;
				columnCounts[x] = true;
				ascendingDiagonalCounts[x + y] = true;
				descendingDiagonalCounts[x + chessboardSize - 1 - y] = true;

				// Last line: all queens are sets then a solution is found present
				if (y + 1 >= chessboardSize) {
					solutionCount++;
					print();
				}
				else {
					// Go to the next line
					solve(y + 1);
				}

				// Remove the current queen
				ascendingDiagonalCounts[x + y] = false;
				descendingDiagonalCounts[x + chessboardSize - 1 - y] = false;
				columnCounts[x] = false;
				chessboard[y][x] = false;
			}
		}
	}

	@Override
	public void reset() {

		super.reset();

		if (chessboard.length != chessboardSize) {

			chessboard = new boolean[chessboardSize][chessboardSize];
			columnCounts = new boolean[chessboardSize];
			ascendingDiagonalCounts = new boolean[chessboardSize * 2 - 1];
			descendingDiagonalCounts = new boolean[chessboardSize * 2 - 1];
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard[y][x];
	}
}
