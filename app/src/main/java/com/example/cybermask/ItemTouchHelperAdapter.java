package com.example.cybermask;

// The code was reference from CodingWithSmith - RecyclerView with ItemTouchHelper
// https://www.youtube.com/watch?v=uvzP8KTz4Fg
public interface ItemTouchHelperAdapter {
    void onItemMove (int fromPosition, int toPosition);

    void onItemSwiped(int position);

}
