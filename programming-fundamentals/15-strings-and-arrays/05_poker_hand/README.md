# Poker Hand

Reads five playing cards, such as `10H QS AD`, and names the best poker hand they make, from straight flush down to high card. The hand is classified from how many cards there are of each rank, plus a same-suit check. A to 5 counts as a straight. Hands with the wrong number of cards, invalid cards or a repeated card are rejected.

## Run

```bash
javac PokerHand.java
java PokerHand
```

## Example runs

```
Cards are a rank (2-10, J, Q, K, A) and a suit (H, D, C, S), eg. 10H or QS
Please input 5 cards: 10H JH QH KH AH
Straight flush
```

```
Cards are a rank (2-10, J, Q, K, A) and a suit (H, D, C, S), eg. 10H or QS
Please input 5 cards: 3D 3S 9C 9H 3H
Full house
```

```
Cards are a rank (2-10, J, Q, K, A) and a suit (H, D, C, S), eg. 10H or QS
Please input 5 cards: 2C 4D 5H 3S AS
Straight
```

```
Cards are a rank (2-10, J, Q, K, A) and a suit (H, D, C, S), eg. 10H or QS
Please input 5 cards: KS KD 7H 7C 2D
Two pair
```

```
Cards are a rank (2-10, J, Q, K, A) and a suit (H, D, C, S), eg. 10H or QS
Please input 5 cards: 8C 2H JD 4S 6H
High card
```

```
Cards are a rank (2-10, J, Q, K, A) and a suit (H, D, C, S), eg. 10H or QS
Please input 5 cards: AH AH 3C 4D 5S
Invalid hand: AH appears twice
```
