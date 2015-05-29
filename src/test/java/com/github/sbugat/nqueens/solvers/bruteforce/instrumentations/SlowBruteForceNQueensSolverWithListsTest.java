package com.github.sbugat.nqueens.solvers.bruteforce.instrumentations;

import com.github.sbugat.nqueens.GenericNQueensSolverTemplateTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class SlowBruteForceNQueensSolverWithListsTest extends GenericNQueensSolverTemplateTest {

	/** Test only with a size of 6 because this algorithm is very very slow. */
	private static final int MAXIMUM_TESTED_CHESSBOARD_SIZE = 5;

	public SlowBruteForceNQueensSolverWithListsTest() {

		super(new SlowBruteForceNQueensSolverWithLists(MAXIMUM_TESTED_CHESSBOARD_SIZE, false), MAXIMUM_TESTED_CHESSBOARD_SIZE);
	}
}
