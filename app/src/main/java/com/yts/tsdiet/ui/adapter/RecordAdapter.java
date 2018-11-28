package com.yts.tsdiet.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yts.tsdiet.R;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.data.model.RecordFood;
import com.yts.tsdiet.databinding.RecordFoodItemBinding;
import com.yts.tsdiet.databinding.RecordHeaderBinding;
import com.yts.tsdiet.viewmodel.RecordFoodViewModel;
import com.yts.tsdiet.viewmodel.RecordViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RecordAdapter extends RecyclerView.Adapter {
    private final int HEADER_TYPE = 0;
    private final int FOOD_TYPE = 1;

    private List<Object> mRecordList;

    public RecordAdapter(List<Object> recordList) {
        mRecordList = recordList;
    }

    public void setRecordList(List<Object> recordList) {
        mRecordList = recordList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        Object item = mRecordList.get(position);
        if (item instanceof Record) {
            return HEADER_TYPE;
        } else {
            return FOOD_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            RecordHeaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_record_header, parent, false);
            return new HeaderViewHolder(binding);
        }
        RecordFoodItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_record_food, parent, false);
        return new RecordFoodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == HEADER_TYPE) {
            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
            Object item = mRecordList.get(position);
            RecordViewModel model = new RecordViewModel();
            if (item instanceof Record) {
                model.setRecord((Record) item);
            }
            holder.setViewModel(model);
        } else if (viewType == FOOD_TYPE) {
            RecordFoodViewHolder holder = (RecordFoodViewHolder) viewHolder;
            Object item = mRecordList.get(position);
            RecordFoodViewModel model = new RecordFoodViewModel();
            if (item instanceof RecordFood) {
            }
            holder.setViewModel(model);
        }
    }

    @Override
    public int getItemCount() {
        if (mRecordList != null) {
            return mRecordList.size();
        }
        return 0;
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private RecordHeaderBinding binding;

        public HeaderViewHolder(@NonNull RecordHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(RecordViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }

    private class RecordFoodViewHolder extends RecyclerView.ViewHolder {
        private RecordFoodItemBinding binding;

        public RecordFoodViewHolder(@NonNull RecordFoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(RecordFoodViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }
}
