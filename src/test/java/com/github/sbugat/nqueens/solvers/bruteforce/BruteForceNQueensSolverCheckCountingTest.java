package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolverTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverCheckCountingTest extends GenericNQueensSolverTest {

	public BruteForceNQueensSolverCheckCountingTest() {

		// Test only with a size of 6 because this algorithm is very very slow
		super(new BruteForceNQueensSolverCheckCounting(6, false));
	}
}
