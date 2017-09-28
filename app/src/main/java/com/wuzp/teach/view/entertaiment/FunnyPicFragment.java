package com.wuzp.teach.view.entertaiment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuzp.teach.R;
import com.wuzp.teach.adapter.BindingViewHolder;
import com.wuzp.teach.adapter.CommonAdapter;
import com.wuzp.teach.base.TechFragment;
import com.wuzp.teach.databinding.FragmentFunnyPicBinding;
import com.wuzp.teach.databinding.ItemFunnyPicBinding;
import com.wuzp.teach.network.entity.entertaiment.EntertainmentBean;
import com.wuzp.teach.utils.GlideUtil;
import com.wuzp.teach.widget.common.RecyclerItemDecoration;

import java.util.List;

/**
 * Created by wuzp on 2017/9/24.
 */
public class FunnyPicFragment extends TechFragment<FragmentFunnyPicBinding, FunnyPicPresenter> implements FunnyPicView {
    private CommonAdapter<EntertainmentBean.ContentBean> adapter;
    private List<EntertainmentBean.ContentBean> mData;

    @Override
    protected FunnyPicPresenter createPresenter() {
        return new FunnyPicPresenter(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_funny_pic;
    }

    @Override
    protected void initView() {
        super.initView();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        RecyclerItemDecoration itemDecoration = new RecyclerItemDecoration(mContext,R.drawable.drawable_item_divider_joke_text);
        binding.recyclerFunny.setLayoutManager(layoutManager);
        binding.recyclerFunny.addItemDecoration(itemDecoration);
    }

    @Override
    protected void initData() {
        super.initData();
        adapter = new CommonAdapter<EntertainmentBean.ContentBean>(mContext, R.layout.item_funny_pic) {
            @Override
            public void convert(BindingViewHolder holder, int position) {
                ItemFunnyPicBinding binding = holder.getBinding();
                binding.textTitle.setText(mData.get(position).getTitle());
                binding.textTime.setText(mData.get(position).getCt());
                GlideUtil.load(mContext,mData.get(position).getImg(),R.drawable.icon_default_item,R.drawable.icon_default_item,binding.imgFunny);
            }
        };
        binding.recyclerFunny.setAdapter(adapter);
        presenter.start();
        //showLoading();
    }

    //设置搞笑图片数据
    @Override
    public void setFunnyPicData(List<EntertainmentBean.ContentBean> data) {
        mData = data;
        adapter.setData(data);
        hideLayoutError();
    }

    @Override
    public void error(int code, String msg) {

    }

    private void hideLayoutError(){
        if(binding.layoutError.layoutError.getVisibility() == View.VISIBLE){
            binding.layoutError.layoutError.setVisibility(View.INVISIBLE);
        }
    }
}
