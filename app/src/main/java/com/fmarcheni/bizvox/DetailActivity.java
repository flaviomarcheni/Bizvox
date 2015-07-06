package com.fmarcheni.bizvox;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.transition.Fade;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.fmarcheni.bizvox.entity.Shot;
import com.fmarcheni.bizvox.util.StringUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Flavio on 04/07/2015.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String SHOT_SELECTED = "SHOT_SELECTED";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.foto_teaser_do_shot)
    ImageView backgroundImageView;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.views_count)
    TextView viewsCount;
    @InjectView(R.id.comments)
    TextView commentCount;
    @InjectView(R.id.created_at)
    TextView createdAt;
    @InjectView(R.id.description)
    TextView description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.inject(this);

        setupWindowAnimations();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Shot shot = (Shot) getIntent().getSerializableExtra(SHOT_SELECTED);
        description.setText(Html.fromHtml(StringUtils.getNotNullString(shot.getDescription())));
        commentCount.setText(shot.getComments_count().toString());
        viewsCount.setText(shot.getViews_count().toString());
        title.setText(shot.getTitle());
        createdAt.setText(StringUtils.getFormattedDate(shot.getCreated_at(), "yyyy-MM-dd'T'HH:mm:ss'Z'"));
        supportPostponeEnterTransition();
        Picasso.with(this).load(shot.getImages().getNormal()).centerCrop().fit().into(backgroundImageView, new Callback() {
            @Override
            public void onSuccess() {
                supportStartPostponedEnterTransition();
            }

            @Override
            public void onError() {

            }
        });

    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            getWindow().setReturnTransition(fade);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
