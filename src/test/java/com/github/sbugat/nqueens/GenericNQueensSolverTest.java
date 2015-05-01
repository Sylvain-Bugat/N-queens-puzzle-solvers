package com.github.sbugat.nqueens;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.github.sbugat.nqueens.tools.SequenceTools;

public abstract class GenericNQueensSolverTest {

	/** The solver to test. */
	protected final GenericNQueensSolver genericNQueensSolver;

	protected GenericNQueensSolverTest(final GenericNQueensSolver genericNQueensSolverArg) {
		genericNQueensSolver = genericNQueensSolverArg;
	}

	@Test
	public void testNQueensSolver() {

		final long solutionCount = genericNQueensSolver.solve();
		Assertions.assertThat(solutionCount).isEqualTo(SequenceTools.getExpectedSolutions(genericNQueensSolver.getPuzzleSize()));
	}
}
