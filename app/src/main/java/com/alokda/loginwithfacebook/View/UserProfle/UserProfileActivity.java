package com.alokda.loginwithfacebook.View.UserProfle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alokda.loginwithfacebook.Model.ListModel;
import com.alokda.loginwithfacebook.R;
import com.alokda.loginwithfacebook.View.Adapters.FavouriteAdapter;
import com.alokda.loginwithfacebook.View.Login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import static com.alokda.loginwithfacebook.Model.User.LoggedUser;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv_user;
    TextView tv_user;
    LinearLayout view_logout;
    RecyclerView rv_favourite;
    List<ListModel> mList;
    FavouriteAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profie);

        initView();
        initFakeData();
        initAdapter();
        setData();

    }

    private void initView() {
        iv_user = (ImageView) findViewById(R.id.civ_userProfile_image);
        tv_user = (TextView) findViewById(R.id.tv_userProfile_userName);
        view_logout = (LinearLayout) findViewById(R.id.view_userProfile_logout);
        rv_favourite = (RecyclerView) findViewById(R.id.rv_userProfile_favourite);

        view_logout.setOnClickListener(this);
        view_logout.setTag(0);

    }

    private void initFakeData() {

        mList = new ArrayList<>();
        ListModel model = new ListModel();
        model.setStar(false);
        model.setTitle("My First Event Title");
        model.setDate("Create On: 8-1-2019 ");
        model.setDetail("Loarm Text");
        mList.add(model);
    }

    private void setData() {
        if (LoggedUser != null) {
            if (!LoggedUser.getFirstName().equals(null)) {
                tv_user.setText(LoggedUser.getFirstName());
            } else {
                tv_user.setText(getString(R.string.userName));
            }
            if (!LoggedUser.getProfileURL().equals(null)) {
                iv_user.setImageURI(Uri.parse(LoggedUser.getProfileURL()));
            }
        }
    }

    private void logout() {
        LoggedUser = null;
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

    }

    private void initAdapter() {
        layoutManager = new LinearLayoutManager(this);
        adapter = new FavouriteAdapter(mList, getApplicationContext());
        rv_favourite.setLayoutManager(layoutManager);
        rv_favourite.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch ((Integer) v.getTag()) {
            case 0:
                logout();
                break;
            default:
                break;
        }
    }
}
