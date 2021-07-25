package com.github.isuperred.presenter.row;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Toast;

import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;

import com.github.isuperred.R;
import com.github.isuperred.activity.VideoDetailActivity;
import com.github.isuperred.base.BaseListRowPresenter;
import com.github.isuperred.bean.Content;
import com.github.isuperred.utils.FontDisplayUtil;


public class TypeFiveListRowPresenter extends BaseListRowPresenter {
    @SuppressLint("RestrictedApi")
    @Override
    protected void initializeRowViewHolder(RowPresenter.ViewHolder holder) {
        super.initializeRowViewHolder(holder);
        final ViewHolder rowViewHolder = (ViewHolder) holder;
        rowViewHolder.getGridView().setHorizontalSpacing(FontDisplayUtil.dip2px(rowViewHolder.getGridView().getContext(), 24));
        rowViewHolder.getGridView().setFocusScrollStrategy(HorizontalGridView.FOCUS_SCROLL_ITEM);

        setOnItemViewClickedListener(new BaseOnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder,
                                      Object item, RowPresenter.ViewHolder rowViewHolder, Object row) {
                if (item instanceof Content.DataBean.WidgetsBean) {
                    Toast.makeText(((ViewHolder) rowViewHolder).getGridView().getContext(),
                            "位置:" + ((ViewHolder) rowViewHolder).getGridView().getSelectedPosition(),
                            Toast.LENGTH_SHORT).show();
                    ((ViewHolder) rowViewHolder).getGridView().getContext().startActivity(new Intent(((ViewHolder) rowViewHolder).getGridView().getContext(), VideoDetailActivity.class));

                }

            }
        });
    }
}
