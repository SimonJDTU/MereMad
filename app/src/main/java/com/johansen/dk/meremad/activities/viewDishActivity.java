package com.johansen.dk.meremad.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.johansen.dk.meremad.R;
import com.johansen.dk.meremad.adapter.dishDescriptionAdapter;
import com.johansen.dk.meremad.model.foodItem;

import java.util.ArrayList;

public class viewDishActivity extends AppCompatActivity {

    com.johansen.dk.meremad.model.foodItem dish;
    ImageView foodImage;
    TextView foodName;
    RecyclerView ingredienceList, howtocookList;
    ArrayList<String> dishIngrediences, dishHowtocook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dish);
        dish = (foodItem) getIntent().getSerializableExtra("foodItem");
        foodImage = findViewById(R.id.viewDishImage);
        foodImage.setImageResource(dish.getImageRes());
        //foodImage.setTransitionName(foodItem.getName()+"Trans");
        foodName = findViewById(R.id.viewDishName);
        foodName.setText(dish.getName());
        //foodName.setTypeface(tf);

        dishIngrediences=dish.getIngrediences();
        dishHowtocook=dish.getHowtocook();

        ingredienceList = findViewById(R.id.ingredienceList);
        LinearLayoutManager mLayoutManager = new GridLayoutManager(this,2);
        ingredienceList.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter ingredienceAdapter = new dishDescriptionAdapter(dishIngrediences);
        ingredienceList.setAdapter(ingredienceAdapter);

        howtocookList = findViewById(R.id.howtocookList);
        LinearLayoutManager mmLayoutManager = new LinearLayoutManager(this);;
        howtocookList.setLayoutManager(mmLayoutManager);
        RecyclerView.Adapter howtocookAdapter = new dishDescriptionAdapter(dishIngrediences);
        howtocookList.setAdapter(howtocookAdapter);
    }
}
