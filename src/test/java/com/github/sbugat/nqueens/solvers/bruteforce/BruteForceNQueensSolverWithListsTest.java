package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolverTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverWithListsTest extends GenericNQueensSolverTest {

	/** Test only with a size of 6 because this algorithm is very slow. */
	private static final int MAXIMUM_TESTED_CHESSBOARD_SIZE = 6;

	public BruteForceNQueensSolverWithListsTest() {

		super(new BruteForceNQueensSolverWithLists(MAXIMUM_TESTED_CHESSBOARD_SIZE, false), MAXIMUM_TESTED_CHESSBOARD_SIZE);
	}
}
