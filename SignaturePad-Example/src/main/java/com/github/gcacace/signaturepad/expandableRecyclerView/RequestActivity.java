package com.github.gcacace.signaturepad.expandableRecyclerView;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.gcacace.signaturepad.GlobalVar;
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

        adapter.addFragment(new IncomingFragment(), "Ý kiến đến");
        adapter.addFragment(new SendFragment(), "Ý kiến đi");
        adapter.addFragment(new LuongDataFragment(), "Luồng xử lý");
        adapter.addFragment(new PhieuDuyetFragment(), "Phiếu trình kí văn bản");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (GlobalVar.getInstance().isShowPheDuyet())
        {
            GlobalVar.getInstance().setShowPheDuyet(false);
            viewPager.setCurrentItem(3);
        }
    }
}
