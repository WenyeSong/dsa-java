/*
 * Copyright 2020 Emory University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.GraphQuiz;
import edu.emory.cs.set.DisjointSet;

import java.util.List;
import java.util.PriorityQueue;


/** @author Jinho D. Choi ({@code jinho.choi@emory.edu}) */
public class MSTKruskal implements MST {
    @Override
    public SpanningTree getMinimumSpanningTree(Graph graph) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(graph.getAllEdges());
        DisjointSet forest = new DisjointSet(graph.size());
        SpanningTree tree = new SpanningTree();

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();

            if (!forest.inSameSet(edge.getTarget(), edge.getSource())) {
                tree.addEdge(edge);

                // a spanning tree is found
                if (tree.size() + 1 == graph.size()) break;
                // merge forests
                forest.union(edge.getTarget(), edge.getSource());
            }
        }

        return tree;
    }

    public static void main(String[] args) {
            Graph g = new Graph(3);
            g.setUndirectedEdge(0,1,1);
            g.setUndirectedEdge(0, 2, 1);
            g.setUndirectedEdge(1, 2, 1);
            MSTKruskal MST = new MSTKruskal();
            MST.getMinimumSpanningTree(g);

        }
    }


