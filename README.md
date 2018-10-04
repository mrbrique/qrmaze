# qrmaze
Generate QR code, with extra posibilities


## Background
This is a small tool I created for myself to take a textual input to generate QR code presented in AutoCAD. Hope it benefits whoever has the same needs.

## Build
`mvn clean compile assembly:single`

## Usage
### Basic
`java -jar qrmaze-1.0-SNAPSHOT-jar-with-dependencies.jar helloworld`
would produce 3 files into output folder
```
\---output
        qrmaze.png
        qrmaze.scr
        qrmaze.svg
```

Filename | Description
------------ | -------------
qrmaze.png | a raster image of the QR code encoded input "helloworld"
qrmaze.svg | a vector graphic of the same
qrmaze.scr | an AutoCAD script which draw the QR code in 2D polyline

### With -v Argument
`java -jar qrmaze-1.0-SNAPSHOT-jar-with-dependencies.jar -v 11 helloworld`
Encode the same data with suggested QR code version 11. The resulted image will have 61x61 module. See [Reference](#reference) section for details about QR code version and module.


## License
GNU General Public License v3.0

## Reference
[Information capacity and versions of QR Code](http://www.qrcode.com/en/about/version.html)