package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolverTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class SlowBruteForceNQueensSolverWithListsTest extends GenericNQueensSolverTest {

	public SlowBruteForceNQueensSolverWithListsTest() {

		// Test only with a size of 5 because this algorithm is very very slow
		super(new SlowBruteForceNQueensSolverWithLists(5, false));
	}
}
