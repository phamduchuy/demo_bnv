package com.github.gcacace.signaturepad.expandableRecyclerView;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.IncomingFragment;
import com.github.gcacace.signaturepad.expandableRecyclerView.sendRequest.SendFragment;

import it.gcacace.signaturepad.R;

public class RequestActivity extends AppCompatActivity
{
    private ViewPager viewPager;
    private TabPagerAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new IncomingFragment(), "YÊU CẦU GỬI ĐẾN");
        adapter.addFragment(new SendFragment(), "YÊU CẦU GỬI ĐI");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
