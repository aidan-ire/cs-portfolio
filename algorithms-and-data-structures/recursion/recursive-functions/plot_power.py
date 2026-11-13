import csv
import matplotlib.pyplot as plt

ns = []
recursive = []
iterative = []

with open("power_timing.csv") as f:
    for row in csv.DictReader(f):  # written by Power.java
        ns.append(int(row["n"]))
        recursive.append(int(row["recursive_ns"]))
        iterative.append(int(row["iterative_ns"]))

plt.plot(ns, recursive, marker="o", label="recursive")
plt.plot(ns, iterative, marker="o", label="iterative")
plt.xlabel("n (exponent)")
plt.ylabel("average time per call (ns)")
plt.title("a^n: recursion vs iteration")
plt.legend()
plt.grid(True)
plt.savefig("power_timing.png", dpi=150)
