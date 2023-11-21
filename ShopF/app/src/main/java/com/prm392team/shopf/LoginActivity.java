package com.prm392team.shopf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prm392team.shopf.Entity.Category;
import com.prm392team.shopf.Entity.Order;
import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.Entity.User;
import com.prm392team.shopf.Fragment.AccountFragment;
import com.prm392team.shopf.Fragment.HomeFragment;
import com.prm392team.shopf.RoomDB.FFoodDB;
import com.prm392team.shopf.RoomDB.UserDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText login_name;
    private EditText login_pass;
    private Button BtnLogin;
    private TextView registerRedirect;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";

    private void bindingView(){
        login_name = findViewById(R.id.login_name);
        login_pass = findViewById(R.id.login_pass);
        BtnLogin = findViewById(R.id.BtnLogin);
        registerRedirect = findViewById(R.id.registerRedirect);
    }
    private void bindingAction() {
        BtnLogin.setOnClickListener(this::onLoginbtnClick);
        registerRedirect.setOnClickListener(this::OnregisterRedirectListener);
    }

    private void OnregisterRedirectListener(View view) {
        registerRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

    private void onLoginbtnClick(View view) {

        String user = login_name.getText().toString();
        String pass = login_pass.getText().toString();

        if (user.isEmpty()){
            login_name.setError("Username cannot be empty");
        }
        if (user.isEmpty()) {
            login_pass.setError("Password cannot be empty");
        }
        else {
            FFoodDB db = FFoodDB.getInstance(getApplicationContext());
            UserDAO userDAO = db.userDAO();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    User u = userDAO.getAccount(user, pass);
                    if (u == null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Username or password invalid", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_USER, user);
                        editor.putString(KEY_PASS, pass);
                        editor.apply();
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        Intent j = new Intent(LoginActivity.this, AccountFragment.class);
                        i.putExtra("userIdLogin", u.getUserId());
                        startActivity(i);
                    }
                }
            }).start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        bindingView();
        bindingAction();

        String user = sharedPreferences.getString(KEY_USER, null);
        if (user != null){
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
        }
        else{
            addCategory();
            addUser();
            addProduct();
            addOrder();
        }


    }

    public void addCategory() {
        Category category=new Category( "Đồ Có Cồn", "cate4");
        Category category1=new Category("Đồ An Nhanh", "cate2");
        Category category2=new Category("Bún/Phở/Mỳ", "cate8");
        Category category3=new Category( "Tráng Miệng/Đồ Ngọt", "cate3");
        Category category4=new Category( "Đồ Ăn Vặt", "cate5");
        Category category5=new Category( "Cơm", "cate6");
        Category category6=new Category( "Đồ Uống", "cate7");
        Category category7=new Category( "Đồ Ăn Dinh Dưỡng", "cate10");
        Category category8=new Category( "Lẩu Và Nướng", "cate9");
        Category category9=new Category( "Bánh", "cate1");

        FFoodDB.getInstance(this).categoryDAO().insertCategory(category);
        FFoodDB.getInstance(this).categoryDAO().insertCategory(category1);
        FFoodDB.getInstance(this).categoryDAO().insertCategory(category2);
        FFoodDB.getInstance(this).categoryDAO().insertCategory(category3);
        FFoodDB.getInstance(this).categoryDAO().insertCategory(category4);
        FFoodDB.getInstance(this).categoryDAO().insertCategory(category5);
        FFoodDB.getInstance(this).categoryDAO().insertCategory(category6);
        FFoodDB.getInstance(this).categoryDAO().insertCategory(category7);
        FFoodDB.getInstance(this).categoryDAO().insertCategory(category8);
        FFoodDB.getInstance(this).categoryDAO().insertCategory(category9);

        System.err.println(category + "Category Successfully");
    }

    public void addUser() {
        User category=new User("ducw@gmail.com", "ducw", "123", "0553647789", "Số nhà 25, thôn 2, Hòa Lạc", 1, "10/08/2001");
        User category1=new User("quangw@gmail.com", "quangw", "123", "0224578654", "Số nhà 23, thôn 3, Hòa Lạc", 2, "12/06/2001");
        User category2=new User("ly@gmail.com", "lyw", "123", "035789456", "Số nhà 25, thôn 3, Hòa Lạc", 3, "05/07/2001");
        User category3=new User("cuongw@gmail.com", "cuongw", "123abc", "012458693", "Số nhà 25, thôn 4, Hòa Lạc", 2, "14/04/2000");
        User category4=new User("khoaw@gmail.com", "khoaw", "aaa111", "0124457862", "Dom B, Đại học FPT", 3, "30/12/2012");

        FFoodDB.getInstance(this).userDAO().insertUser(category);
        FFoodDB.getInstance(this).userDAO().insertUser(category1);
        FFoodDB.getInstance(this).userDAO().insertUser(category2);
        FFoodDB.getInstance(this).userDAO().insertUser(category3);
        FFoodDB.getInstance(this).userDAO().insertUser(category4);

        System.err.println(category + "User Successfully");
    }

    public void addProduct() {
        Product category=new Product("Rượu soju", 12, "Rượu soju", 48.000, 20.0, "ruousoju", 1, 1, 2123);
        Product category1=new Product("Pizza nhân bò bằm", 456, "Pizza nhân bò bằm", 53.000, 20.0, "pizza", 2, 1, 7123);
        Product category2=new Product("Bún chả obama", 453, "Bún chả obama", 30.000, 20.0, "buncha", 3, 4, 1283);
        Product category3=new Product("Kem bơ", 456, "Kem bơ", 12.000, 20.0, "kembo", 4, 1, 17823);
        Product category4=new Product("Xiên bẩn các loại", 354, "Xiên bẩn các loại", 5.000, 20.0, "xienban", 5, 1, 5278);
        Product category5=new Product("Cơm rang thập cẩm", 453, "Cơm rang thập cẩm", 30.000, 20.0, "comrangnguyenviet", 6, 1, 785);
        Product category6=new Product("Cà phê trứng", 68, "Cà phê trứng", 12.0, 38.000, "caphetrung", 7, 1, 287);
        Product category7=new Product("Salad rau hoa quả trộn", 35, "Salad rau hoa quả trộn", 20.000, 20.0, "salad", 8, 1, 58);
        Product category8=new Product("Set lẩu thái tại nhà", 45, "Set lẩu thái tại nhà", 129.000, 20.0, "lauthai", 9, 1, 12273);
        Product category9=new Product("Bánh mì dân tổ", 78, "Bánh mì dân tổ", 12.0, 20.000, "banhmidanto", 10, 10, 87);

        Product category10=new Product("Bia tươi", 35, "Bia tươi", 12.0, 20.000, "biatuoi", 1, 2, 2445);
        Product category11=new Product("Khoai tây lắc phô mai", 45, "Khoai tây lắc phô mai", 30.000, 20.0, "khoaiphomai", 2, 2, 4522);
        Product category12=new Product("Mỳ trộn indomie kèm topping", 45, "Mỳ trộn indomie kèm topping", 35.000, 20.0, "mitron", 3, 2, 457);
        Product category13=new Product("Bánh ngọt các loại", 25, "Bánh ngọt các loại", 30.000, 20.0, "banhngot", 4, 2, 87);
        Product category14=new Product("Bánh chuối, bánh khoai chiên", 45, "Bánh chuối, bánh khoai chiên", 8.000, 20.0, "banhchuoi", 5, 1, 1723);
        Product category15=new Product("Cơm tấm sài gòn", 12, "Cơm tấm sài gòn", 129.000, 20.0, "comtam", 6, 2, 78123);
        Product category16=new Product("Trà sữa chân châu", 8, "Trà sữa chân châu", 29.000, 20.0, "trasua", 7, 2, 127853);
        Product category17=new Product("Đồ chay các loại", 12, "Đồ chay các loại", 24.000, 20.0, "dochay", 8, 2, 78);
        Product category18=new Product("Set nướng tại nhà", 5, "Set nướng tại nhà", 129.000, 20.0, "setnuong", 9, 2, 587);
        Product category19=new Product("Bánh bao các loại", 12, "Bánh bao các loại", 19.000, 20.0, "banhbao", 10, 2, 58);

        FFoodDB.getInstance(this).productDAO().insertProduct(category);
        FFoodDB.getInstance(this).productDAO().insertProduct(category1);
        FFoodDB.getInstance(this).productDAO().insertProduct(category2);
        FFoodDB.getInstance(this).productDAO().insertProduct(category3);
        FFoodDB.getInstance(this).productDAO().insertProduct(category4);
        FFoodDB.getInstance(this).productDAO().insertProduct(category5);
        FFoodDB.getInstance(this).productDAO().insertProduct(category6);
        FFoodDB.getInstance(this).productDAO().insertProduct(category7);
        FFoodDB.getInstance(this).productDAO().insertProduct(category8);
        FFoodDB.getInstance(this).productDAO().insertProduct(category9);
        FFoodDB.getInstance(this).productDAO().insertProduct(category10);
        FFoodDB.getInstance(this).productDAO().insertProduct(category11);
        FFoodDB.getInstance(this).productDAO().insertProduct(category12);
        FFoodDB.getInstance(this).productDAO().insertProduct(category13);
        FFoodDB.getInstance(this).productDAO().insertProduct(category14);
        FFoodDB.getInstance(this).productDAO().insertProduct(category15);
        FFoodDB.getInstance(this).productDAO().insertProduct(category16);
        FFoodDB.getInstance(this).productDAO().insertProduct(category17);
        FFoodDB.getInstance(this).productDAO().insertProduct(category18);
        FFoodDB.getInstance(this).productDAO().insertProduct(category19);

        System.err.println(category + "Product Successfully");
    }

    public void addOrder() {
        Order category=new Order(8, 3, "01/03/2023", 40.00, "Số nhà 25, thôn 2, Hòa Lạc", 1);
        Order category1=new Order(2, 4, "05/03/2023", 30.00,  "Số nhà 23, thôn 3, Hòa Lạc", 2);
        Order category2=new Order(5, 3, "12/03/2023", 120.00, "Số nhà 25, thôn 3, Hòa Lạc", 3);
        Order category3=new Order(6, 5, "17/02/2023", 53.00, "Số nhà 25, thôn 4, Hòa Lạc", 2);
        Order category4=new Order(10, 1, "10/03/2023", 57.00, "Dom B, Đại học FPT", 1);
        Order category5=new Order(5, 1, "10/03/2023", 57.00, "Dom B, Đại học FPT", 1);
        Order category6=new Order(7, 1, "10/03/2023", 57.00, "Dom B, Đại học FPT", 2);
        Order category7=new Order(6, 1, "10/03/2023", 57.00, "Dom B, Đại học FPT", 3);

        FFoodDB.getInstance(this).orderDAO().insertOrder(category);
        FFoodDB.getInstance(this).orderDAO().insertOrder(category1);
        FFoodDB.getInstance(this).orderDAO().insertOrder(category2);
        FFoodDB.getInstance(this).orderDAO().insertOrder(category3);
        FFoodDB.getInstance(this).orderDAO().insertOrder(category4);
        FFoodDB.getInstance(this).orderDAO().insertOrder(category5);
        FFoodDB.getInstance(this).orderDAO().insertOrder(category6);
        FFoodDB.getInstance(this).orderDAO().insertOrder(category7);

        System.err.println(category + "Order Successfully");
    }
}