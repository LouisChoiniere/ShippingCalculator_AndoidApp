package com.louis.shippingcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.louis.shippingcalculator.controller.AddressController;
import com.louis.shippingcalculator.controller.BoxController;
import com.louis.shippingcalculator.data.AddressDA;
import com.louis.shippingcalculator.data.BoxDA;
import com.louis.shippingcalculator.data.DatabaseHandler;
import com.louis.shippingcalculator.model.Address;
import com.louis.shippingcalculator.model.Box;

import androidx.annotation.Nullable;
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


//        // Add data box
//        BoxController.add(this, new Box("A2", 10, 15, 16));
//        BoxController.add(this, new Box("D20", 20, 20, 20));
//        BoxController.add(this, new Box("D5", 5, 5, 5));
//
//        for (Box box : boxDA.getAllBox()) {
//            Log.d(TAG, box.toString());
//        }
//
//        // Add data address
//        AddressDA addressDA = new AddressDA(MainActivity.this);
//
//        AddressController.add(this, new Address("Louis", "249Street", "Montreal", "Quebec", "J4V 2A8", "Canada"));
//        AddressController.add(this, new Address("Simon", "1120 Panama", "Brossard", "Quebec", "J4V 2A8", "Canada"));
//        AddressController.add(this, new Address("Yu Qiao", "1150 Croissant", "Brossard", "Quebec", "J4V 2A8", "Canada"));
//        AddressController.add(this, new Address("Ryan", "564 Yettus", "Brossard", "Quebec", "J4V 2A8", "Canada"));
//        AddressController.add(this, new Address("Gabe", "8133 Yeet", "Brossard", "Quebec", "J4V 2A8", "Canada"));
//
//        for (Address address : addressDA.getAllAddress()) {
//            Log.d(TAG, address.toString());
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        if (resultCode != RESULT_CANCELED) {
            BoxController.activityResult(this, requestCode, resultCode, intent);
            AddressController.activityResult(this, requestCode, resultCode, intent);
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }
}