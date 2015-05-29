package com.github.sbugat.nqueens.backtracking;

import com.github.sbugat.nqueens.GenericNQueensSolverTemplateTest;
import com.github.sbugat.nqueens.solvers.backtracking.SlowBackTrackingNQueensSolver;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class SlowBackTrackingNQueensSolverTest extends GenericNQueensSolverTemplateTest {

	/** Test only with a size of 8 because this algorithm is quite slow. */
	private static final int MAXIMUM_TESTED_CHESSBOARD_SIZE = 8;

	public SlowBackTrackingNQueensSolverTest() {

		super(new SlowBackTrackingNQueensSolver(MAXIMUM_TESTED_CHESSBOARD_SIZE, false), MAXIMUM_TESTED_CHESSBOARD_SIZE);
	}
}
