package com.example.tatwa10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tatwa10.Fragments.AddMedicalRecordFragment;
import com.example.tatwa10.Fragments.AppointmentFragment;
import com.example.tatwa10.Fragments.DeveloperFragment;
import com.example.tatwa10.Fragments.EditProfileFragment;
import com.example.tatwa10.Fragments.FindDoctorsFragment;
import com.example.tatwa10.Fragments.HomeFragment;
import com.example.tatwa10.Fragments.MedicalRecordsFragment;
import com.example.tatwa10.Fragments.HealthPrecautionFragment;
import com.example.tatwa10.Fragments.PrescriptionFragment;
import com.example.tatwa10.ModelClass.Profile;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    public static NavigationView navigationView;
    public static String currentFragment;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    public static String patientName;
    public static String authNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DIRECT ACCESS MODE: Use demo patient data
        authNumber = "+919876543210"; // Demo phone number
        patientName = "Demo Patient"; // Demo patient name

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[] { Manifest.permission.CALL_PHONE },
                1);

        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);

        // DIRECT ACCESS MODE: Removed Firebase patient data fetching
        // All data will be stored and retrieved locally
        /*
         * CollectionReference collectionReference =
         * FirebaseFirestore.getInstance().collection("Patients");
         * collectionReference.whereEqualTo("phone", authNumber).get()
         * .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
         * 
         * @Override
         * public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
         * for (DocumentSnapshot doc : queryDocumentSnapshots) {
         * Profile profile = doc.toObject(Profile.class);
         * if (profile != null) {
         * patientName = profile.getName();
         * }
         * break;
         * }
         * }
         * });
         */

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new HomeFragment()).commit();
                } else if (itemId == R.id.nav_doctors) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new FindDoctorsFragment()).commit();
                } else if (itemId == R.id.nav_appointment) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AppointmentFragment()).commit();
                } else if (itemId == R.id.nav_prescription) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new PrescriptionFragment()).commit();
                } else if (itemId == R.id.nav_edit_profile) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new EditProfileFragment()).commit();
                } else if (itemId == R.id.nav_health_precaution) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new HealthPrecautionFragment()).commit();
                } else if (itemId == R.id.nav_blog) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://179.61.192.4/~tatwadar/index.php/blog/"));
                    startActivity(intent);
                } else if (itemId == R.id.nav_contact_us) {
                    Intent newIntent = new Intent();
                    newIntent.setAction(Intent.ACTION_VIEW);
                    newIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                    newIntent.setData(Uri.parse("http://179.61.192.4/~tatwadar/index.php/contact-us/"));
                    startActivity(newIntent);
                } else if (itemId == R.id.nav_about_us) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new DeveloperFragment()).commit();
                } else if (itemId == R.id.nav_log_out) {
                    logOut();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        // Initialize currentFragment to prevent NullPointerException
        if (currentFragment == null) {
            currentFragment = "home";
        }

    }

    private void logOut() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are you sure you want to log out?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // DIRECT ACCESS MODE: No Firebase signout needed
                        Toast.makeText(MainActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, StartingActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setTitle("Log Out");

        dialog.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to make calls from this app",
                            Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (currentFragment != null && currentFragment.equals("home")) {
                if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                    Intent a = new Intent(Intent.ACTION_MAIN);
                    a.addCategory(Intent.CATEGORY_HOME);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(a);
                    // super.onBackPressed();
                    return;
                } else {
                    Toast.makeText(getBaseContext(), "Press Back Again To Exit", Toast.LENGTH_SHORT).show();
                }
                mBackPressed = System.currentTimeMillis();

            } else {
                super.onBackPressed();
            }

        }
    }

}
