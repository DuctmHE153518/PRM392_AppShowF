package com.prm392team.shopf;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.prm392team.shopf.Entity.User;
import com.prm392team.shopf.Fragment.OrderCancelFragment;
import com.prm392team.shopf.Fragment.OrderCompleteFragment;
import com.prm392team.shopf.Fragment.OrderShippingFragment;
import com.prm392team.shopf.R;
import com.prm392team.shopf.RoomDB.UserDAO;

class MyViewAdapter extends FragmentStateAdapter {
    public MyViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new OrderShippingFragment();
            case 1:
                return new OrderCompleteFragment();
            default:
                return new OrderCancelFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

public class OrderStatusActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private  MyViewAdapter myViewAdapter;
    private void bindingView(){
        viewPager2 = findViewById((R.id.product_list_view));
        tabLayout = findViewById((R.id.tab_layout_status));
        myViewAdapter = new MyViewAdapter(this);
    }
    private void bindingAction(){
        viewPager2.setAdapter(myViewAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        bindingView();
        bindingAction();
    }
}