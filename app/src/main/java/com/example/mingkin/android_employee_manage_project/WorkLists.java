package com.example.mingkin.android_employee_manage_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import org.ksoap2.serialization.SoapObject;
import org.w3c.dom.Text;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mingkin on 2014/12/7.
 */
public class WorkLists extends Activity
{
    private List<Map<String, Object>> WorkList = new ArrayList<Map<String, Object>>();
    private TextView WorkLists_fWorkerName;
    private TextView WorkLists_fWorkerNr;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worklists);

        WorkLists_fWorkerName = (TextView) findViewById(R.id.WorkLists_fWorkerName);
        WorkLists_fWorkerNr = (TextView) findViewById(R.id.WorkLists_fWorkerNr);

        Intent intent = this.getIntent();
        WorkLists_fWorkerName.setText(intent.getStringExtra("fWorkerName"));//接收从上个页面传来的intent带的值，调用ID为双引号内绿色字体（fWorkerName）
        WorkLists_fWorkerNr.setText(intent.getStringExtra("fWorkerNr"));//接收从上个页面传来的intent带的值，调用ID为双引号内绿色字体（fWorkerNr）

        init();//调用下面的列表获取WebService返回值的方法
    }

    private void init()
    {
        final ListView listview = (ListView) findViewById(R.id.WorkList);
        //通过工具类调用WebService接口
        WebService.callWebService(WebService.WEB_SERVER_URL, "selectAllWorklistInfor", null, new WebService.WebServiceCallBack()//双引号内绿色字体为WebService调用的方法名
        {
            //WebService接口返回的数据回调到这个方法中
            @Override
            public void callBack(SoapObject result)
            {
                if(result != null)
                {
                    WorkList = MainSoapObject(result);
                    SimpleAdapter adapter = new SimpleAdapter(WorkLists.this,
                                                              WorkList,R.layout.worklists_items,
                                                              new String[] { "fAutoNr_", "fMouldNr" , "fProcName" , "fPieceName" , "fWorkerNr" , "fWorkerName" , "fMachineNr" , "fMachineName" , "fSchBegDate" , "fSchEndDate"},
                                                              new int[] {R.id.WorkLists_fAutoNr_, R.id.WorkLists_fMouldNr, R.id.WorkLists_fProcName, R.id.WorkLists_fPieceName, R.id.fWorkerNr, R.id.fWorkerName, R.id.WorkLists_fMachineNr, R.id.WorkLists_fMachineName, R.id.WorkLists_fSchBegDate, R.id.WorkLists_fSchEndDate }); //创建SimpleAdapter
                    listview.setAdapter(adapter);//将适配器与ListView关联
                }
                else
                {
                    Toast.makeText(WorkLists.this, "获取WebService数据错误", Toast.LENGTH_SHORT).show();//弹出连接错误信息
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
                intent.setClass(WorkLists.this, Info.class);//从WorkLists页面跳转到Info页面
                intent.putExtra("WorkLists_fWorkerName", WorkLists_fWorkerName.getText());//绑定员工值（特别注意！！这里绑定到intent的值是WorkLists_fWorkerName，既从Employee传来的员工名，并非这个页面从WebService获取的新员工名值）
                intent.putExtra("WorkLists_fWorkerNr", WorkLists_fWorkerNr.getText());//绑定员工号值（特别注意！！这里绑定到intent的值是WorkLists_fWorkerNr，既从Employee传来的员工号，并非这个页面从WebService获取的新员工号值）
                intent.putExtra("WorkLists_fAutoNr_", WorkList.get(position).get("fAutoNr_").toString());//绑定识别码值
                intent.putExtra("WorkLists_fMouldNr", WorkList.get(position).get("fMouldNr").toString());//绑定模具编号值
                intent.putExtra("WorkLists_fProcName", WorkList.get(position).get("fProcName").toString());//绑定工艺值
                intent.putExtra("WorkLists_fPieceName", WorkList.get(position).get("fPieceName").toString());//绑定工件值
                intent.putExtra("WorkLists_fMachineNr", WorkList.get(position).get("fMachineNr").toString());//绑定机台号值
                intent.putExtra("WorkLists_fMachineName", WorkList.get(position).get("fMachineName").toString());//绑定机台值
                startActivity(intent);//启动事件，将intent中的一切发送到下一页面（Info页面）
            }
        });
    }
    /**
     * 解析SoapObject对象
     * @param result
     * @return
     */
    private List<Map<String, Object>> MainSoapObject(SoapObject result)
    {
        List<Map<String, Object>> MainItems = new ArrayList<Map<String, Object>>(); //创建一个list集合
        SoapObject provinceSoapObject = (SoapObject) result.getProperty("selectAllWorklistInforResult");
        for(int i=0; i<provinceSoapObject.getPropertyCount(); i=i+10)
        {
            Map<String, Object> map = new HashMap<String, Object>(); //实例化Map对象
            map.put("fAutoNr_",provinceSoapObject.getProperty(i).toString());
            map.put("fMouldNr",provinceSoapObject.getProperty(i+1).toString());
            map.put("fProcName",provinceSoapObject.getProperty(i+2).toString());
            map.put("fPieceName",provinceSoapObject.getProperty(i+3).toString());
            map.put("fWorkerNr",provinceSoapObject.getProperty(i+4).toString());
            map.put("fWorkerName",provinceSoapObject.getProperty(i+5).toString());
            map.put("fMachineNr",provinceSoapObject.getProperty(i+6).toString());
            map.put("fMachineName",provinceSoapObject.getProperty(i+7).toString());
            map.put("fSchBegDate",provinceSoapObject.getProperty(i+8).toString());
            map.put("fSchEndDate",provinceSoapObject.getProperty(i+9).toString());
            MainItems.add(map);
        }
        return MainItems;
    }
}
