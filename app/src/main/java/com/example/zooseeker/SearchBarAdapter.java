package com.example.zooseeker;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class SearchBarAdapter extends RecyclerView.Adapter<SearchBarAdapter.ViewHolder> {
    private List<ZooData.VertexInfo> searchResults = Collections.emptyList();

    public void setSearchResults(List<ZooData.VertexInfo> newSearchResults) {
        this.searchResults.clear();
        this.searchResults = newSearchResults;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.search_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setZooData(searchResults.get(position));
    }

    @Override
    public int getItemCount() { return searchResults.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private ZooData.VertexInfo zooData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.search_result_text);
        }

        public ZooData.VertexInfo getZooData() {return zooData;}

        public void setZooData(ZooData.VertexInfo zooData) {
            this.zooData = zooData;
            this.textView.setText(zooData.name);
        }
    }
}
