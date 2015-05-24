package com.github.sbugat.nqueens.tools;

public final class InvalidSolutionsException extends Exception {

	private static final long serialVersionUID = -2918035232656432720L;

	private final long solutionFound;

	private final long solutionExpected;

	private final int puzzleSize;

	public InvalidSolutionsException(final long solutionFoundArg, final long solutionExpectedArg, final int puzzleSizeArg) {

		solutionFound = solutionFoundArg;
		solutionExpected = solutionExpectedArg;
		puzzleSize = puzzleSizeArg;
	}

	@Override
	public String getMessage() {
		return toString();
	}

	@Override
	public String toString() {
		return solutionFound + " solutions found, expected: " + solutionExpected + " for a chessboard of size " + puzzleSize;
	}
}
