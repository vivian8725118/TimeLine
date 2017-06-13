# TimeLine
This project aims to provide a easy to use *Staggered TimeLine* implementation.

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

Currently there are 2 styles available, as seen in the Screenshots. You can find the implementation for the second style here. [ItemDecoration2.java](https://github.com/vivian8725118/TimeLine/blob/master/app/src/main/java/com/vivian/timeline/timeline2/ItemDecoration2.java)  

## Example
<div>
<image hspace="20" src="https://github.com/vivian8725118/TimeLine/blob/master/art/FEDD719A6C84658E728E03762C5334AE.jpg" width=40% height=40%/>
<image src="https://github.com/vivian8725118/TimeLine/blob/master/art/A6A1B601503A23E054ABC9B205B2131F.png?raw=true" width=40% height=40%/>
</div>
