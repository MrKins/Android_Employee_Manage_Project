package com.example.mingkin.android_employee_manage_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mingkin on 2014/12/5.
 */
public class Employee extends Activity
{
    private List<Map<String, Object>> EmployeeList = new ArrayList<Map<String, Object>>();

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee);
        init();//调用下面的列表获取WebService返回值的方法
    }

    private void init()
    {
        final ListView listview = (ListView) findViewById(R.id.Employee_list);
        //通过工具类调用WebService接口
        WebService.callWebService(WebService.WEB_SERVER_URL, "selectAllEmployeeInfor", null, new WebService.WebServiceCallBack()//双引号内绿色字体为WebService调用的方法名
        {
            //WebService接口返回的数据回调到这个方法中
            public void callBack(SoapObject result)
            {
                if(result != null)
                {
                    EmployeeList = EmployeeSoapObject(result);
                    SimpleAdapter adapter = new SimpleAdapter(Employee.this,
                                                              EmployeeList,R.layout.employee_items,
                                                              new String[] { "fWorkerNr", "fWorkerName"},
                                                              new int[] {R.id.Employee_fWorkerNr, R.id.Employee_fWorkerName,}); //创建一个自定义的SimpleAdapter
                    listview.setAdapter(adapter);//将适配器与ListView关联
                }
                else
                {
                    Toast.makeText(Employee.this, "获取WebService数据错误", Toast.LENGTH_SHORT).show();//弹出连接错误信息
                }
            }
        });

        //点击list切换页面
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent();
                intent.setClass(Employee.this, WorkLists.class);//从Employee页面跳转到WorkLists页面
                intent.putExtra("fWorkerName", EmployeeList.get(position).get("fWorkerName").toString());//发送员工名值到intent中绑定，调用ID为第一个双引号的绿色字体（fWorkerName）
                intent.putExtra("fWorkerNr", EmployeeList.get(position).get("fWorkerNr").toString());//发送员工号值到intent中绑定，调用ID为第一个双引号的绿色字体（fWorkerNr）
                startActivity(intent);//启动事件，将intent中的一切发送到下一页面（WorkLists页面）
            }
        });
    }
    /**
     * 解析SoapObject对象
     * @param result
     * @return
     */
    private List<Map<String, Object>> EmployeeSoapObject(SoapObject result)
    {
        List<Map<String, Object>> EmployeeItems = new ArrayList<Map<String, Object>>(); //创建一个list集合
        SoapObject provinceSoapObject = (SoapObject) result.getProperty("selectAllEmployeeInforResult");
        for(int i=0; i<provinceSoapObject.getPropertyCount(); i=i+2)
        {
            Map<String, Object> map = new HashMap<String, Object>(); //实例化Map对象
            map.put("fWorkerNr",provinceSoapObject.getProperty(i).toString());
            map.put("fWorkerName",provinceSoapObject.getProperty(i+1).toString());
            EmployeeItems.add(map);
        }
        return EmployeeItems;
    }
}
