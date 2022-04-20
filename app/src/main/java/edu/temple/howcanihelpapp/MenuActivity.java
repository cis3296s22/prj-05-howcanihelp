package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import edu.temple.howcanihelpapp.Firebase.AuthenticationHelperImpl;

public class MenuActivity extends AppCompatActivity {
    Button request, donate;
    ImageButton mapbtn, logout;
    TextView welcomeUser;
    StringBuilder sb;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MenuActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // declare variables:
        request = findViewById(R.id.menuRequestButton);
        donate = findViewById(R.id.menuDonateButton);
        welcomeUser = (TextView) findViewById(R.id.welcomeText);
        mapbtn = (ImageButton) findViewById(R.id.mapButton);
        logout = findViewById(R.id.logout);

        // button configs
        mapbtn.setColorFilter(Color.argb(255, 0, 0, 0));

        /*// make StringBuilder for welcoming app user:
        sb = new StringBuilder(R.string.welcome_user);
        sb.append(R.string.user_name);
        welcomeUser.setText(sb.toString());*/

        // declare click listeners:
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // redirect to RequestsActivity
                Intent requestIntent = new Intent(MenuActivity.this, RequestsActivity.class);
                startActivity(requestIntent);
            }
        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // redirect to DonationsActivity
                Intent donateIntent = new Intent(MenuActivity.this, DonationsActivity.class);
                startActivity(donateIntent);
            }
        });
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to MapsActivity
                Intent mapIntent = new Intent(MenuActivity.this, MapsActivity.class);
                startActivity(mapIntent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthenticationHelperImpl.getInstance().signOut(signOutSuccess -> {
                    if(!signOutSuccess)
                        Toast.makeText(MenuActivity.this,
                                "There was an error signing out.", Toast.LENGTH_SHORT).show();
                    Intent logoutIntent = new Intent(MenuActivity.this, Login.class);
                    startActivity(logoutIntent);
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!AuthenticationHelperImpl.getInstance().isAuthenticated())
            startActivity(new Intent(MenuActivity.this, Login.class));
    }
}
