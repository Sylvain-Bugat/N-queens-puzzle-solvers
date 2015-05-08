package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolverTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class SlowBruteForceNQueensSolverWithListsTest extends GenericNQueensSolverTest {

	/** Test only with a size of 6 because this algorithm is very very slow. */
	private static final int MAXIMUM_TESTED_CHESSBOARD_SIZE = 5;

	public SlowBruteForceNQueensSolverWithListsTest() {

		super(new SlowBruteForceNQueensSolverWithLists(MAXIMUM_TESTED_CHESSBOARD_SIZE, false), MAXIMUM_TESTED_CHESSBOARD_SIZE);
	}
}
