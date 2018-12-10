# MyCV
A native Android app written entirely in Kotlin to showcase a Curriculum Vitae :page_facing_up:

## Why MVP?
1. Simplicity
2. Testability
3. Extensibility

## Architecture Diagram
<img src="https://github.com/bharatdodeja/MyCV/blob/master/MyCV%20Architecture%20Diagram.jpg" alt="Architecture Diagram"/>

## Why Reactive Extensions (Rx)?
Reactive Extensions makes asynchronous programming easy with observable streams especially event driven programs like Android app.

## JSON Resume
- [JSON Resume](https://jsonresume.org) is a community driven open source initiative to create JSON-based standard for resumes.
- It is used as REST API to get CV data from backend.

## Testing

### Unit Tests
- JUnit tests for testing presenter, repository, data source

### Mocking
- Mocking of final classes and methods of Kotlin using mockito extension [mock-maker-inline](https://github.com/mockito/mockito/wiki/What%27s-new-in-Mockito-2#mock-the-unmockable-opt-in-mocking-of-final-classesmethods)

### UI Tests (Espresso + Robot Pattern) 
- [Espresso](https://developer.android.com/training/testing/espresso/) tests for testing UI using [Robot Pattern](https://gist.github.com/bharatdodeja/ac001b6a24028bde56943ee40cab7dbd) and Kotlin DSL

## Prerequisites
- It needs keystore.properties file, which is not published on git, to build the project. It contains base URL for the API.


### TODO v1.0
- [X] MVP with repository as architectural pattern
- [X] LifecycleObserver from [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) for making Presenter lifecycle aware
- [X] Reactive programming using Rx
- [X] User conventional way to inject dependencies into Activity
- [X] [Retrofit 2](https://github.com/square/retrofit) for networking with [OkHttp](https://github.com/square/okhttp) as client
- [X] UI Tests using [Espresso](https://developer.android.com/training/testing/espresso/) and [Robot pattern](https://gist.github.com/bharatdodeja/ac001b6a24028bde56943ee40cab7dbd)
- [X] Capture UI Test [Screenshots](https://developer.android.com/reference/android/support/test/runner/screenshot/Screenshot) using [Android Testing Support Library](https://android.github.io/android-test/)
- [X] Fake data source for easy UI testing
- [X] Unit test presenters and repositories using [AndroidJUnitRunner](https://developer.android.com/training/testing/junit-runner)
- [X] Mocking using [Mockito 2](https://github.com/mockito/mockito)
- [X] Use [JSON Resume](https://jsonresume.org) for showing JSON-based resumes
- [ ] CI/CD using [CircleCI](https://circleci.com/)
- [X] Add open source license
- [X] Design architecture diagram for README

### TODO v2.0
- [ ] MVVM with LiveData, ViewModel and DataBinding
- [ ] ORM to support offline using [Room](https://developer.android.com/topic/libraries/architecture/room)
- [ ] Stubbed data for UI tests using [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
- [ ] Replace Dagger with [Koin](https://insert-koin.io/) as dependency injection framework
- [ ] Replace [AndroidJUnitRunner](https://developer.android.com/training/testing/junit-runner) with [JUnit4 rules 
with AndroidX Test](https://developer.android.com/training/testing/junit-rules)
- [ ] Replace dependency injection framework with [Dagger 2](https://google.github.io/dagger/)
- [ ] Install [Git hooks](https://www.atlassian.com/git/tutorials/git-hooks)
- [ ] Write blog post explaining the architecture and tools used



### License

```
MIT License

Copyright (c) 2018 Bharat Dodeja

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
