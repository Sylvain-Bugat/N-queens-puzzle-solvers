package com.github.sbugat.nqueens.tools;

import java.lang.reflect.Field;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SequenceToolsTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCheckSolutionsFound() throws Exception {

		final Field solutionSequenceField = SequenceTools.class.getDeclaredField("SOLUTION_SEQUENCE");
		solutionSequenceField.setAccessible(true);
		final long[] solutionSequence = (long[]) solutionSequenceField.get(SequenceTools.class);

		for (int i = 1; i < solutionSequence.length; i++) {
			SequenceTools.checkSolutionsFound(i, solutionSequence[i]);
		}
	}

	@Test
	public void testGetExpectedSolutions() throws Exception {

		final Field solutionSequenceField = SequenceTools.class.getDeclaredField("SOLUTION_SEQUENCE");
		solutionSequenceField.setAccessible(true);
		final long[] solutionSequence = (long[]) solutionSequenceField.get(SequenceTools.class);

		for (int i = 1; i < solutionSequence.length; i++) {
			Assertions.assertThat(SequenceTools.getExpectedSolutions(i)).isEqualTo(solutionSequence[i]);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckSolutionsFoundZero() throws InvalidSolutionsException {

		SequenceTools.checkSolutionsFound(0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckSolutionsFoundMaxSequence() throws Exception {

		final Field solutionSequenceField = SequenceTools.class.getDeclaredField("SOLUTION_SEQUENCE");
		solutionSequenceField.setAccessible(true);
		final long[] solutionSequence = (long[]) solutionSequenceField.get(SequenceTools.class);

		SequenceTools.checkSolutionsFound(solutionSequence.length, 0);
	}

	@Test
	public void testCheckSolutionsFoundInvalidSolutionsException() throws InvalidSolutionsException {
		expectedException.expect(InvalidSolutionsException.class);
		expectedException.expectMessage("0 solutions found, expected: 1 for a chessboard of size 1");
		SequenceTools.checkSolutionsFound(1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetExpectedSolutionsZero() throws InvalidSolutionsException {

		SequenceTools.getExpectedSolutions(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetExpectedSolutionsMaxSequence() throws Exception {

		final Field solutionSequenceField = SequenceTools.class.getDeclaredField("SOLUTION_SEQUENCE");
		solutionSequenceField.setAccessible(true);
		final long[] solutionSequence = (long[]) solutionSequenceField.get(SequenceTools.class);

		SequenceTools.getExpectedSolutions(solutionSequence.length);
	}

}
