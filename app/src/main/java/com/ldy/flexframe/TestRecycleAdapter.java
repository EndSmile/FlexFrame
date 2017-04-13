package com.ldy.flexframe;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ldy.flexframe.databinding.ItemTestBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldy on 2017/3/30.
 */

public class TestRecycleAdapter extends RecyclerView.Adapter<TestRecycleAdapter.ViewHolder> {

    private List<String> strings;

    public TestRecycleAdapter(List<String> list) {
        strings = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.generateHolder(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setText(strings.get(position));
//        holder.binding.setVariable(BR.text,strings.get(position));
        holder.binding.executePendingBindings();
    }

    public void updateList(List<String> list){
        strings = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemTestBinding binding;

        public static ViewHolder generateHolder(ViewGroup viewGroup){
            ItemTestBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.getContext()),
                    R.layout.item_test,
                    viewGroup, false);
            ViewHolder holder = new ViewHolder(binding.getRoot());
            holder.binding = binding;
            return holder;
        }
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
