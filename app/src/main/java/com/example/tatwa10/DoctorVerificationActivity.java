package com.example.tatwa10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DoctorVerificationActivity extends AppCompatActivity {

    private EditText editTextPassword;
    private Button buttonLogin;
    private Spinner spinnerDoctor;
    private ProgressDialog dialog;
    private DoctorDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_verification);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Signing In... Please wait");

        // Initialize local database
        dbHelper = new DoctorDbHelper(this);

        // Add sample doctors if database is empty
        initializeSampleDoctors();

        editTextPassword = findViewById(R.id.edit_text_doctor_password);
        buttonLogin = findViewById(R.id.button_doctor_login);
        spinnerDoctor = findViewById(R.id.spinner_doctors_login);

        String[] names = getResources().getStringArray(R.array.doctors_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoctor.setAdapter(adapter);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDoctor();
            }
        });
    }

    private void initializeSampleDoctors() {
        // Add sample doctors to local database using ACTUAL names from strings.xml
        dbHelper.addDoctor("Dr Adiveppa Hosangadi", "adiveppa123");
        dbHelper.addDoctor("Dr Amruth", "amruth12345");
        dbHelper.addDoctor("Dr Bharat Kshatri", "bharat12345");
        dbHelper.addDoctor("Dr Sanjeev Kalsoor", "sanjeev12345");
        dbHelper.addDoctor("Dr Kasturi Donimath", "kasturi12345");
        dbHelper.addDoctor("Dr Ravi Khasnis", "ravi12345678");
    }

    private void loginDoctor() {
        String name = spinnerDoctor.getSelectedItem().toString();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(password) || password.length() < 8) {
            Toast.makeText(DoctorVerificationActivity.this, "Please enter a Valid Password (min 8 characters)",
                    Toast.LENGTH_SHORT).show();
        } else {
            dialog.show();

            // Check credentials using local database
            boolean isValid = dbHelper.checkDoctor(name, password);

            dialog.dismiss();

            if (isValid) {
                Toast.makeText(DoctorVerificationActivity.this, "Login Successful! Welcome " + name, Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(DoctorVerificationActivity.this, DoctorMainActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(DoctorVerificationActivity.this,
                        "Incorrect Password. Try:\nDr Adiveppa Hosangadi - adiveppa123\nDr Amruth - amruth12345",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}
