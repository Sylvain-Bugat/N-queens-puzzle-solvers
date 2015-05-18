package com.github.sbugat.nqueens;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.sbugat.nqueens.solvers.bruteforce.BruteForceNQueensSolverArray;
import com.github.sbugat.nqueens.tools.SequenceTools;

public class GenericNQueensSolverTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void testNQueensSolver() throws IOException {

		final GenericNQueensSolver genericNQueensSolver = new BruteForceNQueensSolverArray(5, true);

		// Solve with the chessboard size and compare the number of solutions with the expected sequence
		final long solutionCount = genericNQueensSolver.solve();
		Assertions.assertThat(solutionCount).isEqualTo(SequenceTools.getExpectedSolutions(genericNQueensSolver.getChessboardSize()));

		// Compare standard output
		final byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/GenericNQueensSolverTest-expected-output.txt"));
		Assertions.assertThat(outContent.toString()).isEqualTo(new String(encoded, StandardCharsets.ISO_8859_1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNQueensSolverIllegalArgumentException() {

		new BruteForceNQueensSolverArray(0, true);
	}
}
