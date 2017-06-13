# TimeLine
Staggered TimeLine

## Usage
Use StaggerGridLayoutManager to setLayoutManager，and add the ItemDecoration file to divide items.
The second params in ItemDecoration(context,distance) is used to set the space of items.

```
mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
mRecyclerView.addItemDecoration(new ItemDecoration(this,100));
```

## Example
<div>
<image hspace="20" src="https://github.com/vivian8725118/TimeLine/blob/master/art/FEDD719A6C84658E728E03762C5334AE.jpg" width=40% height=40%/>
<image src="https://github.com/vivian8725118/TimeLine/blob/master/art/A6A1B601503A23E054ABC9B205B2131F.png?raw=true" width=40% height=40%/>
</div>
