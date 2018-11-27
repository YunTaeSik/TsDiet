package com.yts.tsdiet.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yts.tsdiet.R;
import com.yts.tsdiet.databinding.CalendarBinding;
import com.yts.tsdiet.databinding.CalendarHeaderBinding;
import com.yts.tsdiet.viewmodel.CalendarHeaderViewModel;
import com.yts.tsdiet.viewmodel.CalendarViewModel;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @Override
    public long getItemId(int position) {
        Object item = mCalendarList.get(position);
        if (item instanceof Long) {
            return (Long) item;
        } else if (item instanceof Calendar) {
            return ((Calendar) item).getTimeInMillis();
        } else {
            return position;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            CalendarHeaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_calendar_header, parent, false);
            return new HeaderViewHolder(binding);
        }
        CalendarBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_calendar, parent, false);
        return new CalendarViewHolder(binding);
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
        } else if (viewType == CALENDAR_TYPE) {
            CalendarViewHolder holder = (CalendarViewHolder) viewHolder;
            Object item = mCalendarList.get(position);
            CalendarViewModel model = new CalendarViewModel();
            if (item instanceof Calendar) {
                Calendar calendar = (Calendar) item;
                model.setCalendar(calendar);
                model.setDayList(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1));
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

    private class CalendarViewHolder extends RecyclerView.ViewHolder {
        private CalendarBinding binding;

        public CalendarViewHolder(@NonNull CalendarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(CalendarViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }
}
