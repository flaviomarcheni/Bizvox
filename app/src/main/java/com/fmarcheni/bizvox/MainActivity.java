package com.fmarcheni.bizvox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.widget.Toast;

import com.fmarcheni.bizvox.entity.Shot;
import com.fmarcheni.bizvox.fragment.MainFragment;
import com.fmarcheni.bizvox.service.ShotServiceExecutor;
import com.fmarcheni.bizvox.util.InternetUtil;
import com.fmarcheni.bizvox.util.Load;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Flavio on 03/07/2015.
 */
public class MainActivity extends AppCompatActivity implements  Callback<ArrayList<Shot>> {
    private MainFragment mainFragment;
    Load load;
    ShotServiceExecutor  executor;
    private String REQUEST_RESULT = "shotList";
    private ArrayList<Shot> shots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_container);
        mainFragment = new MainFragment();
        load = new Load(this);
        getSupportFragmentManager().beginTransaction().add(R.id.container, mainFragment).commit();
        load.show();
        executor = new ShotServiceExecutor();
        if(savedInstanceState !=null){
            shots = (ArrayList<Shot>) savedInstanceState.getSerializable(REQUEST_RESULT);

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        if(shots !=null){
            EventBus.getDefault().post(shots);;
        }else{
            if(!InternetUtil.isOnline(getBaseContext())){
                Toast.makeText(getBaseContext(),getString(R.string.check_connection), Toast.LENGTH_LONG).show();
            }else{
                EventBus.getDefault().post(1);
            }
        }

    }

    public void onEvent(ArrayList<Shot> shots){
        if(this.shots!=null){

            for(Shot s: shots){
                if(!this.shots.contains(s))
                    this.shots.add(s);
            }
        }else{
            this.shots = shots;
        }
        mainFragment.loadShots(shots);
        load.hide();
    }
    public void onEvent(Integer page){
        load.show();
        if(!InternetUtil.isOnline(getBaseContext())){
            Toast.makeText(getBaseContext(),getString(R.string.check_connection), Toast.LENGTH_LONG).show();
        }else{
            executor.getShotService().getShotList(page,30,this);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void success(ArrayList<Shot> shots, Response response) {
        EventBus.getDefault().post(shots);
    }
    @Override
    public void failure(RetrofitError error) {
        load.hide();
        Toast.makeText(getBaseContext(),getString(R.string.req_fail), Toast.LENGTH_LONG).show();

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(REQUEST_RESULT, this.shots);
    }
}
