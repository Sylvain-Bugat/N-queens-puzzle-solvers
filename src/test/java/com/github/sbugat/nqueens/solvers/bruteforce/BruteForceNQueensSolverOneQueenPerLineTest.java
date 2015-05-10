package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolverTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverOneQueenPerLineTest extends GenericNQueensSolverTest {

	/** Test only with a size of 7 because this algorithm is slow. */
	private static final int MAXIMUM_TESTED_CHESSBOARD_SIZE = 7;

	public BruteForceNQueensSolverOneQueenPerLineTest() {

		super(new BruteForceNQueensSolverOneQueenPerLine(MAXIMUM_TESTED_CHESSBOARD_SIZE, false), MAXIMUM_TESTED_CHESSBOARD_SIZE);
	}
}
