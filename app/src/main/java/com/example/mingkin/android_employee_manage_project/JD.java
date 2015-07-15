package com.example.mingkin.android_employee_manage_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Mingkin on 2014/12/5.
 */
public class JD extends Activity
{
    private TextView JD_fAutoNr_;
    private TextView JD_fMouldNr;
    private TextView JD_fProcName;
    private TextView JD_fPieceName;
    private TextView JD_fWorkerName;
    private TextView JD_fMachineName;
    private TextView JD_fWorkerNr;
    private TextView JD_fMachineNr;
    private TextView JD_fActType;

    private TextView JD_StartFlag;
    private TextView JD_PauseFlag;
    private TextView JD_StopFlag;


    //private Button JD_Employee;
    private Button JD_Start;
    private Button JD_Pause;
    private Button JD_Stop;


    protected void onCreate(Bundle savedInstanceState)
    {


        if (Build.VERSION.SDK_INT >= 11)
        {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.jd);
        JD_fAutoNr_ = (TextView) findViewById(R.id.JD_fAutoNr_);
        JD_fMouldNr = (TextView) findViewById(R.id.JD_fMouldNr);
        JD_fProcName = (TextView) findViewById(R.id.JD_fProcName);
        JD_fPieceName = (TextView) findViewById(R.id.JD_fPieceName);
        JD_fWorkerName = (TextView) findViewById(R.id.JD_fWorkerName);
        JD_fMachineName = (TextView) findViewById(R.id.JD_fMachineName);
        JD_fWorkerNr = (TextView) findViewById(R.id.JD_fWorkerNr);
        JD_fMachineNr = (TextView) findViewById(R.id.JD_fMachineNr);
        JD_fActType = (TextView) findViewById(R.id.JD_fActType);

        JD_Start = (Button) findViewById(R.id.JD_Start);
        JD_Pause = (Button) findViewById(R.id.JD_Pause);
        JD_Stop = (Button) findViewById(R.id.JD_Stop);

        JD_StartFlag = (TextView) findViewById(R.id.JD_StartFlag);
        JD_PauseFlag = (TextView) findViewById(R.id.JD_PauseFlag);
        JD_StopFlag = (TextView) findViewById(R.id.JD_StopFlag);


        Intent intent = this.getIntent();
        JD_fAutoNr_.setText(intent.getStringExtra("JD_fAutoNr_"));
        JD_fMouldNr.setText(intent.getStringExtra("JD_fMouldNr"));
        JD_fProcName.setText(intent.getStringExtra("JD_fProcName"));
        JD_fPieceName.setText(intent.getStringExtra("JD_fPieceName"));
        JD_fWorkerName.setText(intent.getStringExtra("JD_fWorkerName"));
        JD_fMachineName.setText(intent.getStringExtra("JD_fMachineName"));
        JD_fWorkerNr.setText(intent.getStringExtra("JD_fWorkerNr"));
        JD_fMachineNr.setText(intent.getStringExtra("JD_fMachineNr"));

        /*JD_Employee = (Button) findViewById(R.id.JD_Employee);

        JD_Employee.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(JD.this , Employee.class);
                startActivity(intent);
            }
        });*/

        JD_Start.setOnClickListener(new StartBtnOnclickListener());
        JD_Pause.setOnClickListener(new PauseBtnOnclickListener());
        JD_Stop.setOnClickListener(new StopBtnOnclickListener());
    }

    class StartBtnOnclickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            JD_Start.setVisibility(View.INVISIBLE);
            JD_Pause.setVisibility(View.VISIBLE);
            JD_Stop.setVisibility(View.VISIBLE);
            final String ActType = JD_fActType.getText().toString().trim();//与JDR同理
            final String WorkerCardNr = JD_fWorkerNr.getText().toString().trim();//与JDR同理
            final String MachineCardNr = JD_fMachineNr.getText().toString().trim();//与JDR同理
            final String MSAutoNr_ = JD_fAutoNr_.getText().toString().trim();//与JDR同理
            final String AddFlag = JD_StartFlag.getText().toString().trim();//与JDR同理
            setDatafNr(ActType , WorkerCardNr , MachineCardNr , MSAutoNr_ , AddFlag);
        }
    }

    class PauseBtnOnclickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            JD_Start.setVisibility(View.VISIBLE);
            JD_Pause.setVisibility(View.INVISIBLE);
            final String ActType = JD_fActType.getText().toString().trim();
            final String WorkerCardNr = JD_fWorkerNr.getText().toString().trim();
            final String MachineCardNr = JD_fMachineNr.getText().toString().trim();
            final String MSAutoNr_ = JD_fAutoNr_.getText().toString().trim();
            final String AddFlag = JD_PauseFlag.getText().toString().trim();
            setDatafNr(ActType , WorkerCardNr , MachineCardNr , MSAutoNr_ , AddFlag);
        }
    }

    class StopBtnOnclickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            JD_Start.setVisibility(View.INVISIBLE);
            JD_Pause.setVisibility(View.INVISIBLE);
            JD_Stop.setVisibility(View.INVISIBLE);
            final String ActType = JD_fActType.getText().toString().trim();
            final String WorkerCardNr = JD_fWorkerNr.getText().toString().trim();
            final String MachineCardNr = JD_fMachineNr.getText().toString().trim();
            final String MSAutoNr_ = JD_fAutoNr_.getText().toString().trim();
            final String AddFlag = JD_StopFlag.getText().toString().trim();
            setDatafNr(ActType , WorkerCardNr , MachineCardNr , MSAutoNr_ , AddFlag);
        }
    }


    public void setDatafNr(String fActType, String fWorkerCardNr, String fMachineCardNr, String fMSAutoNr_ , String fAddFlag)
    {

        // 命名空间
        String nameSpace = "http://tempuri.org/";

        // 调用的方法名称
        String methodName = "insertActDaily";//WebService调用的方法名

        // EndPoint
        String endPoint = "http://172.16.112.178:8181/Service1.asmx";//WebService的地址！重要！更换服务器地址记得更改此语句！

        // SOAP Action
        String soapAction = "http://tempuri.org/insertActDaily";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId，不可以随便写，必须和提供的参数名相同
        rpc.addProperty("fActType", fActType);
        rpc.addProperty("fWorkerCardNr", fWorkerCardNr);
        rpc.addProperty("fMachineCardNr", fMachineCardNr);
        rpc.addProperty("fMSAutoNr_", fMSAutoNr_);
        rpc.addProperty("fAddFlag", fAddFlag);

        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.bodyOut = rpc;

        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;

        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE transport = new HttpTransportSE(endPoint);

        try
        {
            // 调用WebService
            transport.call(soapAction, envelope);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
