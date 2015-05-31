package com.github.sbugat.nqueens;

import java.util.ArrayList;
import java.util.List;

import com.github.sbugat.nqueens.solvers.bruteforce.instrumentations.BruteForceNQueensSolverWithLists;
import com.github.sbugat.nqueens.solvers.bruteforce.instrumentations.SlowBruteForceNQueensSolverWithLists;
import com.github.sbugat.nqueens.tools.InvalidSolutionsException;
import com.google.common.collect.Lists;

public abstract class GenererateJavaScriptGraphData {

	private static final List<String> VALUES_LIST = Lists.newArrayList("queenPlacements", "methodCalls", "squareReads", "explicitTests", "implicitTests");

	private static List<GenericInstrumentedNQueensSolver> getSolvers(final int chessboardSize) {

		final List<GenericInstrumentedNQueensSolver> genericInstrumentedNQueensSolverList = new ArrayList<>();

		// genericNQueensSolverList.add(new SlowBruteForceNQueensSolverWithListsNoQueensLimit(chessboardSize, true));
		genericInstrumentedNQueensSolverList.add(new BruteForceNQueensSolverWithLists(chessboardSize, false));
		genericInstrumentedNQueensSolverList.add(new SlowBruteForceNQueensSolverWithLists(chessboardSize, false));

		// genericNQueensSolverList.add(new BruteForceNQueensSolverFullyOptimized(chessboardSize));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverOneDimension(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverWithLists(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverCheckCounting(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverWithExplicitInlineConstraits(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverWithExplicitConstraits(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverArray(chessboardSize, false));
		// genericNQueensSolverList.add(new BruteForceNQueensSolverCheckRefactored(chessboardSize, false));

		return genericInstrumentedNQueensSolverList;
	}

	/**
	 * Main greedy program.
	 * 
	 * @param args program options
	 * @throws InvalidSolutionsException if an algorithm found an invalid number of solutions
	 */
	public static void main(final String[] args) throws InvalidSolutionsException {

		// Chessboard size
		final int maxChessboardSize = 6;

		final StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("$(function () {" + System.lineSeparator());
		stringBuilder.append("	//Data" + System.lineSeparator());
		stringBuilder.append("	var data = [" + System.lineSeparator());

		int solverNumber = 0;
		for (int i = 1; i <= maxChessboardSize; i++) {

			stringBuilder.append("		{\"size\": \"" + i + "\",");
			final List<GenericInstrumentedNQueensSolver> genericInstrumentedNQueensSolverList = getSolvers(i);
			solverNumber = genericInstrumentedNQueensSolverList.size();

			int solverId = 1;
			for (final GenericInstrumentedNQueensSolver genericInstrumentedNQueensSolver : genericInstrumentedNQueensSolverList) {

				genericInstrumentedNQueensSolver.solve();
				if (i > 1) {
					stringBuilder.append(", ");
				}
				stringBuilder.append(genericInstrumentedNQueensSolver.toJavaScriptData("solver" + solverId));

				solverId++;
			}

			if (i == maxChessboardSize) {
				stringBuilder.append("}" + System.lineSeparator());
			}

			else {
				stringBuilder.append("}," + System.lineSeparator());
			}
		}
		stringBuilder.append("	];" + System.lineSeparator());

		for (final String value : VALUES_LIST) {

			stringBuilder.append("	Morris.Line({" + System.lineSeparator());
			stringBuilder.append("		element: '" + value + "'," + System.lineSeparator());
			stringBuilder.append("		hideHover: 'auto'," + System.lineSeparator());
			stringBuilder.append("		data: data," + System.lineSeparator());
			stringBuilder.append("		xkey: 'size'," + System.lineSeparator());
			stringBuilder.append("		ykeys: [");
			for (int i = 1; i <= solverNumber; i++) {

				if (i > 1) {
					stringBuilder.append(", ");
				}
				stringBuilder.append("\"solver" + i + value + "\"");
			}
			stringBuilder.append("]," + System.lineSeparator());
			stringBuilder.append("		labels: ['City', 'Highway', 'Idle']," + System.lineSeparator());
			stringBuilder.append("		resize: true," + System.lineSeparator());
			stringBuilder.append("		lineColors: ['#A52A2A','#72A0C1']," + System.lineSeparator());
			stringBuilder.append("		yLabelFormat: function(y) { return y.toString(); }" + System.lineSeparator());
			stringBuilder.append("	});" + System.lineSeparator());
		}

		stringBuilder.append("});" + System.lineSeparator());

		System.out.println(stringBuilder);
	}
}
