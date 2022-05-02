package com.example.zooseeker;

import java.util.ArrayList;
import java.util.List;

public class Plan {
    public List<ZooData> plan;

    public Plan(ZooData exhibit) {
        plan = new ArrayList<ZooData>();
        plan.add(exhibit);
    }

    public void add(ZooData exhibit){
        plan.add(exhibit);
    }

    public void display() {
        for (int i = 0; i < plan.size(); i++) {
            System.out.print(plan.get(i).getName() + ", ");

        }
    }

}
