package com.github.sbugat.nqueens;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.sbugat.nqueens.solvers.bruteforce.BruteForceNQueensSolverIterative;
import com.github.sbugat.nqueens.tools.BenchmarkTools;
import com.github.sbugat.nqueens.tools.InvalidSolutionsException;

public abstract class BenchmarkAllSolvers {

	private static List<GenericNQueensSolver> getSolvers(final int chessboardSize) {

		final List<GenericNQueensSolver> genericNQueensSolverList = new ArrayList<>();

		// genericNQueensSolverList.add(new SlowBruteForceNQueensSolverWithListsNoQueensLimit(chessboardSize, true));
		genericNQueensSolverList.add(new BruteForceNQueensSolverIterative(chessboardSize, false));

		// genericNQueensSolverList.add(new BruteForceNQueensSolverFullyOptimized(chessboardSize));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverOneDimension(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverWithLists(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverCheckCounting(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverWithExplicitInlineConstraits(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverWithExplicitConstraits(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverArray(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverCheckRefactored(chessboardSize, false));

		return genericNQueensSolverList;
	}

	/**
	 * Main greedy program.
	 * 
	 * @param args options
	 * @throws InvalidSolutionsException
	 */
	public static void main(final String args[]) throws InvalidSolutionsException {

		// Chessboard size
		final int chessboardSize = 8;
		final int benchmarkRun = 5;

		final List<GenericNQueensSolver> genericNQueensSolverList = getSolvers(chessboardSize);

		for (final GenericNQueensSolver genericNQueensSolver : genericNQueensSolverList) {

			System.out.println("Start: " + new Date()); //$NON-NLS-1$

			System.out.print(genericNQueensSolver.getClass().getSimpleName() + ": "); //$NON-NLS-1$
			final long solverBenchmark = BenchmarkTools.benchmark(genericNQueensSolver, benchmarkRun);
			System.out.println("\nDone! - " + BenchmarkTools.nanoSecondsToString(solverBenchmark)); //$NON-NLS-1$

			System.out.println("End: " + new Date()); //$NON-NLS-1$
		}
	}
}
