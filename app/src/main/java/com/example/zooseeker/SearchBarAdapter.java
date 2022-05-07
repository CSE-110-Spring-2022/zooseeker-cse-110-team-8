package com.example.zooseeker;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SearchBarAdapter extends RecyclerView.Adapter<SearchBarAdapter.ViewHolder> implements Filterable {
    private List<ZooData.VertexInfo> searchResults = Collections.emptyList();
    private List<ZooData.VertexInfo> searchResultsAll = Collections.emptyList();


    public void setSearchResults(List<ZooData.VertexInfo> newSearchResults) {
        this.searchResults.clear();
        this.searchResults = newSearchResults;
        this.searchResultsAll = new ArrayList<>(newSearchResults);
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

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ZooData.VertexInfo> filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(searchResultsAll);
            }else{
                for(ZooData.VertexInfo exhibit: searchResultsAll)
                {
                    if(exhibit.name.toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(exhibit);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }
        //run on ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
              searchResults.clear();
              if(filterResults.values instanceof  List)
                  for(ZooData.VertexInfo item: (List<ZooData.VertexInfo>)filterResults.values){
                      searchResults.add(item);
                  }
              notifyDataSetChanged();
        }
    };

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
