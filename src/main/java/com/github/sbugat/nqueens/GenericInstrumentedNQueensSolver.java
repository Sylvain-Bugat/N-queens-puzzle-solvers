package com.github.sbugat.nqueens;

public abstract class GenericInstrumentedNQueensSolver extends GenericNQueensSolver {

	/** Number of queens placement counter. */
	protected long queenPlacementCount;

	/** Number of tests done counter. */
	protected long testsCount;

	protected GenericInstrumentedNQueensSolver(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);
	}

	@Override
	public void reset() {

		super.reset();

		// Reinitialize generic instrumentations
		queenPlacementCount = 0;
	}

	@Override
	public String toString() {

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("queen placement count:");
		stringBuilder.append(queenPlacementCount);
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("tests count:");
		stringBuilder.append(testsCount);

		return stringBuilder.toString();
	}
}
