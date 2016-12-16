package lesson3;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rx.Observable;

import util.RxMockito;

public class GeneratorTest {

	private Generator myGenerator = new MyGenerator();

	@Before
	public void setup() {
	}

	/**
	 * Verify that if we provide a list of numbers, our number generated emits these numbers
	 * one after the other.
	 */
	@Test
	public void testGenerateNumbers() {
		List<Integer> sourceList = Arrays.asList(1, 1, 2, 3, 5, 8, 11);
		Observable<Integer> myNumberGenerator = myGenerator.numberGenerator(sourceList);

		int expectedNumbersGenerated = sourceList.size();
		Integer expectedFirstNumber = sourceList.get(0);

		// util.RxMockito is our own internal Rx test utility.
		// You can pass an observable into it and make some assertions
		// to check if it has actually emitted the events that you expected.
		RxMockito.testObservable(myNumberGenerator, mySubscriber -> {

			// This should almost always be the first line (unless you are testing errors of course)
			mySubscriber.assertNoErrors();

			// Check that we got exactly sourceList.size() numbers: 1, 1, 2, ...
			Assert.assertEquals(expectedNumbersGenerated, mySubscriber.countEvents());

			// Check that the first number we got is 1
			Assert.assertEquals(expectedFirstNumber, mySubscriber.firstEvent());
		});
	}

	/**
	 * Verify that our number generator
	 */
	@Test
	public void testNumbersWithPrefix() {
		List<Integer> sourceList = Arrays.asList(1, 1, 2, 3, 5, 8, 11);
		Observable<String> myStringGenerator = myGenerator.generatorWithPrefix("Int::", myGenerator.numberGenerator(sourceList));

		int expectedNumbersGenerated = sourceList.size();
		String expectedFirst = "Int::" + sourceList.get(0);
		List<String> expectedEvents = Arrays.asList("Int::1", "Int::1", "Int::2", "Int::3", "Int::5", "Int::8", "Int::11");

		// util.RxMockito is our own internal Rx test utility.
		// You can pass an observable into it and make some assertions
		// to check if it has actually emitted the events that you expected.
		RxMockito.testObservable(myStringGenerator, mySubscriber -> {

			// This should almost always be the first line (unless you are testing errors of course)
			mySubscriber.assertNoErrors();

			// Check that we got exactly sourceList.size() numbers: 1, 1, 2, ...
			Assert.assertEquals(expectedNumbersGenerated, mySubscriber.countEvents());

			// Check that the first prefix + number combination we got is Int::1
			Assert.assertEquals(expectedFirst, mySubscriber.firstEvent());

			// Check all events
			Assert.assertEquals(expectedEvents, mySubscriber.getOnNextEvents());
		});
	}

	@Test
	public void testGenerateNumbersWithPrefix() {
		List<Integer> sourceList = Arrays.asList(1, 1, 2, 3, 5, 8, 11);
		Observable<String> myStringGenerator = myGenerator.generatorWithPrefix("Int::", sourceList);

		int expectedNumbersGenerated = sourceList.size();
		String expectedFirst = "Int::" + sourceList.get(0);
		List<String> expectedEvents = Arrays.asList("Int::1", "Int::1", "Int::2", "Int::3", "Int::5", "Int::8", "Int::11");

		// util.RxMockito is our own internal Rx test utility.
		// You can pass an observable into it and make some assertions
		// to check if it has actually emitted the events that you expected.
		RxMockito.testObservable(myStringGenerator, mySubscriber -> {

			// This should almost always be the first line (unless you are testing errors of course)
			mySubscriber.assertNoErrors();

			// Check that we got exactly sourceList.size() numbers: 1, 1, 2, ...
			Assert.assertEquals(expectedNumbersGenerated, mySubscriber.countEvents());

			// Check that the first prefix + number combination we got is Int::1
			Assert.assertEquals(expectedFirst, mySubscriber.firstEvent());

			// Check all events
			Assert.assertEquals(expectedEvents, mySubscriber.getOnNextEvents());
		});
	}

