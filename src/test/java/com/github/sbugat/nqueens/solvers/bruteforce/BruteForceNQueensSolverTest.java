package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolverTest;

/**
 * Greedy N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverTest extends GenericNQueensSolverTest {

	public BruteForceNQueensSolverTest() {

		// Test only with a size of 6 because the greedy algorithm is very slow
		super(new BruteForceNQueensSolver(6, false));
	}
}
