package lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rx.Observable;

public class LoremIpsumModelTest {
	
	private LoremIpsumModel loremIpsumModel;
	
	@Before
	public void setup(){
		loremIpsumModel = new LoremIpsumModel();
	}
	
	@Test
	public void testLoremIpsumSubscription(){
		List<String> loremIpsumWordList = new ArrayList<>();

		Observable<String> loremIpsumWordsStream = loremIpsumModel.loremIpsumWordsStream();
		
		// FIXME: subscribe to the stream of words and add them in the list
		
		// Some asserts, don't touch :-)
		Assert.assertEquals(69, loremIpsumWordList.size());
		
		String reconstruction = loremIpsumWordList.stream().collect(Collectors.joining(" "));
		Assert.assertEquals(LoremIpsumModel.LOREM_IPSUM, reconstruction);
	}
	
	@Test
	public void testAnnoyingLoremIpsumSubscription(){
		List<String> loremIpsumWordList = new ArrayList<>();

		Observable<String> loremIpsumWordsStream = loremIpsumModel.annoyingLoremIpsumWordsStream();
		List<Throwable> throwables = new ArrayList<>();

		// FIXME: subscribe to the stream of words and add them in the list
		//        however the stream will, at some point throw an exception.
		//        Add that exception to the throwables list
		
		// Some asserts, don't touch :-)
		Assert.assertEquals(53, loremIpsumWordList.size());

		String actualReceived = loremIpsumWordList.stream()
				.limit(53)
				.collect(Collectors.joining(" "));
		String expectedReceived = Arrays.asList(LoremIpsumModel.LOREM_IPSUM.split(" ")).stream()
				.limit(53)
				.collect(Collectors.joining(" "));
		Assert.assertEquals(expectedReceived, actualReceived);
	}
}
