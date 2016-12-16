package lesson3;

import java.util.List;

import rx.Observable;

public interface Generator {

	/**
	 * LEVEL: EASY
	 * <p>
	 * Create a new observable from a list of numbers
	 *
	 * @param sourceList
	 * @return
	 */
	Observable<Integer> numberGenerator(List<Integer> sourceList);

	/**
	 * LEVEL: EASY
	 * <p>
	 * <p>
	 * Input:
	 * prefix: "A"
	 * sourceList: 1, 2, 3
	 * <p>
	 * Expected output: A1, A2, A3
	 *
	 * @param sourceList
	 * @return
	 */
	Observable<String> generatorWithPrefix(String prefix, Observable<Integer> sourceList);

	/**
	 * LEVEL: EASY
	 * <p>
	 * Input:
	 * prefix: "A"
	 * sourceList: 1, 2, 3
	 * <p>
	 * Expected output: A1, A2, A3
	 *
	 * @param prefix
	 * @param sourceList
	 * @return
	 */
	Observable<String> generatorWithPrefix(String prefix, List<Integer> sourceList);

	/**
	 * LEVEL: INTERMEDIATE
	 * <p>
	 * Given two observables which both emit numbers
	 * combine them into an observable which emits all numbers
	 *
	 * @param generator1
	 * @param generator2
	 * @return
	 */
	Observable<Integer> combineGenerators(Observable<Integer> generator1, Observable<Integer> generator2);

	/**
	 * LEVEL: HARD
	 * <p>
	 * For each number in the source list, transform it into a combination of the number and *all* prefixes.
	 * So
	 * prefixes: "A", "B", "C"
	 * number  : 1, 2, 3
	 * <p>
	 * Expected output A1, B1, C1, A2, B2, C2, A3, B3, C3
	 *
	 * @param prefixes
	 * @param sourceList
	 * @return
	 */
	Observable<String> generateNumbersWithAllPrefixes(List<String> prefixes, List<Integer> sourceList);

	/**
	 * LEVEL: HARD
	 * 
	 * Given a list of DNA fragments return a concatenated stream of characters from these fragments one after the other
	 * so
	 * 
	 * input: ["AC", "GG", "ACCT"]
	 * output: "A", "C", "G", "G", "A", "C", "C", "T" 
	 * 
	 * 
	 * @param dnaFragments
	 * @return
	 */
	Observable<String> nucleotides(List<String> dnaFragments);

}
