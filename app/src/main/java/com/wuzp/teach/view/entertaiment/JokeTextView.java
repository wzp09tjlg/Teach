package com.wuzp.teach.view.entertaiment;

import com.wuzp.teach.base.BaseView;
import com.wuzp.teach.network.entity.entertaiment.EntertainmentBean;

import java.util.List;

/**
 * Created by wuzp on 2017/9/24.
 */
public interface JokeTextView extends BaseView {

    public void setJokeTextData(List<EntertainmentBean.ContentBean> data);
}
