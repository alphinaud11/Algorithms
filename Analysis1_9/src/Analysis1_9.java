public class Analysis1_9 {

    public static class Graph {
        static class Edge {
            int src = 0;
            int dest = 0;
            int weight = 0;
        }

        int V;
        int E;
        Edge[] edge;

        // Creates a graph with V vertices and E edges
        Graph(int v, int e) {
            V = v;
            E = e;
            edge = new Edge[e];
            for (int i = 0; i < e; ++i)
                edge[i] = new Edge();
        }
    }

        public static int BellmanFord(Graph graph, int srcTax) {
            int V = graph.V;
            int E = graph.E;
            int[] dist = new int[V];

            dist[0] = srcTax;
            for (int i = 1; i < V; i++)
                dist[i] = Integer.MAX_VALUE;

            for (int i = 1; i < V; i++) {
                for (int j = 0; j < E; ++j) {
                    int u = graph.edge[j].src;
                    int v = graph.edge[j].dest;
                    int weight = graph.edge[j].weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                        dist[v] = dist[u] + weight;
                }
            }

            return Math.min(dist[V-1], dist[V-2]);
        }

        public static void main(String[] args) {
            int[] riverToRiver = {1, 2};
            int[] oasisToOasis = {1, 2};
            int[] riverToOasis = {1, 2};
            int[] oasisToRiver = {1, 2};
            int[] riverTaxes = {8, 5, 3};
            int[] oasisTaxes = {1, 2, 3};

            int n = riverTaxes.length; // Number of poll stations
            int V = (2*n) - 1; // Number of vertices in graph
            int E = (4*(n-1)) - 2; // Number of edges in graph

            if (n == 1) {
                String output = ((2000 - Math.min(riverTaxes[0],oasisTaxes[0])) >= 0) ? "John can hit the road" : "John should stay at home";
                System.out.println(output);
                return;
            }

            Graph graph = new Graph(V, E);
            graph.edge[0].src = 0;
            graph.edge[0].dest = 1;
            graph.edge[0].weight = riverToRiver[0] + riverTaxes[1];
            graph.edge[1].src = 0;
            graph.edge[1].dest = 2;
            graph.edge[1].weight = riverToOasis[0] + oasisTaxes[1];
            int src = 1;
            int index = 1;
            for (int i=2; i<E; i+=4) {
                graph.edge[i].src = src;
                graph.edge[i].dest = src+2;
                graph.edge[i].weight = riverToRiver[index] + riverTaxes[index+1];
                graph.edge[i+1].src = src;
                graph.edge[i+1].dest = src+3;
                graph.edge[i+1].weight = riverToOasis[index] + oasisTaxes[index+1];
                graph.edge[i+2].src = src+1;
                graph.edge[i+2].dest = src+2;
                graph.edge[i+2].weight = oasisToRiver[index] + riverTaxes[index+1];
                graph.edge[i+3].src = src+1;
                graph.edge[i+3].dest = src+3;
                graph.edge[i+3].weight = oasisToOasis[index] + oasisTaxes[index+1];
                src += 2;
                index++;
            }
            int riverStart = BellmanFord(graph, riverTaxes[0]);
            graph.edge[0].src = 0; //changing source node to oasis
            graph.edge[0].dest = 1;
            graph.edge[0].weight = oasisToRiver[0] + riverTaxes[1];
            graph.edge[1].src = 0;
            graph.edge[1].dest = 2;
            graph.edge[1].weight = oasisToOasis[0] + oasisTaxes[1];
            int oasisStart = BellmanFord(graph, oasisTaxes[0]);

            String output = ((2000 - Math.min(riverStart,oasisStart)) >= 0) ? "John can hit the road" : "John should stay at home";
            System.out.println(output);
        }
}
