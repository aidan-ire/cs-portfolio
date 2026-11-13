# Genomes

Reads a DNA strand, accepting lower or upper case, and keeps asking until it contains only A, C, G and T. It then prints the complementary strand, swapping A with T and C with G.

## Run

```bash
javac Genomes.java
java Genomes
```

## Example run

```
Please input a DNA strand: ACGU
Invalid strand, it can only contain the bases A, C, G and T
Please input a DNA strand: gattaca
Strand:     GATTACA
Complement: CTAATGT
```
