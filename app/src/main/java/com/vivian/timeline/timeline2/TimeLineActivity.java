package com.vivian.timeline.timeline2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.vivian.timeline.R;

import java.util.ArrayList;
import java.util.List;

public class TimeLineActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;

    List<Event> mList = new ArrayList<>();
    TimeLineAdapter2 mAdapter;

    long[] times = {
            1497229200,
            1497240000,
            1497243600,
            1497247200,
            1497249000,
            1497252600
    };
    String[] events = new String[]{
            "去小北门拿快递",
            "跟同事一起聚餐",
            "写文档",
            "和产品开会",
            "整理开会内容",
            "提交代码到git上"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline2);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new ItemDecoration2(this, 50));

        for (int i = 0; i < times.length; i++) {
            Event event = new Event();
            event.setTime(times[i]);
            event.setEvent(events[i]);
            mList.add(event);
        }

        mAdapter = new TimeLineAdapter2(this, mList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
