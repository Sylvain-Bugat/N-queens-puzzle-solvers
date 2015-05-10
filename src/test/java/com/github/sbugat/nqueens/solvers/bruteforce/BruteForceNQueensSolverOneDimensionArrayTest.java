package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolverTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverOneDimensionArrayTest extends GenericNQueensSolverTest {

	/** Test only with a size of 6 because this algorithm is very slow. */
	private static final int MAXIMUM_TESTED_CHESSBOARD_SIZE = 6;

	public BruteForceNQueensSolverOneDimensionArrayTest() {

		super(new BruteForceNQueensSolverOneDimensionArray(MAXIMUM_TESTED_CHESSBOARD_SIZE, true), MAXIMUM_TESTED_CHESSBOARD_SIZE);
	}
}
