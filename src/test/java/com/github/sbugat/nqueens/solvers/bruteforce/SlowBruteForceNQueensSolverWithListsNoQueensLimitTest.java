package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolverTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class SlowBruteForceNQueensSolverWithListsNoQueensLimitTest extends GenericNQueensSolverTest {

	public SlowBruteForceNQueensSolverWithListsNoQueensLimitTest() {

		// Test only with a size of 4 because this algorithm is very very slow
		super(new SlowBruteForceNQueensSolverWithListsNoQueensLimit(4, false));
	}
}
