# Introduction and Goals

## Requirement Overview

The `fs-api` provides a REST API for the
[FatSecret](https://fatsecret.com) service. It provides two endpoints:

* searching for food items based on a query string
* displaying an individual food item

## Quality Goals

* Security: The credentials for the FatSecret service must not be stored
  in plain text, e.g. in a configuration file.

# Constraints

* Java
* Spring Boot
* [fatsecret4j](https://www.fatsecret4j.com/)

# Context and Scope

```
+------+   search    +--------+   search    +-----------+
| User | ----------> | fs-api | ----------> | FatSecret |
+------+  GET food   +--------+  GET food   +-----------+
```

# Building Block View

```
+----------------+  search     +-------------+  search     +------------------+
| FoodController | ----------> | FoodService | ----------> | FatsecretService |
+----------------+  GET food   +-------------+  GET food   +------------------+
```

* The extrnal interfaces are provided by the `FoodController` class.
* The `FoodController` forwards the external requests to the
  `FoodService` class.
* The `Foodservice` makes use of the `FatsecretService` provided by
  `fatsecret4j`.

REST API:
* `/api/v1/food/?q=<queryString>` for searching
* `/api/v1/food/<foodId>` for getting a specific food item

# Deployment View

For deployment `fs-api` can be packaged as a docker container. The
docker container requires the following run-time configuration:

* port forwarding for port 8080
* environment variables `FATSECRET_CONSUMER_KEY` and
  `FATSECRET_CONSUMER_SECRET` containing the OAuth 1 credentials for the
  FatSecret service.

# Crosscutting Concerns

## Loose Coupling

Components should be loosly coupled using the dependency injection
facilities offered by the Spring (Boot) framework.

# Architectural Decisions

## REST or RESTful?

To produce a truely RESTful API, one could start adding links using
Sprint HATEOS. This is currently not needed for any requirement and
therefore left for future enhancement when needed.


## Packaging strategy

I prefer packaging by component, i.e. `FoodController` and `FoodService`
go into one package `food` instead of two packages `controller(s)` and
`service(s)`. In a future version a `recipe` component could be added.


## Domain model

In principle one could develop a separate domain model for the service.
Given the current requirements the domain model provided by the
`CompactFood` and `Food` classes from `fatsecret4j` is sufficient.

# Risks and Technical dept

## Error handling

Error handling should be improved. For example, there is no check that
`FsApiApplication.initFatsecretService()` returns a valid
`FatsecretService` object.

## Tests

There are currently no automatic tests for `FoodController` and
`FoodService`.

## ~~Mesage on STDOUT during operations~~

When an individual food item with only a single `Serving` is requested,
the application prints `Servings not found` to `STDOUT`. In specting the
[fatsecret4j's source
code](https://github.com/fatsecret/fatsecret4j/blob/0cdeb5eb0af29f8dd0dfa0c6d90c4caf12731fb3/src/main/java/com/fatsecret/platform/utils/FoodUtility.java#L59-L65)
these messages are unavoidable and harmless.
