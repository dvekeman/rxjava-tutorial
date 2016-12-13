---
title: Reactive Extensions (Rx) - Lesson 1 
author: Dieter Vekeman
---

# Lesson 1: (very) brief introduction to rx

- Nice features
- Enhanced version of the Observer pattern
- Implemented in many languages (.Net/C#; Java; Scala, 

However also...

- A bit complex (but you'll get used to it quickly)
- Hard to learn (although the documentation is quite good)
- ...

http://reactivex.io/intro.html

# So...

streams of asynchronous events

- asynchronous events could be
    - mouse clicks (ok/cancel but also user navigating through the workflow)
    - data entry
    - internal timer (scheduled task)

# Examples

Example1: 

- Event: User clicks on a button to save a form
- Outcome: Click is converted into a save request on the database

Example2: 

- Event: User enters a concentration in the QC table
- Outcome: 
    - concentration is evaluated against a threshold and - if needed - the flag icon is shown
    - concentration is saved into the database

Example3: Every 30 seconds we need to check the DA queue for new samples and send them to QCI-A

- Event: background timer sending an event every 30s
- Outcome: 
    - DA queue elements are queried in the database => List<QueueElement>
    - QueueElement is transformed into a QCI-A Sample request (Submit sample)
    - Each request is then asynchronously processed on a background thread

# Observables are composable

```
	public Observable<List<BottlePreparation>> getBottlePreparations() {
		return getSampleCount().map(sampleCount -> Lists.newArrayList(
			createBottlePreparation(1),
			createBottlePreparation(2),
			createBottlePreparation(3),
			createBottlePreparation(4)
	}
```

https://s3.amazonaws.com/uploads.hipchat.com/229500/2101849/mgJlFq3w5QotZH5/Screen_Shot_2016-12-16_at_13_49_05.png

# Observables in VAADIN

```
myOkButton.addClickListener(click -> {
  // do something upon OK click
});
```

-> the logic is bound to the click listener. In the best case, the developer dispatches to some controller. How would we access the controller? Wire it into the view?
-> Error handling is explicit: probably every implementation should use try...catch

# How to solve this the RX way?

```
// Note that a lot here is just boilerplate code but here we write it in full to show you how
// it works behind the scenes (and to prove that rx is overly complex)

// Instead of implementing the Click Listener right away we decouple two things
// - Producing / emitting click events
// - Consuming click events (and acting upon them)
public Observable<ClickEvent> myOkButtonClicks(){

	// we return _something that emits events_: an Observable
	// whenever _someone_ subscribes to it, the code inside the Action will be executed
	// Note that this is Java 8 lambda syntax: (subscriber -> {...})
	// the actual type of the lambda function that is being passed is
	// void call(Subscriber t)

	return Observable.<ClickEvent>create(subscriber -> {
		final ClickListener listener = subscriber::onNext;
		// This is Java 8 lambda syntax + method refererces
		// it's the same as
		// final ClickListener listener = new ClickListener() {
		//     public void click(ClickEvent event){
		//       ...
		//     }
		// };

		myOkButton.addClickListener(listener);
		// straightforward: we just add our listener to the button
	});
}
```

# Hiding boilerplate...

If we hide all the boilerplate code, this can be boiled down to

```
public Observable<ClickEvent> myOkButtonClicks(){
  return click(myOkButton);
}
```

Which is really a nice signature and a simple and easy to understand implementation. We now achieved 50% of our
goals: our view just emits some emits if a user clicks this particular button.

# From a client point of view

Anyone can ask our view to get notified about those events and (even better!) our view doesn't know / care who is subscribing to them.

```
public class SomeController{
  public void someControllerMethod(){
    view.myOkButtonClicks().subscribe(clickEvent -> {
        // The OK button was click so now I need to 
        // transfer the funds to Dieters account!
    });
  }
}
```

# Questions?

```
 _______________________________________
( Borrrrrring, let us get some hands on )
( dude                                  )
 ---------------------------------------
        o   ^__^
         o  (oo)\_______
            (__)\       )\/\
                ||----w |
                ||     ||
```

```
 ______________________
< git checkout lesson2 >
 ----------------------
        \   ^__^
         \  (oo)\_______
            (__)\       )\/\
                ||----w |
                ||     ||
```
