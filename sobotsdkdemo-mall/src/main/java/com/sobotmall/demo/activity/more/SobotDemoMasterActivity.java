package com.sobotmall.demo.activity.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sobot.chat.SobotApi;
import com.sobotmall.demo.SobotSPUtil;
import com.sobotmall.demo.R;

/**
 * Created by Administrator on 2017/11/20.
 */

public class SobotDemoMasterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText sobot_appkey;//appkey
    private EditText sobot_partnerId;//partnerId唯一标识用户
    private EditText sobot_platformunion;//sobot_platformunion平台id
    private EditText ed_hot_question;;//
    private EditText sobot_customerCode;;//customerCode
    private EditText sobot_flowCompanyId;;//sobot_flowCompanyId
    private EditText sobot_flowGroupId;;//sobot_flowGroupId
    private RelativeLayout rl_;
    private RelativeLayout sobot_demo_tv_left_rl;
    private ImageView sobot_tv_left;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sobot_demo_master_activity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        inifViews();
    }

    private void inifViews() {
        sobot_demo_tv_left_rl = (RelativeLayout) findViewById(R.id.sobot_demo_tv_left_rl);
        sobot_tv_left = (ImageView) findViewById(R.id.sobot_demo_tv_left);
        sobot_demo_tv_left_rl .setOnClickListener(this);
        sobot_tv_left .setOnClickListener(this);
        TextView sobot_text_title = (TextView) findViewById(R.id.sobot_demo_tv_title);
        sobot_text_title.setText("基本设置");
        sobot_appkey = (EditText) findViewById(R.id.sobot_demo_appkey);
        sobot_partnerId = (EditText) findViewById(R.id.sobot_partnerId);
        sobot_platformunion = (EditText) findViewById(R.id.sobot_platformunion);
        ed_hot_question = (EditText) findViewById(R.id.ed_hot_question);
        sobot_customerCode = (EditText) findViewById(R.id.sobot_customerCode);
        sobot_flowCompanyId = (EditText) findViewById(R.id.sobot_flowCompanyId);
        sobot_flowGroupId = (EditText) findViewById(R.id.sobot_flowGroupId);
        rl_ = (RelativeLayout) findViewById(R.id.rl_);
        rl_.setOnClickListener(this);
        getSobotStartSet();
    }

    @Override
    public void onBackPressed() {
        saveSobotStartSet();
        finish();
    }

    private void saveSobotStartSet() {
        SobotSPUtil.saveStringData(this, "sobot_appkey", sobot_appkey.getText().toString());
        SobotSPUtil.saveStringData(this, "sobot_partnerId", sobot_partnerId.getText().toString());
        SobotSPUtil.saveStringData(this, "ed_hot_question_value", ed_hot_question.getText().toString());
        SobotSPUtil.saveStringData(this, "sobot_platformunion_value", sobot_platformunion.getText().toString());
        SobotSPUtil.saveStringData(this, "sobot_customerCode_value", sobot_customerCode.getText().toString());
        SobotSPUtil.saveStringData(this, "sobot_flowCompanyId_value", sobot_flowCompanyId.getText().toString());
        SobotSPUtil.saveStringData(this, "sobot_flowGroupId_value", sobot_flowGroupId.getText().toString());
        SobotApi.initPlatformUnion(this, SobotSPUtil.getStringData(this, "sobot_platformunion_value", "019"), "");
    }

    private void getSobotStartSet() {
        String sobot_value_appkey = SobotSPUtil.getStringData(this, "sobot_appkey", "");
        if (!TextUtils.isEmpty(sobot_value_appkey)) {
            sobot_appkey.setText(sobot_value_appkey);
        }
        String sobot_value_partnerId = SobotSPUtil.getStringData(this, "sobot_partnerId", "");
        if (!TextUtils.isEmpty(sobot_value_partnerId)) {
            sobot_partnerId.setText(sobot_value_partnerId);
        }
        String ed_hot_question_value = SobotSPUtil.getStringData(this, "ed_hot_question_value", "");
        if (!TextUtils.isEmpty(ed_hot_question_value)) {
            ed_hot_question.setText(ed_hot_question_value);
        }
        String sobot_platformunion_value = SobotSPUtil.getStringData(this, "sobot_platformunion_value", "");
        if (!TextUtils.isEmpty(sobot_platformunion_value)) {
            sobot_platformunion.setText(sobot_platformunion_value);
        }
        String sobot_customerCode_value = SobotSPUtil.getStringData(this, "sobot_customerCode_value", "");
        if (!TextUtils.isEmpty(sobot_customerCode_value)) {
            sobot_customerCode.setText(sobot_customerCode_value);
        }
        String sobot_flowCompanyId_value = SobotSPUtil.getStringData(this, "sobot_flowCompanyId_value", "");
        if (!TextUtils.isEmpty(sobot_flowCompanyId_value)) {
            sobot_flowCompanyId.setText(sobot_flowCompanyId_value);
        }
        String sobot_flowGroupId_value = SobotSPUtil.getStringData(this, "sobot_flowGroupId_value", "");
        if (!TextUtils.isEmpty(sobot_flowGroupId_value)) {
            sobot_flowGroupId.setText(sobot_flowGroupId_value);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == rl_) {//用户资料设置
            Intent intent = new Intent(this, SobotPersonSetActivity.class);
            startActivity(intent);
        } else if (v == sobot_tv_left || v == sobot_demo_tv_left_rl) {
            saveSobotStartSet();
            finish();
        }
    }
}