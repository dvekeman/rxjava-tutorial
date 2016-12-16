package util;

import org.mockito.Mockito;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.observers.TestSubscriber;
import rx.subjects.ReplaySubject;

/**
 * See documentation at 
 * https://confluence.genohm.com/display/MID/Unit+Testing+with+Observables
 */
public class RxMockito {

	/**
	 * Similar to standard Mockito.when but specifically targeted at simulating observable behaviour.
	 * 
	 * The call to be simulated is passed as an argument and a Subject is returned. Apply your simulated behaviour
	 * to this subject. Every subscription to the observable will get a replay of all the simulations.
	 * 
	 * Example (see Example' below for a more optimized syntax):
	 * <pre>
	 *   // We want to simulate the ExperimentRunStepModel#getLinkedContent observable
	 *   ReplaySubject s = util.RxMockito.obsWhen(
	 *     () -> experimentRunStepContentModel.getLinkedContent()
	 *   );
	 *   // Next we simulate some onNext calls to this subject so that subscribers can be tested.
	 *   s.onNext(contentList);
	 * </pre>
	 * 
	 * Example': Same as above but rewritten using a more concise syntax
	 * <pre>
	 *   util.RxMockito.obsWhen(() -> experimentRunStepContentModel.getLinkedContent()).onNext(contentList);
	 * </pre>
	 * 
	 * @param call The call to intercept
	 * @param <E>  The 
	 * @return
	 */
	public static <E> ReplaySubject<E> obsWhen(Func0<Observable<E>> call) {
		if (call.call() == null) {
			Mockito.when(call.call()).thenReturn(ReplaySubject.create());
		}
		return (ReplaySubject<E>) call.call();
	}

	/**
	 * Test method for observables: pass in an observable and a lambda with the actions that need to be
	 * run against it as a subscriber (checks, asserts, ...).
	 * <br>
	 * Here is a simple example where we test some observable from our production code. Our test code
	 * consists of checking that there were no errors and asserting that the first event emitted on 
	 * the Observable is true:
	 * <pre>
	 *   util.RxMockito.testObservable(someObservable, subscriber -> {
	 *     subscriber.assertNoErrors();
	 *     assertEquals(subscriber.firstEvent(), true);
	 *   }
	 * </pre>
	 * @param observable The source observable (typically from production code)
	 * @param action1    The asserts, checks, ... that need to be applied to the subscriber. For example:
	 *                   <pre>
	 *                   subscriber.assertNoErrors();
	 *                   assertEquals(subscriber.firstEvent(), ...)
	 *                   subscriber.assertUnsubscribed
	 *                   ...
	 *                   </pre>
	 * @param <E>        The type of the Observable
	 * @return           In most cases you won't need the subscriber that is returned here as you should use 
	 *                   the action to run the checks.
	 *                   However in some advanced or common cases it might be useful to access the test subscriber
	 *                   directly. Note that we return our own {@link RxMockitoSubscriber} which itself wraps a 
	 *                   {@link TestSubscriber} which can be accessed by calling {@link RxMockitoSubscriber#testSubscriber()}
	 */
	public static <E> Subscriber<E> testObservable(Observable<E> observable, Action1<RxMockitoSubscriber<E>> action1){
		RxMockitoSubscriber<E> subscriber = new RxMockitoSubscriber<>(new TestSubscriber<>());
		observable.subscribe(subscriber);
		action1.call(subscriber);
		return subscriber;
	}

}
