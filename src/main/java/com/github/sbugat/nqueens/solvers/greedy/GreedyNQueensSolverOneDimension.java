package com.github.sbugat.nqueens.solvers.greedy;

import com.github.sbugat.nqueens.GenericNQueensSolver;
import com.github.sbugat.nqueens.tools.InvalidSolutionsException;
import com.github.sbugat.nqueens.tools.SequenceTools;

/**
 * Greedy algorithm for the N queens puzzle solver. This algorithm is not optimized at all and is a floor value for optimisations tests.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class GreedyNQueensSolverOneDimension extends GenericNQueensSolver {

	/** Chessboard only one one dimension with all lines. */
	private final boolean[] chessboard;
	/** Current number of placedQueens */
	private int placedQueens;

	public GreedyNQueensSolverOneDimension(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new boolean[chessboardSizeArg * chessboardSizeArg];
	}

	@Override
	public long solve() {

		// Start the algorithm at the first position
		solve(0);

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * Solving recursive method, do a greedy algorithm by testing all combinations.
	 * 
	 * @param i index of the unique dimension
	 */
	private void solve(final int i) {

		// Place a queen on the current position
		chessboard[i] = true;
		placedQueens++;

		// All queens are sets on the chessboard then a solution may be present
		if (placedQueens >= chessboardSize) {
			if (checkSolutionChessboard()) {
				solutionCount++;
				print();
			}
		}
		else {

			if (i + 1 < chessboard.length) {
				solve(i + 1);
			}
		}
		placedQueens--;
		chessboard[i] = false;

		if (i + 1 < chessboard.length) {
			solve(i + 1);
		}
	}

	/**
	 * Check if a chessboard with N queens is a solution (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard contain a solution, false otherwise
	 */
	private boolean checkSolutionChessboard() {

		// Check if 2 queens are on the same line
		for (int y = 0; y < chessboard.length; y += chessboardSize) {

			boolean usedLine = false;
			for (int x = y; x < y + chessboardSize; x++) {

				if (chessboard[x]) {
					if (usedLine) {
						return false;
					}
					else {
						usedLine = true;
					}
				}
			}
		}

		// Check if 2 queens are on the same column
		for (int x = 0; x < chessboardSize; x++) {

			boolean usedColumn = false;
			for (int i = x; i < chessboard.length; i += chessboardSize) {

				if (chessboard[i]) {
					if (usedColumn) {
						return false;
					}
					else {
						usedColumn = true;
					}
				}
			}
		}

		// Check if 2 queens are on the same descending diagonal
		for (int diagonal = 0; diagonal < chessboardSize * 2 - 1; diagonal++) {

			boolean usedDiagonal = false;

			for (int y = 0; y < chessboardSize; y++) {

				final int x = diagonal - y;

				if (x >= 0 && x < chessboardSize) {

					if (chessboard[y * chessboardSize + x]) {
						if (usedDiagonal) {
							return false;
						}
						else {
							usedDiagonal = true;
						}
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

					if (chessboard[y * chessboardSize + x]) {
						if (usedDiagonal) {
							return false;
						}
						else {
							usedDiagonal = true;
						}
					}
				}
			}
		}

		return true;
	}

	/**
	 * Main greedy program.
	 * 
	 * @param args options
	 * @throws InvalidSolutionsException
	 */
	public static void main(final String args[]) throws InvalidSolutionsException {

		// Chessboard size (8 is quite long for this algorithm)
		final int chessboardSize = 8;

		// Instantiate adn run the greedy solver
		final GreedyNQueensSolverOneDimension genericNQueensSolver = new GreedyNQueensSolverOneDimension(chessboardSize, true);
		final long solutionCount = genericNQueensSolver.solve();

		// End of the algorithm print the total of solution(s) found
		System.out.println("\nTotal number of solution(s):" + solutionCount); //$NON-NLS-1$
		// Check if the number of solutions found is correct
		SequenceTools.checkSolutionsFound(chessboardSize, solutionCount);
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard[y * chessboardSize + x];
	}
}
