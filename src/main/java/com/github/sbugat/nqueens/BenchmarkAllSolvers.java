package com.github.sbugat.nqueens;

import java.util.ArrayList;
import java.util.List;

import com.github.sbugat.nqueens.solvers.greedy.GreedyNQueensSolver;
import com.github.sbugat.nqueens.solvers.greedy.GreedyNQueensSolverCheckCounting;
import com.github.sbugat.nqueens.solvers.greedy.GreedyNQueensSolverCheckRefactored;
import com.github.sbugat.nqueens.solvers.greedy.GreedyNQueensSolverOneDimension;
import com.github.sbugat.nqueens.solvers.greedy.GreedyNQueensSolverWithExplicitConstraits;
import com.github.sbugat.nqueens.solvers.greedy.GreedyNQueensSolverWithExplicitInlineConstraits;
import com.github.sbugat.nqueens.solvers.greedy.GreedyNQueensSolverWithLists;
import com.github.sbugat.nqueens.tools.BenchmarkTools;
import com.github.sbugat.nqueens.tools.InvalidSolutionsException;

public abstract class BenchmarkAllSolvers {

	private static List<GenericNQueensSolver> getSolvers(final int chessboardSize) {

		final List<GenericNQueensSolver> genericNQueensSolverList = new ArrayList<>();

		genericNQueensSolverList.add(new GreedyNQueensSolverOneDimension(chessboardSize, false));
		genericNQueensSolverList.add(new GreedyNQueensSolverWithLists(chessboardSize, false));
		genericNQueensSolverList.add(new GreedyNQueensSolverCheckCounting(chessboardSize, false));
		genericNQueensSolverList.add(new GreedyNQueensSolverWithExplicitInlineConstraits(chessboardSize, false));
		genericNQueensSolverList.add(new GreedyNQueensSolverWithExplicitConstraits(chessboardSize, false));
		genericNQueensSolverList.add(new GreedyNQueensSolver(chessboardSize, false));
		genericNQueensSolverList.add(new GreedyNQueensSolverCheckRefactored(chessboardSize, false));

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
		final int chessboardSize = 6;
		final int benchmarkRun = 5;

		final List<GenericNQueensSolver> genericNQueensSolverList = getSolvers(chessboardSize);

		for (final GenericNQueensSolver genericNQueensSolver : genericNQueensSolverList) {

			final long solverBenchmark = BenchmarkTools.benchmark(genericNQueensSolver, benchmarkRun);

			System.out.println(genericNQueensSolver.getClass().getSimpleName() + " - " + BenchmarkTools.nanoSecondsToString(solverBenchmark)); //$NON-NLS-1$
		}
	}
}
