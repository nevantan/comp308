# TME4 Questions

GitHub Repo: https://github.com/nevantan/comp308

Project Folder: https://github.com/nevantan/comp308/tree/master/tme3

## Unit 8: Section 4, Learning Objective 1, Question 1

**Prompt:** According to the textbook author, what are the reasons that the applet revolution did not happen?

1. Most machines don't include the necessary Java software to run applets.
2. Downloading and installing 10MB+ packages are no something you would want to do on the web.
3. Users wouldn't want to or are afraid for various reasons including security.

Along with these points, applet technology has largely been superceeded by huge advances in web technologies
like HTML5 and JavaScript. Oracle has deprecated applets as of JDK 9 (released 2017-09-21).

## Unit 9: Section 1, Learning Objective 2, Question 5

**Prompt:** What is the effect of calling `yield()`?

A call to `yield()` sends a signal that indicates that threads of the same priority may now be run. Note that
this is only a hint to the task scheduler and may be ignored.
