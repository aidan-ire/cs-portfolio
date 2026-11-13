import csv
import matplotlib.pyplot as plt

sizes = []
linear = []
binary = []

with open("search_timing.csv") as f:
    for row in csv.DictReader(f):  # written by arraysearch.java
        sizes.append(int(row["n"]) / 1_000_000)
        linear.append(float(row["linear_ms"]))
        binary.append(float(row["binary_ns"]))

fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(11, 4.5))  # two plots, the units are too far apart for one axis

ax1.plot(sizes, linear, marker="o")
ax1.set_xlabel("array size n (millions)")
ax1.set_ylabel("average time (ms)")
ax1.set_title("Linear search")
ax1.grid(True)

ax2.plot(sizes, binary, marker="o", color="tab:orange")
ax2.set_xlabel("array size n (millions)")
ax2.set_ylabel("average time (ns)")
ax2.set_title("Binary search")
ax2.set_ylim(bottom=0)
ax2.grid(True)

fig.tight_layout()
fig.savefig("search_timing.png", dpi=150)
