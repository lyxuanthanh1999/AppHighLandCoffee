package com.tieuluan.mainprojectmycoffee.ultil;

import androidx.recyclerview.widget.RecyclerView;

public interface RecycleViewTouchHelperListener {
    void onSwipe(RecyclerView.ViewHolder viewHolder,int direction,int postition);
}
