package com.dicoding.associate.projectcataloguemovie2.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.associate.projectcataloguemovie2.Content.ComingSoonFragment;
import com.dicoding.associate.projectcataloguemovie2.Content.NowPlayingFragment;
import com.dicoding.associate.projectcataloguemovie2.R;

public class HomeTabFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;

    public HomeTabFragment(){
        // public constructor kosong yang diperlukan
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate Layout untuk Fragment ini
        view = inflater.inflate(R.layout.fragment_home_tab, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new sliderAdapter(getChildFragmentManager()));
        tabLayout = view.findViewById(R.id.viewPagerTab);
        tabLayout.post(new Runnable(){
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    private class sliderAdapter extends FragmentPagerAdapter {
        String now_playing = getResources().getString(R.string.now_playing);
        String coming_soon = getResources().getString(R.string.upcoming);
        final String tabs[] = {now_playing, coming_soon};

        public sliderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new NowPlayingFragment();
                case 1:
                    return new ComingSoonFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return tabs[position];
        }
    }
}
