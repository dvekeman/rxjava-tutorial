---
title: Reactive Extensions (Rx) - Lesson 3 - Observables + some first operators
author: Dieter Vekeman
---

# Lesson 3

In this lesson we focus only on how to create an Observable and how to transform them. One of the main advantages of Observables is that they are composable. The Rx library has a bunch of operators defined on an observable. Which you'll have to explore a bit.

I suggest that you try to use as much as possible the API (http://reactivex.io/RxJava/javadoc/rx/Observable.html) and take some time to try to understand the marble diagrams that you'll often see in the javadoc.

You get two things for free: an interface and some unit tests. Your job is to implement the methods in the MyGenerator.java

I suggest your start by running the unit tests (obviously they will all fail)

Either on the command line
```
./gradlew test
```

or in your IDE.

Look for the FIXMEs in the ```MyGenerator.java``` class.

```
 ______________________
< git checkout lesson4 >
 ----------------------
        \   ^__^
         \  (oo)\_______
            (__)\       )\/\
                ||----w |
                ||     ||
```
