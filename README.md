# circle-ci-test

## Development overview

This project uses the [*MVVM*](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) *+ LiveData* architecture.

The business logic MUST be written in `Repository`. And the logic around `View` MUST be written in each `ViewModel` classes.

These code MUST be written by **Pure Java(Kotlin)** without Android, except `LiveData` classes and parent `ViewModel` class inherited by each `ViewModel` classes.
It means that these code can be test using UnitTest without any Android devices or the emulator.

And these code SHOULD covers almost logics of this project.
It means that testing these code (`Repositoty` and `ViewModel`) is nearly enough for testing this project.
It is the policy of this project code.

Of cource, you write paired many test code when you write implementation code.  
In that case, you should write most of its test code in `src/test/java` (UnitTest), NOT `src/androidTest/java` (InstrumentedTest).
If you cannot do it, please remember above policies.
Maybe something wrong.

## Packages

### `<app-package>.data.**`

Here is what is called the *Model*.  
These packages include business logic and datasource access.

#### `<app-package>.data`

***Written In PURE Java(Kotlin)***

The entities are placed here. These have some annotations of [`Room`](https://developer.android.com/jetpack/androidx/releases/room).

#### `<app-package>.data.source`

***Written In PURE Java(Kotlin)***

##### `AppRepository`

The interface of `Repository`.
You can create mock from this when you testing.

##### `AppRepositoryImpl`

This is an implementation of business logic.

You MUST NOT write code for I/O, database or API.
These code MUST write in `data.source.local` or `data.source.remote`, and inject here.
This class MUST be written by **Pure Java(Kotlin)**, NOT depends on Android.
This class (business logic) can test by Unit Test with mock easily, without Android and I/O.

Of cource, it does not mean that all business logics must be written in one file.
You SHOULD sepalete classes and files properly. Huge classes or files are NOT recommended.
All classes belonging to `Repository` MUST NOT include code of I/O, and these MUST be written by **Pure Java(Kotlin)**, and they MUST NOT have any indivisual referrences to/from `View` group classes, only `AppRepository` is an interface to/from other groups.

#### `<app-package>.data.source.local`

***Written In DIRTY Java(Kotlin)***

This is an implementation of database access using [`Room`](https://developer.android.com/jetpack/androidx/releases/room).
This class MUST be kept simple, it SHOULD NOT include any logics.
The logic SHOULD be written in `Repository`.

##### `LocalDataSource`

The interface of local datasource access.
When you test the repository or the app, you can create mock from this interface.
You can create the mock with **Pure Java(Kotlin)**. It means that you can test `Repository` by UnitTest without Android.
It is useful for testing a delay response too.

### `<app-package>.di.**`

***Written In DIRTY Java(Kotlin)***

This is an implementation for injection(Dagger2).

### `<app-package>.ui.**`

Here is what is called the *View and ViewModel*.

#### `<app-package>.ui`

***Written In DIRTY Java(Kotlin)***

Common classes or functions for views.

#### `<app-package>.ui.<view-name>`

This is an implementation for view and view model of `<view-name>`.

##### `<view-name>ViewModel`

***Written In PURE Java(Kotlin)***

This is an implementation of all logic of `<view-name>Activity` and `<view-name>Fragment`.
You MUST NOT write code depends on Android, except `LiveData` and `ViewModel`.
This class (all logic about view) can test by Unit Test with mock easily, without Android device or emulator.

Of cource, it does not mean that all view logics must be written in one file.
You SHOULD sepalete classes and files properly. Huge classes or files are NOT recommended.
All classes belonging to `ViewGroup` MUST be written by **Pure Java(Kotlin)**.

##### `<view-name>Activity`, `<view-name>Fragment`, and its layout files

***Written In DIRTY Java(Kotlin)***

These are the surfaces and the designs of each views. These implementations SHOULD NOT include any logics, except the procedures depend on Android.
