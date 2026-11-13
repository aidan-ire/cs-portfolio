# Caesar Cipher

Encrypts or decrypts a message with a Caesar cipher. One `shift` method does both: encrypting moves letters forward by the key and decrypting moves them back. Keys of any size, including negative ones, wrap round the alphabet. Case and non-letters are kept.

## Run

```bash
javac CaesarCipher.java
java CaesarCipher
```

## Example runs

```
Encrypt or decrypt (e/d): e
Please input the message: Meet me at Dublin, 3pm!
Please input the key (shift): 3
Encrypted: Phhw ph dw Gxeolq, 3sp!
```

```
Encrypt or decrypt (e/d): x
Please answer e or d: d
Please input the message: Phhw ph dw Gxeolq, 3sp!
Please input the key (shift): 3
Decrypted: Meet me at Dublin, 3pm!
```

```
Encrypt or decrypt (e/d): e
Please input the message: Zebra
Please input the key (shift): -1
Encrypted: Ydaqz
```
