package lesson3;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class MyGenerator implements Generator {
	
	@Override
	public Observable<Integer> numberGenerator(List<Integer> sourceList) {
		// FIXME: create observables directly from a list of values
		return Observable.empty();
	}

	@Override
	public Observable<String> generatorWithPrefix(String prefix, Observable<Integer> sourceList) {
		// FIXME: transform an observable of integers into an observable of string
		//        browse the api documentation: http://reactivex.io/RxJava/javadoc/rx/Observable.html
		return Observable.empty();
	}

	@Override
	public Observable<String> generatorWithPrefix(String prefix, List<Integer> sourceList){
		// FIXME: this is somewhat a combination of the previous two exercises
		return Observable.empty();
	}

	@Override
	public Observable<Integer> combineGenerators(Observable<Integer> generator1, Observable<Integer> generator2) {
		// FIXME: One of the main advantages of Observables over Futures is that they 
		// are composable. There are many operators that transform observables into new observables.
		// Find one that combines the two input generators (note: also look at the expected output from the javadoc
		// to identify which combinator you need to use here)
		// http://reactivex.io/RxJava/javadoc/rx/Observable.html
		return Observable.empty();
	}

	@Override
	public Observable<String> generateNumbersWithAllPrefixes(List<String> prefixes, List<Integer> sourceList){
		// FIXME: generate a many-to-many combination of the two lists
		// It's mandatory to use the withAllPrefixes method somehow, so look at the signature of
		// that method and then fit in your implementation
		return Observable.empty();
	}

	public Observable<String> nucleotides(List<String> dnaFragments){
		// FIXME: This is special because each item in the source list is mapped to another list
		// so we get [AB, CD] -> [[A, B], [C, D]]
		// but in the end we just need [A, B, C, D]
		return Observable.empty();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	/**
	 * This will take a number and a list of prefixes and generate an output that emits a list of strings
	 * where each prefix is applied to the number so
	 * 
	 * input: 1, "A", "B"
	 * output: "A1", "A2"
	 * 
	 * @param number
	 * @param prefixes
	 * @return
	 */
	private Observable<String> withAllPrefixes(Integer number, List<String> prefixes){
		return Observable.from(prefixes).map(prefix -> prefix + number);
	}

	/**
	 * This will take a piece of text as input (presumably containing only nucleotide chars such as A C G T)
	 * and then return a list of individual chars (wrapped in a string) from it so
	 * 
	 * input: "AAACGTG"
	 * output: "A", "A", "A", "C", "G", "T", "G" 
	 * @param text
	 * @return
	 */
	private Observable<String> textToNucleotides(String text){
		char[] nucleotides = text.toCharArray();
		List<String> nucleotidesList = new ArrayList<>();
		for(char ch: nucleotides){
			nucleotidesList.add(String.valueOf(ch));
		}
		return Observable.from(nucleotidesList);
	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

}
