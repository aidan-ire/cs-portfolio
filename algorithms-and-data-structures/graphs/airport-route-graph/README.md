# Introduction to graphs

Load an edge list of airport routes into an adjacency list and analyse it: count the nodes and edges,
work out the density, find the degree of an airport and the five best connected airports, find
everything within N steps of an airport, and see how much of Dublin's reach is lost when five major
European hubs are removed.

## Build and run

```bash
javac AirportGraph.java
java AirportGraph airports-sample.csv
```

With no argument the program reads `airports.csv` from the current folder.

## The data

`airports-sample.csv` is a small synthetic dataset in the same format, used for testing. The results below are from this file.

The format is one route per line, as two 3-letter airport codes separated by a comma:

```
DUB,LHR
DUB,AMS
```

## How it works

The graph is a `HashMap<String, List<String>>` mapping each airport code to an `ArrayList` of the
airports it has a route to. It is undirected, so a line `DUB,AMS` adds AMS to DUB's list and DUB to
AMS's list.

| Method | What it does |
|---|---|
| `loadGraph(path)` | reads the file line by line, splits on the comma and adds each edge |
| `countNodes()` | number of keys in the map |
| `countEdges()` | adds up the lengths of all the lists and halves it, since each edge is stored twice |
| `density()` | 2E / (n(n − 1)), the fraction of all possible edges that exist |
| `find_degree(airport_code)` | length of that airport's list |
| `topAirports(k)` | the k airports with the highest degree |
| `reachable(s, N)` | breadth first search from s that stops N levels out |
| `remove_node(airport_code)` | removes the airport from each neighbour's list, then removes its own entry |

## Output for the sample file

```
Nodes: 29
Edges: 70
Density: 0.172414

Degree of DUB: 11

Top 5 airports by degree:
1. LHR (16)
2. FRA (14)
3. AMS (13)
4. CDG (11)
5. DUB (11)

Airports reachable from DUB in 1 step: 11
Airports reachable from DUB in 1 or 2 steps: 28

After removing AMS, FRA, LHR, CDG and MAD:
Nodes: 24
Edges: 18
Airports reachable from DUB in 1 or 2 steps: 10
Decrease: 18 (64.3%)
```

In the sample, Dublin can reach all 28 other airports directly or with one stop. With the five hubs
gone it can only reach 10, a drop of 18 (64.3%), even though only 5 of the 29 airports were removed.
Those five carried 52 of the 70 routes, and most of Dublin's one-stop connections went through them.
The density of 0.17 means about 17% of the possible pairs of airports have a direct route. The
sample is small and built around hubs, so a real route network would be expected to be much sparser.

## Tests run

All of the values above were checked against a separate calculation on the same file. Individual
methods were also tested on the sample graph:

| Call | Result | Checks |
|---|---|---|
| `find_degree("NOC")` | 1 | airport with a single route |
| `find_degree("XXX")` | -1 | airport not in the graph |
| `reachable("DUB", 0)` | empty | distance 0 reaches nothing new |
| `reachable("NOC", 1)` | `[MAN]` | one step |
| `reachable("NOC", 2)`, `reachable("NOC", 3)` | 4 and 20 airports | the search stops at the right level |
| `reachable("DUB", 10)` | 28 | N larger than the graph needs, no airport counted twice |
| `reachable("XXX", 2)` | empty | starting airport not in the graph |
| `remove_node("XXX")` | no change | removing an airport that isn't there |
| `remove_node("MAN")` | 28 nodes, 66 edges, NOC left with degree 0 | all 4 of MAN's routes removed from both sides |
| `topAirports(100)` | 28 airports | asking for more airports than exist |
| file with `LHR,DUB` after `DUB,LHR` | edge stored once | the sample's last two lines repeat routes in the other direction |

## Notes

- If the same route appears more than once in the file, including once in each direction, it is only
  stored once. Otherwise degrees and the edge count would be too high.
- `reachable` doesn't include the starting airport in its result, so the counts are of other
  airports.
- Airports with the same degree are listed in alphabetical order in the top 5.
- Blank lines and lines starting with `#` are skipped, which is how the sample file carries its label
  on the first line. The program doesn't skip a header row, so if a copy of the dataset has one it
  would be read as a route.
- The node, edge and density question is answered once.
