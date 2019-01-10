package com.alokda.loginwithfacebook.View.Main;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alokda.loginwithfacebook.Model.ListModel;
import com.alokda.loginwithfacebook.R;
import com.alokda.loginwithfacebook.View.Adapters.ListAdapter;
import com.alokda.loginwithfacebook.View.Interface.ListListener;
import com.alokda.loginwithfacebook.View.Login.LoginActivity;
import com.alokda.loginwithfacebook.View.UserProfle.UserProfileActivity;
import com.alokda.loginwithfacebook.ViewModel.ListEventViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.alokda.loginwithfacebook.Model.User.LoggedUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ListListener {
    TextView tv_noData, tv_userName;
    ImageView iv_userImage;
    RecyclerView rv_list;
    ListAdapter listAdapter;
    RecyclerView.LayoutManager mLayout;
    List<ListModel> mList;
    ListEventViewModel listEventViewModel;

    @Override
    protected void onStart() {
        super.onStart();
        if (LoggedUser == null) {
            goToLogin();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initNavigation(navigationView.getHeaderView(0));

        initListener();
        initFakeData();
        initView();
        initAdapter();
        setAdapter();

        //   getDataFromWebServices();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initView() {
        rv_list = (RecyclerView) findViewById(R.id.rv_activityMain_list);
        tv_noData = (TextView) findViewById(R.id.tv_activityMain_noData);
    }

    private void initNavigation(View view) {
        tv_userName = (TextView) view.findViewById(R.id.userName);
        iv_userImage = (ImageView) view.findViewById(R.id.imageView);

        if (LoggedUser != null) {
            if (!LoggedUser.getFirstName().equals(null)) {
                tv_userName.setText(LoggedUser.getFirstName());
            } else {
                tv_userName.setText(getString(R.string.userName));
            }
            if (!LoggedUser.getProfileURL().equals(null)) {
                iv_userImage.setImageURI(Uri.parse(LoggedUser.getProfileURL()));
            }
        }
        iv_userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUserActivity();
            }
        });


    }

    private void goToUserActivity() {
        Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
        startActivity(intent);
    }

    private void initAdapter() {
        mLayout = new LinearLayoutManager(getApplicationContext());
        rv_list.setLayoutManager(mLayout);
        listAdapter = new ListAdapter(mList, getApplicationContext(), this);

    }

    private void setAdapter() {
        if (mList.size() > 0) {
            showRecycler();
            rv_list.setAdapter(listAdapter);
        } else {
            hideRecycler();
        }

    }

    private void hideRecycler() {
        rv_list.setVisibility(View.GONE);
        tv_noData.setVisibility(View.VISIBLE);

    }

    private void showRecycler() {
        rv_list.setVisibility(View.VISIBLE);
        tv_noData.setVisibility(View.GONE);
    }

    private void initListener() {
        listEventViewModel = ViewModelProviders.of(this).get(ListEventViewModel.class);

        listEventViewModel.getListEvent().observe(this, new Observer<List<ListModel>>() {
            @Override
            public void onChanged(@Nullable List<ListModel> listModels) {
                //here we will receive data from webservices and inflate ui
                // setAdapter();

            }
        });

    }

    private void initFakeData() {
        mList = new ArrayList<>();
        ListModel model = new ListModel();
        model.setStar(false);
        model.setTitle("My First Event Title");
        model.setDate("Create On: 8-1-2019 ");
        model.setDetail("Loarm Text");
        mList.add(model);
        model = new ListModel();
        model.setStar(true);
        model.setTitle("My Favourite Event Title");
        model.setDate("Create On: 8-1-2019 ");
        model.setDetail("Loarm Text");

        mList.add(model);
        model = new ListModel();
        model.setStar(false);
        model.setTitle("My First Event Title");
        model.setDate("Create On: 8-1-2019 ");
        model.setDetail("Loarm Text");

        mList.add(model);
        mList.add(model);
        mList.add(model);
        mList.add(model);
        mList.add(model);
        mList.add(model);


    }

    private void goToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void getDataFromWebServices() {
        listEventViewModel.getListEventFromServer();
    }

    private void goToDetail(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("Key", mList.get(position));
        startActivity(intent);

    }

    @Override
    public void onClickItem(int position) {
        goToDetail(position);
    }

}
