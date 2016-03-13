# Standard Fonts Demo PDF

This example shows how to draw a demonstration page for each of the 14 standard
fonts in a PDF using the [Apache PDFBox](https://pdfbox.apache.org) library.

Each page shows all the available informations provided by the
`org.apache.pdfbox.pdmodel.font.PDFontDescriptor` class and also a text sample
in many sizes ranging from 4 to 20 points.


### Requirements

* Java 5 or higher

### Output

The application generates the following PDF file:

* **standard-fonts-demo.pdf** &mdash; [view](standard-fonts-demo.pdf) | [download](standard-fonts-demo.pdf?raw=true)

### Notes

This example is based on Apache PDFBox version **1.8.11** (referenced as Maven
*dependency*). However, it should also work with other versions of PDFBox.
