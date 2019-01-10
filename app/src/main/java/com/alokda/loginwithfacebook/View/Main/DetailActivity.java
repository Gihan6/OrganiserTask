package com.alokda.loginwithfacebook.View.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alokda.loginwithfacebook.Model.ListModel;
import com.alokda.loginwithfacebook.R;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_edit, iv_delete, iv_star;
    TextView tv_description, tv_date;
    ListModel model;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activty);


        model = (ListModel) getIntent().getSerializableExtra("Key");
        initView();
        if (model != null) {
            updateUi();
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        iv_edit = findViewById(R.id.iv_activityDetail_edit);
        iv_delete = findViewById(R.id.iv_activityDetail_delete);
        iv_star = findViewById(R.id.iv_activityDetail_star);
        tv_date = findViewById(R.id.tv_activityDetail_date);
        tv_description = findViewById(R.id.tv_activityDetail_detail);

        iv_star.setOnClickListener(this);
        iv_star.setTag(0);

    }

    private void updateUi() {
        if (!model.getTitle().equals(null) && !model.getTitle().equals("")) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(model.getTitle());
        }
        if (!model.getDetail().equals(null) && !model.getDetail().equals("")) {
            tv_description.setText(model.getDetail());
        }
        if (!model.getDate().equals(null) && !model.getDate().equals("")) {
            tv_date.setText(model.getDate());
        }
        if (!model.getStar().equals(null) && model.getStar()) {
            iv_star.setBackgroundResource(R.drawable.star_color);
        } else {
            iv_star.setBackgroundResource(R.drawable.star);

        }
    }


    @Override
    public void onClick(View v) {

        switch ((Integer) v.getTag()) {
            case 0:
                changeStar();
                break;
            default:
                break;
        }
    }

    private void changeStar() {
        if (model.getStar() != null && model.getStar()) {
            iv_star.setBackgroundResource(R.drawable.star);
            model.setStar(false);
            Toast.makeText(getApplicationContext(), getString(R.string.unFavourite), Toast.LENGTH_SHORT).show();
        } else {
            iv_star.setBackgroundResource(R.drawable.star_color);
            model.setStar(true);
            Toast.makeText(getApplicationContext(), getString(R.string.favourite), Toast.LENGTH_SHORT).show();

        }
    }

}
