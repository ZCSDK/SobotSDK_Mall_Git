package com.sobot.mall.demo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sobot.chat.api.enumtype.SobotChatTitleDisplayMode;
import com.sobot.chat.utils.ZhiChiConstant;

public class SobotStartSetActivity extends Activity {

    private EditText sobot_appkey;//appkey
    private EditText sobot_partnerId;//partnerId唯一标识用户
    private EditText sobot_receptionistId;//指定转入的人工客服id
    private EditText sobot_robot_code;//指定接入的机器人编号
    private EditText sobot_tv_chat_index_show_text;//如果聊天页头部显示固定文案，则要获取text
    private EditText sobot_show_history_ruler;//历史记录的显示规则
    private EditText sobot_demo_skillname;//技能组名称
    private EditText sobot_demo_skillid;//技能组id
    private EditText sobot_transferKeyWord;//转人工关键字
    private EditText sobot_isArtificialIntelligence_num;//如果是只能转人工，需要设置当应到问题出现几次以后显示转人工按钮
    private CheckBox sobot_receptionistId_must;//是否必须转入指定客服
    private CheckBox sobot_isArtificialIntelligence;//是否智能转人工,如果是，需要设置setArtificialIntelligenceNum为大于等于1的数字，默认是1
    private CheckBox sobot_isUseVoice;//是否使用语音功能
    private CheckBox sobot_isShowSatisfaction;//是否弹出满意度评价
    private CheckBox sobot_nike_param;//留言页面，选中昵称为必传参数
    private CheckBox sobot_isShowNikename;//留言页面，选中显示昵称输入框
    private CheckBox sobot_isOpenNotification;//是否开启消息提醒
    private CheckBox sobot_evaluationCompletedExit;//评价完是否结束会话
    private RadioGroup sobot_rg_initModeType;//设置SDK客服模式。
    private RadioButton rg_initModeType_noSet;//不设置(-1) 后台设置优先
    private RadioButton rg_initModeType_only_robot;//1  仅机器人
    private RadioButton rg_initModeType_only_customer;//2  仅人工
    private RadioButton rg_initModeType_robot_first;//3  机器人优先
    private RadioButton rg_initModeType_custom_first;//4  人工优先
    private RadioGroup sobot_rg_title_value_show;//设置SDK头部显示文案
    private RadioButton sobot_show_custom_nike;//客服昵称
    private RadioButton sobot_show_fixed_text;//固定文案，自定义文案
    private RadioButton sobot_show_company_name;//公司名称
    private String sobot_bool_rg_initModeType = "-1";//客服模式默认值
    private String sobot_title_value_show_type = "0";//头部显示文案类型默认值
    private String sobot_show_history = "0";//显示历史记录默认时间段
    private String sobot_title_value;//默认头部文案

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sobot_start_set_activity);

        initSobotView();
    }

    private void initSobotView() {
        sobot_appkey = (EditText) findViewById(R.id.sobot_appkey);
        sobot_partnerId = (EditText) findViewById(R.id.sobot_partnerId);
        sobot_receptionistId = (EditText) findViewById(R.id.sobot_receptionistId);
        sobot_robot_code = (EditText) findViewById(R.id.sobot_robot_code);
        sobot_demo_skillname = (EditText) findViewById(R.id.sobot_demo_skillname);
        sobot_demo_skillid = (EditText) findViewById(R.id.sobot_demo_skillid);
        sobot_transferKeyWord = (EditText) findViewById(R.id.sobot_transferKeyWord);
        sobot_isArtificialIntelligence_num = (EditText) findViewById(R.id.sobot_isArtificialIntelligence_num);

        sobot_tv_chat_index_show_text = (EditText) findViewById(R.id.sobot_tv_chat_index_show_text);
        sobot_show_history_ruler = (EditText) findViewById(R.id.sobot_show_history_ruler);

        sobot_receptionistId_must = (CheckBox) findViewById(R.id.sobot_receptionistId_must);
        sobot_isArtificialIntelligence = (CheckBox) findViewById(R.id.sobot_isArtificialIntelligence);
        sobot_isUseVoice = (CheckBox) findViewById(R.id.sobot_isUseVoice);
        sobot_isShowSatisfaction = (CheckBox) findViewById(R.id.sobot_isShowSatisfaction);
        sobot_nike_param = (CheckBox) findViewById(R.id.sobot_nike_param);
        sobot_isShowNikename = (CheckBox) findViewById(R.id.sobot_isShowNikename);
        sobot_isOpenNotification = (CheckBox) findViewById(R.id.sobot_isOpenNotification);
        sobot_evaluationCompletedExit = (CheckBox) findViewById(R.id.sobot_evaluationCompletedExit);
        sobot_rg_initModeType = (RadioGroup) findViewById(R.id.sobot_rg_initModeType);
        sobot_rg_title_value_show = (RadioGroup) findViewById(R.id.sobot_rg_title_value_show);

        rg_initModeType_noSet = (RadioButton) findViewById(R.id.rg_initModeType_noSet);
        rg_initModeType_only_robot = (RadioButton) findViewById(R.id.rg_initModeType_only_robot);
        rg_initModeType_only_customer = (RadioButton) findViewById(R.id.rg_initModeType_only_customer);
        rg_initModeType_robot_first = (RadioButton) findViewById(R.id.rg_initModeType_robot_first);
        rg_initModeType_custom_first = (RadioButton) findViewById(R.id.rg_initModeType_custom_first);
        sobot_show_custom_nike = (RadioButton) findViewById(R.id.sobot_show_custom_nike);
        sobot_show_fixed_text = (RadioButton) findViewById(R.id.sobot_show_fixed_text);
        sobot_show_company_name = (RadioButton) findViewById(R.id.sobot_show_company_name);

        getSobotStartSet();
    }

    @Override
    public void onBackPressed() {
        saveSobotStartSet();
        finish();
    }

    private void saveSobotStartSet() {
        DemoSPUtil.saveStringData(SobotStartSetActivity.this, "sobot_appkey", sobot_appkey.getText().toString());
        DemoSPUtil.saveStringData(SobotStartSetActivity.this, "sobot_partnerId", sobot_partnerId.getText().toString());
        DemoSPUtil.saveStringData(SobotStartSetActivity.this, "sobot_receptionistId", sobot_receptionistId.getText().toString());
        DemoSPUtil.saveStringData(SobotStartSetActivity.this, "sobot_demo_robot_code", sobot_robot_code.getText().toString());
        DemoSPUtil.saveBooleanData(SobotStartSetActivity.this, "sobot_receptionistId_must", sobot_receptionistId_must.isChecked());
        DemoSPUtil.saveBooleanData(SobotStartSetActivity.this, "sobot_isArtificialIntelligence", sobot_isArtificialIntelligence.isChecked());
        DemoSPUtil.saveBooleanData(SobotStartSetActivity.this, "sobot_isUseVoice", sobot_isUseVoice.isChecked());
        DemoSPUtil.saveBooleanData(SobotStartSetActivity.this, "sobot_isShowSatisfaction", sobot_isShowSatisfaction.isChecked());
        DemoSPUtil.saveBooleanData(SobotStartSetActivity.this, "sobot_nike_param", sobot_nike_param.isChecked());
        DemoSPUtil.saveBooleanData(SobotStartSetActivity.this, "sobot_isShowNikename", sobot_isShowNikename.isChecked());
        DemoSPUtil.saveStringData(SobotStartSetActivity.this, "sobot_rg_initModeType", getInitModeType() + "");
        DemoSPUtil.saveBooleanData(SobotStartSetActivity.this, "sobot_isOpenNotification", sobot_isOpenNotification.isChecked());
        DemoSPUtil.saveBooleanData(SobotStartSetActivity.this, "sobot_evaluationCompletedExit_value", sobot_evaluationCompletedExit.isChecked());
        DemoSPUtil.saveStringData(SobotStartSetActivity.this,"sobot_title_value",sobot_tv_chat_index_show_text.getText().toString());
        DemoSPUtil.saveStringData(SobotStartSetActivity.this,"sobot_title_value_show_type",getTitleValue().getValue() + "");
        DemoSPUtil.saveStringData(SobotStartSetActivity.this,"sobot_show_history_ruler",sobot_show_history_ruler.getText().toString());
        DemoSPUtil.saveStringData(SobotStartSetActivity.this,"sobot_demo_skillname",sobot_demo_skillname.getText().toString());
        DemoSPUtil.saveStringData(SobotStartSetActivity.this,"sobot_demo_skillid",sobot_demo_skillid.getText().toString());
        DemoSPUtil.saveStringData(SobotStartSetActivity.this,"sobot_transferKeyWord",sobot_transferKeyWord.getText().toString());
        DemoSPUtil.saveStringData(SobotStartSetActivity.this,"sobot_isArtificialIntelligence_num_value",sobot_isArtificialIntelligence_num.getText().toString());
    }

    private void getSobotStartSet() {
        String sobot_value_appkey = DemoSPUtil.getStringData(SobotStartSetActivity.this, "sobot_appkey", "");
        if (!TextUtils.isEmpty(sobot_value_appkey)) {
            sobot_appkey.setText(sobot_value_appkey);
        }
        String sobot_value_partnerId = DemoSPUtil.getStringData(SobotStartSetActivity.this, "sobot_partnerId", "");
        if (!TextUtils.isEmpty(sobot_value_partnerId)) {
            sobot_partnerId.setText(sobot_value_partnerId);
        }
        String sobot_value_receptionistId = DemoSPUtil.getStringData(SobotStartSetActivity.this, "sobot_receptionistId", "");
        if (!TextUtils.isEmpty(sobot_value_receptionistId)) {
            sobot_receptionistId.setText(sobot_value_receptionistId);
        }
        String sobot_value_robot_code = DemoSPUtil.getStringData(SobotStartSetActivity.this, "sobot_demo_robot_code", "");
        if (!TextUtils.isEmpty(sobot_value_robot_code)) {
            sobot_robot_code.setText(sobot_value_robot_code);
        }
        String sobot_demo_skillname_value = DemoSPUtil.getStringData(SobotStartSetActivity.this, "sobot_demo_skillname", "");
        if (!TextUtils.isEmpty(sobot_demo_skillname_value)) {
            sobot_demo_skillname.setText(sobot_demo_skillname_value);
        }
        String sobot_demo_skillid_value = DemoSPUtil.getStringData(SobotStartSetActivity.this, "sobot_demo_skillid", "");
        if (!TextUtils.isEmpty(sobot_demo_skillid_value)) {
            sobot_demo_skillid.setText(sobot_demo_skillid_value);
        }
        String sobot_isArtificialIntelligence_num_value = DemoSPUtil.getStringData(SobotStartSetActivity.this, "sobot_isArtificialIntelligence_num_value", "3");
        if (!TextUtils.isEmpty(sobot_isArtificialIntelligence_num_value)) {
            sobot_isArtificialIntelligence_num.setText(sobot_isArtificialIntelligence_num_value);
        }
        String sobot_transferKeyWord_value = DemoSPUtil.getStringData(SobotStartSetActivity.this, "sobot_transferKeyWord", "");
        if (!TextUtils.isEmpty(sobot_transferKeyWord_value)) {
            sobot_transferKeyWord.setText(sobot_transferKeyWord_value);
        }

        boolean sobot_bool_receptionistId_must = DemoSPUtil.getBooleanData(SobotStartSetActivity.this, "sobot_receptionistId_must", false);
        sobot_receptionistId_must.setChecked(sobot_bool_receptionistId_must);
        boolean sobot_bool_isArtificialIntelligence = DemoSPUtil.getBooleanData(SobotStartSetActivity.this, "sobot_isArtificialIntelligence", false);
        sobot_isArtificialIntelligence.setChecked(sobot_bool_isArtificialIntelligence);
        boolean sobot_bool_isUseVoice = DemoSPUtil.getBooleanData(SobotStartSetActivity.this, "sobot_isUseVoice", false);
        sobot_isUseVoice.setChecked(sobot_bool_isUseVoice);
        boolean sobot_bool_isShowSatisfaction = DemoSPUtil.getBooleanData(SobotStartSetActivity.this, "sobot_isShowSatisfaction", false);
        sobot_isShowSatisfaction.setChecked(sobot_bool_isShowSatisfaction);
        boolean sobot_bool_isOpenNotification = DemoSPUtil.getBooleanData(SobotStartSetActivity.this, "sobot_nike_param", false);
        sobot_nike_param.setChecked(sobot_bool_isOpenNotification);
        boolean sobot_bool_nike_param = DemoSPUtil.getBooleanData(SobotStartSetActivity.this, "sobot_isShowNikename", false);
        sobot_isShowNikename.setChecked(sobot_bool_nike_param);
        sobot_bool_rg_initModeType = DemoSPUtil.getStringData(SobotStartSetActivity.this, "sobot_rg_initModeType", sobot_bool_rg_initModeType + "");
        setRadioBtnCheckedType(sobot_bool_rg_initModeType);
        boolean sobot_bool_isShowPhone = DemoSPUtil.getBooleanData(SobotStartSetActivity.this, "sobot_isOpenNotification", sobot_isOpenNotification.isChecked());
        sobot_isOpenNotification.setChecked(sobot_bool_isShowPhone);
        boolean sobot_evaluationCompletedExit_value = DemoSPUtil.getBooleanData(SobotStartSetActivity.this, "sobot_evaluationCompletedExit_value", sobot_evaluationCompletedExit.isChecked());
        sobot_evaluationCompletedExit.setChecked(sobot_evaluationCompletedExit_value);
        sobot_title_value_show_type = DemoSPUtil.getStringData(SobotStartSetActivity.this,"sobot_title_value_show_type",sobot_title_value_show_type);
        sobot_title_value = DemoSPUtil.getStringData(SobotStartSetActivity.this,"sobot_title_value","");
        setRadioBtnCheckedTitleVale(sobot_title_value_show_type);
        sobot_show_history = DemoSPUtil.getStringData(SobotStartSetActivity.this,"sobot_show_history_ruler",sobot_show_history);
        if (!TextUtils.isEmpty(sobot_show_history) && !"0".equals(sobot_show_history)){
            sobot_show_history_ruler.setText(sobot_show_history);
        } else {
            sobot_show_history_ruler.setHint("历史记录显示时间");
        }
    }

    private int getInitModeType(){
        int resutlt = -1;
        int id = sobot_rg_initModeType.getCheckedRadioButtonId();
        switch (id){
            case R.id.rg_initModeType_noSet:
                resutlt = -1;
                break;
            case R.id.rg_initModeType_only_robot:
                resutlt = ZhiChiConstant.type_robot_only;
                break;
            case R.id.rg_initModeType_only_customer:
                resutlt = ZhiChiConstant.type_custom_only;
                break;
            case R.id.rg_initModeType_robot_first:
                resutlt = ZhiChiConstant.type_robot_first;
                break;
            case R.id.rg_initModeType_custom_first:
                resutlt = ZhiChiConstant.type_custom_first;
                break;
        }
        return resutlt;
    }

    private void setRadioBtnCheckedType(String type){
        switch (type){
            case "1":
                rg_initModeType_only_robot.setChecked(true);
                break;
            case "2":
                rg_initModeType_only_customer.setChecked(true);
                break;
            case "3":
                rg_initModeType_robot_first.setChecked(true);
                break;
            case "4":
                rg_initModeType_custom_first.setChecked(true);
                break;
            default:
                rg_initModeType_noSet.setChecked(true);
                break;
        }
    }

    private SobotChatTitleDisplayMode getTitleValue(){
        SobotChatTitleDisplayMode result = SobotChatTitleDisplayMode.Default;
        int id = sobot_rg_title_value_show.getCheckedRadioButtonId();
        switch (id){
            case R.id.sobot_show_custom_nike://显示客服昵称
                result = SobotChatTitleDisplayMode.Default;
                break;
            case R.id.sobot_show_fixed_text://显示固定文案
                result = SobotChatTitleDisplayMode.ShowFixedText;
                break;
            case R.id.sobot_show_company_name://显示公司名称
                result = SobotChatTitleDisplayMode.ShowCompanyName;
                break;
        }
        return result;
    }

    private void setRadioBtnCheckedTitleVale(String type){
        switch (type){
            case "0":
                sobot_tv_chat_index_show_text.setText("");
                sobot_show_custom_nike.setChecked(true);
                break;
            case "1":
                if (!TextUtils.isEmpty(sobot_title_value)){
                    sobot_tv_chat_index_show_text.setText(sobot_title_value);
                }
                sobot_show_fixed_text.setChecked(true);
                break;
            case "2":
                sobot_tv_chat_index_show_text.setText("");
                sobot_show_company_name.setChecked(true);
                break;
            default:
                sobot_tv_chat_index_show_text.setText("");
                break;
        }
    }
}