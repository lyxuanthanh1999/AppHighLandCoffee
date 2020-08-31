package com.tieuluan.mainprojectmycoffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nex3z.notificationbadge.NotificationBadge;
import com.tieuluan.mainprojectmycoffee.Database.DataSource.CartRepository;
import com.tieuluan.mainprojectmycoffee.Fragment.AccountFragment;
import com.tieuluan.mainprojectmycoffee.Fragment.CartFragment;
import com.tieuluan.mainprojectmycoffee.Fragment.DashboardFragment;
import com.tieuluan.mainprojectmycoffee.Fragment.HomeFragment;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.ultil.Server;


public class BannerAdvertisementActivity extends AppCompatActivity {


    BottomNavigationView mMainBotoomNav;
    FrameLayout mMain_fram;


    //Fragment
    private HomeFragment homeFragment;
    private DashboardFragment dashboardFragment;
    private CartFragment cartFragment;
    private AccountFragment accountFragment;

    //Notification badge
    BadgeDrawable badgeDrawable;
    //NotificationBadge badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_advertisement);
            init();
            //ActionViewFlipper();

    }

    private void init()
    {

        mMainBotoomNav=findViewById(R.id.main_nav);
        mMain_fram=findViewById(R.id.Main_frame);
        homeFragment=new HomeFragment();
        dashboardFragment =new DashboardFragment();
        cartFragment=new CartFragment();
        accountFragment=new AccountFragment();
        setFragment(homeFragment);



        mMainBotoomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                    {
                        Toast.makeText(BannerAdvertisementActivity.this,"Trang Chủ",Toast.LENGTH_SHORT).show();
                        setFragment(homeFragment);
                        return true;
                    }
                    case R.id.navigation_dashboard:
                    {
                        Toast.makeText(BannerAdvertisementActivity.this,"Quán",Toast.LENGTH_SHORT).show();
                        setFragment(dashboardFragment);
                        return true;
                    }
                    case R.id.navigation_Cart:
                    {
                        setFragment(cartFragment);
                        Toast.makeText(BannerAdvertisementActivity.this, "Giỏ Hàng", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    case R.id.navigation_account:
                    {
                        Toast.makeText(BannerAdvertisementActivity.this,"Tài Khoản",Toast.LENGTH_SHORT).show();
                        setFragment(accountFragment);

                        return true;
                    }
                }
                return false;
            }
        });

        //badge=findViewById(R.id.cart_badge);

        badgeDrawable = mMainBotoomNav.getOrCreateBadge(R.id.navigation_Cart);
//        badgeDrawable.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
        updateCart();
    }
//=============================Cập Nhập giỏ hàng coi có hàng không=============================
    private void updateCart()
    {
        if(badgeDrawable==null)
            return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               if(Server.cartRepository==null)
               {
                   Server.cartRepository =new CartRepository();
               }
                else
               {
                   if(Server.cartRepository.CountCartItems()==0)
                       badgeDrawable.setVisible(false);
                   else {
                       //        badgeDrawable.setNumber(2);
                       badgeDrawable.setVisible(true);
                       //badgeDrawable.setMaxCharacterCount(3);
                       badgeDrawable.setNumber(Server.cartRepository.CountCartItems());
                   }
               }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCart();
    }
//============================================================================================================
    // Hàm đặt fragment vào
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Main_frame,fragment);
        transaction.commit();
    }

}
