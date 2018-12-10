# MyCV
A native Android app writtern entirely in Kotlin to showcase a Curriculum Vitae :page_facing_up:

## Why MVP?
1. Simplicity
2. Testability
3. Extensibility

## Architecture Diagram
<img src="https://github.com/bharatdodeja/MyCV/blob/master/MyCV_Architecture_Diagram.png" alt="Architecture Diagram"/>

## Why Reactive Extensions (Rx)?
Reactive Extensions makes asynchronous programming easy with observable streams especially event driven programs like Android app.

## JSON Resume
[JSON Resume](https://jsonresume.org) is a community driven open source initiative to create JSON-based standard for resumes.

## Testing
- JUnit tests for testing presenter, repository, data source
- Espresso tests for testing UI using Robot patterns and Kotlin DSL

## TODO v1.0
- [X] MVP with repository as architectural pattern
- [X] LifecycleObserver from [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) for making Presenter lifecycle aware
- [X] Reactive programming using Rx
- [X] User conventional way to inject dependencies into Activity
- [ ] Replace dependency injection framework with [Dagger 2](https://google.github.io/dagger/)
- [X] [Retrofit 2](https://github.com/square/retrofit) for networking with [OkHttp](https://github.com/square/okhttp) as client
- [ ] UI Tests using [Espresso](https://developer.android.com/training/testing/espresso/) and [Robot pattern](https://gist.github.com/bharatdodeja/ac001b6a24028bde56943ee40cab7dbd)
- [ ] Capture UI Test [Screenshots](https://developer.android.com/reference/android/support/test/runner/screenshot/Screenshot) using [Android Testing Support Library](https://android.github.io/android-test/)
- [ ] Stubed data for UI tests using [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
- [ ] Unit test presenters and repositories using [AndroidJUnitRunner](https://developer.android.com/training/testing/junit-runner)
- [ ] Mocking using [Mockito 2](https://github.com/mockito/mockito)
- [X] Use [JSON Resume](https://jsonresume.org) for showing JSON-based resumes
- [ ] CI/CD using [CircleCI](https://circleci.com/)
- [ ] Insall [Git hooks](https://www.atlassian.com/git/tutorials/git-hooks)
- [ ] Add open source license
- [X] Design architecture diagram for README
- [ ] Write blog post explaning the architecture and tools used

## TODO v2.0
- [ ] MVVM with LiveData, ViewModel and DataBinding
- [ ] ORM to support offline using [Room](https://developer.android.com/topic/libraries/architecture/room)
- [ ] Replace Dagger with [Koin](https://insert-koin.io/) as dependency injection framework
- [ ] Replace [AndroidJUnitRunner](https://developer.android.com/training/testing/junit-runner) with [JUnit4 rules with AndroidX Test](https://developer.android.com/training/testing/junit-rules)
