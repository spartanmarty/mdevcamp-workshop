** Shared Kotlin Multiplatform module **
===

This is a shared Kotlin Multiplatform module that can be used in both Android and iOS applications.
It contains common code that can be reused across platforms, such as data models, business logic,
and utility functions.

## Basic package structure

* package `internal` - Contains internal classes and functions that are **not** exposed to the public API.
* everything outside of `internal` package is considered as the public API.

## Parking API

We are using third party API for the parking data. There are few easy steps to get it working:

* Create an account on [Golemio API](https://api.golemio.cz/docs/public-openapi/) and follow the instructions
in **General info -> Authorization**
* Once you have your own API key, replace `GOLEMIO_API_KEY` inside the `NetworkExecutor` class
