package com.yts.tsdiet.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yts.tsdiet.BaseActivity;
import com.yts.tsdiet.R;
import com.yts.tsdiet.databinding.MainViewBinding;
import com.yts.tsdiet.ui.fragment.CalendarFragment;
import com.yts.tsdiet.ui.fragment.ChartFragment;
import com.yts.tsdiet.viewmodel.MainViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private String[] tags = {"CALENDAR", "CHART"};
    private String mCurrentFragmentTag = "CALENDAR";


    private MainViewBinding binding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        model = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        setFragments();
        binding.navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_calendar:
                moveFragment(tags[0]);
                return true;
            case R.id.navigation_chart:
                moveFragment(tags[1]);
                return true;
        }
        return false;
    }

    private void setFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(CalendarFragment.newInstance());
        fragments.add(ChartFragment.newInstance());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            fragmentTransaction.add(R.id.container, fragment, tags[i]);
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.show(fragments.get(1));
        fragmentTransaction.commit();
    }

    private void moveFragment(String tag) {
        if (mCurrentFragmentTag.equals(tag)) {
            return;
        }
        mCurrentFragmentTag = tag;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragmentManager.getFragments()) {
            String fTag = fragment.getTag();
            if (fTag != null) {
                if (fTag.equals(tag)) {
                    fragmentTransaction.show(fragment);
                } else {
                    fragmentTransaction.hide(fragment);
                }
            }

        }
        fragmentTransaction.commit();
    }

}
