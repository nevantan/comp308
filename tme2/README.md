# TME2 Questions

GitHub Repo: https://github.com/nevantan/comp308

Project Folder: https://github.com/nevantan/comp308/tree/master/tme2

## Unit 4: Section 1, Learning Objective 1, Question 1

**Prompt**: What are the four levels of access specification in Java?

*public*: The `public` keyword makes members accessible from anywhere to anything.

*private*: The `private` keyword is nearly the exact opposite of `public`, restricting
access to other members of the class.

*protected*: The `protected` keyword is similar to `private`, but it additionally
allows child classes to access protected members of the parent class.

*package*: The `package` keyword is used to divide classes themselves (not merely their
members) into packages, between which they cannot access each other.

## Unit 5: Section 1, Learning Objective 2, Question 2

**Prompt**: What is the purpose of an Iterator?

An Iterator is a design pattern the provides utility methods to make accessing and
manipulating elements of a collection in sequential order easier. Iterators will
typically provide a `next()` method which returns the next element in the collection
or false if there are no more, a `hasNext()` method which returns a boolean related to
whether there is another element to be fetched, and a `remove()` method to remove the
last fetched element from the collection.