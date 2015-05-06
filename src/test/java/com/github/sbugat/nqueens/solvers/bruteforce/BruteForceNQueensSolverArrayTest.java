package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolverTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverArrayTest extends GenericNQueensSolverTest {

	public BruteForceNQueensSolverArrayTest() {

		// Test only with a size of 6 because this algorithm is very slow
		super(new BruteForceNQueensSolverArray(6, false));
	}
}
