package com.example.kyoda.myapplication;

/*
 * Created by kyoda on 2017/06/12.
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
import java.util.Locale;

/*
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
*/


public class OnClickMainActivity extends MainActivity implements View.OnClickListener {


    private TextView mon;
    private StringBuilder buf;

    private BigDecimal hidari, migi;
    private String ope;

    private MathContext mc = new MathContext(14, RoundingMode.HALF_UP);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mon = (TextView)findViewById(R.id.textView0);
        buf = new StringBuilder();

        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);

        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSub).setOnClickListener(this);
        findViewById(R.id.buttonMul).setOnClickListener(this);
        findViewById(R.id.buttonDiv).setOnClickListener(this);

        findViewById(R.id.buttonEqual).setOnClickListener(this);
        findViewById(R.id.buttonC).setOnClickListener(this);
        findViewById(R.id.buttonCE).setOnClickListener(this);

        findViewById(R.id.buttonDot).setOnClickListener(this);
        findViewById(R.id.buttonMinus).setOnClickListener(this);


        cClickEvent();

    }



    @Override
    public void onClick(View v) {
        if (v != null) {
            Button b = (Button)v;
            String s = b.getText().toString();

            switch (s) {
                case "+":
                case "-":
                case "x":
                case "÷":
                    operationClickEvent(s);
                    break;
                case "=":
                    equalClickEvent();
                    break;
                case "C":
                    cClickEvent();
                    break;
                case "CE":
                    ceClickEvent();
                    break;
                case ".":
                    dotClickEvent();
                    break;
                case "+/-":
                    minusClickEvent();
                    break;
                default:
                    numberClickEvent(b);
                    break;
            }

        }
    }

    private void operationClickEvent(String s) {
        ope = s;
        if (buf.length() > 0) {
            hidari = new BigDecimal(buf.toString());
            buf.setLength(0);
        }
    }

    private void equalClickEvent() {
        if (buf.length() > 0
                && !(ope.isEmpty())
                && !(buf.length() == 1 && buf.charAt(0) == '0')) {

            migi = new BigDecimal(buf.toString());
            double res = cal(hidari, migi, ope);
            if (res == (long) res) {
                mon.setText(String.format(Locale.US, "%d", (long) res));
            } else {
                mon.setText(String.format("%s", res));
            }

        }
    }

    private void initValue() {
        migi =  new BigDecimal(0);
        hidari = new BigDecimal(0);
        ope = null;
        buf.setLength(0);
    }

    private void ceClickEvent() {
        buf.setLength(0);
        mon.setText(String.format(Locale.US, "%d", 0));
    }

    private void cClickEvent() {
        initValue();
        mon.setText(String.format(Locale.US, "%d", 0));
    }

    private void dotClickEvent() {
        if (buf.length() == 0) {
            buf.append("0.");
        } else if (buf.length() > 0
                && buf.charAt(buf.length() - 1) != '.'
                && buf.indexOf(".") == -1) {
            buf.append(".");
        }
    }

    private void minusClickEvent() {
        if (buf.length() == 0) {
            buf.append("-");
        } else if (buf.length() == 1
                && buf.charAt(0) == '-') {
            buf.setLength(0);
        }
    }

    private void numberClickEvent(Button b) {
        //Log.d("implements v", v.getContext().toString());

        if (!(buf.length() == 1 && Integer.parseInt(buf.toString()) == 0)) {
            buf.append(b.getText().toString());
            mon.setText(String.format("%s", buf.toString()));
        }

    }

    private double cal(BigDecimal h, BigDecimal m, String ope) {

        BigDecimal r = new BigDecimal(0);

        switch (ope) {
            case "+":
                r = h.add(m);
                break;
            case "-":
                r = h.subtract(m);
                break;
            case "x":
                r = h.multiply(m);
                break;
            case "÷":
                r = h.divide(m, mc);
                break;
        }

        return r.round(mc).doubleValue();

    }

    /*
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);



    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        */

}
