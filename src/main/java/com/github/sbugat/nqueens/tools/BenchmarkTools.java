package com.github.sbugat.nqueens.tools;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.sbugat.nqueens.GenericNQueensSolver;

public abstract class BenchmarkTools {

	public static long benchmark(final GenericNQueensSolver genericNQueensSolver, final int benchmarkNumber) throws InvalidSolutionsException {

		final List<Long> benckmark = new ArrayList<>();

		for (int runNumber = 0; runNumber < benchmarkNumber; runNumber++) {
			genericNQueensSolver.reset();
			final long startNanoTime = System.nanoTime();
			final long solutionCount = genericNQueensSolver.solve();
			final long endNanoTime = System.nanoTime();
			System.out.print('.');

			SequenceTools.checkSolutionsFound(genericNQueensSolver.getChessboardSize(), solutionCount);

			benckmark.add(Long.valueOf(endNanoTime - startNanoTime));
		}

		// Exclude 20% slowest and 20% fastest benchmarks (example: for 5 benchmarks, exlude the fastest and slowest benchmark)
		final int numberExcludedRun = benchmarkNumber / 5;

		// Calculate and return the average time of the 60% mid-range
		BigInteger timeSum = BigInteger.valueOf(0L);
		Collections.sort(benckmark);
		int includedRunCount = 0;
		for (int runNumber = numberExcludedRun; runNumber < benchmarkNumber - numberExcludedRun; runNumber++) {
			timeSum = timeSum.add(BigInteger.valueOf(benckmark.get(runNumber).longValue()));

			includedRunCount++;
		}

		return timeSum.divide(BigInteger.valueOf(includedRunCount)).longValue();
	}

	public static String nanoSecondsToString(final long nanoSeconds) {

		if (nanoSeconds < 1_000L) {
			return nanoSeconds + " ns"; //$NON-NLS-1$
		}

		final BigDecimal microSeconds = BigDecimal.valueOf(nanoSeconds).divide(BigDecimal.valueOf(1_000L)).setScale(2, RoundingMode.HALF_EVEN);
		if (microSeconds.longValue() < 1_000L) {
			return microSeconds.toPlainString() + " µs"; //$NON-NLS-1$
		}

		final BigDecimal milliSeconds = BigDecimal.valueOf(nanoSeconds).divide(BigDecimal.valueOf(1_000_000L)).setScale(2, RoundingMode.HALF_EVEN);
		if (milliSeconds.longValue() < 1_000L) {
			return milliSeconds.toPlainString() + " ms"; //$NON-NLS-1$
		}

		final BigDecimal seconds = BigDecimal.valueOf(nanoSeconds).divide(BigDecimal.valueOf(1_000_000_000L)).setScale(2, RoundingMode.HALF_EVEN);
		System.out.println(seconds.longValue());
		if (seconds.longValue() < 60L) {
			return seconds.toPlainString() + " s"; //$NON-NLS-1$
		}

		final BigDecimal minutes = BigDecimal.valueOf(nanoSeconds).divide(BigDecimal.valueOf(60_000_000_000L), RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN);
		if (minutes.longValue() < 60) {
			return StringUtils.EMPTY + minutes.longValue() + DecimalFormatSymbols.getInstance().getDecimalSeparator() + minutes.remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(3)).divide(BigDecimal.valueOf(5), RoundingMode.HALF_EVEN).movePointRight(minutes.scale()) + " m"; //$NON-NLS-1$
		}

		final BigDecimal hours = BigDecimal.valueOf(nanoSeconds).divide(BigDecimal.valueOf(3_600_000_000_000L)).setScale(2, RoundingMode.HALF_EVEN);
		return StringUtils.EMPTY + hours.longValue() + DecimalFormatSymbols.getInstance().getDecimalSeparator() + hours.multiply(BigDecimal.valueOf(3)).divide(BigDecimal.valueOf(5)).remainder(BigDecimal.ONE) + " h"; //$NON-NLS-1$
	}
}
