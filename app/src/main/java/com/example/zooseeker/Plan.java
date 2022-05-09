package com.example.zooseeker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Plan {
    public List<ZooData.VertexInfo> plan;

    public Plan(List<ZooData.VertexInfo> exhibits) {
        plan = new ArrayList<ZooData.VertexInfo>();
        plan = exhibits;
    }

    public List<ZooData.VertexInfo> getPlan() {
        return plan;
    }

    public Plan() {
        plan = Collections.emptyList();
    }

    public void addPlan(ZooData.VertexInfo exhibit){
        plan.add(exhibit);
    }

    public void setPlan(List<ZooData.VertexInfo> exhibits) {plan = exhibits;}

    public void display() {
        for (int i = 0; i < plan.size(); i++) {
            System.out.print(plan.get(i).name + ", ");
        }
    }
}
