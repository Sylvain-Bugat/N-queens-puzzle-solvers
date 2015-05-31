package com.github.sbugat.nqueens;

public abstract class GenericInstrumentedNQueensSolver extends GenericNQueensSolver {

	/** Number of queen placements counter. */
	protected long queenPlacementsCount;

	/** Number of square reads counter. */
	protected long squareReadsCount;

	/** Number of tests done counter. */
	protected long squareWritesCount;

	/** Number of explicittests done counter. */
	protected long explicitTestsCount;

	/** Number of implicit tests done counter (loop). */
	protected long implicitTestsCount;

	/** Number of methods calls counter. */
	protected long methodCallsCount;

	protected GenericInstrumentedNQueensSolver(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);
	}

	@Override
	public void reset() {

		super.reset();

		// Reinitialize generic instrumentations
		queenPlacementsCount = 0;
		squareReadsCount = 0;
		squareWritesCount = 0;
		explicitTestsCount = 0;
		implicitTestsCount = 0;
		methodCallsCount = 0;
	}

	@Override
	public String toString() {

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("queen placements count:");
		stringBuilder.append(queenPlacementsCount);

		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("square reads count:");
		stringBuilder.append(squareReadsCount);

		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("square writes count:");
		stringBuilder.append(squareWritesCount);

		stringBuilder.append(System.lineSeparator());
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("explicit tests count:");
		stringBuilder.append(explicitTestsCount);
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("implicit tests count:");
		stringBuilder.append(implicitTestsCount);
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("total tests count:");
		stringBuilder.append(explicitTestsCount + implicitTestsCount);

		stringBuilder.append(System.lineSeparator());
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("method calls count:");
		stringBuilder.append(methodCallsCount);

		return stringBuilder.toString();
	}

	public final String toJavaScriptData(final String dataPrefix) {

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" \"" + dataPrefix + "queenPlacements\": " + queenPlacementsCount + ", ");
		stringBuilder.append(" \"" + dataPrefix + "methodCalls\": " + methodCallsCount + ", ");
		stringBuilder.append(" \"" + dataPrefix + "squareReads\": " + squareReadsCount + ", ");
		stringBuilder.append(" \"" + dataPrefix + "explicitTests\": " + explicitTestsCount + ", ");
		stringBuilder.append(" \"" + dataPrefix + "implicitTests\": " + implicitTestsCount);

		return stringBuilder.toString();
	}
}
