package com.example.zooseeker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Plan {
    public List<ZooData.VertexInfo> plan;

    public Plan(ZooData.VertexInfo exhibit) {
        plan = new ArrayList<ZooData.VertexInfo>();
        plan.add(exhibit);
    }

    public Plan() {
        plan = Collections.emptyList();
    }

    public void add(ZooData.VertexInfo exhibit){
        plan.add(exhibit);
    }

    public void display() {
        for (int i = 0; i < plan.size(); i++) {
            System.out.print(plan.get(i).name + ", ");
        }
    }
}
