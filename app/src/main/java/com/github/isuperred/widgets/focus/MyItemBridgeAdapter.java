package com.github.isuperred.widgets.focus;

import android.view.View;
import android.widget.AdapterView;

import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.Presenter;

/**
 * Auth:CL
 * Description：ItemBridgeAdapter 基类封装（子类可复写如下三个方法，实现点击、长按、焦点事件的监听）
 * <pre>
 * public abstract OnItemViewClickedListener getOnItemViewClickedListener();
 *
 * public OnItemViewLongClickedListener getOnItemViewLongClickedListener(){
 * return null;
 * }
 *
 * public OnItemFocusChangedListener getOnItemFocusChangedListener(){
 * return null;
 * }
 * <pre/>
 **/
public abstract class MyItemBridgeAdapter extends ItemBridgeAdapter {

    protected MyItemBridgeAdapter(ObjectAdapter adapter) {
        super(adapter, null);
    }

    @Override
    protected void onBind(final ViewHolder viewHolder) {
        if (getOnItemViewClickedListener() != null) {
            //设置点击事件
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnItemViewClickedListener().onItemClicked(v, viewHolder.getViewHolder(), viewHolder.getItem());

                }
            });
        }
        if (getOnItemViewLongClickedListener() != null) {
            //设置长按事件
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (getOnItemViewLongClickedListener() != null) {
                        return getOnItemViewLongClickedListener().onItemLongClicked(v, viewHolder.getViewHolder(), viewHolder.getItem());
                    }
                    return true;
                }
            });
        }
        if (getOnItemFocusChangedListener() != null) {
            //设置焦点监听事件
            viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    getOnItemFocusChangedListener().onItemFocusChanged(v, viewHolder.getViewHolder(),
                            viewHolder.getItem(), hasFocus, viewHolder.getAdapterPosition());
                }
            });
        }
        super.onBind(viewHolder);
    }

    @Override
    protected void onUnbind(ViewHolder viewHolder) {
        super.onUnbind(viewHolder);
        viewHolder.itemView.setOnClickListener(null);
        if (getOnItemFocusChangedListener() != null) {
            viewHolder.itemView.setOnFocusChangeListener(null);
        }
    }

    /**返回ItemView点击事件监听器*/
    public abstract OnItemViewClickedListener getOnItemViewClickedListener();

    /**返回ItemView长按事件监听器*/
    public OnItemViewLongClickedListener getOnItemViewLongClickedListener() {
        return null;
    }

    /**返回ItemView焦点变更监听器*/
    public OnItemFocusChangedListener getOnItemFocusChangedListener() {
        return null;
    }

    public interface OnItemViewClickedListener {
        void onItemClicked(View focusView, Presenter.ViewHolder itemViewHolder, Object item);
    }

    public interface OnItemViewLongClickedListener {
        boolean onItemLongClicked(View focusView, Presenter.ViewHolder itemViewHolder, Object item);
    }

    public interface OnItemFocusChangedListener {
        void onItemFocusChanged(View focusView, Presenter.ViewHolder itemViewHolder, Object item, boolean hasFocus, int pos);
    }
}
