package com.wuzp.teach.view.main.fragment;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuzp.teach.R;
import com.wuzp.teach.adapter.ShelfAdapter;
import com.wuzp.teach.base.TechFragment;
import com.wuzp.teach.database.table.BookTable;
import com.wuzp.teach.databinding.FragmentReadBinding;
import com.wuzp.teach.utils.LogUtil;

/**
 * Created by wuzp on 2017/9/23.
 */
public class ReadFragment extends TechFragment<FragmentReadBinding,FunPresenter> implements FunView,LoaderManager.LoaderCallbacks<Cursor> {
    private String sortString = SORT_STUTES_READTIME;
    public static final String SORT_STUTES_READTIME = BookTable.NET_READ_TIME + " DESC";
    public static final String SORT_STUTES_UPDATETIME = BookTable.LAST_UPDATE_TIME + " DESC";
    public static final String SORT_STUTES_INTO = BookTable.DOWNLOAD_TIME + " DESC";
    public static final String WHERE_CURSOR = "( " + BookTable.FLAG + " in ('normal','addfail'))";

    private ShelfAdapter shelfAdapter;

    @Override
    protected FunPresenter createPresenter() {
        return new FunPresenter(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_read;
    }

    @Override
    protected void initView() {
        super.initView();
        binding.layoutTitle.imgTitleBack.setVisibility(View.INVISIBLE);
        binding.layoutTitle.imgTitleMenu.setVisibility(View.INVISIBLE);
        binding.layoutTitle.textTitle.setText("读书");

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,3);
        binding.recycler.setLayoutManager(layoutManager);

    }

    @Override
    protected void initData() {
        super.initData();
        getActivity().getLoaderManager().initLoader(0, null, this);
        shelfAdapter = new ShelfAdapter(mContext);
        binding.recycler.setAdapter(shelfAdapter);
    }

    /** cursorloader方法 */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse("content://" + "com.wuzp.book/" + BookTable.TABLE_NAME);
        return new CursorLoader(getContext(), uri, BookTable.COLUMNS, WHERE_CURSOR, null, sortString);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        LogUtil.e("onLoadFinished  data:" + data.getCount() + "  string:" + data.toString());
        shelfAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
      shelfAdapter.swapCursor(null);
    }

    @Override
    public void error(int code, String msg) {}
}
