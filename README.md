# TimeLine
左右交错的时间轴

## Usage
使用StaggerGridLayoutManager设置LayoutManager，并添加自定义的ItemDecoration。
其中，ItemDecoration的第二个参数用来设置item之间的间隔。

```
mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
mRecyclerView.addItemDecoration(new ItemDecoration(this,100));
```
MainActivity是图一的效果，TimeLineActivity是图二的效果
## 原理
用StaggerGridLayoutManager实现了类似瀑布流的效果，然后在ItemDecoration中画出分割线的效果。
在ItemDecoration中分别画了横线、竖线、还有时间的图标这三个来实现时间轴的效果。

## 效果图
<div>
<image hspace="20" src="https://github.com/vivian8725118/TimeLine/blob/master/art/FEDD719A6C84658E728E03762C5334AE.jpg" width=30% height=30%/>
<image src="https://github.com/vivian8725118/TimeLine/blob/master/art/8CB5B8DFC2BC98DA94990301D5E36B37.jpg?raw=true" width=30% height=30%/>
</div>
