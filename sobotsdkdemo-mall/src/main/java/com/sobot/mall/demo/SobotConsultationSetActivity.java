package com.sobot.mall.demo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class SobotConsultationSetActivity extends Activity {

    private CheckBox sobot_goods_is_show_info;//是否显示咨询信息设置
    private EditText sobot_goods_title;//标题
    private EditText sobot_goods_describe;//摘要
    private EditText sobot_goods_lable;//标签
    private EditText sobot_goods_imgUrl;//图片Url 必须为有效连接才可以显示这张图片
    private EditText sobot_goods_fromUrl;//发送内容
    private boolean isShow = false;//是否显示咨询信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sobot_consultation_set_activity);

        initSobotView();
    }

    private void initSobotView() {
        sobot_goods_is_show_info = (CheckBox) findViewById(R.id.sobot_goods_is_show_info);
        sobot_goods_title = (EditText) findViewById(R.id.sobot_goods_title);
        sobot_goods_describe = (EditText) findViewById(R.id.sobot_goods_describe);
        sobot_goods_lable = (EditText) findViewById(R.id.sobot_goods_lable);
        sobot_goods_imgUrl = (EditText) findViewById(R.id.sobot_goods_imgUrl);
        sobot_goods_fromUrl = (EditText) findViewById(R.id.sobot_goods_fromUrl);
        sobot_goods_is_show_info.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                setSobotView(checked);
            }
        });
        getSobotConsultation();
    }

    private void setSobotView(boolean checked){
        if (checked){
            sobot_goods_title.setVisibility(View.VISIBLE);
            sobot_goods_describe.setVisibility(View.VISIBLE);
            sobot_goods_lable.setVisibility(View.VISIBLE);
            sobot_goods_imgUrl.setVisibility(View.VISIBLE);
            sobot_goods_fromUrl.setVisibility(View.VISIBLE);
        } else {
            sobot_goods_title.setVisibility(View.INVISIBLE);
            sobot_goods_describe.setVisibility(View.INVISIBLE);
            sobot_goods_lable.setVisibility(View.INVISIBLE);
            sobot_goods_imgUrl.setVisibility(View.INVISIBLE);
            sobot_goods_fromUrl.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        saveSobotConsultation();
        finish();
    }

    private void saveSobotConsultation() {
        DemoSPUtil.saveBooleanData(SobotConsultationSetActivity.this, "sobot_goods_is_show_info", sobot_goods_is_show_info.isChecked());
        DemoSPUtil.saveStringData(SobotConsultationSetActivity.this, "sobot_goods_title_value", sobot_goods_title.getText().toString());
        DemoSPUtil.saveStringData(SobotConsultationSetActivity.this, "sobot_goods_describe_value", sobot_goods_describe.getText().toString());
        DemoSPUtil.saveStringData(SobotConsultationSetActivity.this, "sobot_goods_lable_value", sobot_goods_lable.getText().toString());
        DemoSPUtil.saveStringData(SobotConsultationSetActivity.this, "sobot_goods_imgUrl_value", sobot_goods_imgUrl.getText().toString());
        DemoSPUtil.saveStringData(SobotConsultationSetActivity.this, "sobot_goods_fromUrl_value", sobot_goods_fromUrl.getText().toString());
    }

    private void getSobotConsultation(){
        isShow = DemoSPUtil.getBooleanData(SobotConsultationSetActivity.this,"sobot_goods_is_show_info",false);
        sobot_goods_is_show_info.setChecked(isShow);
        if (isShow){
            sobot_goods_title.setVisibility(View.VISIBLE);
            sobot_goods_describe.setVisibility(View.VISIBLE);
            sobot_goods_lable.setVisibility(View.VISIBLE);
            sobot_goods_imgUrl.setVisibility(View.VISIBLE);
            sobot_goods_fromUrl.setVisibility(View.VISIBLE);
            } else {
            sobot_goods_title.setVisibility(View.INVISIBLE);
            sobot_goods_describe.setVisibility(View.INVISIBLE);
            sobot_goods_lable.setVisibility(View.INVISIBLE);
            sobot_goods_imgUrl.setVisibility(View.INVISIBLE);
            sobot_goods_fromUrl.setVisibility(View.INVISIBLE);
        }

        String sobot_goods_title_value = DemoSPUtil.getStringData(SobotConsultationSetActivity.this,"sobot_goods_title_value","");
        if (!TextUtils.isEmpty(sobot_goods_title_value)){
            sobot_goods_title.setText(sobot_goods_title_value);
        }
        String sobot_goods_describe_value = DemoSPUtil.getStringData(SobotConsultationSetActivity.this,"sobot_goods_describe_value","");
        if (!TextUtils.isEmpty(sobot_goods_describe_value)){
            sobot_goods_describe.setText(sobot_goods_describe_value);
        }
        String sobot_goods_lable_value = DemoSPUtil.getStringData(SobotConsultationSetActivity.this,"sobot_goods_lable_value","");
        if (!TextUtils.isEmpty(sobot_goods_lable_value)){
            sobot_goods_lable.setText(sobot_goods_lable_value);
        }
        String sobot_goods_imgUrl_value = DemoSPUtil.getStringData(SobotConsultationSetActivity.this,"sobot_goods_imgUrl_value","");
        if (!TextUtils.isEmpty(sobot_goods_imgUrl_value)){
            sobot_goods_imgUrl.setText(sobot_goods_imgUrl_value);
        }
        String sobot_goods_fromUrl_value = DemoSPUtil.getStringData(SobotConsultationSetActivity.this,"sobot_goods_fromUrl_value","");
        if (!TextUtils.isEmpty(sobot_goods_fromUrl_value)){
            sobot_goods_fromUrl.setText(sobot_goods_fromUrl_value);
        }
    }
}