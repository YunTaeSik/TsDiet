package com.yts.tsdiet.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yts.tsdiet.R;
import com.yts.tsdiet.databinding.CalendarHeaderBinding;
import com.yts.tsdiet.databinding.DayItemBinding;
import com.yts.tsdiet.databinding.EmptyDayBinding;
import com.yts.tsdiet.viewmodel.CalendarHeaderViewModel;
import com.yts.tsdiet.viewmodel.CalendarViewModel;
import com.yts.tsdiet.viewmodel.EmptyViewModel;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class NewCalendarAdapter extends RecyclerView.Adapter {
    private final int HEADER_TYPE = 0;
    private final int EMPTY_TYPE = 1;
    private final int DAY_TYPE = 2;

    private List<Object> mCalendarList;

    public NewCalendarAdapter(List<Object> calendarList) {
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
        } else if (item instanceof String) {
            return EMPTY_TYPE;
        } else {
            return DAY_TYPE;

        }
    }
/*

    @Override
    public long getItemId(int position) {
        Object item = mCalendarList.get(position);
        if (item instanceof Long) {
            return (Long) item;
        } else if (item instanceof String) {
            return (item).hashCode();
        } else if (item instanceof Calendar) {
            return ((Calendar) item).getTimeInMillis();
        } else {
            return position;
        }
    }

*/

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            CalendarHeaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_calendar_header, parent, false);
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) binding.getRoot().getLayoutParams();
            params.setFullSpan(true);
            binding.getRoot().setLayoutParams(params);
            return new HeaderViewHolder(binding);
        } else if (viewType == EMPTY_TYPE) {
            EmptyDayBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_day_empty, parent, false);
            return new EmptyViewHolder(binding);
        }
        DayItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_day, parent, false);
        return new DayViewHolder(binding);
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
        } else if (viewType == EMPTY_TYPE) {
            EmptyViewHolder holder = (EmptyViewHolder) viewHolder;
            EmptyViewModel model = new EmptyViewModel();
            holder.setViewModel(model);
        } else if (viewType == DAY_TYPE) {
            DayViewHolder holder = (DayViewHolder) viewHolder;
            Object item = mCalendarList.get(position);
            CalendarViewModel model = new CalendarViewModel();
            if (item instanceof Calendar) {
                model.initGoal(holder.itemView.getContext());
                model.setCalendar((Calendar) item);
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

        public HeaderViewHolder(@NonNull CalendarHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(CalendarHeaderViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }


    private class EmptyViewHolder extends RecyclerView.ViewHolder {
        private EmptyDayBinding binding;

        public EmptyViewHolder(@NonNull EmptyDayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(EmptyViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }

    }

    private class DayViewHolder extends RecyclerView.ViewHolder {
        private DayItemBinding binding;

        public DayViewHolder(@NonNull DayItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(CalendarViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }
}
