package com.example.skusamzas.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skusamzas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class CreateAccount extends AppCompatActivity {

    private Button registerButton;
    private EditText registerName, registerEmail, registerPassword, confirmPass;
    private TextView backToLogin;
    public static String TAG = CreateAccount.class.getSimpleName();
    private String userName;
    private FirebaseAuth mAuth;
    private ProgressDialog mAuthProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        registerButton =  findViewById( R.id.registerButton);
        registerName =  findViewById(R.id.registerName);
        registerEmail  = findViewById(R.id.registerEmail);
        registerPassword= findViewById(R.id.registerPassword);
        confirmPass = findViewById(R.id.confirmPassword);
        backToLogin =findViewById(R.id.backToLogin);
        mAuth = FirebaseAuth.getInstance();

        backToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(CreateAccount.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
        registerButton.setOnClickListener(v -> createNewUser());
        createAuthProgressDialog();

    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            registerEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            registerName.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            registerPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            registerPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }
    private void createNewUser() {
        userName = registerName.getText().toString().trim();
        final String name = registerName.getText().toString().trim();
        final String email = registerEmail.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();
        String confirmPassword = confirmPass.getText().toString().trim();
        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;
        mAuthProgressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    mAuthProgressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Authentication successful");
                        createFirebaseUserProfile(task.getResult().getUser());
                    } else {
                        Toast.makeText(CreateAccount.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void createFirebaseUserProfile(final FirebaseUser user) {

        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName)
                .build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, user.getDisplayName());
                    }
                });
    }
    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

}
