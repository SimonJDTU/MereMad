package com.johansen.dk.meremad.model;

import android.content.Context;

import com.johansen.dk.meremad.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class textToObject {


    public static ArrayList<foodItem> getList(String filename, Context context) {

        ArrayList<foodItem> list = new ArrayList<>(0);

        try {
            InputStreamReader is = new InputStreamReader(context.getAssets().open(filename));
            BufferedReader reader = new BufferedReader(is);
            int amountOptions;
            String line = reader.readLine();

            while (line != null) {
                foodItem ft = new foodItem();
                ft.setID(Integer.parseInt(reader.readLine()));
                ft.setName(reader.readLine());

                ft.setImageRes(context.getResources().getIdentifier(reader.readLine(),"drawable",context.getPackageName()));
                amountOptions = Integer.parseInt(reader.readLine());
                for(int i=0;amountOptions>i;i++){
                    ft.addToIngrediences(reader.readLine());
                }
                list.add(ft);
                line = reader.readLine();
                
            }
            is.close();
        }catch(IOException e){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+" CANT FIND FILE");
            e.printStackTrace();
        }
        return list;
    }


    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }



}
