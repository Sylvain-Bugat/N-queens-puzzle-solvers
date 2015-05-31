package com.github.sbugat.nqueens.solvers.backtracking.instrumentations;

import java.util.ArrayList;
import java.util.List;

import com.github.sbugat.nqueens.GenericInstrumentedNQueensSolver;

/**
 * Back-tracking algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class SlowBackTrackingNQueensSolver extends GenericInstrumentedNQueensSolver {

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
		methodCallsCount++;
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

		implicitTestsCount++;
		for (int x = 0; x < chessboardSize; x++) {

			// Put a queen on the current position
			queenPlacementsCount++;
			methodCallsCount += 3;
			squareWritesCount++;
			chessboard.get(x).set(y, Integer.valueOf(1));

			methodCallsCount++;
			implicitTestsCount++;
			if (checkValidChessboard()) {

				// Last line: all queens are sets then a solution is found present
				implicitTestsCount++;
				if (y + 1 >= chessboardSize) {
					solutionCount++;

					methodCallsCount++;
					print();
				}
				else {
					// Go to the next line
					methodCallsCount++;
					solve(y + 1);
				}
			}

			// Remove the current queen
			methodCallsCount += 3;
			squareWritesCount++;
			chessboard.get(x).set(y, Integer.valueOf(0));

			implicitTestsCount++;
		}
	}

	/**
	 * Check if the current chessboard is valid (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard is valid, false otherwise
	 */
	private boolean checkValidChessboard() {

		// Check if 2 queens are on the same line
		implicitTestsCount++;
		for (int y = 0; y < chessboardSize; y++) {

			boolean usedLine = false;
			implicitTestsCount++;
			for (int x = 0; x < chessboardSize; x++) {

				explicitTestsCount++;
				methodCallsCount += 3;
				squareReadsCount++;
				if (1 == chessboard.get(x).get(y).intValue()) {
					explicitTestsCount++;
					if (usedLine) {
						return false;
					}
					usedLine = true;
				}

				implicitTestsCount++;
			}
			implicitTestsCount++;
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
				if (1 == chessboard.get(x).get(y).intValue()) {
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
						if (1 == chessboard.get(x).get(y).intValue()) {
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
						if (1 == chessboard.get(x).get(y).intValue()) {

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

	@Override
	public String getName() {
		return "List back-tracking";
	}
}
