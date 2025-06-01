** Shared Kotlin Multiplatform module **
===

This is a shared Kotlin Multiplatform module that can be used in both Android and iOS applications.
It contains common code that can be reused across platforms, such as data models, business logic,
and utility functions.

## Basic package structure

* package `internal` - Contains internal classes and functions that are **not** exposed to the public API.
* everything outside of `internal` package is considered as the public API.
