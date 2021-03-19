package com.example.dashboarddesign;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexzh.circleimageview.ItemSelectedListener;
import com.google.android.material.appbar.AppBarLayout;

public class MainActivity extends AppCompatActivity implements ItemSelectedListener {
    private static final int PICK_IMAGES = 1;
    // declarations
    Button btn_back, btn_next, btn_menu;
    TextView tv_numCompte, tv_solde1, tv_solde2, tv_notif;
    com.alexzh.circleimageview.CircleImageView iv_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        iv_user = findViewById(R.id.iv_user);

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
            }
        });

        if (iv_user != null) {
            iv_user.setOnItemSelectedClickListener(this);
        }

    }

    @Override
    public void onSelected(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, PICK_IMAGES);
    }

    @Override
    public void onUnselected(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGES) {
                if (data.getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    for (int i = 0; i < mClipData.getItemCount(); i++) {
                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        // display your images
                        iv_user.setImageURI(uri);
                    }
                } else if (data.getData() != null) {
                    Uri uri = data.getData();
                    // display your image
                    iv_user.setImageURI(uri);
                }
            }
        }
    }
}