package com.dangxy.readhub.model.teach;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dangxy.readhub.R;
import com.dangxy.readhub.ReadhubApplication;
import com.dangxy.readhub.base.BaseLazyFragment;
import com.dangxy.readhub.entity.TeachEntity;

import butterknife.BindView;

/**
 * @description  描述
 * @author  dangxy99
 * @date   2017/12/30
 */
public class TeachFragment extends BaseLazyFragment implements TeachContract.ITeachView {


    @BindView(R.id.rv_teach)
    RecyclerView rvTeach;
    @BindView(R.id.srl_teach)
    SwipeRefreshLayout srlTeach;
    private TeachPresenter teachPresenter;
    private TeachListAdapter teachListAdapter;

    public TeachFragment() {
    }


    @Override
    protected void loadData() {
        teachPresenter = new TeachPresenter(this);
        teachPresenter.getData();
        teachPresenter.setRefresh(srlTeach, rvTeach);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_teach;
    }

    @Override
    protected void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReadhubApplication.getInstance());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTeach.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void setRefresh(TeachEntity teachEntity) {
        teachListAdapter.refresh(teachEntity.getData());
    }

    @Override
    public void getTeachEntity(TeachEntity teachEntity, int page) {
        teachListAdapter = new TeachListAdapter(teachEntity.getData());
        rvTeach.setAdapter(teachListAdapter);
    }
}