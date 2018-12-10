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
