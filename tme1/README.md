# TME1 Questions
GitHub Repo: https://github.com/nevantan/comp308

Project Folder: https://github.com/nevantan/comp308/tree/master/tme1

## Unit 1: Section 1, Learning Objective 1
**Prompt:** Review the checklist and install the software if necessary.

This isn't exactly a question but I thought my process was interesting enough to
document for this assignment.

The checklist in the article provided lists two requirements:
1. The Java SE Development Kit 8 (JDK 8)
2. A text editor. 

Initially, I had assumed I already had the JDK installed. However, after attempting to
run `javac -version`, I realized that this was not the case. For reference, my desktop
is running Ubuntu 16.10. As I am planning to reformat my desktop soon, instead of setting
up the JDK 8, I decided to use Google Cloud Shell instead. As it turns out, the shell is
pre-configured for OpenJDK 8 and comes with an in-browser code editor, which satisfies
the second item on the checklist as well.

This approach allows me to keep all of my data in the cloud and access it from any device
so long as I have an internet connection.

## Unit 2: Section 2, Learning Objective 1, Question 3
**Prompt:** What are the nine primitive types supported by Java?

**boolean:** A true/false value

**char:** A single character such as 'a' or '1' (note that '1' is different than the
number 1). Represented by single quotes in Java.

**byte:** An integer value that takes up 8 bits to represent values from -128 to 127.

**short:** An integer value that takes up 16 bits to represent values from -32768 to 32767.

**int:** An integer value that takes up 32 bits to represent values from -2147483648 to 2147483647.

**long:** An integer value that takes up 64 bits to represent values from -2^63 to (2^63)-1

**float:** A single-precision floating point value. A common usage is to store fractional values
that do not have too many decimal places.

**double:** A double-precision floating point value. The common usage for this primitive
is the same as a float but it allows for the storage of more decimal places and is thus
more precise at the cost of requiring twice as many bits of memory.

**void:** Despite being listed in the textbook as one of Java's "nine primitive types",
"void" is not actually a primitive. The Java language spec lists only eight primitive
types in [Section 4.2](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.2).

## Unit 3: Section 1, Learning Objective 1, Question 1

**Prompt:** What is the precedence among arithmetic operators?

Java operators have 16 levels of precedence where higher levels are resolved before lower
ones. Within levels, operators are resolved left-to-right. The 16 levels are as follows:

**Level 16:** [ ], ( ), .

**Level 15:** foo++, foo--

**Level 14:** Uniary operators (++foo, --foo, +, -, !, ~)

**Level 13:** Type casting (e.g. "(int)"), new

**Level 12:** Multiplicative (*, /, %)

**Level 11:** Additive (+, -), String concatenation (+)

**Level 10:** Bitwise shift (<<, >>, >>>)

**Level 9:** Relational (<, <=, >, >=, instanceof)

**Level 8:** Equality (==, !=)

**Level 7:** Bitwise AND (&)

**Level 6:** Bitwise XOR (^)

**Level 5:** Bitwise OR (|)

**Level 4:** Logical AND (&&)

**Level 3:** Logical OR (||)

**Level 2:** Ternary (?:, e.g. 5 >= 0 ? true : false)

**Level 1:** Assignment (=, +=, -=, *=, /=, %=, &=, ^=, |=, <<=, >>=, >>>=)

*Source:* https://introcs.cs.princeton.edu/java/11precedence/

In practical usage, it is typically better practice to make use of parentheses to enforce
order of operations as they are easier to reason about and do not require the reader to
have memorized the precedence list above.