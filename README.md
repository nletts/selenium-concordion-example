# selenium-concordion-example
Originally this I created this project simply to learn to use these two frameworks, but then a bigger picture formed:

## The Goal
* Ability to execute the tests across multiple web drivers at run time
* 'Headless' and 'Regression' modes. _Headless_ used on build server. _Regression_ used prior to release build and runs across all specified browsers
* Spring annotation configuration and integration
* Ability to run Concordion test in IDE and get errors via console (no need to generate report)


## Setup
Requires Java 1.7 and [Gradle 2.0+](https://gradle.org/)

For the web drivers you will need to download the related drivers/files:
* [PhantomJS](http://phantomjs.org/download.html)
* [Chrome](https://sites.google.com/a/chromium.org/chromedriver/downloads)
* [Firefox](https://getfirebug.com/downloads) - You'll need firebug

The default run mode is _Headless_:
```
gradle
```
To run in _Regression_ mode:
```
gradle regression
```