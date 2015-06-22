# Books I/O With JAXB

This example shows how to use the **JAXB** API (see the [official tutorial](http://docs.oracle.com/javase/tutorial/jaxb/index.html))
to *marshal* (serialize into XML) and *unmarshal* (deserialize from XML) a
"books" objects model (`Books`/`Book`/`Author` classes) to/from a file.

Given as input the `books-input.xml` file with the following content:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<books>
    <book title="The Java Programming Language" edition="4th"
          isbn10="0-321-34980-6" isbn13="978-0-321-34980-4"
          pages="928">
        <author firstName="Ken"   lastName="Arnold"/>
        <author firstName="James" lastName="Gosling"/>
        <author firstName="David" lastName="Holmes"/>
    </book>
</books>
```

The generated output (note: using JDK 8) of this example is a `books-output.xml`
file with the following content (one book is added):

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<books>
    <book title="The Java Programming Language" edition="4th" isbn10="0-321-34980-6" isbn13="978-0-321-34980-4" pages="928">
        <author firstName="Ken" lastName="Arnold"/>
        <author firstName="James" lastName="Gosling"/>
        <author firstName="David" lastName="Holmes"/>
    </book>
    <book title="Java Generics and Collections" isbn10="0-596-52775-6" isbn13="978-0-596-52775-4" pages="286">
        <author firstName="Maurice" lastName="Naftalin"/>
        <author firstName="Philip" lastName="Wadler"/>
    </book>
</books>
```

### Requirements

* Java 6 or higher (requires JAXB 2 or higher)
