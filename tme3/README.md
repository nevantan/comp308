# TME3 Questions

GitHub Repo: https://github.com/nevantan/comp308

Project Folder: https://github.com/nevantan/comp308/tree/master/tme3

## Unit 6: Section 1, Learning Objective 2, Question 6

**Prompt:** What is the function of `instanceof`?

The `instanceof` keyword is a comparison operator between an object and a class. The result is
`true` if the given object is an instance of the specified class.

Example:
```
Foo foo = new Foo();
Bar bar = new Bar();
System.out.println(foo instanceof Foo); // True
System.out.println(foo instanceof Bar); // False
```

## Unit 7: Section 2, Learning Objective 2, Question 1

**Prompt:** Give an example of a URL and identify the protocol, host, filename, and port number.

**Format:** `[protocol]://[host]:[port]/[filename]`

### Example 1

https://google.ca/robots.txt

**Protocol:** HTTPS

**Host:** google.ca (172.217.1.3)

**Port:** 443 (default https)

**Filename:** robots.txt

### Example 2

ftp://127.0.0.1:2121/hello_world.txt

**Protocol:** FTP

**Host:** 127.0.0.1 (localhost)

**Port:** 2121

**Filename:** hello_world.txt