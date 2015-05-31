package com.github.sbugat.nqueens;

import java.util.ArrayList;
import java.util.List;

import com.github.sbugat.nqueens.solvers.bruteforce.instrumentations.BruteForceNQueensSolverWithLists;
import com.github.sbugat.nqueens.solvers.bruteforce.instrumentations.SlowBruteForceNQueensSolverWithLists;
import com.github.sbugat.nqueens.tools.InvalidSolutionsException;
import com.google.common.collect.Lists;

public abstract class GenererateJavaScriptGraphData {

	private static final List<String> VALUES_LIST = Lists.newArrayList("queenPlacements", "methodCalls", "squareReads", "explicitTests", "implicitTests");
	private static final List<String> TEXT_VALUES_LIST = Lists.newArrayList("queen placements", "method calls", "square reads", "tests", "loop tests");

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

		stringBuilder.append("<div id=\"monitor\" class=\"panel panel-default tab-box\">" + System.lineSeparator());
		stringBuilder.append("	<div class=\"panel-heading\">" + System.lineSeparator());
		stringBuilder.append("		<h3 class=\"panel-title\">" + System.lineSeparator());
		stringBuilder.append("			<i class=\"fa fa-signal\"></i>Algorithms comparison" + System.lineSeparator());
		stringBuilder.append("		</h3>" + System.lineSeparator());
		stringBuilder.append("		<ul class=\"nav nav-tabs\">" + System.lineSeparator());
		for (int i = 0; i < VALUES_LIST.size(); i++) {
			if (0 == i) {
				stringBuilder.append("			<li class=\"active\">" + System.lineSeparator());
			}
			else {
				stringBuilder.append("			<li>" + System.lineSeparator());
			}
			stringBuilder.append("				<a href=\"#" + VALUES_LIST.get(i) + "Tab\" data-toggle=\"tab\">" + TEXT_VALUES_LIST.get(i) + "</a>" + System.lineSeparator());
			stringBuilder.append("			</li>" + System.lineSeparator());
		}
		stringBuilder.append("		</ul>" + System.lineSeparator());
		stringBuilder.append("	</div>" + System.lineSeparator());

		stringBuilder.append("	<div class=\"panel-body\">" + System.lineSeparator());
		stringBuilder.append("		<div class=\"tab-content\">" + System.lineSeparator());
		for (int i = 0; i < VALUES_LIST.size(); i++) {

			if (0 == i) {
				stringBuilder.append("			<div id=\"" + VALUES_LIST.get(i) + "Tab\" class=\"tab-pane active\">" + System.lineSeparator());
			}
			else {
				stringBuilder.append("			<div id=\"" + VALUES_LIST.get(i) + "Tab\" class=\"tab-pane\">" + System.lineSeparator());
			}
			stringBuilder.append("				<div class=\"row\">" + System.lineSeparator());
			stringBuilder.append("					<div class=\"caption\">" + System.lineSeparator());
			stringBuilder.append("						" + TEXT_VALUES_LIST.get(i) + System.lineSeparator());
			stringBuilder.append("					</div>" + System.lineSeparator());
			stringBuilder.append("					<div id=\"" + VALUES_LIST.get(i) + "\"></div>" + System.lineSeparator());
			stringBuilder.append("					<div class=\"legend\">" + System.lineSeparator());
			stringBuilder.append("						<span id=\"" + VALUES_LIST.get(i) + "\" class=\"label\">" + TEXT_VALUES_LIST.get(i) + "</span>" + System.lineSeparator());
			stringBuilder.append("					</div>" + System.lineSeparator());
			stringBuilder.append("				</div>" + System.lineSeparator());
			stringBuilder.append("			</div>" + System.lineSeparator());
		}
		stringBuilder.append("		</div>" + System.lineSeparator());
		stringBuilder.append("	</div>" + System.lineSeparator());
		stringBuilder.append("</div>" + System.lineSeparator());

		stringBuilder.append(System.lineSeparator() + "<script>" + System.lineSeparator());

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
				if (solverId > 1) {
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

		final List<GenericInstrumentedNQueensSolver> genericInstrumentedNQueensSolverList = getSolvers(1);
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
				stringBuilder.append("'solver" + i + value + "'");
			}
			stringBuilder.append("]," + System.lineSeparator());

			stringBuilder.append("		labels: [");
			for (int i = 0; i < genericInstrumentedNQueensSolverList.size(); i++) {

				if (i > 0) {
					stringBuilder.append(", ");
				}
				stringBuilder.append("'" + genericInstrumentedNQueensSolverList.get(i).getName() + "'");
			}
			stringBuilder.append("]," + System.lineSeparator());

			stringBuilder.append("		resize: true," + System.lineSeparator());
			stringBuilder.append("		lineColors: ['#A52A2A','#72A0C1']," + System.lineSeparator());
			stringBuilder.append("		yLabelFormat: function(y) { return y.toString(); }" + System.lineSeparator());
			stringBuilder.append("	});" + System.lineSeparator());
		}

		stringBuilder.append("});" + System.lineSeparator());

		stringBuilder.append(System.lineSeparator() + "</script>" + System.lineSeparator());

		System.out.println(stringBuilder);
	}
}
