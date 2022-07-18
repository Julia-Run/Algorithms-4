/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class E34_MonoMinPath {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public class Path {
        private double weight;
        private DirectedEdge lastEdge;

        Path(double weight, DirectedEdge lastEdge) {
            this.weight = weight;
            this.lastEdge = lastEdge;
        }

        public double weight() {
            return weight;
        }

        public DirectedEdge lastEdge() {
            return lastEdge;
        }
    }

    public E34_MonoMinPath(EdgeWeightedDigraph g, int s) {

        edgeTo = new DirectedEdge[g.V()];
        distTo = new double[g.V()];
        for (int i = 0; i < g.V(); ++i) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Comparator<DirectedEdge> edgesComparator = new Comparator<DirectedEdge>() {
            @Override
            public int compare(DirectedEdge edge1, DirectedEdge edge2) {
                if (edge1.weight() > edge2.weight()) {
                    return -1;
                }
                else if (edge1.weight() < edge2.weight()) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        };

        DirectedEdge[][] edgesMap = new DirectedEdge[g.V()][];
        for (int i = 0; i < g.V(); ++i) {
            DirectedEdge[] temp = new DirectedEdge[g.outdegree(i)];
            int index = 0;
            for (DirectedEdge e : g.adj(i)) temp[index++] = e;
            Arrays.sort(temp, edgesComparator);
            edgesMap[i] = temp;
        }

    }

    PriorityQueue<E34_MonoMinPath.Path>
            priorityQueue = new PriorityQueue<>(
            new Comparator<E34_MonoMinPath.Path>() {
                @Override
                public int compare(E34_MonoMinPath.Path path1,
                                   E34_MonoMinPath.Path path2) {
                    if (path1.weight() < path2.weight()) {
                        return -1;
                    }
                    else if (path1.weight() > path2.weight()) {
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }
            });


    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                edgeTo[w] = e;
                distTo[w] = distTo[v] + e.weight();
            }
        }
    }


    public static void main(String[] args) {
    }
}