	@Test
	public void testGenerateOddEvenNumbers() {
		List<Integer> even = Arrays.asList(0, 2, 4, 6, 8);
		List<Integer> odd = Arrays.asList(1, 3, 5, 7, 9);

		Observable<Integer> generator =
				myGenerator.combineGenerators(myGenerator.numberGenerator(even), myGenerator.numberGenerator(odd));
		int expectedNumbersGenerated = even.size() + odd.size();
		List<Integer> expectedEvents = Arrays.asList(0, 2, 4, 6, 8, 1, 3, 5, 7, 9);

		// util.RxMockito is our own internal Rx test utility.
		// You can pass an observable into it and make some assertions
		// to check if it has actually emitted the events that you expected.
		RxMockito.testObservable(generator, mySubscriber -> {

			// This should almost always be the first line (unless you are testing errors of course)
			mySubscriber.assertNoErrors();

			// Check that we got exactly sourceList.size() numbers: 1, 1, 2, ...
			Assert.assertEquals(expectedNumbersGenerated, mySubscriber.countEvents());

			// Check all events
			Assert.assertEquals(expectedEvents, mySubscriber.getOnNextEvents());
		});
	}
	
	@Test
	public void testGenerateNumbersWithAllPrefixes() {
		List<Integer> sourceList = Arrays.asList(1, 2, 3);
		List<String> prefixes = Arrays.asList("Num::", "Int::");
		Observable<String> myStringGenerator = myGenerator.generateNumbersWithAllPrefixes(prefixes, sourceList);

		int expectedNumbersGenerated = sourceList.size() * 2;
		String expectedFirst = "Num::" + sourceList.get(0);
		List<String> expectedEvents = Arrays.asList("Num::1", "Int::1", "Num::2", "Int::2", "Num::3", "Int::3");

		// util.RxMockito is our own internal Rx test utility.
		// You can pass an observable into it and make some assertions
		// to check if it has actually emitted the events that you expected.
		RxMockito.testObservable(myStringGenerator, mySubscriber -> {

			// This should almost always be the first line (unless you are testing errors of course)
			mySubscriber.assertNoErrors();

			// Check that we got exactly sourceList.size() numbers: 1, 1, 2, ...
			Assert.assertEquals(expectedNumbersGenerated, mySubscriber.countEvents());

			// Check that the first prefix + number combination we got is Int::1
			Assert.assertEquals(expectedFirst, mySubscriber.firstEvent());

			// Check all events
			Assert.assertEquals(expectedEvents, mySubscriber.getOnNextEvents());
		});
	}

	@Test
	public void testNucleotides() {
		List<String> fragments = Arrays.asList("ACCGT", "GTGTCC", "CACAGG", "TGGCA");
		Observable<String> myNucleotides = myGenerator.nucleotides(fragments);

		int expectedNrOfNucleotides = 22;
		String expectedFirst = "A";
		List<String> expectedEvents = Arrays.asList("A", "C", "C", "G", "T", "G", "T", "G", "T", "C", "C", "C", "A", "C", "A", "G", "G", "T", "G", "G", "C", "A");

		// util.RxMockito is our own internal Rx test utility.
		// You can pass an observable into it and make some assertions
		// to check if it has actually emitted the events that you expected.
		RxMockito.testObservable(myNucleotides, mySubscriber -> {

			// This should almost always be the first line (unless you are testing errors of course)
			mySubscriber.assertNoErrors();

			// Check that we got exactly sourceList.size() numbers: 1, 1, 2, ...
			Assert.assertEquals(expectedNrOfNucleotides, mySubscriber.countEvents());

			// Check that the first prefix + number combination we got is Int::1
			Assert.assertEquals(expectedFirst, mySubscriber.firstEvent());

			// Check all events
			Assert.assertEquals(expectedEvents, mySubscriber.getOnNextEvents());
		});
	}


}