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

# Architectural Decisions

## REST or RESTful?

To produce a truely RESTful API, one could start adding links using
Sprint HATEOS. This is currently not needed for any requirement and
therefore left for future enhancement when needed.


## Packaging strategy

I prefer packaging by component, i.e. `FoodController` and `FoodService`
go into one package `food` instead of two packages `controller(s)` and
`service(s)`. In a future version a `recipe` component could be added.
