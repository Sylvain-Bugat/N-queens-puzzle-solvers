package com.github.sbugat.nqueens.solvers.greedy;

import com.github.sbugat.nqueens.GenericNQueensSolverTest;

/**
 * Greedy N queens solver test.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class GreedyNQueensSolverTest extends GenericNQueensSolverTest {

	public GreedyNQueensSolverTest() {

		// Test only with a size of 6 because the greedy algorithm is very slow
		super(new GreedyNQueensSolver(6, false));
	}
}
