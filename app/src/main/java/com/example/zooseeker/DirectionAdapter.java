package com.example.zooseeker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jgrapht.GraphPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectionAdapter extends RecyclerView.Adapter<DirectionAdapter.ViewHolder> {
    private List<ZooData.VertexInfo> RouteSummary = Collections.emptyList();
    private List<Double> distance_list = new ArrayList<>();
    private List<String> detailedDirection = Collections.emptyList();
    private List<GraphPath<String, IdentifiedWeightedEdge>> Route_list = Collections.emptyList();

    public void setDirections(List<ZooData.VertexInfo> newRouteSummary, List<GraphPath<String, IdentifiedWeightedEdge>> new_Route_list, List<Double> newDistance, List<String> newDirection)
    {
        this.RouteSummary.clear();
        this.RouteSummary = newRouteSummary;
        this.Route_list = new_Route_list;
        this.distance_list = newDistance;
        this.detailedDirection = newDirection;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.direction_result,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DirectionAdapter.ViewHolder holder, int position) {
        holder.setZooData(detailedDirection.get(position));
    }

    @Override
    public int getItemCount() {
        return detailedDirection.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currentDirection;
        private TextView nextDistance;
        private ZooData.VertexInfo zooData;
        GraphPath<String, IdentifiedWeightedEdge> path;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.currentDirection = itemView.findViewById(R.id.direction);
        }
        public ZooData.VertexInfo getZooData() {return zooData;}

        public void setZooData(String direction ) {
            this.zooData = zooData;
            this.currentDirection.setText(direction);
        }
    }

}
