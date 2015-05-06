package com.github.sbugat.nqueens.solvers.bruteforce;

import java.util.ArrayList;
import java.util.List;

import com.github.sbugat.nqueens.GenericNQueensSolver;
import com.github.sbugat.nqueens.tools.InvalidSolutionsException;
import com.github.sbugat.nqueens.tools.SequenceTools;

/**
 * Brute-force algorithm for the N queens puzzle solver with a variable containing the queen number constraint.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverWithLists extends GenericNQueensSolver {

	/** Chessboard used only to display a solution. */
	private final List<List<Boolean>> chessboard;
	/** Current number of queens. */
	private int placedQueens;

	public BruteForceNQueensSolverWithLists(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new ArrayList<>();
		for (int x = 0; x < chessboardSizeArg; x++) {
			final List<Boolean> lineList = new ArrayList<>();
			for (int y = 0; y < chessboardSizeArg; y++) {
				lineList.add(Boolean.FALSE);
			}
			chessboard.add(lineList);
		}
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
	 * @param x X position on the chessboard
	 * @param y Y position on the chessboard
	 */
	private void solve(final int x, final int y) {

		// Put a queen on the current position
		chessboard.get(x).set(y, Boolean.TRUE);
		placedQueens++;

		// All queens are sets on the chessboard then a solution may be present
		if (placedQueens >= chessboardSize) {
			if (checkSolutionChessboard()) {
				solutionCount++;
				print();
			}
		}
		else {

			// Recursive call to the next position
			final int nextX = (x + 1) % chessboardSize;
			// Switch to the next line
			if (0 == nextX) {

				// End of the chessboard check
				if (y + 1 < chessboardSize) {
					solve(nextX, y + 1);
				}
			}
			else {
				solve(nextX, y);
			}
		}

		// Remove the queen on the current position
		placedQueens--;
		chessboard.get(x).set(y, Boolean.FALSE);

		// Recursive call to the next position
		final int nextX = (x + 1) % chessboardSize;
		// Switch to the next line
		if (0 == nextX) {

			// End of the chessboard check
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

				if (chessboard.get(x).get(y).booleanValue()) {
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

				if (chessboard.get(x).get(y).booleanValue()) {
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

					if (chessboard.get(x).get(y).booleanValue()) {
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

					if (chessboard.get(x).get(y).booleanValue()) {
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
		final BruteForceNQueensSolverWithLists genericNQueensSolver = new BruteForceNQueensSolverWithLists(chessboardSize, true);
		final long solutionCount = genericNQueensSolver.solve();

		// End of the algorithm print the total of solution(s) found
		System.out.println("\nTotal number of solution(s):" + solutionCount); //$NON-NLS-1$
		// Check if the number of solutions found is correct
		SequenceTools.checkSolutionsFound(chessboardSize, solutionCount);
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard.get(x).get(y).booleanValue();
	}
}
