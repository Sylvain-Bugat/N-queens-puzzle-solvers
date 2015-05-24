package com.github.sbugat.nqueens.tools;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BenchmarkToolsTest {

	@Test
	public void testNanoSecondsToString() {

		Assertions.assertThat(BenchmarkTools.nanoSecondsToString(0L)).isEqualTo("0 ns");
		Assertions.assertThat(BenchmarkTools.nanoSecondsToString(999L)).isEqualTo("999 ns");

		Assertions.assertThat(BenchmarkTools.nanoSecondsToString(1000L)).isEqualTo(BigDecimal.valueOf(1.0).setScale(2) + " µs");
		Assertions.assertThat(BenchmarkTools.nanoSecondsToString(999_994L)).isEqualTo(BigDecimal.valueOf(999.99).setScale(2) + " µs");

		Assertions.assertThat(BenchmarkTools.nanoSecondsToString(999_995L)).isEqualTo(BigDecimal.valueOf(1.0).setScale(2) + " ms");
		Assertions.assertThat(BenchmarkTools.nanoSecondsToString(999_994_999L)).isEqualTo(BigDecimal.valueOf(999.99).setScale(2) + " ms");

		Assertions.assertThat(BenchmarkTools.nanoSecondsToString(999_995_000L)).isEqualTo(BigDecimal.valueOf(1.0).setScale(2) + " s");
		// Assertions.assertThat(BenchmarkTools.nanoSecondsToString(60_000_000_000L)).isEqualTo(BigDecimal.valueOf(1.0).setScale(2) + " s");
	}
}
