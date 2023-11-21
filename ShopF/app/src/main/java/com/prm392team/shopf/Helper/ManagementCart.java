package com.prm392team.shopf.Helper;

import android.content.Context;
import android.widget.Toast;

import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.Interface.ChangeNumberOfItems;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;

    private TinyDB tinyDB;

    public ManagementCart(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(Product item){
        ArrayList<Product> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for(int i =0;i<listFood.size();i++){
            if(listFood.get(i).getProductName().equals(item.getProductName())){
                existAlready = true;
                n = i;
                break;
            }
        }

        if(existAlready){
            listFood.get(n).setQuantity(item.getQuantity());
        }else{
            listFood.add(item);
        }
        tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context,"Sản phẩm đã được thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
    }

    public void productIncrea(ArrayList<Product> productList, int position, ChangeNumberOfItems changeNumberOfItems){
        productList.get(position).setQuantity(productList.get(position).getQuantity() + 1);
        tinyDB.putListObject("CartList",productList);
        changeNumberOfItems.changed();
    }

    public void productDecrea(ArrayList<Product> productList, int position, ChangeNumberOfItems changeNumberOfItems){
        if(productList.get(position).getQuantity()==1){
            productList.remove(position);
        }else {
            productList.get(position).setQuantity(productList.get(position).getQuantity() - 1 );
        }
        tinyDB.putListObject("CartList",productList);
        changeNumberOfItems.changed();
    }

    public Double getTotalFee(){
        ArrayList<Product> plist= getListCart();
        double fee = 0;
        for(int i = 0; i<plist.size();i++){
            fee = fee + (plist.get(i).getPrice()*plist.get(i).getQuantity());
        }
        return fee;
    }

    public ArrayList<Product> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void ListClear(){
        ArrayList<Product> plist = new ArrayList<>();
        tinyDB.putListObject("CartList",plist);
    }

}