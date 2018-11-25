package com.example.compc.wallpaper;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class home extends AppCompatActivity {

    Button go_animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        go_animal=(Button)findViewById(R.id.button_animal);
        go_animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p1 = new Intent(home.this,animal.class);
                startActivity(p1);
           }
        });
    }
}
