package com.example.mingkin.android_employee_manage_project;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import org.ksoap2.serialization.SoapObject;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mingkin on 2014/12/4.
 */
public class Info extends Activity
{
    private Button JD;
    private Button JDR;
    private Button Other;

    private TextView Info_fAutoNr_;
    private TextView Info_fMouldNr;
    private TextView Info_fProcName;
    private TextView Info_fPieceName;
    private TextView Info_fWorkerName;
    private TextView Info_fMachineName;
    private TextView Info_fMachineNr;
    private TextView Info_fWorkerNr;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        JD = (Button) findViewById(R.id.JD);
        JDR = (Button) findViewById(R.id.JDR);
        Other = (Button) findViewById(R.id.Other);

        Info_fAutoNr_ = (TextView) findViewById(R.id.Info_fAutoNr_);
        Info_fMouldNr = (TextView) findViewById(R.id.Info_fMouldNr);
        Info_fProcName = (TextView) findViewById(R.id.Info_fProcName);
        Info_fPieceName = (TextView) findViewById(R.id.Info_fPieceName);
        Info_fWorkerName = (TextView) findViewById(R.id.Info_fWorkerName);
        Info_fMachineName = (TextView) findViewById(R.id.Info_fMachineName);
        Info_fMachineNr = (TextView) findViewById(R.id.Info_fMachineNr);
        Info_fWorkerNr = (TextView) findViewById(R.id.Info_fWorkerNr);


        Intent intent = this.getIntent();
        Info_fWorkerNr.setText(intent.getStringExtra("WorkLists_fWorkerNr").toString());//接受并SetText员工号
        Info_fWorkerName.setText(intent.getStringExtra("WorkLists_fWorkerName").toString());//接受并SetText员工
        Info_fAutoNr_.setText(intent.getSerializableExtra("WorkLists_fAutoNr_").toString());//接受并SetText识别码
        Info_fMouldNr.setText(intent.getSerializableExtra("WorkLists_fMouldNr").toString());//接受并SetText模具编号
        Info_fProcName.setText(intent.getSerializableExtra("WorkLists_fProcName").toString());//接受并SetText工艺
        Info_fPieceName.setText(intent.getSerializableExtra("WorkLists_fPieceName").toString());//接受并SetText工件
        Info_fMachineNr.setText(intent.getSerializableExtra("WorkLists_fMachineNr").toString());//接受并SetText机台号
        Info_fMachineName.setText(intent.getSerializableExtra("WorkLists_fMachineName").toString());//接受并SetText机台



        JD.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(Info.this , JD.class);
                intent.putExtra("JD_fAutoNr_",Info_fAutoNr_.getText());
                intent.putExtra("JD_fMouldNr",Info_fMouldNr.getText());
                intent.putExtra("JD_fProcName",Info_fProcName.getText());
                intent.putExtra("JD_fPieceName",Info_fPieceName.getText());
                intent.putExtra("JD_fWorkerNr",Info_fWorkerNr.getText());
                intent.putExtra("JD_fWorkerName",Info_fWorkerName.getText());
                intent.putExtra("JD_fMachineNr",Info_fMachineNr.getText());
                intent.putExtra("JD_fMachineName",Info_fMachineName.getText());
                startActivity(intent);
            }
        });

        JDR.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(Info.this , JDR.class);
                intent.putExtra("JDR_fAutoNr_",Info_fAutoNr_.getText());
                intent.putExtra("JDR_fMouldNr",Info_fMouldNr.getText());
                intent.putExtra("JDR_fProcName",Info_fProcName.getText());
                intent.putExtra("JDR_fPieceName",Info_fPieceName.getText());
                intent.putExtra("JDR_fWorkerNr",Info_fWorkerNr.getText());
                intent.putExtra("JDR_fWorkerName",Info_fWorkerName.getText());
                intent.putExtra("JDR_fMachineNr",Info_fMachineNr.getText());
                intent.putExtra("JDR_fMachineName",Info_fMachineName.getText());
                startActivity(intent);
            }
        });
    }
}
