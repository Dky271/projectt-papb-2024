package dk.mobile.bwurger5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_top, new TopFragment())
                    .replace(R.id.fragment_bottom, new BottomFragment())
                    .commit();

            Log.d("MainActivity", "Fragments loaded successfully");
        }
    }

}
