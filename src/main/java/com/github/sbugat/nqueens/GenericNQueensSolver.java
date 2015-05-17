package com.github.sbugat.nqueens;

public abstract class GenericNQueensSolver {

	/** Size of the chess board. */
	protected int chessboardSize;

	/** Number of solution counter. */
	protected long solutionCount;

	/** Print solution flag. */
	protected final boolean printSolution;

	protected GenericNQueensSolver(final int chessboardSizeArg, final boolean printSolutionArg) {

		// Check positive chessboard size
		if (chessboardSizeArg <= 0) {
			throw new IllegalArgumentException("The chessboard size must be positive: " + chessboardSizeArg);
		}

		chessboardSize = chessboardSizeArg;
		printSolution = printSolutionArg;
	}

	/**
	 * Print the current chessboard.
	 */
	public final void print() {

		if (printSolution) {
			System.out.println("\nsolution number " + solutionCount); //$NON-NLS-1$

			for (int y = 0; y < chessboardSize; y++) {

				for (int x = 0; x < chessboardSize; x++) {

					if (getChessboardPosition(x, y)) {
						System.out.print(1);
					}
					else {
						System.out.print(0);
					}
				}
				System.out.println();
			}
		}
	}

	public final int getChessboardSize() {
		return chessboardSize;
	}

	public final void setChessboardSize(final int chessboardSizeArg) {
		chessboardSize = chessboardSizeArg;
	}

	public abstract long solve();

	public void reset() {

		// Reinitialize the number of solutions found
		solutionCount = 0;
	}

	public abstract boolean getChessboardPosition(final int x, final int y);
}
