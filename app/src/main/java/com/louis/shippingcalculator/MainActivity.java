package com.louis.shippingcalculator;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.louis.shippingcalculator.data.AddressDA;
import com.louis.shippingcalculator.data.BoxDA;
import com.louis.shippingcalculator.data.DatabaseHandler;
import com.louis.shippingcalculator.model.Address;
import com.louis.shippingcalculator.model.Box;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "myDebug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_address, R.id.navigation_box)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



        // Add data box
        BoxDA boxDA = new BoxDA(MainActivity.this);

//        boxDA.add(new Box("A2", 10, 15, 16));
//        boxDA.add(new Box("D20", 20, 20, 20));
//        boxDA.add(new Box("D5", 5, 5, 5));

        for (Box box : boxDA.getAllBox()) {
            Log.d(TAG, box.toString());
        }

        // Add data address
        AddressDA addressDA = new AddressDA(MainActivity.this);

//        addressDA.add(new Address("Louis", "249Street", "Montreal", "Quebec", "J4V 2A8", "Canada"));
//        addressDA.add(new Address("Simon", "1120 Panama", "Brossard", "Quebec", "J4V 2A8", "Canada"));
//        addressDA.add(new Address("Yu Qiao", "1150 Croissant", "Brossard", "Quebec", "J4V 2A8", "Canada"));
//        addressDA.add(new Address("Ryan", "564 Yettus", "Brossard", "Quebec", "J4V 2A8", "Canada"));
//        addressDA.add(new Address("Gabe", "8133 Yeet", "Brossard", "Quebec", "J4V 2A8", "Canada"));

        for (Address address : addressDA.getAllAddress()) {
            Log.d(TAG, address.toString());
        }

    }
}