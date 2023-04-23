
package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;

import java.util.*;

/** @author Jinho D. Choi */
public class MSTAllHW implements MSTAll {
    private double maxweight;
    @Override
    public List<SpanningTree> getMinimumSpanningTrees(Graph graph) {
        // TODO: to be updated

        List<SpanningTree> trees = new LinkedList<>();
        SpanningTree minimumSpanningTree = new MSTPrim().getMinimumSpanningTree(graph);
        double totalWeight = minimumSpanningTree.getTotalWeight();

        //eliminate repeated graph, sort by weight
        Set<List<Object>> graphEdges = new HashSet<>();
        for (int i=0; i<graph.size();i++){
            for (Edge edge: graph.getIncomingEdges(i)){
                int source  = edge.getSource();
                int target = edge.getTarget();
                if (source < target){
                    graphEdges.add(Arrays.asList(source, target, edge.getWeight()));
                }else{
                    graphEdges.add(Arrays.asList(target, source, edge.getWeight()));
                }
            }
        }

        List<Object[]> sortedEdges = new ArrayList<>();
        for (List<Object> edge:graphEdges){
            sortedEdges.add(edge.toArray());
        }
//        for (Object[] edge: sortedEdges){
//            System.out.println(edge[0].toString()+","+edge[1].toString()+","+edge[2].toString());
//        }
        Collections.sort(sortedEdges, new EdgeComparator());


//        for (Object[] edge: sortedEdges){
//            System.out.println(edge[0].toString()+","+edge[1].toString()+","+edge[2].toString());
//        }
        List<List<Object[]>> allMsts = findALLMsts(graph.size(), sortedEdges);
        for(List<Object[]> mst:allMsts){
            SpanningTree spanningTree = new SpanningTree();
            for (Object[] nums:mst){
                spanningTree.addEdge(new Edge((int)nums[0], (int)nums[1], (double)nums[2]));
            }
            if (spanningTree.getTotalWeight() == totalWeight){
                trees.add(spanningTree);
            }
        }

        return trees;

    }
    public static int find(int nd, int[] djset){
        int uv=nd;
        while (djset[uv] >= 0){
            uv = djset[uv];
        }
        if (djset[nd] >= 0){
            djset[nd] = uv;
        }
        return uv;
    }
    public static int[] union(int nd1, int nd2, int [] djset){
        int [] unionset = djset.clone();
        if (unionset[nd2] < unionset[nd1]) {
            int temp = nd1;
            nd1 = nd2;
            nd2 = temp;
        }
        unionset[nd1] += unionset[nd2];
        unionset[nd2] = nd1;
        return unionset;
    }

    public int setbit(int pos, int mask){
        return mask | (1 << pos);
    } // move 1 to pos position  001 | 010 = 011


    public List<Object []> maskToEdges(int mask, List<Object []> sedges){
        ArrayList<Object []> edges = new ArrayList<>();
        int idx = 0;
        while(mask>0){
            if ((mask & 1)==1) {
                edges.add(sedges.get(idx));
            }
            mask >>= 1;
            idx++;
        }
        return edges;
    }
    public List<List<Object []>> findALLMsts(int n, List<Object []> sedges){
        List<List<Object[]>> msts = new ArrayList<>();
        this.maxweight = Float.MAX_VALUE;
        int [] djset = new int[n];
        Arrays.fill(djset, -1);
        buildmsts(n, 0, 0,0,0, djset, msts, sedges);  // n:number of vertices    i: edge th
        return msts;
    }
    public void buildmsts(int n, int i, double weight, int mask, int nedges, int[] djset, List<List<Object[]>> msts, List<Object[]> sedges){
        if(nedges == n-1){
            List<Object[]> curMst = maskToEdges(mask, sedges);
            msts.add(curMst);
            if(maxweight == Float.MAX_VALUE){
                maxweight = weight;
            }
            return;
        }
        if (i < sedges.size()){
            int u = (int)sedges.get(i)[0];
            int v = (int)sedges.get(i)[1];
            double wt = (double)sedges.get(i)[2];
            if(weight + wt * ((n - 1) - nedges) <= maxweight){ // prune
                int nd1 = find(u, djset);
                int nd2 = find(v, djset);
                if (nd1 != nd2) {
                    union(nd1, nd2, djset);
                    buildmsts(n, i+1, weight+wt, setbit(i, mask), nedges+1, union(nd1, nd2, djset), msts, sedges);
                }
                buildmsts(n, i+1, weight, mask, nedges, djset, msts, sedges);
            }
        }
    }
    private static class EdgeComparator implements Comparator<Object[]>{
        public int compare(Object[] e1, Object[] e2){
            return (int)((double)e1[2]-(double)e2[2]);
        }

    }
    }



