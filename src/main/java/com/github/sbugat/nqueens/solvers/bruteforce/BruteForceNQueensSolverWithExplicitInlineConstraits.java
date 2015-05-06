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
public final class BruteForceNQueensSolverWithExplicitInlineConstraits extends GenericNQueensSolver {

	/** Chessboard used only to display a solution. */
	private final boolean[][] chessboard;
	/** Array to count queens on each column. */
	private final int[] columnCounts;
	/** Array to count queens on each line. */
	private final int[] lineCounts;
	/** Array to count queens on ascending diagonals, diagonal number = x + y. */
	private final int[] ascendingDiagonalCounts;
	/** Array to count queens on descending diagonals, diagonal number = x + chess board size - 1 - y. */
	private final int[] descendingDiagonalCounts;

	/** Current number of placedQueens */
	private int placedQueens;

	public BruteForceNQueensSolverWithExplicitInlineConstraits(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new boolean[chessboardSizeArg][chessboardSizeArg];
		columnCounts = new int[chessboardSizeArg];
		lineCounts = new int[chessboardSizeArg];
		ascendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
		descendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
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
					print();
				}
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

		// Remove the placed queen
		placedQueens--;
		ascendingDiagonalCounts[ascendingDiagnonal]--;
		descendingDiagonalCounts[descendingDiagnonal]--;
		lineCounts[y]--;
		columnCounts[x]--;
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
	 * Main greedy program.
	 * 
	 * @param args options
	 * @throws InvalidSolutionsException
	 */
	public static void main(final String args[]) throws InvalidSolutionsException {

		// Chessboard size (8 is quite long for this algorithm)
		final int chessboardSize = 7;

		// Instantiate adn run the greedy solver
		final BruteForceNQueensSolverWithExplicitInlineConstraits genericNQueensSolver = new BruteForceNQueensSolverWithExplicitInlineConstraits(chessboardSize, true);
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