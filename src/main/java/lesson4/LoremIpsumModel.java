package lesson4;

import rx.Observable;

public class LoremIpsumModel {
	
	public static final String LOREM_IPSUM = 
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et " +
					"dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
					"aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
					"cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in " +
					"culpa qui officia deserunt mollit anim id est laborum.";
	
	public Observable<String> loremIpsumWordsStream(){
		return Observable.from(LOREM_IPSUM.split(" "));
	}
	
	public Observable<String> annoyingLoremIpsumWordsStream(){
		return loremIpsumWordsStream().doOnNext(word -> {
			if("sint".equals(word)){
				throw new RuntimeException("Random failure in the stream :-(");
			}
		});
	}
}
