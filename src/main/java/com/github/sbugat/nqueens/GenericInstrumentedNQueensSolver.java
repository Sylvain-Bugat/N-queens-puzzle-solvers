package com.github.sbugat.nqueens;

import java.util.Map;

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
		stringBuilder.append(" \"" + dataPrefix + "queenPlacements\": " + (0.0 == Math.log10(queenPlacementsCount) ? 0.1 : Math.log10(queenPlacementsCount)) + ", ");
		stringBuilder.append(" \"" + dataPrefix + "methodCalls\": " + (0.0 == Math.log10(methodCallsCount) ? 0.1 : Math.log10(methodCallsCount)) + ", ");
		stringBuilder.append(" \"" + dataPrefix + "squareReads\": " + (0.0 == Math.log10(squareReadsCount) ? 0.1 : Math.log10(squareReadsCount)) + ", ");
		stringBuilder.append(" \"" + dataPrefix + "explicitTests\": " + (0.0 == Math.log10(explicitTestsCount) ? 0.1 : Math.log10(explicitTestsCount)) + ", ");
		stringBuilder.append(" \"" + dataPrefix + "implicitTests\": " + (0.0 == Math.log10(implicitTestsCount) ? 0.1 : Math.log10(implicitTestsCount)));

		return stringBuilder.toString();
	}

	public final void getLogAssocationValues(final Map<Double, Long> map) {

		map.put(Math.log10(Double.valueOf(queenPlacementsCount)), Long.valueOf(queenPlacementsCount));
		map.put(Math.log10(Double.valueOf(methodCallsCount)), Long.valueOf(methodCallsCount));
		map.put(Math.log10(Double.valueOf(squareReadsCount)), Long.valueOf(squareReadsCount));
		map.put(Math.log10(Double.valueOf(explicitTestsCount)), Long.valueOf(explicitTestsCount));
		map.put(Math.log10(Double.valueOf(implicitTestsCount)), Long.valueOf(implicitTestsCount));
	}

	public abstract String getName();
}
