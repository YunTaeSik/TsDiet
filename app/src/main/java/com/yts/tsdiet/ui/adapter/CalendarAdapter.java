package com.yts.tsdiet.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yts.tsdiet.R;
import com.yts.tsdiet.databinding.CalendarHeaderBinding;
import com.yts.tsdiet.viewmodel.CalendarHeaderViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarAdapter extends RecyclerView.Adapter {
    private final int HEADER_TYPE = 0;
    private final int CALENDAR_TYPE = 1;

    private List<Object> mCalendarList;

    public CalendarAdapter(List<Object> calendarList) {
        mCalendarList = calendarList;
    }

    public void setCalendarList(List<Object> calendarList) {
        mCalendarList = calendarList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mCalendarList.get(position);
        if (item instanceof Long) {
            return HEADER_TYPE;
        } else {
            return CALENDAR_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            CalendarHeaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_calendar_header, parent, false);
            return new HeaderViewHolder(binding);
        }
        CalendarHeaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_calendar_header, parent, false);
        return new HeaderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == HEADER_TYPE) {
            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
            Object item = mCalendarList.get(position);
            CalendarHeaderViewModel model = new CalendarHeaderViewModel();
            if (item instanceof Long) {
                model.setHeaderDate((Long) item);
            }
            holder.setViewModel(model);
        }
    }

    @Override
    public int getItemCount() {
        if (mCalendarList != null) {
            return mCalendarList.size();
        }
        return 0;
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private CalendarHeaderBinding binding;
        private CalendarHeaderViewModel model;

        public HeaderViewHolder(@NonNull CalendarHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(CalendarHeaderViewModel model) {
            this.model = model;
            binding.setModel(model);
            binding.executePendingBindings();
        }

    }

    private class CalendarViewHolder extends RecyclerView.ViewHolder {
        private CalendarHeaderBinding binding;
        private CalendarHeaderViewModel model;

        public CalendarViewHolder(@NonNull CalendarHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(CalendarHeaderViewModel model) {
            this.model = model;
            binding.setModel(model);
        }
    }
}
