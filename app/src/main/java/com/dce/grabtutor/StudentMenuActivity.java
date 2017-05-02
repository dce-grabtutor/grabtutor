package com.dce.grabtutor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dce.grabtutor.Handler.AccountHandler;
import com.dce.grabtutor.Model.Account;
import com.dce.grabtutor.Model.Conversation;
import com.dce.grabtutor.Model.URI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tvNavUserFullName;
    TextView tvNavUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        tvNavUserFullName = (TextView) headerView.findViewById(R.id.tvNavUserFullName);
        tvNavUserEmail = (TextView) headerView.findViewById(R.id.tvNavUserEmail);

        Account account = Account.loggedAccount;
        String fullName = account.getAcc_lname() + ", " + account.getAcc_fname() + " " + account.getAcc_mname();
        String email = account.getAcc_email();

        tvNavUserFullName.setText(fullName);
        tvNavUserEmail.setText(email);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_messages) {
            loadConversations();
        } else if (id == R.id.nav_logout) {
            new AccountHandler(this).removeLoggedAccount();
            LoginActivity.loggedOut = true;
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Messages or Conversations
    public void loadConversations() {
        Intent intent = new Intent(this, ConversationActivity.class);
        startActivity(intent);
    }

    public void btnTutorSearchClick(View view) {
        Intent intent = new Intent(this, StudentTutorSearchActivity.class);
        startActivity(intent);
    }

    public void btnTutorSearchSettingsClick(View view) {
        Intent intent = new Intent(this, StudentTutorSearchSettingsActivity.class);
        startActivity(intent);
    }

}
