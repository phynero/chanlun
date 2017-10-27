package com.phynero.chanzsc;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Handler;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.phynero.chanzsc.myUtils.DeviceUtils;
import com.phynero.chanzsc.myUtils.StrRes;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private StringBuilder str = new StringBuilder();
    private TextView titleTxt;

    private float[] txtSize = {15, 20, 30};
    int sizeid = 1;
    private TextView txt;
    private int curId;
    private Toolbar toolbar;
    float pretime = 0;
    ScrollView sc;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            titleTxt.setTextSize(getTitleSize());
            titleTxt.setText(getResources().getString(StrRes.StrResource[curId - 1]));
            txt.setText(str);
            txt.setTextSize(txtSize[sizeid]);
            sc.scrollTo(0,0);
        }
    };

    /*
     *标题的文字 要多大合适 22个汉字为标准
     */
    private float getTitleSize() {
        //先获取屏幕宽度 获得像素值
        int screenWidth = DeviceUtils.getScreenWidth(this);
        //获取toolbar的高度
        int height = toolbar.getHeight();
        //获得标题可用的像素宽度
        int width = screenWidth - height * 3;
//        float ydpi = getResources().getDisplayMetrics().ydpi;


//        sp =(width/10)*160/ydpi;
        return width / 20;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        sc = (ScrollView) findViewById(R.id.scro);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        titleTxt = (TextView) findViewById(R.id.titletxt);
        txt = (TextView) findViewById(R.id.contentText);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        readCl(1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long curtime = System.currentTimeMillis();
            if (curtime - pretime > 2000) {
                Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
                pretime = curtime;
                return;
            } else {
                finish();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case R.id.action_settings1:
                sizeid = 2;
                Message.obtain(handler).sendToTarget();
                return true;
            case R.id.action_settings2:
                sizeid = 1;
                Message.obtain(handler).sendToTarget();
                return true;
            case R.id.action_settings3:
                sizeid = 0;
                Message.obtain(handler).sendToTarget();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void readCl(int i) {
        curId = i;
        str.setLength(0);

        try {
            InputStream is = getResources().getAssets().open("CZSC" + i);
            InputStreamReader inputStreamReader = new InputStreamReader(is);

            BufferedReader bufferdReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferdReader.readLine()) != null) {
                str.append("\n");
                str.append(line);
            }
            is.close();
        } catch (IOException e) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        Message.obtain(handler).sendToTarget();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.item1:
                readCl(1);
                break;
            case R.id.item2:
                readCl(2);
                break;
            case R.id.item3:
                readCl(3);
                break;
            case R.id.item4:
                readCl(4);
                break;
            case R.id.item5:
                readCl(5);
                break;
            case R.id.item6:
                readCl(6);
                break;
            case R.id.item7:
                readCl(7);
                break;
            case R.id.item8:
                readCl(8);
                break;
            case R.id.item9:
                readCl(9);
                break;

            case R.id.item10:
                readCl(10);
                break;
            case R.id.item11:
                readCl(11);
                break;
            case R.id.item12:
                readCl(12);
                break;
            case R.id.item13:
                readCl(13);
                break;
            case R.id.item14:
                readCl(14);
                break;
            case R.id.item15:
                readCl(15);
                break;
            case R.id.item16:
                readCl(16);
                break;
            case R.id.item17:
                readCl(17);
                break;
            case R.id.item18:
                readCl(18);
                break;
            case R.id.item19:
                readCl(19);
                break;

            case R.id.item20:
                readCl(20);
                break;
            case R.id.item21:
                readCl(21);
                break;
            case R.id.item22:
                readCl(22);
                break;
            case R.id.item23:
                readCl(23);
                break;
            case R.id.item24:
                readCl(24);
                break;
            case R.id.item25:
                readCl(25);
                break;
            case R.id.item26:
                readCl(26);
                break;
            case R.id.item27:
                readCl(27);
                break;
            case R.id.item28:
                readCl(28);
                break;
            case R.id.item29:
                readCl(29);
                break;

            case R.id.item30:
                readCl(30);
                break;
            case R.id.item31:
                readCl(31);
                break;
            case R.id.item32:
                readCl(32);
                break;
            case R.id.item33:
                readCl(33);
                break;
            case R.id.item34:
                readCl(34);
                break;
            case R.id.item35:
                readCl(35);
                break;
            case R.id.item36:
                readCl(36);
                break;
            case R.id.item37:
                readCl(37);
                break;
            case R.id.item38:
                readCl(38);
                break;
            case R.id.item39:
                readCl(39);
                break;

            case R.id.item40:
                readCl(40);
                break;
            case R.id.item41:
                readCl(41);
                break;
            case R.id.item42:
                readCl(42);
                break;
            case R.id.item43:
                readCl(43);
                break;
            case R.id.item44:
                readCl(44);
                break;
            case R.id.item45:
                readCl(45);
                break;
            case R.id.item46:
                readCl(46);
                break;
            case R.id.item47:
                readCl(47);
                break;
            case R.id.item48:
                readCl(48);
                break;
            case R.id.item49:
                readCl(49);
                break;

            case R.id.item50:
                readCl(50);
                break;
            case R.id.item51:
                readCl(51);
                break;
            case R.id.item52:
                readCl(52);
                break;
            case R.id.item53:
                readCl(53);
                break;
            case R.id.item54:
                readCl(54);
                break;
            case R.id.item55:
                readCl(55);
                break;
            case R.id.item56:
                readCl(56);
                break;
            case R.id.item57:
                readCl(57);
                break;
            case R.id.item58:
                readCl(58);
                break;
            case R.id.item59:
                readCl(59);
                break;

            case R.id.item60:
                readCl(60);
                break;
            case R.id.item61:
                readCl(61);
                break;
            case R.id.item62:
                readCl(62);
                break;
            case R.id.item63:
                readCl(63);
                break;
            case R.id.item64:
                readCl(64);
                break;
            case R.id.item65:
                readCl(65);
                break;
            case R.id.item66:
                readCl(66);
                break;
            case R.id.item67:
                readCl(67);
                break;
            case R.id.item68:
                readCl(68);
                break;
            case R.id.item69:
                readCl(69);
                break;

            case R.id.item70:
                readCl(70);
                break;
            case R.id.item71:
                readCl(71);
                break;
            case R.id.item72:
                readCl(72);
                break;
            case R.id.item73:
                readCl(73);
                break;
            case R.id.item74:
                readCl(74);
                break;
            case R.id.item75:
                readCl(75);
                break;
            case R.id.item76:
                readCl(76);
                break;
            case R.id.item77:
                readCl(77);
                break;
            case R.id.item78:
                readCl(78);
                break;
            case R.id.item79:
                readCl(79);
                break;

            case R.id.item80:
                readCl(80);
                break;
            case R.id.item81:
                readCl(81);
                break;
            case R.id.item82:
                readCl(82);
                break;
            case R.id.item83:
                readCl(83);
                break;
            case R.id.item84:
                readCl(84);
                break;
            case R.id.item85:
                readCl(85);
                break;
            case R.id.item86:
                readCl(86);
                break;
            case R.id.item87:
                readCl(87);
                break;
            case R.id.item88:
                readCl(88);
                break;
            case R.id.item89:
                readCl(89);
                break;

            case R.id.item90:
                readCl(90);
                break;
            case R.id.item91:
                readCl(91);
                break;
            case R.id.item92:
                readCl(92);
                break;
            case R.id.item93:
                readCl(93);
                break;
            case R.id.item94:
                readCl(94);
                break;
            case R.id.item95:
                readCl(95);
                break;
            case R.id.item96:
                readCl(96);
                break;
            case R.id.item97:
                readCl(97);
                break;
            case R.id.item98:
                readCl(98);
                break;
            case R.id.item99:
                readCl(99);
                break;

            case R.id.item100:
                readCl(100);
                break;
            case R.id.item101:
                readCl(101);
                break;
            case R.id.item102:
                readCl(102);
                break;
            case R.id.item103:
                readCl(103);
                break;
            case R.id.item104:
                readCl(104);
                break;
            case R.id.item105:
                readCl(105);
                break;
            case R.id.item106:
                readCl(106);
                break;
            case R.id.item107:
                readCl(107);
                break;
            case R.id.item108:
                readCl(108);
                break;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
