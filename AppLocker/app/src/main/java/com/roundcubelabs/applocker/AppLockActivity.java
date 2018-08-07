package com.roundcubelabs.applocker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Maria Inam on 8/7/2018.
 */

public class AppLockActivity extends AppCompatActivity {

    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applock_screen);
        input=(EditText)findViewById(R.id.editText);


    }

    public void submitInput(View v){
        System.out.println("Val="+input.getText().toString());


        if(input.getText().toString().equals("usama")){
            //Open that particular Application

            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
            startActivity(LaunchIntent);
            finish();
        }
        else{
            showHomeScreen();
        }

    }

    public boolean showHomeScreen(){
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(startMain);
        return true;
    }
}
