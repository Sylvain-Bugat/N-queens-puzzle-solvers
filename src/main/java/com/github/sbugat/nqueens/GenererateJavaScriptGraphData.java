package com.github.sbugat.nqueens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.sbugat.nqueens.solvers.bruteforce.instrumentations.BruteForceNQueensSolverArray;
import com.github.sbugat.nqueens.solvers.bruteforce.instrumentations.BruteForceNQueensSolverOneDimensionArray;
import com.github.sbugat.nqueens.tools.InvalidSolutionsException;
import com.google.common.collect.Lists;

public abstract class GenererateJavaScriptGraphData {

	private static final List<String> GRAPH_COLORS = Lists.newArrayList("#A52A2A", "#72A0C1");

	private static final List<String> VALUES_LIST = Lists.newArrayList("queenPlacements", "methodCalls", "squareReads", "explicitTests", "implicitTests");
	private static final List<String> TEXT_VALUES_LIST = Lists.newArrayList("moves", "method calls", "reads", "tests", "loop tests");
	private static final List<String> DETAILED_TEXT_VALUES_LIST = Lists.newArrayList("Queen placements count", "Method calls count", "Square reads count", "Explicit tests count", "Loop tests count");

	private static List<GenericInstrumentedNQueensSolver> getSolvers(final int chessboardSize) {

		final List<GenericInstrumentedNQueensSolver> genericInstrumentedNQueensSolverList = new ArrayList<>();

		// genericInstrumentedNQueensSolverList.add(new SlowBruteForceNQueensSolverWithListsNoQueensLimit(chessboardSize, false));
		// genericInstrumentedNQueensSolverList.add(new SlowBruteForceNQueensSolverWithLists(chessboardSize, false));
		// genericInstrumentedNQueensSolverList.add(new BruteForceNQueensSolverWithLists(chessboardSize, false));
		genericInstrumentedNQueensSolverList.add(new BruteForceNQueensSolverArray(chessboardSize, false));
		genericInstrumentedNQueensSolverList.add(new BruteForceNQueensSolverOneDimensionArray(chessboardSize, false));

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
		final int maxChessboardSize = 8;
		final String javaScriptVariablePrefix = "prefix";

		final List<GenericInstrumentedNQueensSolver> genericInstrumentedNQueensSolverList = getSolvers(1);
		final int solverNumber = genericInstrumentedNQueensSolverList.size();

		final StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<div class=\"panel panel-default tab-box\">" + System.lineSeparator());
		stringBuilder.append("	<div class=\"panel-heading\">" + System.lineSeparator());
		stringBuilder.append("		<h3 class=\"panel-title\">" + System.lineSeparator());
		stringBuilder.append("			<i class=\"glyphicon glyphicon-stats\"></i>Algorithms comparison" + System.lineSeparator());
		stringBuilder.append("		</h3>" + System.lineSeparator());
		stringBuilder.append("		<ul class=\"nav nav-tabs\">" + System.lineSeparator());
		for (int i = 0; i < VALUES_LIST.size(); i++) {
			if (0 == i) {
				stringBuilder.append("			<li class=\"active\">" + System.lineSeparator());
			}
			else {
				stringBuilder.append("			<li>" + System.lineSeparator());
			}
			stringBuilder.append("				<a href=\"#" + javaScriptVariablePrefix + VALUES_LIST.get(i) + "Tab\" data-toggle=\"tab\" data-identifier=\"" + javaScriptVariablePrefix + VALUES_LIST.get(i) + "Graph\">" + TEXT_VALUES_LIST.get(i) + "</a>" + System.lineSeparator());
			stringBuilder.append("			</li>" + System.lineSeparator());
		}
		stringBuilder.append("		</ul>" + System.lineSeparator());
		stringBuilder.append("	</div>" + System.lineSeparator());

		stringBuilder.append("	<div class=\"panel-body\">" + System.lineSeparator());
		stringBuilder.append("		<div class=\"tab-content\">" + System.lineSeparator());
		for (int i = 0; i < VALUES_LIST.size(); i++) {

			if (0 == i) {
				stringBuilder.append("			<div id=\"" + javaScriptVariablePrefix + VALUES_LIST.get(i) + "Tab\" class=\"tab-pane active\">" + System.lineSeparator());
			}
			else {
				stringBuilder.append("			<div id=\"" + javaScriptVariablePrefix + VALUES_LIST.get(i) + "Tab\" class=\"tab-pane\">" + System.lineSeparator());
			}
			stringBuilder.append("				<div class=\"row\">" + System.lineSeparator());
			stringBuilder.append("					<div class=\"caption\">" + System.lineSeparator());
			stringBuilder.append("						" + DETAILED_TEXT_VALUES_LIST.get(i) + System.lineSeparator());
			stringBuilder.append("					</div>" + System.lineSeparator());
			stringBuilder.append("					<div id=\"" + javaScriptVariablePrefix + VALUES_LIST.get(i) + "\"></div>" + System.lineSeparator());
			stringBuilder.append("					<div class=\"legend\">" + System.lineSeparator());

			for (int j = 0; j < solverNumber; j++) {
				stringBuilder.append("						<span class=\"label\" style=\"background-color: " + GRAPH_COLORS.get(j) + ";\">" + genericInstrumentedNQueensSolverList.get(j).getName() + "</span>" + System.lineSeparator());
			}

			stringBuilder.append("					</div>" + System.lineSeparator());
			stringBuilder.append("				</div>" + System.lineSeparator());
			stringBuilder.append("			</div>" + System.lineSeparator());
		}
		stringBuilder.append("		</div>" + System.lineSeparator());
		stringBuilder.append("	</div>" + System.lineSeparator());
		stringBuilder.append("</div>" + System.lineSeparator());

