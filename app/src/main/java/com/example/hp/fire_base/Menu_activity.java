package com.example.hp.fire_base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu_activity extends AppCompatActivity {

    private ImageButton imageButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activity);

        this.setTitle("Menu Activity" );

        imageButton = (ImageButton) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);

        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);

    }
}
