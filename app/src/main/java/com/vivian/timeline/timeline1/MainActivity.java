package com.vivian.timeline.timeline1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.vivian.timeline.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    int[] resId = {
            R.drawable.image_1,
//            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
            R.drawable.image_7,
            R.drawable.image_8
    };

    List<Item> mList = new ArrayList<>();
    TimeLineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new ItemDecoration(this,100));

        for (int i = 0; i < 7; i++) {
            Item item = new Item();
            item.setResId(resId[i]);
            item.setText("我从未见过如此厚颜无耻之人");
            mList.add(item);
        }

        mAdapter = new TimeLineAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
