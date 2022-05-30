package com.example.zooseeker;

import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class App {
    public static void main(String[] args) {

//        Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON("app/src/main/assets/sample_zoo_graph.json");
//        GraphPath<String, IdentifiedWeightedEdge> path =
//                            DijkstraShortestPath.findPathBetween(g, "intxn_front_treetops", "flamingo");


      //  int index=0;
       //  while(index<path.getEdgeList().size()){
        //   System.out.println(path.getEdgeList().toString());
          //  index++;
     //   }
       // path.getEdgeList();
//
//        System.out.println("weight: "+path.getWeight());
       // path.getWeight();

//        // "source" and "sink" are graph terms for the start and end
//        String start = "entrance_exit_gate";
//        String goal = "elephant_odyssey";
//        String pa= "app/src/main/assets/sample_zoo_graph.json";
//
//        // 1. Load the graph...
//        Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON("app/src/main/assets/sample_zoo_graph.json");
//        GraphPath<String, IdentifiedWeightedEdge> path = DijkstraShortestPath.findPathBetween(g, start, goal);
//
//        // 2. Load the information about our nodes and edges...
//        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON("app/src/main/assets/sample_vertex_info.json");
//        Map<String, ZooData.EdgeInfo> eInfo = ZooData.loadEdgeInfoJSON("app/src/main/assets/sample_edge_info.json");
//
//        System.out.printf("The shortest path from '%s' to '%s' is:\n", start, goal);
//
//        int i = 1;
//        for (IdentifiedWeightedEdge e : path.getEdgeList()) {
//            System.out.printf("  %d. Walk %.0f meters along %s from '%s' to '%s'.\n",
//                    i,
//                    g.getEdgeWeight(e),
//                    eInfo.get(e.getId()).street,
//                    vInfo.get(g.getEdgeSource(e).toString()).name,
//                    vInfo.get(g.getEdgeTarget(e).toString()).name);
//            i++;
//        }
//
//        Search.displaySearchResult("mammal");
   }
}