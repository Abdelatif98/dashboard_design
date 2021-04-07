package com.example.dashboarddesign;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexzh.circleimageview.ItemSelectedListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements ItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private static final int PICK_IMAGE = 1;
    //Drawer
    DrawerLayout drawer;
    NavigationView navigationView;
    // declarations
    Button btn_back, btn_next, btn_menu;
    TextView tv_numCompte, tv_solde1, tv_solde2, tv_notif;
    com.alexzh.circleimageview.CircleImageView iv_user;
    // declaring fragments and Fmanager
    final Fragment mbillfrag = new billsFragment();
    final Fragment mcheqfrag = new chequesFragment();
    final Fragment mopfrag = new operationsFragment();
    final Fragment mribfrag = new ribFragment();
    final Fragment othfrag = new OtherFragment();
    final FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // side menu:
        drawer = findViewById(R.id.side_nav);
        navigationView = findViewById(R.id.side_menu);
        // make side menu items clickable
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.side_menu_home);
        // hide/show elements (login/logout)
        Menu menu = navigationView.getMenu();
        // menu.findItem(R.id.side_menu_taches).setVisible(false);


        // remove appBar elevation
        AppBarLayout layout = (AppBarLayout) findViewById(R.id.appBarLayout);
        layout.setOutlineProvider(null);

        //getting the views
        btn_back = findViewById(R.id.btn_back);
        btn_next = findViewById(R.id.btn_next);
        btn_menu = findViewById(R.id.btn_menu);

        tv_numCompte = findViewById(R.id.tv_numCmp);
        tv_solde1 = findViewById(R.id.tv_solde1);
        tv_solde2 = findViewById(R.id.tv_solde2);
        tv_notif = findViewById(R.id.tv_notif);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        iv_user = findViewById(R.id.iv_user);

        // switching fragments
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //events
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"button previous account clicked!",Toast.LENGTH_SHORT).show();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"button next account clicked!",Toast.LENGTH_SHORT).show();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"button menu clicked!",Toast.LENGTH_SHORT).show();
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                } else {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
        });

        // user image view selection
        if (iv_user != null) {
            iv_user.setOnItemSelectedClickListener(this);
        }


    }



    //bottomnavselectItem
    // switching fragments on bottomnav item  click
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.getrans:

                    fm.beginTransaction().replace(R.id.fatherfragment, new operationsFragment()).commit();
                    break;
                case R.id.getbill:
                    fm.beginTransaction().replace(R.id.fatherfragment, new billsFragment()).commit();
                    break;
                case R.id.getcheque:
                    fm.beginTransaction().replace(R.id.fatherfragment, new chequesFragment()).commit();
                    break;
                case R.id.getrib:
                    fm.beginTransaction().replace(R.id.fatherfragment, new ribFragment()).commit();
                    break;
            }

            return true;
        }
    };


    @Override
    public void onSelected(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onUnselected(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                Uri uri = null;
                if (data != null) {
                    uri = data.getData();
                }
                // display your image
                    iv_user.setImageURI(uri);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            MainActivity.super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.side_menu_home:
                break;
            case R.id.side_menu_params:
                Toast.makeText(this, "To Params", Toast.LENGTH_SHORT).show();
                break;
            case R.id.side_menu_comptes:
                Toast.makeText(this, "To Comptes", Toast.LENGTH_SHORT).show();
                break;
            case R.id.side_menu_taches:
                Toast.makeText(this, "To Taches", Toast.LENGTH_SHORT).show();
                break;
            case R.id.side_menu_rib:
                fm.beginTransaction().replace(R.id.fatherfragment, new ribFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}