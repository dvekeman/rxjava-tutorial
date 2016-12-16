---
title: Reactive Extensions (Rx) - Lesson 4 - Subscribe to Observables
author: Dieter Vekeman
---

# Lesson 4

In the previous lesson we've seen how to create a stream of data, here we focus on the receiver side. In Rx terminology this means subscribing to an Observable.

Contrary to the previous excercise where you were given a set of junit tests, here we'll work on the unit tests. Take a brief look at the LoremIpsumModel class. There is not much to it. It just emits the text of Lorem Ipsum as a stream of words.

In the unit tests we need to subscribe to these observables, that's your task. Afterwards there are some asserts which verify if your subscription worked. You shouldn't have to touch these.

Look for the FIXMEs

Good luck.

```
 ______________________
< git checkout lesson5 >
 ----------------------
        \   ^__^
         \  (oo)\_______
            (__)\       )\/\
                ||----w |
                ||     ||
```
