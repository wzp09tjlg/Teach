package com.wuzp.teach.view.entertaiment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuzp.teach.R;
import com.wuzp.teach.adapter.BindingViewHolder;
import com.wuzp.teach.adapter.CommonAdapter;
import com.wuzp.teach.base.TechFragment;
import com.wuzp.teach.databinding.FragmentJokeTextBinding;
import com.wuzp.teach.databinding.ItemJokeTextBinding;
import com.wuzp.teach.network.entity.entertaiment.EntertainmentBean;
import com.wuzp.teach.utils.HtmlUtil;
import com.wuzp.teach.widget.common.RecyclerItemDecoration;

import java.util.List;

/**
 * Created by wuzp on 2017/9/24.
 */
@SuppressWarnings("all")
public class JokeTextFragment extends TechFragment<FragmentJokeTextBinding, JokeTextPresenter> implements JokeTextView {
    private CommonAdapter<EntertainmentBean.ContentBean> adapter;
    private List<EntertainmentBean.ContentBean> mData;

    @Override
    protected JokeTextPresenter createPresenter() {
        return new JokeTextPresenter(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_joke_text;
    }

    @Override
    protected void initView() {
        super.initView();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        RecyclerItemDecoration itemDecoration = new RecyclerItemDecoration(mContext,R.drawable.drawable_item_divider_joke_text);
        binding.recyclerJoke.setLayoutManager(layoutManager);
        binding.recyclerJoke.addItemDecoration(itemDecoration);
    }

    @Override
    protected void initData() {
        super.initData();
        adapter = new CommonAdapter<EntertainmentBean.ContentBean>(mContext, R.layout.item_joke_text) {
            @Override
            public void convert(BindingViewHolder holder, int position) {
                ItemJokeTextBinding binding = holder.getBinding();
                binding.textTitle.setText(mData.get(position).getTitle());
                binding.textTime.setText(mData.get(position).getCt());
                String content = HtmlUtil.convert(mData.get(position).getText());
                binding.textContent.setText(content);
            }
        };
        binding.recyclerJoke.setAdapter(adapter);
        presenter.start();
    }

    //设置笑话数据
    @Override
    public void setJokeTextData(List<EntertainmentBean.ContentBean> data) {
        this.mData = data;
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
