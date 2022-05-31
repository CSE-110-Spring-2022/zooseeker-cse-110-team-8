package com.example.zooseeker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jgrapht.GraphPath;

import java.util.Collections;
import java.util.List;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> {
    private List<ZooData.VertexInfo> RouteSummary = Collections.emptyList();
    private List<GraphPath<String, IdentifiedWeightedEdge>> Route_list = Collections.emptyList();

    public void setRoutes(List<ZooData.VertexInfo> newRouteSummary, List<GraphPath<String, IdentifiedWeightedEdge>> new_Route_list)
    {
        this.RouteSummary.clear();
        this.RouteSummary = newRouteSummary;
        this.Route_list = new_Route_list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.route_result, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteAdapter.ViewHolder holder, int position) {
        holder.setZooData(RouteSummary.get(position), Route_list.get(position));
    }

    @Override
    public int getItemCount() {
        return RouteSummary.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView distance;
        private ZooData.VertexInfo zooData;
        GraphPath<String, IdentifiedWeightedEdge> path;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.route_exhibit_title);
            this.distance = itemView.findViewById(R.id.distance);
        }
        public ZooData.VertexInfo getZooData() {return zooData;}

        public void setZooData(ZooData.VertexInfo zooData, GraphPath<String, IdentifiedWeightedEdge> path ) {
            this.zooData = zooData;
            this.title.setText(zooData.name);
            int dis = (int) path.getWeight();
            String d = dis + " meters";
            this.distance.setText(d);
        }

    }


}
