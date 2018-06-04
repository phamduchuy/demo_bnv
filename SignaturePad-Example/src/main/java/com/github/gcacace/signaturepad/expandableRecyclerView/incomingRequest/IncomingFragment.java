package com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.model.ContentModel;
import com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.model.TitleModel;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.Arrays;
import java.util.List;

import it.gcacace.signaturepad.R;

/**
 * Created by ducqu on 6/1/2018.
 */

public class IncomingFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.incoming_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        List<TitleModel> list = getTitle();

        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator)
        {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        ExpandAdapter adapter  = new ExpandAdapter(getContext(), list);

        adapter.setOnGroupExpandCollapseListener(new GroupExpandCollapseListener() {
            @Override
            public void onGroupExpanded(ExpandableGroup group) {
                Log.d("", "");
            }

            @Override
            public void onGroupCollapsed(ExpandableGroup group) {
                Log.d("", "");
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        int sizeOfChuaXacDinh = list.get(0).getItems().size();
        adapter.clickFirst(sizeOfChuaXacDinh);
    }

    public List<TitleModel> getTitle()
    {
        return Arrays.asList(getTitle1(),
                getTitle2(),
                getTitle3());
    }

    public TitleModel getTitle1()
    {
        return new TitleModel("Chưa xử lý", makeContent());
    }

    public List<ContentModel> makeContent()
    {
        ContentModel model1 = new ContentModel("Tham khảo ý kiến trình ký 1", "tham khảo  bộ nội", "19-6-2018", "1", "Trường phòng kế hoạch", "");
        ContentModel model2 = new ContentModel("Tham khảo ý kiến trình ký 2", "tham khảo  bộ nội", "19-6-2018", "1", "Trường phòng kế hoạch", "");
//        ContentModel model3 = new ContentModel("Tham khảo ý kiến trình ký 3", "tham khảo  bộ nội", "19-6-2018", "2", "Trường phòng kế hoạch", "");
//        ContentModel model4 = new ContentModel("Tham khảo ý kiến trình ký 4", "tham khảo  bộ nội", "19-6-2018", "1", "Trường phòng kế hoạch", "");

        return Arrays.asList(model1, model2/*, model3, model4*/);
    }

    public TitleModel getTitle2()
    {
        return new TitleModel("Hoàn thành", makeContent2());
    }

    public List<ContentModel> makeContent2()
    {
        ContentModel model1 = new ContentModel("Tham khảo ý kiến trình ký 21", "tham khảo  bộ nội", "19-6-2018", "0", "Trường phòng kế hoạch", "");
        ContentModel model2 = new ContentModel("Tham khảo ý kiến trình ký 22", "tham khảo  bộ nội", "19-6-2018", "0", "Trường phòng kế hoạch", "");
//        ContentModel model3 = new ContentModel("Tham khảo ý kiến trình ký 23", "tham khảo  bộ nội", "19-6-2018", "2", "Trường phòng kế hoạch", "");
//        ContentModel model4 = new ContentModel("Tham khảo ý kiến trình ký 24", "tham khảo  bộ nội", "19-6-2018", "1", "Trường phòng kế hoạch", "");
        return Arrays.asList(model1, model2/*, model3, model4*/);
    }

    public TitleModel getTitle3()
    {
        return new TitleModel("Từ chối", makeContent3());
    }

    public List<ContentModel> makeContent3()
    {
        ContentModel model1 = new ContentModel("Tham khảo ý kiến trình ký 31", "tham khảo  bộ nội", "19-6-2018", "2", "Trường phòng kế hoạch", "");
        ContentModel model2 = new ContentModel("Tham khảo ý kiến trình ký 32", "tham khảo  bộ nội", "19-6-2018", "2", "Trường phòng kế hoạch", "");
//        ContentModel model3 = new ContentModel("Tham khảo ý kiến trình ký 33", "tham khảo  bộ nội", "19-6-2018", "2", "Trường phòng kế hoạch", "");
//        ContentModel model4 = new ContentModel("Tham khảo ý kiến trình ký 34", "tham khảo  bộ nội", "19-6-2018", "1", "Trường phòng kế hoạch", "");
        return Arrays.asList(model1, model2/*, model3, model4*/);
    }
}
