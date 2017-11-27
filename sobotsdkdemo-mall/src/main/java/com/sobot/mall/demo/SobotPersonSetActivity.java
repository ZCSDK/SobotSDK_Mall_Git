package com.sobot.mall.demo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.EditText;

public class SobotPersonSetActivity extends Activity {

    private EditText sobot_person_tel;
    private EditText sobot_person_uName;
    private EditText sobot_person_email;
    private EditText sobot_person_realName;
    private EditText sobot_person_qq;
    private EditText sobot_person_face;
    private EditText sobot_person_reMark;
    private EditText sobot_person_visitTitle;
    private EditText sobot_person_visitUrl;
    private EditText sobot_person_key1;
    private EditText sobot_person_key2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sobot_person_set_activity);
        initSobotView();
    }

    private void initSobotView() {
        sobot_person_tel = (EditText) findViewById(R.id.sobot_person_tel);
        sobot_person_uName = (EditText) findViewById(R.id.sobot_person_uName);
        sobot_person_email = (EditText) findViewById(R.id.sobot_person_email);
        sobot_person_realName = (EditText) findViewById(R.id.sobot_person_realName);
        sobot_person_qq = (EditText) findViewById(R.id.sobot_person_qq);
        sobot_person_face = (EditText) findViewById(R.id.sobot_person_face);
        sobot_person_reMark = (EditText) findViewById(R.id.sobot_person_reMark);
        sobot_person_visitTitle = (EditText) findViewById(R.id.sobot_person_visitTitle);
        sobot_person_visitUrl = (EditText) findViewById(R.id.sobot_person_visitUrl);
        sobot_person_key1 = (EditText) findViewById(R.id.sobot_person_key1);
        sobot_person_key2 = (EditText) findViewById(R.id.sobot_person_key2);

        getPersonInfo();
    }

    @Override
    public void onBackPressed() {
        savePersonInfo();
        finish();
    }

    private void savePersonInfo() {
        DemoSPUtil.saveStringData(SobotPersonSetActivity.this, "sobot_tel", sobot_person_tel.getText().toString());
        DemoSPUtil.saveStringData(SobotPersonSetActivity.this, "person_uName", sobot_person_uName.getText().toString());
        DemoSPUtil.saveStringData(SobotPersonSetActivity.this, "sobot_email", sobot_person_email.getText().toString());
        DemoSPUtil.saveStringData(SobotPersonSetActivity.this, "sobot_realname", sobot_person_realName.getText().toString());
        DemoSPUtil.saveStringData(SobotPersonSetActivity.this, "sobot_qq", sobot_person_qq.getText().toString());
        DemoSPUtil.saveStringData(SobotPersonSetActivity.this, "sobot_face", sobot_person_face.getText().toString());
        DemoSPUtil.saveStringData(SobotPersonSetActivity.this, "sobot_reMark", sobot_person_reMark.getText().toString());
        DemoSPUtil.saveStringData(SobotPersonSetActivity.this, "sobot_visitTitle", sobot_person_visitTitle.getText().toString());
        DemoSPUtil.saveStringData(SobotPersonSetActivity.this, "sobot_visitUrl", sobot_person_visitUrl.getText().toString());
        DemoSPUtil.saveStringData(SobotPersonSetActivity.this, "sobot_key1", sobot_person_key1.getText().toString());
        DemoSPUtil.saveStringData(SobotPersonSetActivity.this, "sobot_key2", sobot_person_key2.getText().toString());
    }

    private void getPersonInfo() {
        String person_tel = DemoSPUtil.getStringData(SobotPersonSetActivity.this, "sobot_tel", "");
        if (!TextUtils.isEmpty(person_tel)){
            sobot_person_tel.setText(person_tel);
        }
        String person_uName = DemoSPUtil.getStringData(SobotPersonSetActivity.this, "person_uName", "");
        if (!TextUtils.isEmpty(person_uName)){
            sobot_person_uName.setText(person_uName);
        }
        String person_email = DemoSPUtil.getStringData(SobotPersonSetActivity.this, "sobot_email", "");
        if (!TextUtils.isEmpty(person_email)){
            sobot_person_email.setText(person_email);
        }
        String person_realName = DemoSPUtil.getStringData(SobotPersonSetActivity.this, "sobot_realname", "");
        if (!TextUtils.isEmpty(person_realName)){
            sobot_person_realName.setText(person_realName);
        }
        String person_qq = DemoSPUtil.getStringData(SobotPersonSetActivity.this, "sobot_qq", "");
        if (!TextUtils.isEmpty(person_qq)){
            sobot_person_qq.setText(person_qq);
        }
        String person_face = DemoSPUtil.getStringData(SobotPersonSetActivity.this, "sobot_face", "");
        if (!TextUtils.isEmpty(person_face)){
            sobot_person_face.setText(person_face);
        }
        String person_reMark = DemoSPUtil.getStringData(SobotPersonSetActivity.this, "sobot_reMark", "");
        if (!TextUtils.isEmpty(person_reMark)){
            sobot_person_reMark.setText(person_reMark);
        }
        String person_visitTitle = DemoSPUtil.getStringData(SobotPersonSetActivity.this, "sobot_visitTitle", "");
        if (!TextUtils.isEmpty(person_visitTitle)){
            sobot_person_visitTitle.setText(person_visitTitle);
        }
        String person_visitUrl = DemoSPUtil.getStringData(SobotPersonSetActivity.this, "sobot_visitUrl", "");
        if (!TextUtils.isEmpty(person_visitUrl)){
            sobot_person_visitUrl.setText(person_visitUrl);
        }
        String person_key1 = DemoSPUtil.getStringData(SobotPersonSetActivity.this, "sobot_key1", "");
        if (!TextUtils.isEmpty(person_key1)){
            sobot_person_key1.setText(person_key1);
        }
        String person_key2 = DemoSPUtil.getStringData(SobotPersonSetActivity.this, "sobot_key2", "");
        if (!TextUtils.isEmpty(person_key2)){
            sobot_person_key2.setText(person_key2);
        }
    }
}