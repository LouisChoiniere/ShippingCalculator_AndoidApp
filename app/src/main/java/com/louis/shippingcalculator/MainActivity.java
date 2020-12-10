package com.louis.shippingcalculator;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.louis.shippingcalculator.controller.AddressController;
import com.louis.shippingcalculator.controller.BoxController;
import com.louis.shippingcalculator.data.AddressDA;
import com.louis.shippingcalculator.data.BoxDA;
import com.louis.shippingcalculator.model.Address;
import com.louis.shippingcalculator.model.Box;

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


        // Add demo data if there is no addresses and boxes stored in the database
        AddressDA addressDA = new AddressDA(this);
        if (addressDA.getAllAddress().size() == 0) {
            addressDA.add(new Address("Louis", "958 parklane", "Greenfield park", "Quebec", "J4V 1A8", "Canada"));
            addressDA.add(new Address("Yu Qiao", "1150 Croissant", "Brossard", "Quebec", "J4X 1M9", "Canada"));
            addressDA.add(new Address("Ryan", "915 Stravinski", "Brossard", "Quebec", "J4X 1W8", "Canada"));
        }

        BoxDA boxDA = new BoxDA(this);
        if (boxDA.getAllBox().size() == 0) {
            boxDA.add(new Box("A2", 10, 15, 16));
            boxDA.add(new Box("D20", 20, 20, 20));
            boxDA.add(new Box("D5", 5, 5, 5));
        }
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