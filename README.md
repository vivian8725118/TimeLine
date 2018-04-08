# TimeLine
This project aims to provide a easy way to use *Staggered TimeLine* implementation.

[中文版文档](https://github.com/vivian8725118/TimeLine/blob/master/README_CHINESE.md)

## Provide the gradle dependency
```
compile 'com.vivian.widgets:TimeLineItemDecoration:1.3'
```

## Usage
If you want to use this *TimeLine*  in your project, you have to do the following.

- Set the `StaggeredGridLayoutManager` to your `RecyclerView`
```
mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
```

- Use the `ItemDecoration` of this project [ItemDecoration.java](https://github.com/vivian8725118/TimeLine/blob/master/app/src/main/java/com/vivian/timeline/timeline1/ItemDecoration.java)
```
mRecyclerView.addItemDecoration(new ItemDecoration(this,100));
```
The second ctor-parameter will define the `distance`. In that case 100px.

Currently there are 2 styles available, as seen in the Screenshots. You can find the implementation for the second style here. [DotItemDecoration.java](https://github.com/vivian8725118/TimeLine/blob/master/app/src/main/java/com/vivian/timeline/itemdecoration/DotItemDecoration.java)  
```
  DotItemDecoration mItemDecoration = new DotItemDecoration
                .Builder(this)
                .setOrientation(DotItemDecoration.VERTICAL)//if you want a horizontal item decoration,remember to set horizontal orientation to your LayoutManager
                .setItemStyle(DotItemDecoration.STYLE_DRAW)//choose to draw or use resource
                .setTopDistance(20)//dp
                .setItemInterVal(10)//dp
                .setItemPaddingLeft(10)//default value equals to item interval value
                .setItemPaddingRight(10)//default value equals to item interval value
                .setDotColor(Color.WHITE)
                .setDotRadius(2)//dp
                .setDotPaddingTop(0)
                .setDotInItemOrientationCenter(false)//set true if you want the dot align center
                .setLineColor(Color.RED)//the color of vertical line
                .setLineWidth(1)//dp
                .setEndText("END")//set end text
                .setTextColor(Color.WHITE)
                .setTextSize(10)//sp
                .setDotPaddingText(2)//dp.The distance between the last dot and the end text
                .setBottomDistance(40)//you can add a distance to make bottom line longer
                .create();
```
if you want to do something according to the column of span,implements `SpanIndexListener` of this project [SpanIndexListener](https://github.com/vivian8725118/TimeLine/blob/master/app/src/main/java/com/vivian/timeline/itemdecoration/SpanIndexListener.java).
```
   mItemDecoration.setSpanIndexListener(new SpanIndexListener() {
            @Override
            public void onSpanIndexChange(View view, int spanIndex) {
                view.setBackgroundResource(spanIndex == 0 ? R.drawable.pop_left : R.drawable.pop_right);
            }
   });
```

## Example
<div>
<image hspace="20" src="https://github.com/vivian8725118/TimeLine/blob/master/art/FEDD719A6C84658E728E03762C5334AE.jpg" width=40% height=40%/>
<image src="https://github.com/vivian8725118/TimeLine/blob/master/art/A6A1B601503A23E054ABC9B205B2131F.png?raw=true" width=40% height=40%/>
</div>

# Contact

If you have any problem with this,you can add my wechat:`vivian8725118`,or email me:`1354458047@qq.com`/`vivian8725118@gmail.com`. I'll reply you when I saw it.

# License

    Copyright 2017 Vivian

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
