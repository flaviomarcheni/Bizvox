package com.fmarcheni.bizvox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fmarcheni.bizvox.DetailActivity;
import com.fmarcheni.bizvox.R;
import com.fmarcheni.bizvox.adapter.ShotAdapter;
import com.fmarcheni.bizvox.entity.Shot;

import java.util.List;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Flavio on 04/07/2015.
 */
public class MainFragment extends Fragment implements OnRefreshListener, ShotAdapter.RecyclerViewAdapterCallBack {
    private ShotAdapter shotAdapter;
    private SwipeRefreshLayout refreshLayout;

    private RecyclerView rvShots;
    private RecyclerView.LayoutManager layoutManager;
    private static int lastKnowPosition = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.shots_fragment, container, false);
        //   refreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipe_container);
        rvShots = (RecyclerView) inflate.findViewById(R.id.rv_shots);
        //rvShots.addOnScrollListener(this);
        //  refreshLayout.setOnRefreshListener(this);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        layoutManager = new LinearLayoutManager(getActivity());
        rvShots.setLayoutManager(layoutManager);
        rvShots.setHasFixedSize(true);
        shotAdapter = new ShotAdapter(this);
        rvShots.setAdapter(shotAdapter);
        rvShots.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int totalItemCount = layoutManager.getItemCount();
                int lastFirstVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                lastKnowPosition= lastFirstVisiblePosition;
                    if (totalItemCount > 1 && (lastFirstVisiblePosition >= totalItemCount - 1)) {
                        int nextPage = (totalItemCount / 30) + 1;
                        EventBus.getDefault().post(nextPage);
                    }
            }
        });


    }

    public void loadShots(List<Shot> shots) {
        shotAdapter.addOShots(shots);
        layoutManager.scrollToPosition(lastKnowPosition);
    }

    @Override
    public void onRefresh() {
        //efreshLayout.setRefreshing(true);
        ///   new ShotListTask(getActivity()).execute();

    }

    @Override
    public void onRecyclerViewElementClicked(View view, int position, Shot element) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.SHOT_SELECTED, element);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                Pair.create(view.findViewById(R.id.shot_photo_small), getResources().getString(R.string.foto_teaser_do_shot))
        );
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }
}
