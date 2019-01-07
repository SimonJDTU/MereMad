package com.johansen.dk.meremad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.johansen.dk.meremad.R;
import com.johansen.dk.meremad.adapter.selectionAdapter;
import com.johansen.dk.meremad.model.foodItem;
import com.johansen.dk.meremad.model.order;
import com.johansen.dk.meremad.model.textToObject;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.ArrayList;

public class optionsActivity extends AppCompatActivity implements View.OnClickListener{
    private order selection;
    private TextView titleTV;
    private ArrayList<foodItem> foodData;
    private RecyclerView foodList;
    private ResideMenu resideMenu;
    private Button resideMenuLeft_Btn, resideMenuRight_Btn;
    private RecyclerView.Adapter niceAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_options);

        //https://github.com/SpecialCyci/AndroidResideMenu
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.residemenubg1);
        resideMenu.attachToActivity(this);
        createMenuTitles();

        resideMenuLeft_Btn = (Button) findViewById(R.id.resideMenuLeft);
        resideMenuLeft_Btn.setOnClickListener(this);
        resideMenuRight_Btn = (Button) findViewById(R.id.resideMenuRight);
        resideMenuRight_Btn.setOnClickListener(this);

        foodList = findViewById(R.id.foodList);
        foodList.setHasFixedSize(true);
        titleTV = findViewById(R.id.texttop);
        setPageItems("breakfast");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED){
            if(requestCode == 1){
                selection = (order) data.getSerializableExtra("orderObject");
            }
        }
        if(resultCode == RESULT_OK){
            selection = (order) data.getSerializableExtra("orderObject");
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.resideMenuLeft:
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
                break;
            case R.id.resideMenuRight:
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
                break;
                //these onClick are for resideMenuTitels, they are ordered after creation-order. See CreateMenuTitels() for more info of id's.
            case 0:
                ((selectionAdapter) niceAdapter).clear();
                setPageItems("breakfast");
                resideMenu.closeMenu();
                break;
            case 1:
                ((selectionAdapter) niceAdapter).clear();
                setPageItems("lunch");
                resideMenu.closeMenu();
                break;
            case 2:
                ((selectionAdapter) niceAdapter).clear();
                setPageItems("dinner");
                resideMenu.closeMenu();
                break;
            case 3:
                ((selectionAdapter) niceAdapter).clear();
                setPageItems("drinks");
                resideMenu.closeMenu();
                break;
            case 4:
                ((selectionAdapter) niceAdapter).clear();
                break;
            case 5:
                Toast.makeText(this,"5 HIT",Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(this,"6 HIT",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this,"DEFAULT HIT",Toast.LENGTH_SHORT).show();
        }
    }

    private void launchEditActivity(int position){
        Intent editIntent = new Intent(this, viewDishActivity.class);
        editIntent.putExtra("orderObject", selection);
        editIntent.putExtra("foodItem", foodData.get(position));
        startActivity(editIntent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    private void createMenuTitles(){
        String titlesLeft[] = { "Morgenmad", "Frokost", "Aftensmad", "Drikkelse" };
        int iconLeft[] = { R.drawable.breakfast_icon, R.drawable.lunch_icon, R.drawable.dinner_icon, R.drawable.drinks_icon };
        for (int i = 0; i < titlesLeft.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, iconLeft[i], titlesLeft[i]);
            item.setOnClickListener(this);
            item.setId(i);
            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_LEFT);
        }

        String titlesRight[] = { "Tilføj Ret", "Fjern Ret", "Indstillinger"};
        int iconRight[] = { R.drawable.breakfast_icon, R.drawable.breakfast_icon, R.drawable.breakfast_icon};
        for (int i = 0; i < titlesRight.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, iconRight[i], titlesRight[i]);
            item.setOnClickListener(this);
            item.setId(i+4);
            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_RIGHT);
        }
    }

    private void setPageItems(String choice){
        switch(choice){
        case "breakfast":
            foodData =textToObject.getList("breakfastdata.txt",this);
            niceAdapter = new selectionAdapter(foodData);
            ((selectionAdapter) niceAdapter).setOnItemClickListener(new selectionAdapter.ClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    launchEditActivity(position);
                }
            });
            titleTV.setText("Morgenmad");
            mLayoutManager = new GridLayoutManager(this,2);
            foodList.setLayoutManager(mLayoutManager);
            foodList.setAdapter(niceAdapter);
            break;
            case "lunch":
                foodData = textToObject.getList("lunchdata.txt",this);
                foodList.setHasFixedSize(true);
                niceAdapter = new selectionAdapter(foodData);
                ((selectionAdapter) niceAdapter).setOnItemClickListener(new selectionAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        launchEditActivity(position);
                    }
                });
                titleTV.setText("Frokost");
                mLayoutManager = new GridLayoutManager(this,2);
                foodList.setLayoutManager(mLayoutManager);
                foodList.setAdapter(niceAdapter);
                break;
            case "dinner":
                foodData = textToObject.getList("breakfastdata.txt",this);
                foodList.setHasFixedSize(true);
                niceAdapter = new selectionAdapter(foodData);
                ((selectionAdapter) niceAdapter).setOnItemClickListener(new selectionAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        launchEditActivity(position);
                    }
                });
                titleTV.setText("Aftemsmad");
                mLayoutManager = new GridLayoutManager(this,2);
                foodList.setLayoutManager(mLayoutManager);
                foodList.setAdapter(niceAdapter);
                break;
            case "drinks":
                foodData =textToObject.getList("breakfastdata.txt",this);
                foodList.setHasFixedSize(true);
                niceAdapter = new selectionAdapter(foodData);
                ((selectionAdapter) niceAdapter).setOnItemClickListener(new selectionAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        launchEditActivity(position);
                    }
                });
                titleTV.setText("Drinks");
                mLayoutManager = new GridLayoutManager(this,2);
                foodList.setLayoutManager(mLayoutManager);
                foodList.setAdapter(niceAdapter);
                break;
            default:
                Toast.makeText(this,"DEFAULT HIT",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if(resideMenu.isOpened()){
            resideMenu.closeMenu();
        }else{super.onBackPressed();}
    }
}