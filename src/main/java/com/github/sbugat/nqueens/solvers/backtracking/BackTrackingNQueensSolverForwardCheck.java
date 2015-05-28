package com.github.sbugat.nqueens.solvers.backtracking;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Back-tracking algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BackTrackingNQueensSolverForwardCheck extends GenericNQueensSolver {

	/** Chessboard represented by a 2 dimensional array. */
	private int[][] chessboard;
	/** Array to count queens on each column. */
	private int[] columnCounts;
	/** Array to count queens on ascending diagonals, diagonal number = x + y. */
	private int[] ascendingDiagonalCounts;
	/** Array to count queens on descending diagonals, diagonal number = x + chessboard size - 1 - y. */
	private int[] descendingDiagonalCounts;

	public BackTrackingNQueensSolverForwardCheck(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new int[chessboardSizeArg][chessboardSizeArg];
		columnCounts = new int[chessboardSizeArg];
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
	 * Solving recursive method, do a back-tracking algorithm by testing all valid combinations.
	 * 
	 * @param y line position on the chessboard
	 */
	private void solve(final int y) {

		for (int x = 0; x < chessboardSize; x++) {

			// Check if a queen is already placed on current column or diagonals
			if (0 == columnCounts[x] && 0 == ascendingDiagonalCounts[x + y] && 0 == descendingDiagonalCounts[x + chessboardSize - 1 - y]) {

				// Put a queen on the current position
				chessboard[y][x] = 1;
				columnCounts[x]++;
				ascendingDiagonalCounts[x + y]++;
				descendingDiagonalCounts[x + chessboardSize - 1 - y]++;

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
				ascendingDiagonalCounts[x + y]--;
				descendingDiagonalCounts[x + chessboardSize - 1 - y]--;
				columnCounts[x]--;
				chessboard[y][x] = 0;
			}
		}
	}

	@Override
	public void reset() {

		super.reset();

		if (chessboard.length != chessboardSize) {

			chessboard = new int[chessboardSize][chessboardSize];
			columnCounts = new int[chessboardSize];
			ascendingDiagonalCounts = new int[chessboardSize * 2 - 1];
			descendingDiagonalCounts = new int[chessboardSize * 2 - 1];
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return 1 == chessboard[y][x];
	}
}
