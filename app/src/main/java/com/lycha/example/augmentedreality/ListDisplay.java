package com.lycha.example.augmentedreality;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by user on 09/09/2017.
 */
import android.hardware.Sensor;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.content.Intent;
        import android.content.Context;
        import android.content.pm.PackageManager;
        import java.util.List;

public class ListDisplay extends AppCompatActivity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdisplay);

        final Context context = this;

        PackageManager PM = this.getPackageManager();
        // boolean gps = PM.hasSystemFeature(PackageManager.);
       boolean gps = PM.hasSystemFeature(String.valueOf(Sensor.TYPE_ROTATION_VECTOR));

        mListView = (ListView) findViewById(R.id.recipe_list_view);

        final String[] values = new String[] { "Experiences", "Rides", "Rock Dist", "Gourmet Sq", "Rock St. Afr", "Merchandise", "Bars", "Lounge" };

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(context, ListObject.class);
                detailIntent.putExtra("tipo", values[position]);
                startActivity(detailIntent);
            }

        });
    }
}