package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverHalfFirstLine extends GenericNQueensSolver {

	/** Chessboard represented by a one-dimensional array of bit-flags. */
	private int[] chessboard;
	/** Bit-flags to set queens on each column. */
	private int columnFlags;
	/** Bit-flags to set queens on ascending diagonals, diagonal number = x + y. */
	private long ascendingDiagonalFlags;
	/** Bit-flags to set queens on descending diagonals, diagonal number = x + chess board size - 1 - y. */
	private long descendingDiagonalFlags;

	public BruteForceNQueensSolverHalfFirstLine(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		// Check chessboard size constraint
		if (chessboardSizeArg > Integer.SIZE && chessboardSizeArg * 2 - 1 > Long.SIZE) {
			throw new IllegalArgumentException("Invalid chessboard size: " + chessboardSizeArg + " upper limits are (int size):" + Integer.SIZE + " and (long size):" + Long.SIZE);
		}

		chessboard = new int[chessboardSizeArg];
	}

	@Override
	public long solve() {

		// Start the algorithm at the first line
		startSolve();

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * Solving initialization method, place a queen on only half position of the first line.
	 */
	private void startSolve() {

		// chessboard size 1 bypass
		if (1 == chessboardSize) {
			// Start the algorithm at the first line
			solve(0);
			return;
		}

		// Test half square of the line
		for (int x = 0; x < chessboardSize / 2; x++) {

			// Put a queen on the current position
			final int queenFlag = 1 << x;
			chessboard[0] |= queenFlag;
			final int saveColumnFlags = columnFlags;
			columnFlags |= queenFlag;
			final long ascendingDiagonalFlagsSave = ascendingDiagonalFlags;
			final long descendingDiagonalFlagsSave = descendingDiagonalFlags;
			ascendingDiagonalFlags |= 1 << x;
			descendingDiagonalFlags |= 1 << x + chessboardSize - 1;

			// Go to the second line
			solve(1);

			// Remove the placed queen
			ascendingDiagonalFlags = ascendingDiagonalFlagsSave;
			descendingDiagonalFlags = descendingDiagonalFlagsSave;
			columnFlags = saveColumnFlags;
			chessboard[0] ^= queenFlag;
		}

		// Multiply by 2 the solution count, the other half is not solved
		solutionCount *= 2;

		// If the cheesboard size is odd, test with a queen on the middle of the first line
		if (0 != chessboardSize % 2) {

			final int x = chessboardSize / 2;

			// Put a queen on the current position
			final int queenFlag = 1 << x;
			chessboard[0] |= queenFlag;
			final int saveColumnFlags = columnFlags;
			columnFlags |= queenFlag;
			final long ascendingDiagonalFlagsSave = ascendingDiagonalFlags;
			final long descendingDiagonalFlagsSave = descendingDiagonalFlags;
			ascendingDiagonalFlags |= 1 << x;
			descendingDiagonalFlags |= 1 << x + chessboardSize - 1;

			// Go to the second line
			solve(1);

			// Remove the placed queen
			ascendingDiagonalFlags = ascendingDiagonalFlagsSave;
			descendingDiagonalFlags = descendingDiagonalFlagsSave;
			columnFlags = saveColumnFlags;
			chessboard[0] ^= queenFlag;
		}

	}

	/**
	 * Solving recursive method, do a brute-force algorithm by testing all combinations.
	 * 
	 * @param y line position on the chessboard
	 */
	private void solve(final int y) {

		for (int x = 0; x < chessboardSize; x++) {

			// Put a queen on the current position
			final int queenFlag = 1 << x;
			chessboard[y] |= queenFlag;
			final int saveColumnFlags = columnFlags;
			columnFlags |= queenFlag;
			final long ascendingDiagonalFlagsSave = ascendingDiagonalFlags;
			final long descendingDiagonalFlagsSave = descendingDiagonalFlags;
			ascendingDiagonalFlags |= 1 << x + y;
			descendingDiagonalFlags |= 1 << x + chessboardSize - 1 - y;

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
			ascendingDiagonalFlags = ascendingDiagonalFlagsSave;
			descendingDiagonalFlags = descendingDiagonalFlagsSave;
			columnFlags = saveColumnFlags;
			chessboard[y] ^= queenFlag;
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
		int countAscendingDiagonals = 0;
		int countDescendingDiagonals = 0;
		for (int i = 0; i < chessboardSize * 2 - 1; i++) {

			if ((ascendingDiagonalFlags & 1 << i) != 0) {
				countAscendingDiagonals++;
			}
			if ((descendingDiagonalFlags & 1 << i) != 0) {
				countDescendingDiagonals++;
			}
		}

		if (countAscendingDiagonals != chessboardSize || countDescendingDiagonals != chessboardSize) {
			return false;
		}

		return true;
	}

	@Override
	public void reset() {

		super.reset();

		if (chessboard.length != chessboardSize) {
			chessboard = new int[chessboardSize];
			columnFlags = 0;
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return 0 != (chessboard[y] & 1 << x);
	}
}
