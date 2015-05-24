package com.github.sbugat.nqueens.solvers.bruteforce;

import org.junit.Test;

import com.github.sbugat.nqueens.GenericNQueensSolverTemplateTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverColumnBitFlagsTest extends GenericNQueensSolverTemplateTest {

	/** Test only with a size of 7 because this algorithm is slow. */
	private static final int MAXIMUM_TESTED_CHESSBOARD_SIZE = 7;

	public BruteForceNQueensSolverColumnBitFlagsTest() {

		super(new BruteForceNQueensSolverColumnBitFlags(MAXIMUM_TESTED_CHESSBOARD_SIZE, false), MAXIMUM_TESTED_CHESSBOARD_SIZE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void bruteForceNQueensSolverColumnBitFlagsInvalidTest() {

		new BruteForceNQueensSolverColumnBitFlags(Integer.SIZE + 1, false);
	}
}
