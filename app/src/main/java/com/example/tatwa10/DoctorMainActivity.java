package com.example.tatwa10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatwa10.FragmentDoctors.ApproveAppointmentFragment;
import com.example.tatwa10.FragmentDoctors.CompletedAppointmentFragment;
import com.example.tatwa10.FragmentDoctors.HomeDoctorsFragment;
import com.example.tatwa10.FragmentDoctors.PatientPrescriptionFragment;
import com.example.tatwa10.FragmentDoctors.PendingAppointmentFragment;
import com.example.tatwa10.Fragments.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class DoctorMainActivity extends AppCompatActivity {

    private static final long TIME_INTERVAL = 2000;
    private DrawerLayout drawerLayout;
    public static NavigationView navigationView;
    public static final String SHARED_PREFERENCES = "shared_prefs";
    public static String doctorName;
    private long mBackPressed;
    public static String currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        drawerLayout = findViewById(R.id.drawer_layout_doctor);
        Toolbar toolbar = findViewById(R.id.toolbar_doctor);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navigation_view_doctor);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home2) {
                    currentFragment = "home";
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_doctor, new HomeDoctorsFragment()).commit();
                } else if (itemId == R.id.nav_approve_appointment2) {
                    currentFragment = "approveAppointment";
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_doctor, new ApproveAppointmentFragment()).commit();
                } else if (itemId == R.id.nav_pending_appointment2) {
                    currentFragment = "pendingAppointment";
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_doctor, new PendingAppointmentFragment()).commit();
                } else if (itemId == R.id.nav_completed_appointment2) {
                    currentFragment = "completedAppointment";
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_doctor, new CompletedAppointmentFragment()).commit();
                } else if (itemId == R.id.nav_prescription2) {
                    currentFragment = "prescription";
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_doctor, new PatientPrescriptionFragment()).commit();
                } else if (itemId == R.id.nav_log_out2) {
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
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_doctor, new HomeDoctorsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home2);
        }

        if (getIntent().getStringExtra("name") != null) {
            doctorName = getIntent().getStringExtra("name");
            Toast.makeText(DoctorMainActivity.this, doctorName, Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", doctorName);
            editor.apply();
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
            doctorName = sharedPreferences.getString("name", "no name");
            Toast.makeText(DoctorMainActivity.this, doctorName, Toast.LENGTH_SHORT).show();
        }

        // Initialize currentFragment to prevent NullPointerException
        if (currentFragment == null) {
            currentFragment = "home";
        }

    }

    private void logOut() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Log Out?")
                .setMessage("Are you sure you want to log out?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Clear local session
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear(); // Clear all data
                        editor.apply();

                        String name = (doctorName != null) ? doctorName : "Doctor";
                        Toast.makeText(DoctorMainActivity.this, name + " Logged Out Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(DoctorMainActivity.this, StartingActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog.show();
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
