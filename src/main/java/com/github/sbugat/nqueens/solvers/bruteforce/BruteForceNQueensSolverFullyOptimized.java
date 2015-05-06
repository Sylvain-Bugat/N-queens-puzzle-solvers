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
public final class BruteForceNQueensSolverFullyOptimized extends GenericNQueensSolver {

	/** Array to count queens on each column. */
	private final int[] columnCounts;
	/** Array to count queens on each line. */
	private final int[] lineCounts;
	/** Array to count queens on ascending diagonals, diagonal number = x + y. */
	private final int[] ascendingDiagonalCounts;
	/** Array to count queens on descending diagonals, diagonal number = x + chess board size - 1 - y. */
	private final int[] descendingDiagonalCounts;

	private final int squareChessboardSizeMinusOne;

	/** Current number of placedQueens */
	private int placedQueens;

	public BruteForceNQueensSolverFullyOptimized(final int chessboardSizeArg) {

		super(chessboardSizeArg, false);

		squareChessboardSizeMinusOne = chessboardSizeArg * chessboardSizeArg - 1;

		columnCounts = new int[chessboardSizeArg];
		lineCounts = new int[chessboardSizeArg];
		ascendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
		descendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
	}

	@Override
	public long solve() {

		// Start the algorithm at the first position

		for (int i = 0; i < chessboardSize / 2; i++) {
			firstSolve(i);
		}
		solutionCount *= 2;

		if (chessboardSize % 2 != 0) {
			firstSolve(chessboardSize / 2 + 1);
		}

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * First line method to place a queen and test all possibilities on the next lines.
	 * 
	 * @param pos current position on the chessboard
	 */
	private void firstSolve(final int pos) {

		final int y = pos / chessboardSize;
		final int x = pos % chessboardSize;

		// Place a queen on the current position
		lineCounts[y]++;
		columnCounts[x]++;
		final int ascendingDiagnonal = x + y;
		final int descendingDiagnonal = x + chessboardSize - 1 - y;
		ascendingDiagonalCounts[ascendingDiagnonal]++;
		descendingDiagonalCounts[descendingDiagnonal]++;
		placedQueens++;

		solve(chessboardSize);

		// Remove the placed queen
		placedQueens--;
		ascendingDiagonalCounts[ascendingDiagnonal]--;
		descendingDiagonalCounts[descendingDiagnonal]--;
		lineCounts[y]--;
		columnCounts[x]--;
	}

	/**
	 * Solving recursive method, do a greedy algorithm by testing all combinations.
	 * 
	 * @param pos current position on the chessboard
	 */
	private void solve(final int pos) {

		final int y = pos / chessboardSize;
		final int x = pos % chessboardSize;
		// Place a queen on the current position
		lineCounts[y]++;
		columnCounts[x]++;
		final int ascendingDiagnonal = x + y;
		final int descendingDiagnonal = x + chessboardSize - 1 - y;
		ascendingDiagonalCounts[ascendingDiagnonal]++;
		descendingDiagonalCounts[descendingDiagnonal]++;
		placedQueens++;

		// All queens are sets on the chessboard then a solution may be present
		if (placedQueens >= chessboardSize) {

			boolean solution = true;
			// Check if 2 queens are on the same line and column
			for (int i = 0; i < columnCounts.length; i++) {

				if (columnCounts[i] > 1 || lineCounts[i] > 1) {
					solution = false;
					break;
				}
			}

			if (solution) {
				// Check if 2 queens are on the same column
				for (int i = 0; i < ascendingDiagonalCounts.length; i++) {

					if (ascendingDiagonalCounts[i] > 1 || descendingDiagonalCounts[i] > 1) {
						solution = false;
						break;
					}
				}

				if (solution) {
					solutionCount++;
				}
			}
		}
		else {

			if (pos < squareChessboardSizeMinusOne) {
				solve(pos + 1);
			}
		}

		// Remove the placed queen
		placedQueens--;
		ascendingDiagonalCounts[ascendingDiagnonal]--;
		descendingDiagonalCounts[descendingDiagnonal]--;
		lineCounts[y]--;
		columnCounts[x]--;

		if (pos < squareChessboardSizeMinusOne) {
			solve(pos + 1);
		}
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
		final BruteForceNQueensSolverFullyOptimized genericNQueensSolver = new BruteForceNQueensSolverFullyOptimized(chessboardSize);
		final long solutionCount = genericNQueensSolver.solve();

		// End of the algorithm print the total of solution(s) found
		System.out.println("\nTotal number of solution(s):" + solutionCount); //$NON-NLS-1$
		// Check if the number of solutions found is correct
		SequenceTools.checkSolutionsFound(chessboardSize, solutionCount);
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		throw new UnsupportedOperationException();
	}
}