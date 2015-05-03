package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolver;
import com.github.sbugat.nqueens.tools.InvalidSolutionsException;
import com.github.sbugat.nqueens.tools.SequenceTools;

/**
 * Greedy algorithm for the N queens puzzle solver. This algorithm is not optimized at all and is a floor value for optimisations tests.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolver extends GenericNQueensSolver {

	/** Chessboard used only to display a solution. */
	private final boolean[][] chessboard;
	/** Current number of placedQueens */
	private int placedQueens;

	public BruteForceNQueensSolver(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new boolean[chessboardSizeArg][chessboardSizeArg];
	}

	@Override
	public long solve() {

		// Start the algorithm at the first position
		solve(0, 0);

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * Solving recursive method, do a greedy algorithm by testing all combinations.
	 * 
	 * @param y number of the line stating at 0
	 */
	private void solve(final int x, final int y) {

		// Place a queen on the current position
		chessboard[y][x] = true;
		placedQueens++;

		// All queens are sets on the chessboard then a solution may be present
		if (placedQueens >= chessboardSize) {
			if (checkSolutionChessboard()) {
				solutionCount++;
				print();
			}
		}
		else {

			final int nextX = (x + 1) % chessboardSize;
			if (0 == nextX) {

				if (y + 1 < chessboardSize) {
					solve(nextX, y + 1);
				}
			}
			else {
				solve(nextX, y);
			}
		}
		placedQueens--;
		chessboard[y][x] = false;

		final int nextX = (x + 1) % chessboardSize;
		if (0 == nextX) {

			if (y + 1 < chessboardSize) {
				solve(nextX, y + 1);
			}
		}
		else {
			solve(nextX, y);
		}
	}

	/**
	 * Check if a chessboard with N queens is a solution (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard contain a solution, false otherwise
	 */
	private boolean checkSolutionChessboard() {

		// Check if 2 queens are on the same line
		for (int y = 0; y < chessboardSize; y++) {

			boolean usedLine = false;
			for (int x = 0; x < chessboardSize; x++) {

				if (chessboard[y][x]) {
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
			for (int y = 0; y < chessboardSize; y++) {

				if (chessboard[y][x]) {
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

					if (chessboard[y][x]) {
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

					if (chessboard[y][x]) {
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
		final BruteForceNQueensSolver genericNQueensSolver = new BruteForceNQueensSolver(chessboardSize, true);
		final long solutionCount = genericNQueensSolver.solve();

		// End of the algorithm print the total of solution(s) found
		System.out.println("\nTotal number of solution(s):" + solutionCount); //$NON-NLS-1$
		// Check if the number of solutions found is correct
		SequenceTools.checkSolutionsFound(chessboardSize, solutionCount);
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard[y][x];
	}
}
