package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolverTest;

/**
 * Brute-force N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverCountQueensTest extends GenericNQueensSolverTest {

	public BruteForceNQueensSolverCountQueensTest() {

		// Test only with a size of 6 because this algorithm is very slow
		super(new BruteForceNQueensSolverCountQueens(6, false));
	}
}
