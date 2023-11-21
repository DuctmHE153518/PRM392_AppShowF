package com.prm392team.shopf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.prm392team.shopf.Entity.User;
import com.prm392team.shopf.R;
import com.prm392team.shopf.RoomDB.FFoodDB;
import com.prm392team.shopf.RoomDB.OrderDAO;
import com.prm392team.shopf.RoomDB.ProductDAO;
import com.prm392team.shopf.RoomDB.UserDAO;

public class AccountActivity extends AppCompatActivity {

    private TextView username;
    private EditText email;
    private EditText phone;
    private EditText dob;
    private Button update;
    private Button cancelUpdate;
    private ImageView imgAccAva;
    FFoodDB db;
    UserDAO userDAO;

    private void bindingView(){
        username = findViewById(R.id.txtUsernameAM);
        email = findViewById(R.id.emailAM);
        phone = findViewById(R.id.phoneAM);
        dob = findViewById(R.id.dobAM);
        update = findViewById(R.id.updateInfor);
        cancelUpdate = findViewById(R.id.btnCancelUpdate);
        imgAccAva = findViewById(R.id.imgAccAva);
        imgAccAva.setImageResource(R.drawable.profile);

        db = FFoodDB.getInstance(this);
        userDAO = db.userDAO();
    }
    private void bindingAction(){
        User user = getUser();
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        dob.setText(String.valueOf(user.getDob()));

        cancelUpdate.setOnClickListener(this::onCancelUpdate);
    }

    private void onCancelUpdate(View view) {
        finish();
    }

    private User getUser() {

        int getIds= getIntent().getIntExtra("userId",0);
        User u = userDAO.getUserByIds(getIds);
        return u;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        bindingView();
        bindingAction();
    }
}