package com.example.mingkin.android_employee_manage_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Main extends Activity
{
    private Button Employee;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Employee = (Button) findViewById(R.id.Employee);

        //员工登陆按钮监听器，触发该方法则会跳转页面
        Employee.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(Main.this , Employee.class);//指从Main页面跳转到Employee页面
                startActivity(intent);//开始事件
            }
        });
    }
}
