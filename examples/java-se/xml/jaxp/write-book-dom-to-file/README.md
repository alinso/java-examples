# Write book DOM to file

This example shows how to use the **JAXP** API (see the [official tutorial](http://docs.oracle.com/javase/tutorial/jaxp/index.html))
to create a "book" document using the **DOM** (Document Object Model) interface.

After that the DOM of the book is created in memory, it is written to a file
using the Transformation API (also part of JAXP).

The output of this example is a `book.xml` file with the following content:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<book edition="4th" isbn10="0-321-34980-6" isbn13="978-0-321-34980-4" pages="928" title="The Java Programming Language">
<description>The fourth edition of "The Java Programming Language" directly from the creators of the Java programming language.</description>
<author firstName="Ken" lastName="Arnold"/>
<author firstName="James" lastName="Gosling"/>
<author firstName="David" lastName="Holmes"/>
</book>
```

Note: depending on the JAXP implementation, the formatting may be slightly different.

### Requirements

* Java 1.4 or higher
