import csv
import matplotlib.pyplot as plt

limits = []
counters = []

with open("counter_results.csv") as f:
    for row in csv.DictReader(f):  # written by sieve.java
        limits.append(int(row["limit"]))
        counters.append(int(row["counter"]))

plt.plot(limits, counters, marker="o", label="counter")
plt.plot([limits[0], limits[-1]], [counters[0], counters[-1]], linestyle="--", label="straight line, first to last point")  # to compare the shape against
plt.xlabel("limit")
plt.ylabel("counter (steps in the inner loop)")
plt.title("Sieve of Eratosthenes: counter vs limit")
plt.legend()
plt.grid(True)
plt.savefig("counter_vs_limit.png", dpi=150)
