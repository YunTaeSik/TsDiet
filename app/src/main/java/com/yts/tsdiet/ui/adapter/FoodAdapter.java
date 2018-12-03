package com.yts.tsdiet.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.yts.tsdiet.R;
import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.databinding.FoodItemBinding;
import com.yts.tsdiet.realm.RealmService;
import com.yts.tsdiet.utils.NullFilter;
import com.yts.tsdiet.viewmodel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;

public class FoodAdapter extends RecyclerView.Adapter implements Filterable {
    private List<Food> mFoodList;
    private List<Food> mFoodListFilter;

    public FoodAdapter(List<Food> foodList) {
        mFoodList = foodList;
        mFoodListFilter = foodList;
    }

    public void setFoodList(List<Food> foodList) {
        mFoodList = foodList;
        mFoodListFilter = foodList;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {

        try {
            return Integer.parseInt(mFoodListFilter.get(position).getIdx());
        } catch (Exception e) {
            return position;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_food, parent, false);
        return new FoodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        FoodViewHolder holder = (FoodViewHolder) viewHolder;
        FoodViewModel model = new FoodViewModel();
        Food food = mFoodListFilter.get(position);
        model.setFood(food);
        holder.setViewModel(model);
    }


    @Override
    public int getItemCount() {
        if (mFoodListFilter != null) {
            return mFoodListFilter.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults filterResults = new FilterResults();
                String search = charSequence.toString().toLowerCase().replaceAll(" ", "");

                if (search.isEmpty()) {
                    filterResults.values = mFoodList;
                } else {
                    filterResults.values = RealmService.getFoodList(Realm.getDefaultInstance(), search);
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFoodListFilter = (List<Food>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private class FoodViewHolder extends RecyclerView.ViewHolder {
        private FoodItemBinding binding;

        public FoodViewHolder(@NonNull FoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(FoodViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }
}
