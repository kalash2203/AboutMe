package android.example.aboutme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TabActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabb);


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.view_pager);

        adapter = new MainAdapter(getSupportFragmentManager());
        adapter.AddFragment(new SavingPlan(),"Saving Plan");
        adapter.AddFragment(new CreateProfile(),"Profile");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

    }

    public void onClickFirstFragButton(int index,Fragment fragment,String s){

        adapter.RemoveFragment(index);
        adapter.AddNewFragment(index,fragment,s);
        adapter.notifyDataSetChanged();

    }


    private static class MainAdapter extends FragmentStatePagerAdapter {

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        public void AddFragment(Fragment fragment, String s) {
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }

        public void RemoveFragment(int index){
            fragmentArrayList.remove(index);
            stringArrayList.remove(index);
        }

        public void AddNewFragment(int index, Fragment fragment,String s){
            fragmentArrayList.add(index,fragment);
            stringArrayList.add(index,s);
        }



        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrayList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            // this method will be called for every fragment in the ViewPager

            // POSITION_NONE means something like: this fragment is no longer valid
            // triggering the ViewPager to re-build the instance of this fragment.
            return POSITION_NONE;
        }
    }