package com.github.sbugat.nqueens;

public abstract class GenericInstrumentedNQueensSolver extends GenericNQueensSolver {

	/** Number of queens placement counter. */
	protected long queenPlacementCount;

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

		return stringBuilder.toString();
	}
}
