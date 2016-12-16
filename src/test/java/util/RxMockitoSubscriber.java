package util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observers.TestSubscriber;

/**
 * TestSubscriber for Observables, don't instantiate this directly 
 * but use {@link RxMockito#testObservable(Observable, Action1)}
 * 
 * Here is a simple example where we test some observable from our production code. Our test code
 * consists of checking that there were no errors and asserting that the first event emitted on 
 * the Observable is true:
 * <pre>
 *   util.RxMockito.testObservable(someObservable, subscriber -> {
 *     subscriber.assertNoErrors();
 *     assertEquals(subscriber.firstEvent(), true);
 *   }
 * </pre>
 * @param <T> The type of the Observable
 */
public class RxMockitoSubscriber<T> extends Subscriber<T> {

	private TestSubscriber<T> subscriber;

	protected RxMockitoSubscriber(TestSubscriber subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public void onCompleted() {
		subscriber.onCompleted();
	}

	@Override
	public void onError(Throwable e) {
		subscriber.onError(e);
	}

	@Override
	public void onNext(T t) {
		subscriber.onNext(t);
	}

	/**
	 * 
	 * @return
	 */
	public T firstEvent() {
		return subscriber.getOnNextEvents().get(0);
	}

	/**
	 * {@link TestSubscriber#getOnNextEvents()}
	 */
	public List<T> getOnNextEvents() {
		return subscriber.getOnNextEvents();
	}

	/**
	 * Count the number of events that have been emitted on the observable
	 * @return
	 */
	public int countEvents(){
		return subscriber.getOnNextEvents().size();
	}

	/**
	 * {@link TestSubscriber#assertReceivedOnNext(List)}
	 */
	public void assertReceivedOnNext(List<T> items) {
		subscriber.assertReceivedOnNext(items);
	}

	/**
	 * {@link TestSubscriber#assertTerminalEvent()} 
	 */
	public void assertTerminalEvent() {
		subscriber.assertTerminalEvent();
	}

	/**
	 * {@link TestSubscriber#assertUnsubscribed()}
	 */
	public void assertUnsubscribed() {
		subscriber.assertUnsubscribed();
	}

	/**
	 * {@link TestSubscriber#assertNoErrors()}
	 */
	public void assertNoErrors() {
		subscriber.assertNoErrors();
	}


	/**
	 * {@link TestSubscriber#awaitTerminalEvent()}
	 */
	public void awaitTerminalEvent() {
		subscriber.awaitTerminalEvent();
	}

	/**
	 * {@link TestSubscriber#awaitTerminalEvent(long, TimeUnit)}
	 */
	public void awaitTerminalEvent(long timeout, TimeUnit unit) {
		subscriber.awaitTerminalEvent(timeout, unit);
	}

	/**
	 * {@link TestSubscriber#awaitTerminalEventAndUnsubscribeOnTimeout(long, TimeUnit)}
	 */
	public void awaitTerminalEventAndUnsubscribeOnTimeout(long timeout, TimeUnit unit) {
		subscriber.awaitTerminalEventAndUnsubscribeOnTimeout(timeout, unit);
	}

	/**
	 * In case the underlying TestSubscriber is needed for some more advanced stuff or 
	 * things not covered by this API.
	 * 
	 * @return The underlying test subscriber
	 */
	public TestSubscriber<T> testSubscriber(){
		return subscriber;
	}
}
