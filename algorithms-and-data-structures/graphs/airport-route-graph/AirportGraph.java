import java.io.*;
import java.util.*;

public class AirportGraph{
    // adjacency list, each airport code maps to the list of airports it has a route to
    public static Map<String, List<String>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException{
        String path = "airports.csv";
        if(args.length > 0) path = args[0]; // a different file can be given on the command line

        if(!new File(path).exists()){
            System.out.println("Could not find " + path + ", give the path to the csv file as an argument");
            return;
        }

        loadGraph(path);

        System.out.println("Nodes: " + countNodes());
        System.out.println("Edges: " + countEdges());
        System.out.printf("Density: %.6f%n", density());

        System.out.println();
        System.out.println("Degree of DUB: " + find_degree("DUB"));

        System.out.println();
        System.out.println("Top 5 airports by degree:");
        List<String> top = topAirports(5);
        for(int i = 0; i < top.size(); i++){
            System.out.println((i + 1) + ". " + top.get(i) + " (" + find_degree(top.get(i)) + ")");
        }

        int before = reachable("DUB", 2).size();
        System.out.println();
        System.out.println("Airports reachable from DUB in 1 step: " + reachable("DUB", 1).size());
        System.out.println("Airports reachable from DUB in 1 or 2 steps: " + before);

        String[] hubs = {"AMS", "FRA", "LHR", "CDG", "MAD"};
        for(int i = 0; i < hubs.length; i++) remove_node(hubs[i]);

        int after = reachable("DUB", 2).size();
        System.out.println();
        System.out.println("After removing AMS, FRA, LHR, CDG and MAD:");
        System.out.println("Nodes: " + countNodes());
        System.out.println("Edges: " + countEdges());
        System.out.println("Airports reachable from DUB in 1 or 2 steps: " + after);
        if(before > 0){
            System.out.printf("Decrease: %d (%.1f%%)%n", before - after, 100.0 * (before - after) / before);
        }
    }

    /*
     * loadGraph
     * reads an edge list from a csv file, one route per line as two airport
     * codes separated by a comma (eg. DUB,AMS), and builds the adjacency list.
     * blank lines, lines starting with # and lines without two codes are skipped.
     * Arguments:
     *   path - the csv file to read
     */
    public static void loadGraph(String path) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader(path));
        String line = in.readLine();

        while(line != null){
            line = line.trim();

            if(line.length() > 0 && !line.startsWith("#")){
                String[] fields = line.split(",");

                if(fields.length >= 2){
                    addEdge(fields[0].trim(), fields[1].trim());
                }
            }

            line = in.readLine();
        }
        in.close();
    }

    /*
    adds the route between a and b. the graph is undirected so b goes in a's list
    and a goes in b's list. if the file has the same route twice (or once in each
    direction) it is only stored once, otherwise the degrees and the edge count
    would be too high.
    */
    public static void addEdge(String a, String b){
        if(a.length() == 0 || b.length() == 0) return;

        if(!graph.containsKey(a)) graph.put(a, new ArrayList<>());
        if(!graph.containsKey(b)) graph.put(b, new ArrayList<>());

        if(a.equals(b)) return; // a route from an airport to itself is not an edge

        if(!graph.get(a).contains(b)) graph.get(a).add(b);
        if(!graph.get(b).contains(a)) graph.get(b).add(a);
    }

    // number of nodes, one entry in the map per airport
    public static int countNodes(){
        return graph.size();
    }

    /*
    every edge a-b is stored twice, once in a's list and once in b's list, so
    adding up the length of every list counts each edge twice. half of that is
    the number of edges.
    */
    public static int countEdges(){
        int total = 0;

        for(List<String> neighbours : graph.values()){
            total += neighbours.size();
        }

        return total / 2;
    }

    /*
    density is the number of edges divided by the most edges the graph could
    have. an undirected graph with n nodes can have at most n(n - 1) / 2 edges,
    so density = 2E / (n(n - 1)). 1 means every pair of airports is connected.
    */
    public static double density(){
        int n = countNodes();
        if(n < 2) return 0; // no pairs of nodes, avoids dividing by 0

        return 2.0 * countEdges() / ((double)n * (n - 1));
    }

    /*
     * find_degree
     * the degree of an airport is how many airports it has a direct route to,
     * which is the length of its list.
     * Arguments:
     *   airport_code - the 3 letter code of the airport
     * Returns:
     *   the degree of the airport, or -1 if it is not in the graph
     */
    public static int find_degree(String airport_code){
        if(!graph.containsKey(airport_code)) return -1;

        return graph.get(airport_code).size();
    }

    /*
     * topAirports
     * finds the k airports with the highest degree. it makes k passes over the
     * nodes, each time picking the highest degree airport that hasn't been
     * picked yet. airports with the same degree are taken in alphabetical order.
     * Arguments:
     *   k - how many airports to return
     * Returns:
     *   up to k airport codes, highest degree first
     */
    public static List<String> topAirports(int k){
        List<String> top = new ArrayList<>();

        for(int i = 0; i < k && i < graph.size(); i++){
            String best = null;

            for(String code : graph.keySet()){
                if(top.contains(code)) continue; // already picked

                if(best == null || find_degree(code) > find_degree(best)
                        || (find_degree(code) == find_degree(best) && code.compareTo(best) < 0)){
                    best = code;
                }
            }

            top.add(best);
        }

        return top;
    }

    /*
     * reachable
     * finds every node within N steps of s using a breadth first search. the
     * search goes out one level at a time: the nodes 1 step away, then the new
     * nodes 1 step from those (2 steps away) and so on, stopping after level N.
     * dist records how far each node found so far is from s, which also stops a
     * node from being visited twice.
     * Arguments:
     *   s - the starting node
     *   N - the largest distance allowed
     * Returns:
     *   the nodes at distance 1 to N from s. s itself is not included. the list
     *   is empty if s is not in the graph.
     */
    public static List<String> reachable(String s, int N){
        List<String> result = new ArrayList<>();
        if(!graph.containsKey(s)) return result;

        Map<String, Integer> dist = new HashMap<>();
        List<String> queue = new ArrayList<>();
        dist.put(s, 0);
        queue.add(s);

        int front = 0; // index of the next node to take from the queue
        while(front < queue.size()){
            String current = queue.get(front);
            front++;

            if(dist.get(current) == N) continue; // its neighbours would be more than N away

            for(String next : graph.get(current)){
                if(!dist.containsKey(next)){
                    dist.put(next, dist.get(current) + 1);
                    queue.add(next);
                    result.add(next);
                }
            }
        }

        return result;
    }

    /*
     * remove_node
     * removes an airport from the graph. it is taken out of the list of each of
     * its neighbours first, then its own entry is removed from the map. does
     * nothing if the airport is not in the graph.
     * Arguments:
     *   airport_code - the airport to remove
     */
    public static void remove_node(String airport_code){
        if(!graph.containsKey(airport_code)) return;

        for(String neighbour : graph.get(airport_code)){
            graph.get(neighbour).remove(airport_code); // removes the link back to this airport
        }

        graph.remove(airport_code);
    }
}
