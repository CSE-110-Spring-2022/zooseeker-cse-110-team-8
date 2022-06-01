package com.example.zooseeker;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SearchBarAdapter extends RecyclerView.Adapter<SearchBarAdapter.ViewHolder> implements Filterable {
    private List<ZooData.VertexInfo> searchResults = Collections.emptyList();
    private List<ZooData.VertexInfo> searchResultsAll = Collections.emptyList();
    private List<ZooData.VertexInfo> selected_result = new ArrayList<>();
    private Integer exhibit_count = 0;


    public void setSearchResults(List<ZooData.VertexInfo> newSearchResults) {
        this.searchResults.clear();
        this.searchResults = newSearchResults;
        this.searchResultsAll = new ArrayList<>(newSearchResults);
        this.exhibit_count = selected_result.size();

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

    //get all items selected
    public List<ZooData.VertexInfo> getAll(){
        return selected_result;
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
                    if(exhibit.name.toLowerCase().contains(charSequence.toString().toLowerCase())
                    ||exhibit.tags.toString().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(exhibit);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }
        //run on ui thread
        @SuppressLint("NotifyDataSetChanged")
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
        private TextView textViewTitle;
        private TextView textViewTags;
        private CheckBox checkBox_complete;
        private ZooData.VertexInfo zooData;
        private TextView exhibit_count_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //bind format of ui with corresponding template.
            this.textViewTitle = itemView.findViewById(R.id.search_result_title);
            this.textViewTags = itemView.findViewById(R.id.search_view_tags);
            this.checkBox_complete = itemView.findViewById(R.id.check_box);
            this.exhibit_count_text = itemView.findViewById(R.id.total_exhibit_count);


            checkBox_complete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(checkBox_complete.isChecked()) {
                        selected_result.add(searchResults.get(getAdapterPosition()));
                    }else
                    {
                        selected_result.remove(searchResults.get(getAdapterPosition()));
                    }
                    notifyDataSetChanged();
                }
            });
        }
        public ZooData.VertexInfo getZooData() {return zooData;}

        //binding data with ui
        public void setZooData(ZooData.VertexInfo zooData) {
            this.zooData = zooData;
            this.textViewTitle.setText(zooData.name);
            this.textViewTags
                    .setText(zooData.tags.toString()
                            .substring(1, zooData.tags.toString().length()-1));
            if(exhibit_count == null)
            {
                exhibit_count = 0;
            }

        }

    }
}
