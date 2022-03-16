[![codecov](https://codecov.io/gh/santimattius/kotlin-hexagonal-architecture/branch/master/graph/badge.svg?token=BCIWKUK8RN)](https://codecov.io/gh/santimattius/kotlin-hexagonal-architecture) ![Quality Checks](https://github.com/santimattius/kotlin-hexagonal-architecture/actions/workflows/action.yml/badge.svg)

# kotlin-hexagonal-architecture
Hexagonal Architecture Project


## Layers

**Domain**

Concepts that are in our context (User, Product, Cart, etc), and business rules that are determined exclusively by us (domain services),

**Application**

The application layer is where the use cases of our application live (register user, publish product, add product to cart, etc).

**Infrastructure**

Code that changes based on external decisions. In this layer will live the implementations of the interfaces that we will define a domain level. That is, we will rely on the SOLID DIP to be able to decouple from external dependencies.
