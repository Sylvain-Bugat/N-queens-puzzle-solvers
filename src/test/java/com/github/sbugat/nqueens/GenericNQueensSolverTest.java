package com.github.sbugat.nqueens;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.github.sbugat.nqueens.tools.SequenceTools;

public abstract class GenericNQueensSolverTest {

	/** The solver to test. */
	private final GenericNQueensSolver genericNQueensSolver;

	/** The maximum chessboard size to test. */
	private final int maximumChessboardSize;

	protected GenericNQueensSolverTest(final GenericNQueensSolver genericNQueensSolverArg, final int maximumChessboardSizeArg) {
		genericNQueensSolver = genericNQueensSolverArg;
		maximumChessboardSize = maximumChessboardSizeArg;
	}

	@Test
	public void testNQueensSolver() {

		for (int chessboardSize = 1; chessboardSize <= maximumChessboardSize; chessboardSize++) {

			// Set the chessboard size and reset all temporary data
			genericNQueensSolver.setChessboardSize(chessboardSize);
			genericNQueensSolver.reset();

			// Solve with the chessboard size and compare the number of solutions with the expected sequence
			final long solutionCount = genericNQueensSolver.solve();
			Assertions.assertThat(solutionCount).isEqualTo(SequenceTools.getExpectedSolutions(genericNQueensSolver.getChessboardSize()));
		}
	}
}
