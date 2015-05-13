package com.github.sbugat.nqueens.solvers.experimental;

import java.util.ArrayList;
import java.util.List;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BackTrackingNQueensSolverDivide2 extends GenericNQueensSolver {

	/** Chessboard represented by a 2 dimensional array. */
	private boolean[][] chessboard;
	/** Array to count queens on each column. */
	private int[] columnCounts;
	/** Array to count queens on ascending diagonals, diagonal number = x + y. */
	private int[] ascendingDiagonalCounts;
	/** Array to count queens on descending diagonals, diagonal number = x + chess board size - 1 - y. */
	private int[] descendingDiagonalCounts;

	private final List<Long> up = new ArrayList<>();
	private final List<Long> down = new ArrayList<>();

	private final int chessboardSizeMinusOne;
	private final int chessboardSize1Point5MinusOne;
	private final int chessboardSizeDividedBy2;

	public BackTrackingNQueensSolverDivide2(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new boolean[chessboardSizeArg][chessboardSizeArg];
		columnCounts = new int[chessboardSizeArg];
		ascendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
		descendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];

		chessboardSizeMinusOne = chessboardSizeArg - 1;
		chessboardSize1Point5MinusOne = chessboardSize * 3 / 2 - 1;
		chessboardSizeDividedBy2 = chessboardSize / 2;
	}

	@Override
	public long solve() {

		// Start the algorithm at the first line
		solve(0);

		for (final Long upSignature : up) {

			for (final Long downSignature : down) {

				if ((upSignature.longValue() & downSignature.longValue()) == 0) {
					// System.out.println(upSignature + " " + downSignature);
					solutionCount++;
					print();
				}
			}
		}

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * Solving recursive method, do a brute-force algorithm by testing all combinations.
	 * 
	 * @param y line position on the chessboard
	 */
	private void solve(final int y) {

		for (int x = 0; x < chessboardSize; x++) {

			if (columnCounts[x] == 0) {
				final int ascendingDiagnonal = x + y;
				final int descendingDiagnonal = x + chessboardSize - 1 - y;
				if (ascendingDiagonalCounts[ascendingDiagnonal] == 0 && descendingDiagonalCounts[descendingDiagnonal] == 0) {
					// Put a queen on the current position
					chessboard[y][x] = true;
					columnCounts[x]++;
					ascendingDiagonalCounts[ascendingDiagnonal]++;
					descendingDiagonalCounts[descendingDiagnonal]++;

					// Middle line
					if (y + 1 >= chessboardSizeDividedBy2) {
						long signatureUp = 0;
						for (int i = 0; i < columnCounts.length; i++) {

							signatureUp |= columnCounts[i] << i;
						}
						long signatureDown = signatureUp;

						for (int i = 0; i < ascendingDiagonalCounts.length; i++) {

							if (i >= chessboardSizeDividedBy2 && i < chessboardSize1Point5MinusOne) {
								signatureDown |= ascendingDiagonalCounts[i] << i - chessboardSizeDividedBy2 + chessboardSize;
							}
							if (i < chessboardSizeMinusOne) {
								signatureUp |= ascendingDiagonalCounts[i] << i + chessboardSize;
							}

						}

						// System.out.println(Long.toBinaryString(signatureDown));
						// System.out.println(Long.toBinaryString(signatureUp));

						for (int i = 0; i < descendingDiagonalCounts.length; i++) {

							if (i >= chessboardSizeDividedBy2 && i < chessboardSize1Point5MinusOne) {
								signatureDown |= descendingDiagonalCounts[i] << i + chessboardSize * 2 - chessboardSizeDividedBy2;
							}
							if (i > chessboardSizeMinusOne) {
								signatureUp |= descendingDiagonalCounts[i] << i + chessboardSize;
							}
						}
						// System.out.println(Long.toBinaryString(signatureDown));
						// System.out.println(Long.toBinaryString(signatureUp));

						up.add(Long.valueOf(signatureUp));
						down.add(Long.valueOf(signatureDown));
					} else {
						// Go to the next line
						solve(y + 1);
					}

					// Remove the placed queen
					ascendingDiagonalCounts[ascendingDiagnonal]--;
					descendingDiagonalCounts[descendingDiagnonal]--;
					columnCounts[x]--;
					chessboard[y][x] = false;
				}
			}
		}
	}

	@Override
	public void reset() {

		super.reset();
		up.clear();
		down.clear();

		if (chessboard.length != chessboardSize) {
			chessboard = new boolean[chessboardSize][chessboardSize];
			columnCounts = new int[chessboardSize];
			ascendingDiagonalCounts = new int[chessboardSize * 2 - 1];
			descendingDiagonalCounts = new int[chessboardSize * 2 - 1];
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard[y][x];
	}
}
