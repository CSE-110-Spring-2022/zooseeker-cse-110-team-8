package com.example.zooseeker;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jgrapht.GraphPath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

//    public void addPlan(ZooData.VertexInfo exhibit){
//        plan.add(exhibit);
//    }
//
//    public void setPlan(List<ZooData.VertexInfo> exhibits) {plan = exhibits;}
//
//    public void display() {
//        for (int i = 0; i < plan.size(); i++) {
//            System.out.print(plan.get(i).name + ", ");
//        }
//    }
}
