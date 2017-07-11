package uk.co.tezk.mybarcalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void handleCategoriesClick(View view) {
        Intent intent = new Intent(this, AdministerCategoryActivity.class);
        startActivity(intent);
    }
}
