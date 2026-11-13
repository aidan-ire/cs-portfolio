# Decode

Treats the message as a Caesar cipher. Reads the message and the shift it was encoded with, and shifts each letter back by that amount. Case is kept, and other characters are left alone.

## Run

```bash
javac Decode.java
java Decode
```

## Example runs

```
Please input the message: Khoor, Zruog!
Please input the shift: 3
Decoded: Hello, World!
```

```
Please input the message: Ebiil
Please input the shift: -23
Decoded: Byffi
```
