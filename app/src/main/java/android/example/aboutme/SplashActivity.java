package android.example.aboutme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            getSupportActionBar().hide();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    if (AppController.getInstance().isLoggedIn()) {
                        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                        finish();
                    } else {
                        Intent mainIntent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }
            }, SPLASH_DISPLAY_LENGTH);

        }
    }
