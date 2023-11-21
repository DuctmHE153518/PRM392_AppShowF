package com.prm392team.shopf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prm392team.shopf.Entity.User;
import com.prm392team.shopf.RoomDB.FFoodDB;
import com.prm392team.shopf.RoomDB.UserDAO;

public class RegistrationActivity extends AppCompatActivity {

    private EditText register_name;
    private EditText register_pass;
    private EditText register_confirm;
    private Button BtnRegister;
    private TextView loginRedirect;

    private void bindingView(){
        register_name = findViewById(R.id.register_name);
        register_pass = findViewById(R.id.register_pass);
        register_confirm = findViewById(R.id.register_confirm);
        BtnRegister = findViewById(R.id.BtnRegister);
        loginRedirect = findViewById(R.id.loginRedirect);
    }
    private void bindingAction() {
        BtnRegister.setOnClickListener(this::onRegisterbtnClick);
        loginRedirect.setOnClickListener(this::OnLoginRedirectListener);
    }

    private void OnLoginRedirectListener(View view) {
        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    private void onRegisterbtnClick(View view) {
        User user = new User();
        user.setUsername(register_name.getText().toString());
        user.setPassword(register_pass.getText().toString() );
        String confirm = register_confirm.getText().toString();

        if (user.getUsername().isEmpty()){
            register_name.setError("Username cannot be empty");
        }
        if (user.getPassword().isEmpty()) {
            register_pass.setError("Password cannot be empty");
        }
        if (!confirm.equals(user.getPassword()) && confirm.isEmpty()) {
            register_confirm.setError("Password not match or empty");
        } else {
            if (checkInput(user)) {
                FFoodDB db = FFoodDB.getInstance(getApplicationContext());
                UserDAO userDAO = db.userDAO();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userDAO.insertUser(user);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Registered Successfull!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(i);
                            }
                        });
                    }
                }).start();
            }
        }
    }

    private Boolean checkInput(User user) {
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        bindingView();
        bindingAction();
    }
}