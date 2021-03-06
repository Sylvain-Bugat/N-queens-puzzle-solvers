package com.github.sbugat.nqueens.solvers.bruteforce.instrumentations;

import java.util.ArrayList;
import java.util.List;

import com.github.sbugat.nqueens.GenericInstrumentedNQueensSolver;

/**
 * Slow brute-force algorithm for the N queens puzzle solver with a queen limit of N (size of the chessboard).
 * 
 * @author Sylvain Bugat
 * 
 */
public final class SlowBruteForceNQueensSolverWithLists extends GenericInstrumentedNQueensSolver {

	/** Chessboard represented by a list of lists. */
	private final List<List<Boolean>> chessboard;

	public SlowBruteForceNQueensSolverWithLists(final int chessboardSizeArg, final boolean printSolutionArg) {

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
		methodCallsCount++;
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
		queenPlacementsCount++;
		methodCallsCount += 2;
		squareWritesCount++;
		chessboard.get(x).set(y, Boolean.TRUE);

		// All queens are sets on the chessboard then a solution may be present
		explicitTestsCount++;
		methodCallsCount++;
		if (getPlacedQueens() >= chessboardSize) {
			explicitTestsCount++;
			methodCallsCount++;
			if (checkSolutionChessboard()) {
				solutionCount++;
				methodCallsCount++;
				print();
			}
		}
		else {

			// Recursive call to the next position
			final int nextX = (x + 1) % chessboardSize;
			// Switch to the next line
			explicitTestsCount++;
			if (0 == nextX) {

				// End of the chessboard check
				explicitTestsCount++;
				if (y + 1 < chessboardSize) {
					methodCallsCount++;
					solve(nextX, y + 1);
				}
			}
			else {
				methodCallsCount++;
				solve(nextX, y);
			}
		}

		// Remove the queen on the current position
		methodCallsCount += 2;
		squareWritesCount++;
		chessboard.get(x).set(y, Boolean.FALSE);

		// Recursive call to the next position
		final int nextX = (x + 1) % chessboardSize;
		// Switch to the next line
		explicitTestsCount++;
		if (0 == nextX) {

			// End of the chessboard check
			explicitTestsCount++;
			if (y + 1 < chessboardSize) {
				methodCallsCount++;
				solve(nextX, y + 1);
			}
		}
		else {
			methodCallsCount++;
			solve(nextX, y);
		}
	}

	/**
	 * Count the number of queens on the chessboard.
	 * 
	 * @return the number of currently place queens
	 */
	private int getPlacedQueens() {

		int placedQueens = 0;

		// Count all queens on the chessboard
		implicitTestsCount++;
		for (int x = 0; x < chessboardSize; x++) {

			implicitTestsCount++;
			for (int y = 0; y < chessboardSize; y++) {

				explicitTestsCount++;
				methodCallsCount += 3;
				squareReadsCount++;
				if (chessboard.get(x).get(y).booleanValue()) {
					placedQueens++;
				}

				implicitTestsCount++;
			}

			implicitTestsCount++;
		}
		return placedQueens;
	}

	/**
	 * Check if a chessboard with N queens is a solution (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard contain a solution, false otherwise
	 */
	private boolean checkSolutionChessboard() {

		// Check if 2 queens are on the same line
		implicitTestsCount++;
		for (int y = 0; y < chessboardSize; y++) {

			boolean usedLine = false;
			implicitTestsCount++;
			for (int x = 0; x < chessboardSize; x++) {

				explicitTestsCount++;
				methodCallsCount += 3;
				squareReadsCount++;
				if (chessboard.get(x).get(y).booleanValue()) {
					explicitTestsCount++;
					if (usedLine) {
						return false;
					}
					usedLine = true;
				}

				implicitTestsCount++;
			}

			implicitTestsCount++;
		}

		// Check if 2 queens are on the same column
		implicitTestsCount++;
		for (int x = 0; x < chessboardSize; x++) {

			boolean usedColumn = false;
			implicitTestsCount++;
			for (int y = 0; y < chessboardSize; y++) {

				explicitTestsCount++;
				methodCallsCount += 3;
				squareReadsCount++;
				if (chessboard.get(x).get(y).booleanValue()) {
					explicitTestsCount++;
					if (usedColumn) {
						return false;
					}
					usedColumn = true;
				}

				implicitTestsCount++;
			}

			implicitTestsCount++;
		}

		// Check if 2 queens are on the same descending diagonal
		implicitTestsCount++;
		for (int diagonal = 0; diagonal < chessboardSize * 2 - 1; diagonal++) {
			boolean usedDiagonal = false;
			implicitTestsCount++;
			for (int y = 0; y < chessboardSize; y++) {
				final int x = diagonal - y;
				explicitTestsCount++;
				if (x >= 0) {
					explicitTestsCount++;
					if (x < chessboardSize) {
						explicitTestsCount++;
						methodCallsCount += 3;
						squareReadsCount++;
						if (chessboard.get(x).get(y).booleanValue()) {
							explicitTestsCount++;
							if (usedDiagonal) {
								return false;
							}
							usedDiagonal = true;
						}
					}
				}

				implicitTestsCount++;
			}

			implicitTestsCount++;
		}

		// Check if 2 queens are on the same ascending diagonal
		implicitTestsCount++;
		for (int diagonal = 0; diagonal < chessboardSize * 2 - 1; diagonal++) {
			boolean usedDiagonal = false;
			implicitTestsCount++;
			for (int y = 0; y < chessboardSize; y++) {
				final int x = diagonal - chessboardSize + 1 + y;
				explicitTestsCount++;
				if (x >= 0) {
					explicitTestsCount++;
					if (x < chessboardSize) {
						explicitTestsCount++;
						methodCallsCount += 3;
						squareReadsCount++;
						if (chessboard.get(x).get(y).booleanValue()) {

							explicitTestsCount++;
							if (usedDiagonal) {
								return false;
							}
							usedDiagonal = true;
						}
					}
				}

				implicitTestsCount++;
			}

			implicitTestsCount++;
		}

		return true;
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard.get(x).get(y).booleanValue();
	}

	@Override
	public String getName() {
		return "List brute-force";
	}
}
