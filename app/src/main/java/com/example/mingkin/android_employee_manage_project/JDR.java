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
 * Created by Mingkin on 2014/12/6.
 */
public class JDR extends Activity
{
    private TextView JDR_fAutoNr_;
    private TextView JDR_fMouldNr;
    private TextView JDR_fProcName;
    private TextView JDR_fPieceName;
    private TextView JDR_fWorkerName;
    private TextView JDR_fMachineName;
    private TextView JDR_fWorkerNr;
    private TextView JDR_fMachineNr;
    private TextView JDR_fActType;

    private TextView JDR_StartFlag;
    private TextView JDR_PauseFlag;
    private TextView JDR_StopFlag;


    //private Button JDR_Employee;
    private Button JDR_Start;
    private Button JDR_Pause;
    private Button JDR_Stop;


    protected void onCreate(Bundle savedInstanceState)
    {


        if (Build.VERSION.SDK_INT >= 11)
        {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.jdr);
        JDR_fAutoNr_ = (TextView) findViewById(R.id.JDR_fAutoNr_);
        JDR_fMouldNr = (TextView) findViewById(R.id.JDR_fMouldNr);
        JDR_fProcName = (TextView) findViewById(R.id.JDR_fProcName);
        JDR_fPieceName = (TextView) findViewById(R.id.JDR_fPieceName);
        JDR_fWorkerName = (TextView) findViewById(R.id.JDR_fWorkerName);
        JDR_fMachineName = (TextView) findViewById(R.id.JDR_fMachineName);
        JDR_fWorkerNr = (TextView) findViewById(R.id.JDR_fWorkerNr);
        JDR_fMachineNr = (TextView) findViewById(R.id.JDR_fMachineNr);
        JDR_fActType = (TextView) findViewById(R.id.JDR_fActType);

        JDR_Start = (Button) findViewById(R.id.JDR_Start);
        JDR_Pause = (Button) findViewById(R.id.JDR_Pause);
        JDR_Stop = (Button) findViewById(R.id.JDR_Stop);

        JDR_StartFlag = (TextView) findViewById(R.id.JDR_StartFlag);
        JDR_PauseFlag = (TextView) findViewById(R.id.JDR_PauseFlag);
        JDR_StopFlag = (TextView) findViewById(R.id.JDR_StopFlag);


        Intent intent = this.getIntent();
        JDR_fAutoNr_.setText(intent.getStringExtra("JDR_fAutoNr_"));
        JDR_fMouldNr.setText(intent.getStringExtra("JDR_fMouldNr"));
        JDR_fProcName.setText(intent.getStringExtra("JDR_fProcName"));
        JDR_fPieceName.setText(intent.getStringExtra("JDR_fPieceName"));
        JDR_fWorkerName.setText(intent.getStringExtra("JDR_fWorkerName"));
        JDR_fMachineName.setText(intent.getStringExtra("JDR_fMachineName"));
        JDR_fWorkerNr.setText(intent.getStringExtra("JDR_fWorkerNr"));
        JDR_fMachineNr.setText(intent.getStringExtra("JDR_fMachineNr"));


        JDR_Start.setOnClickListener(new StartBtnOnclickListener());
        JDR_Pause.setOnClickListener(new PauseBtnOnclickListener());
        JDR_Stop.setOnClickListener(new StopBtnOnclickListener());
    }

    class StartBtnOnclickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            JDR_Start.setVisibility(View.INVISIBLE);
            JDR_Pause.setVisibility(View.VISIBLE);
            JDR_Stop.setVisibility(View.VISIBLE);
            final String ActType = JDR_fActType.getText().toString().trim();
            final String WorkerCardNr = JDR_fWorkerNr.getText().toString().trim();
            final String MachineCardNr = JDR_fMachineNr.getText().toString().trim();
            final String MSAutoNr_ = JDR_fAutoNr_.getText().toString().trim();
            final String AddFlag = JDR_StartFlag.getText().toString().trim();
            setDatafNr(ActType , WorkerCardNr , MachineCardNr , MSAutoNr_ , AddFlag);
        }
    }

    class PauseBtnOnclickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            JDR_Start.setVisibility(View.VISIBLE);
            JDR_Pause.setVisibility(View.INVISIBLE);
            final String ActType = JDR_fActType.getText().toString().trim();
            final String WorkerCardNr = JDR_fWorkerNr.getText().toString().trim();
            final String MachineCardNr = JDR_fMachineNr.getText().toString().trim();
            final String MSAutoNr_ = JDR_fAutoNr_.getText().toString().trim();
            final String AddFlag = JDR_PauseFlag.getText().toString().trim();
            setDatafNr(ActType , WorkerCardNr , MachineCardNr , MSAutoNr_ , AddFlag);
        }
    }

    class StopBtnOnclickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            JDR_Start.setVisibility(View.INVISIBLE);
            JDR_Pause.setVisibility(View.INVISIBLE);
            JDR_Stop.setVisibility(View.INVISIBLE);
            final String ActType = JDR_fActType.getText().toString().trim();//get到XML的ID名为JDR_fActType中的信息，再写入到WebService中
            final String WorkerCardNr = JDR_fWorkerNr.getText().toString().trim();//与上同理
            final String MachineCardNr = JDR_fMachineNr.getText().toString().trim();//与上同理
            final String MSAutoNr_ = JDR_fAutoNr_.getText().toString().trim();//与上同理
            final String AddFlag = JDR_StopFlag.getText().toString().trim();//与上同理
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
