package com.example.zooseeker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Plan implements Serializable {

    List<String> exhibit_id;


    List<ZooData.VertexInfo> plan;
    List<GraphPath<String, IdentifiedWeightedEdge>> sorted_routes;
    List<Double> distance;
    int index;
    public Plan(List<ZooData.VertexInfo> newPlan, List<GraphPath<String, IdentifiedWeightedEdge>> newSortedRoute, List<Double> newDistance, int newIndex) {
        sorted_routes = newSortedRoute;
        plan = newPlan;
        distance = newDistance;
        index = newIndex;
    }

    public List<ZooData.VertexInfo> getPlan() {
        return plan;
    }

    public List<GraphPath<String, IdentifiedWeightedEdge>> getRoute() {return sorted_routes;}

    public List<Double> getDistance(){return distance;}

    public Plan() {
        plan = Collections.emptyList();
        sorted_routes = Collections.emptyList();
        distance = Collections.emptyList();
    }

    public void Shortest_Path(Context context, List<ZooData.VertexInfo> selected_exhibits, List<GraphPath<String, IdentifiedWeightedEdge>> Sorted_routes, List<Double> distance, List<ZooData.VertexInfo> Sorted_exhibits) {
        double route_distance = 0;
        String currentlocation="entrance_exit_gate";
        ZooData.VertexInfo low_exhibit = null;



        double lowest_weight= 9999;
        String lowest_path="entrance_exit_gate";


        Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON(context,"sample_zoo_graph.json");
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON(context,"sample_vertex_info.json");
        Map<String, ZooData.EdgeInfo> eInfo = ZooData.loadEdgeInfoJSON(context,"sample_edge_info.json");


        // push all Plan Vertex id into map,
        // so we can use it to select shortest path from
        // current_location
        HashMap<String,Boolean> m = new HashMap<String, Boolean>();
        for(int i=0;i<selected_exhibits.size();i++){
            m.put(selected_exhibits.get(i).id,Boolean.TRUE);
        }


        for(int k=0;k<selected_exhibits.size();k++){
            GraphPath<String, IdentifiedWeightedEdge> currentpath;
            for(int i=0;i<selected_exhibits.size();i++){

                //if True we
                if(m.containsKey(selected_exhibits.get(i).id)) {
                    currentpath = DijkstraShortestPath.findPathBetween(g, currentlocation, selected_exhibits.get(i).id);

                    //get min
                    if (lowest_weight > currentpath.getWeight()) {
                        lowest_weight = currentpath.getWeight();
                        lowest_path = selected_exhibits.get(i).id;
                        low_exhibit = selected_exhibits.get(i);

                    }

                }

            }
            m.remove(lowest_path);

            //go from current -> dest
            currentpath = DijkstraShortestPath.findPathBetween(g, currentlocation, lowest_path);

            Sorted_routes.add(currentpath);
            route_distance += currentpath.getWeight();
            distance.add(route_distance);

            Sorted_exhibits.add(low_exhibit);
            System.out.println("weight: "+ currentpath.getWeight());
            System.out.println("now i am going from  "+ currentlocation+  " to: "+ lowest_path);
            currentlocation=lowest_path;
            lowest_weight=9999;

            int j = 1;
        }
    }
    public void getshortestPath(Context context, List<GraphPath<String, IdentifiedWeightedEdge>> Sorted_routes, List<Double> distance, List<ZooData.VertexInfo> Sorted_exhibits, List<ZooData.VertexInfo> selected_exhibits) {
        double route_distance = 0;
        double lowest_weight= 9999;
        String lowest_path="entrance_exit_gate";

        String currentlocation="entrance_exit_gate";
        Graph<String, IdentifiedWeightedEdge> g = ZooData.loadZooGraphJSON(context,"sample_zoo_graph.json");
        Map<String, ZooData.VertexInfo> vInfo = ZooData.loadVertexInfoJSON(context,"sample_vertex_info.json");
        Map<String, ZooData.EdgeInfo> eInfo = ZooData.loadEdgeInfoJSON(context,"sample_edge_info.json");
        GraphPath<String, IdentifiedWeightedEdge> low_path;
        ZooData.VertexInfo low_exhibit = null;

        // push all Plan Vertex id into map,
        // so we can use it to select shortest path from
        // current_location
        HashMap<String,Boolean> m = new HashMap<String, Boolean>();
        for(int i = 0; i< selected_exhibits.size(); i++){
            m.put(selected_exhibits.get(i).id,Boolean.TRUE);
        }


        for(int k = 0; k< selected_exhibits.size(); k++){
            GraphPath<String, IdentifiedWeightedEdge> currentpath;
            for(int i = 0; i< selected_exhibits.size(); i++){

                //if True we
                if(m.containsKey(selected_exhibits.get(i).id)) {
                    currentpath = DijkstraShortestPath.findPathBetween(g, currentlocation, selected_exhibits.get(i).id);

                    //get min
                    if (lowest_weight > currentpath.getWeight()) {
                        lowest_weight = currentpath.getWeight();
                        lowest_path = selected_exhibits.get(i).id;
                        low_exhibit = selected_exhibits.get(i);

                    }

                }

            }
            m.remove(lowest_path);

            //go from current -> dest
            currentpath = DijkstraShortestPath.findPathBetween(g, currentlocation, lowest_path);

            Sorted_routes.add(currentpath);
            route_distance += currentpath.getWeight();
            distance.add(route_distance);

            Sorted_exhibits.add(low_exhibit);
            System.out.println("weight: "+ currentpath.getWeight());
            System.out.println("now i am going from  "+ currentlocation+  " to: "+ lowest_path);
            currentlocation=lowest_path;
            lowest_weight=9999;

            int j = 1;


            for (IdentifiedWeightedEdge e : currentpath.getEdgeList()) {
                System.out.printf("  %d. Walk %.0f meters along %s from '%s' to '%s'.\n",
                        j,
                        g.getEdgeWeight(e),
                        eInfo.get(e.getId()).street,
                        vInfo.get(g.getEdgeSource(e).toString()).name,
                        vInfo.get(g.getEdgeTarget(e).toString()).name);
                j++;
            }
        }
    }


}
