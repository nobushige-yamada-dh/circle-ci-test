# circle-ci-test

## Development overview

This project uses the [*MVVM*](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) *+ LiveData* architecture.

The business logic MUST be written in `Repository` class. And the logic around `View` MUST be written in each `ViewModel` classes.

These code MUST be written by **Pure Java(Kotlin)** without Android, except `LiveData` classes and parent `ViewModel` class inherited by each `ViewModel` classes. It means that these code can be test using UnitTest without any Android devices or the emulator.

And these code SHOULD covers almost logics of this project. It means that testing these code (`Repositoty` and `ViewModel`) is nearly enough for testing this project. It is the policy of this project code.

Of cource, you write paired many test code when you write implementation code.  
In that case, you should write most of its test code in `src/test/java` (UnitTest), NOT `src/androidTest/java` (InstrumentedTest). If you cannot do it, please remember above policies. Maybe something wrong.
