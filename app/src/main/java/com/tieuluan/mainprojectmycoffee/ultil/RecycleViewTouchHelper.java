package com.tieuluan.mainprojectmycoffee.ultil;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.tieuluan.mainprojectmycoffee.adapter.RecAdapterCart;

public class RecycleViewTouchHelper extends ItemTouchHelper.SimpleCallback {


    /**
     * Creates a Callback for the given drag and swipe allowance. These values serve as
     * defaults
     * and if you want to customize behavior per ViewHolder, you can override
     * {@link #getSwipeDirs(RecyclerView, ViewHolder)}
     * and / or {@link #getDragDirs(RecyclerView, ViewHolder)}.
     *
     * @param dragDirs  Binary OR of direction flags in which the Views can be dragged. Must be
     *                  composed of {@link #LEFT}, {@link #RIGHT}, {@link #START}, {@link
     *                  #END},
     *                  {@link #UP} and {@link #DOWN}.
     * @param swipeDirs Binary OR of direction flags in which the Views can be swiped. Must be
     *                  composed of {@link #LEFT}, {@link #RIGHT}, {@link #START}, {@link
     *                  #END},
     *                  {@link #UP} and {@link #DOWN}.
     */
    RecycleViewTouchHelperListener listener;

    public RecycleViewTouchHelper(int dragDirs,int swipeDirs,RecycleViewTouchHelperListener listener)
    {
        super(dragDirs,swipeDirs);
        this.listener=listener;
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if(listener!=null)
            listener.onSwipe(viewHolder,direction,viewHolder.getAdapterPosition());
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View forgroundview =((RecAdapterCart.VH)viewHolder).view_forebackground;
        getDefaultUIUtil().clearView(forgroundview);

    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(viewHolder!=null)
        {
            View forgroundview=((RecAdapterCart.VH)viewHolder).view_forebackground;
            getDefaultUIUtil().onSelected(forgroundview);
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View forgroundview=((RecAdapterCart.VH)viewHolder).view_forebackground;

        getDefaultUIUtil().onDraw(c, recyclerView, forgroundview, dX, dY, actionState, isCurrentlyActive);
    }
}
