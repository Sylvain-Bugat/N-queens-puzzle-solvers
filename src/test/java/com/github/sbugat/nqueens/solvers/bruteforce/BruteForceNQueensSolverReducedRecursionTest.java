package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolverTemplateTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverReducedRecursionTest extends GenericNQueensSolverTemplateTest {

	/** Test only with a size of 7 because this algorithm is slow. */
	private static final int MAXIMUM_TESTED_CHESSBOARD_SIZE = 7;

	public BruteForceNQueensSolverReducedRecursionTest() {

		super(new BruteForceNQueensSolverReducedRecursion(MAXIMUM_TESTED_CHESSBOARD_SIZE, false), MAXIMUM_TESTED_CHESSBOARD_SIZE);
	}
}