		stringBuilder.append(System.lineSeparator() + "<script>" + System.lineSeparator() + System.lineSeparator());

		// Redraw graph script
		stringBuilder.append("$('ul.nav a').on('shown.bs.tab', function (e) {" + System.lineSeparator());
		stringBuilder.append("	var types = $(this).attr(\"data-identifier\");" + System.lineSeparator());
		stringBuilder.append("	var typesArray = types.split(\",\");" + System.lineSeparator());
		stringBuilder.append("	$.each(typesArray, function (key, value) {" + System.lineSeparator());
		stringBuilder.append("		eval(value + \".redraw()\");" + System.lineSeparator());
		stringBuilder.append("		eval(value + \".resizeHandler()\");" + System.lineSeparator());
		stringBuilder.append("	})" + System.lineSeparator());
		stringBuilder.append("});" + System.lineSeparator() + System.lineSeparator());

		// Graph data function
		final Map<Double, Long> logValuesAssociationMap = new HashMap<>();

		stringBuilder.append("//Data" + System.lineSeparator());
		stringBuilder.append("var " + javaScriptVariablePrefix + "data = [" + System.lineSeparator());

		for (int i = 1; i <= maxChessboardSize; i++) {

			stringBuilder.append("	{\"size\": \"" + i + "\",");

			int solverId = 1;
			for (final GenericInstrumentedNQueensSolver genericInstrumentedNQueensSolver : getSolvers(i)) {

				genericInstrumentedNQueensSolver.solve();
				if (solverId > 1) {
					stringBuilder.append(", ");
				}
				stringBuilder.append(genericInstrumentedNQueensSolver.toJavaScriptData("solver" + solverId));

				genericInstrumentedNQueensSolver.getLogAssocationValues(logValuesAssociationMap);

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

		stringBuilder.append("//Data" + System.lineSeparator());
		stringBuilder.append("var " + javaScriptVariablePrefix + "logData = {");

		int entryId = 1;
		for (final Map.Entry<Double, Long> entry : logValuesAssociationMap.entrySet()) {

			if (entryId > 1) {
				stringBuilder.append(", ");
			}

			final Double key;
			if (0.0 == entry.getKey().doubleValue()) {
				key = Double.valueOf(0.1);
			}
			else {
				key = entry.getKey();
			}

			stringBuilder.append(" \"" + key.toString().replaceFirst("\\.0$", "") + "\": " + entry.getValue());

			entryId++;
		}
		stringBuilder.append("	};" + System.lineSeparator());

		for (final String value : VALUES_LIST) {

			stringBuilder.append("var " + javaScriptVariablePrefix + value + "Graph = Morris.Line({" + System.lineSeparator());
			stringBuilder.append("	element: '" + javaScriptVariablePrefix + value + "'," + System.lineSeparator());
			stringBuilder.append("	hideHover: 'auto'," + System.lineSeparator());
			stringBuilder.append("	data: " + javaScriptVariablePrefix + "data," + System.lineSeparator());
			stringBuilder.append("	xkey: 'size'," + System.lineSeparator());
			stringBuilder.append("	ykeys: [");
			for (int i = 1; i <= solverNumber; i++) {

				if (i > 1) {
					stringBuilder.append(", ");
				}
				stringBuilder.append("'solver" + i + value + "'");
			}
			stringBuilder.append("]," + System.lineSeparator());

			stringBuilder.append("	labels: [");
			for (int i = 0; i < solverNumber; i++) {

				if (i > 0) {
					stringBuilder.append(", ");
				}
				stringBuilder.append("'" + genericInstrumentedNQueensSolverList.get(i).getName() + "'");
			}
			stringBuilder.append("]," + System.lineSeparator());

			stringBuilder.append("	resize: true," + System.lineSeparator());
			stringBuilder.append("	parseTime: false," + System.lineSeparator());
			stringBuilder.append("	lineColors: [");
			for (int i = 0; i < solverNumber; i++) {

				if (i > 0) {
					stringBuilder.append(", ");
				}
				stringBuilder.append("'" + GRAPH_COLORS.get(i) + "'");
			}
			stringBuilder.append("]," + System.lineSeparator());
			stringBuilder.append("	yLabelFormat: function(y) { if( " + javaScriptVariablePrefix + "logData[y] ) { return " + javaScriptVariablePrefix + "logData[y].toLocaleString(); } else { ");
			stringBuilder.append("if( 0 == y ) { return 0; } ");
			stringBuilder.append("return \"10^\" + y;");
			stringBuilder.append("} }," + System.lineSeparator());

			stringBuilder.append("	xLabelFormat: function(obj) { return (obj.x + 1).toLocaleString(); }," + System.lineSeparator());
			stringBuilder.append("});" + System.lineSeparator());
		}

		stringBuilder.append(System.lineSeparator() + "</script>" + System.lineSeparator());

		System.out.println(stringBuilder);
	}
}
