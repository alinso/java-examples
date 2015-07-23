# RGB Image Filters

This example shows how to filter images using one predefined and four custom
*RGB image filters* (extensions of `java.awt.image.RGBImageFilter` class).

The four custom filters are:

* `AverageDesaturationImageFilter`: a filter for an "average" desaturation using
  the classic formula *Gray = (Red + Green + Blue) / 3* .
* `ColorMaskImageFilter`: a filter that applies a "mask" of bits.
* `HueShiftImageFilter`: a filter that shifts the "hue" component by a value.
* `NegativeImageFilter`: a filter that gives the negative of the colors.

With some minimal/compact code added, a simple Swing frame shows a demonstration
of the original image and seven filtered images (GUI is clearly *not* the main
point of this Java Example).

### Requirements

* Java 1.4 or higher

### Screenshots

![Screenshot 1](screenshot-01.png "Screenshot 1")

### Credits

The &ldquo;colored pencils&rdquo; image is derived from the original photo
&ldquo;Lapis de Cor&rdquo; produced by **Heitor Jose** (&ldquo;tchor1974&rdquo;).
<br>Please see:

* http://www.freeimages.com/photo/lapis-de-cor-1155223
