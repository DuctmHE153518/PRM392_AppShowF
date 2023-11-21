package com.prm392team.shopf.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.prm392team.shopf.AccountActivity;
import com.prm392team.shopf.Fragment.OrderCompleteFragment;
import com.prm392team.shopf.Fragment.OrderShippingFragment;
import com.prm392team.shopf.LoginActivity;
import com.prm392team.shopf.OrderDetailActivity;
import com.prm392team.shopf.OrderHistoryActivity;
import com.prm392team.shopf.OrderStatusActivity;
import com.prm392team.shopf.R;

public class AccountFragment extends Fragment {

    private View view;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";

    private ConstraintLayout orderHistory;
    private ConstraintLayout accountManager;
    private ConstraintLayout farvoriteProduct;
    private ConstraintLayout orderStatus;
    private ConstraintLayout btnLogout;
    private TextView txtusername;
    private ImageView imgAva;
    int userId;
    private void onClickAccountManager(View view) {
        Intent i = new Intent(getActivity(), AccountActivity.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        bindingView();
        bindingAction();

        return view;

    }

    private void bindingView(){
        orderHistory = view.findViewById((R.id.orderHistoryBtn));
        accountManager = view.findViewById((R.id.accountManager));
        farvoriteProduct = view.findViewById((R.id.favoriteProductBtn));
        orderStatus = view.findViewById((R.id.orderStatusBtn));
        btnLogout = view.findViewById((R.id.btnLogout));
        txtusername = view.findViewById((R.id.txtusername));
        imgAva = view.findViewById((R.id.imgAva));
        txtusername.setText(KEY_USER);
        imgAva.setImageResource(R.drawable.profile);
    }
    private  void bindingAction(){
        orderHistory.setOnClickListener(this::onClickOrderHistory);
        accountManager.setOnClickListener(this::onClickAccountManager);
        farvoriteProduct.setOnClickListener(this::onClickFavoriteProduct);
        orderStatus.setOnClickListener(this::onClickViewOrderStatus);
        btnLogout.setOnClickListener(this::onBtnLogoutClick);



        if(getActivity().getIntent() != null){
            userId = getActivity().getIntent().getIntExtra("userIdLogin", 0);
        }

    }

    private void onBtnLogoutClick(View view) {
        Intent i = new Intent(getActivity(), LoginActivity.class);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER, null);
        editor.putString(KEY_PASS, null);
        editor.apply();
        startActivity(i);
    }

    private void onClickFavoriteProduct(View view) {
        Intent i = new Intent(getActivity(), OrderHistoryActivity.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }

    private void onClickViewOrderStatus(View view) {
        Intent x = new Intent(getActivity(), OrderStatusActivity.class);
        Intent i = new Intent(getActivity(), OrderShippingFragment.class);
        Intent j = new Intent(getActivity(), OrderCompleteFragment.class);
        Intent k = new Intent(getActivity(), OrderCancelFragment.class);
        Intent l = new Intent(getActivity(), OrderDetailActivity.class);
        x.putExtra("userId", userId);
        startActivity(x);
    }

    private void onClickOrderHistory(View view) {
        Intent i = new Intent(getActivity(), OrderHistoryActivity.class);
        i.putExtra("userId", userId);
        startActivity(i);
    }
}