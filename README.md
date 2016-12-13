---
title: Reactive Extensions (Rx) - Lesson 2 - Gradle setup
author: Dieter Vekeman
---

# Lesson 2

# Prerequisites

Gradle needs to be installed on your system. If you run gradle --version it should output the gradle version and some more info. If that's not the case then install it first...

# Setup rx dependencies

Before we get hands-on rx we need to setup our project.

I've written some code but it doesn't compile, can you add the gradle wrapper?

# Task

The ultimate goal: make it compile using gradle and import the project into eclipse.

- Initilialize the gradle project (create the build.gradle, settings.gradle)
- Setup the rx dependencies (check mavenCentral). Note that we use rxjava, not rxjava2. Apart from that the version should not really matter much for this tutorial.
- Setup a gradle plugin for integration with eclispe

TIP: At some point you'll want to import the gradle project into your Eclipse workspace. There are a few possible ways to do that. Either you use some tooling in Eclipse. That's perfectly fine for me, however here the excercise is to use a gradle plugin that does the job of generating the .classpath and .project files.

Too easy?

```
 ______________________
< git checkout lesson3 >
 ----------------------
        \   ^__^
         \  (oo)\_______
            (__)\       )\/\
                ||----w |
                ||     ||
```
