package com.sobot.mall.demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.sobot.chat.SobotApi;
import com.sobot.chat.api.enumtype.SobotChatTitleDisplayMode;
import com.sobot.chat.api.model.ConsultingContent;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.utils.LogUtils;
import com.sobot.chat.utils.ToastUtil;
import com.sobot.chat.utils.ZhiChiConstant;

import java.util.HashMap;
import java.util.Map;

public class SobotDemoActivity extends Activity implements View.OnClickListener {

    private Button personSet;//用户资料设置按钮
    private Button productSet;//资讯信息设置按钮
    private Button startSet;//启动参数设置按钮
    private Button startSDK;//启动SDK按钮
    private Button sobot_btn_open_leave_msg;//开启离线消息
    private Button sobot_btn_close_conn;//断开连接
    private Button sobot_btn_exit_service;//退出客服
    private Button sobot_message_center;//消息中心
    private Button sobot_clear_message_center;//清除消息中心
    private MyReceiver receiver;//广播
    private Information info;
    private boolean sobot_isOpenNotification = false;//是否打开推送
    private boolean sobot_evaluationCompletedExit = false;//评价完是否结束会话
    private String sobot_title_vlaue = "";//如果头部显示自定义文案，文案内容
    private long sobot_show_history_ruler = 0;//显示多少分钟内的历史记录  10分钟 -24小时
    private int enumType = 0;//0 默认,  1  自定义,  2  公司name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sobot_demo_activity);
        info = new Information();
        LogUtils.isDebug = true;
        regReceiver();
        initSobotView();
    }

    private void initSobotView() {
        personSet = (Button) findViewById(R.id.sobot_demo_person_set);
        productSet = (Button) findViewById(R.id.sobot_demo_product_set);
        startSet = (Button) findViewById(R.id.sobot_demo_start_set);
        startSDK = (Button) findViewById(R.id.sobot_demo_starting);

        sobot_btn_open_leave_msg = (Button) findViewById(R.id.sobot_btn_open_leave_msg);
        sobot_btn_open_leave_msg.setTag(false);
        sobot_btn_close_conn = (Button) findViewById(R.id.sobot_btn_close_conn);
        sobot_btn_exit_service = (Button) findViewById(R.id.sobot_btn_exit_service);
        sobot_message_center = (Button) findViewById(R.id.sobot_message_center);
        sobot_clear_message_center = (Button) findViewById(R.id.sobot_clear_message_center);
        setSobotClickListener();
    }

    private void setSobotClickListener() {
        //用户信息设置
        personSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SobotDemoActivity.this, SobotPersonSetActivity.class);
                startActivity(intent);
            }
        });

        //资讯信息设置
        productSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SobotDemoActivity.this, SobotConsultationSetActivity.class);
                startActivity(intent);
            }
        });

        //启动参数设置
        startSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SobotDemoActivity.this, SobotStartSetActivity.class);
                startActivity(intent);
            }
        });

        //启动SDK
        startSDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置平台id
				String platformUnion = "";
				if (!TextUtils.isEmpty(platformUnion)){
					SobotApi.initPlatformUnion(getApplicationContext(), platformUnion);
				} else {
					ToastUtil.showToast(SobotDemoActivity.this, "平台id 不能为空 ！！！");
				}
                
                //用户信息设置开始d
                DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_key1", "");
                DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_key2", "");
                info.setTel(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_tel", ""));
                info.setRealname(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_realname", ""));
                info.setEmail(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_email", ""));
                info.setUname(DemoSPUtil.getStringData(SobotDemoActivity.this, "person_uName", ""));
                info.setRemark(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_reMark", ""));
                info.setFace(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_face", ""));
                info.setQq(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_qq", ""));
                info.setVisitTitle(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_visitTitle", ""));
                info.setVisitUrl(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_visitUrl", ""));
                Map<String, String> customInfo = new HashMap<>();
                customInfo.put("sobot_key1",DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_key1", ""));
                customInfo.put("sobot_key2",DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_key2", ""));
                info.setCustomInfo(customInfo);
                //用户信息设置结束

                //咨询信息设置开始
                boolean isShow = DemoSPUtil.getBooleanData(SobotDemoActivity.this, "sobot_goods_is_show_info", false);
                if (isShow) {
                    ConsultingContent consult = new ConsultingContent();
                    consult.setSobotGoodsTitle(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_goods_title_value", ""));
                    consult.setSobotGoodsDescribe(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_goods_describe_value", ""));
                    consult.setSobotGoodsLable(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_goods_lable_value", ""));
                    consult.setSobotGoodsImgUrl(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_goods_imgUrl_value", ""));
                    consult.setSobotGoodsFromUrl(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_goods_fromUrl_value", ""));
                    info.setConsultingContent(consult);
                } else {
                    info.setConsultingContent(null);
                }
                //咨询信息设置结束

                //启动参数设置开始
                info.setUid(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_partnerId", ""));
                info.setReceptionistId(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_receptionistId", ""));
                info.setRobotCode(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_demo_robot_code", ""));
                info.setTranReceptionistFlag(DemoSPUtil.getBooleanData(SobotDemoActivity.this, "sobot_receptionistId_must", false) ? 1 : 0);
                if (DemoSPUtil.getBooleanData(SobotDemoActivity.this, "sobot_isArtificialIntelligence", false)) {
                    info.setArtificialIntelligence(true);
                    if (TextUtils.isEmpty(DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_isArtificialIntelligence_num_value","")) && "".equals(DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_isArtificialIntelligence_num_value",""))) {
                        info.setArtificialIntelligenceNum(3);
                    } else {
                        info.setArtificialIntelligenceNum(Integer.parseInt(DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_isArtificialIntelligence_num_value","")));
                    }
                } else {
                    info.setArtificialIntelligence(false);
                }
                info.setUseVoice(DemoSPUtil.getBooleanData(SobotDemoActivity.this, "sobot_isUseVoice", false));
                info.setShowSatisfaction(DemoSPUtil.getBooleanData(SobotDemoActivity.this, "sobot_isShowSatisfaction", false));
                if (!TextUtils.isEmpty(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_rg_initModeType", ""))){
                    info.setInitModeType(Integer.parseInt(DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_rg_initModeType", "")));
                }
                info.setSkillSetName(DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_demo_skillname", ""));
                info.setSkillSetId(DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_demo_skillid", ""));
                sobot_isOpenNotification = DemoSPUtil.getBooleanData(SobotDemoActivity.this, "sobot_isOpenNotification", false);
                sobot_evaluationCompletedExit = DemoSPUtil.getBooleanData(SobotDemoActivity.this, "sobot_evaluationCompletedExit_value", false);
                sobot_title_vlaue = DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_title_value", "");
                if (!TextUtils.isEmpty(DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_title_value_show_type", ""))){
                    enumType = Integer.parseInt(DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_title_value_show_type", ""));
                }
                if (!TextUtils.isEmpty(DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_show_history_ruler",""))){
                    sobot_show_history_ruler = Long.parseLong(DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_show_history_ruler",""));
                }
                if (!TextUtils.isEmpty(DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_transferKeyWord",""))){
                    info.setTransferKeyWord(DemoSPUtil.getStringData(SobotDemoActivity.this,"sobot_transferKeyWord",""));
                }
                //启动参数设置结束

                String appkey = DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_appkey", "");
                if (!TextUtils.isEmpty(appkey)) {
                    info.setAppkey(appkey);

                    //设置标题显示模式
                    SobotApi.setChatTitleDisplayMode(getApplicationContext(),
                            SobotChatTitleDisplayMode.values()[enumType], sobot_title_vlaue);
                    //设置是否开启消息提醒
                    SobotApi.setNotificationFlag(getApplicationContext(), sobot_isOpenNotification
                            , R.drawable.sobot_logo_small_icon, R.drawable.sobot_logo_icon);
                    SobotApi.hideHistoryMsg(getApplicationContext(), sobot_show_history_ruler);
                    SobotApi.setEvaluationCompletedExit(getApplicationContext(),sobot_evaluationCompletedExit);
                    SobotApi.startSobotChat(SobotDemoActivity.this, info);
                    //设置超链接的监听
                        /*SobotApi.setHyperlinkListener(new HyperlinkListener() {
							@Override
							public void onUrlClick(String url) {
								ToastUtil.showToast(getApplicationContext(),"点击了超链接，url="+url);
							}

							@Override
							public void onEmailClick(String email) {
								ToastUtil.showToast(getApplicationContext(),"点击了邮件，email="+email);
							}

							@Override
							public void onPhoneClick(String phone) {
								ToastUtil.showToast(getApplicationContext(),"点击了电话，phone="+phone);
							}
						});*/
                } else {
                    ToastUtil.showToast(SobotDemoActivity.this, "AppKey 不能为空 ！！！");
                }
            }
        });

        sobot_btn_open_leave_msg.setOnClickListener(this);
        sobot_btn_close_conn.setOnClickListener(this);
        sobot_btn_exit_service.setOnClickListener(this);
        sobot_message_center.setOnClickListener(this);
        sobot_clear_message_center.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    //设置广播获取新收到的信息和未读消息数
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int noReadNum = intent.getIntExtra("noReadCount", 0);
            String content = intent.getStringExtra("content");
            //新消息内容
            LogUtils.i("新消息内容:" + content);
        }
    }

    private void regReceiver() {
        //注册广播获取新收到的信息和未读消息数
        if (receiver == null) {
            receiver = new MyReceiver();
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(ZhiChiConstant.sobot_unreadCountBrocast);
        registerReceiver(receiver, filter);
    }

    @Override
    public void onClick(View v) {
        // 开启通道接受离线消息，开启后会将消息以广播的形式发送过来
        // 如果无需此功能那么可以不做调用
        if (v == sobot_btn_open_leave_msg) {
            boolean isConn = (Boolean) sobot_btn_open_leave_msg.getTag();
            // 开启通道接受离线消息，开启后会将消息以广播的形式发送过来
            // 如果无需此功能那么可以不做调用
            if (isConn) {
                // 关闭通道,清除当前会话缓存
                sobot_btn_open_leave_msg.setText("开启离线消息");
                SobotApi.disSobotChannel(getApplicationContext());
            } else {
                sobot_btn_open_leave_msg.setText("关闭离线消息");
                // 开启通道接收离线消息，开启后会将消息以广播的形式发送过来
                // 如果无需此功能那么可以不做调用
                SobotApi.initSobotChannel(getApplicationContext(), DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_partnerId", ""));
            }
            sobot_btn_open_leave_msg.setTag(!isConn);
        }

        if (v == sobot_btn_close_conn) {
            //断开与服务器的连接
            SobotApi.disSobotChannel(getApplicationContext());
        }

        if (v == sobot_btn_exit_service) {
            // 退出智齿客服、断开与服务器的连接,
            SobotApi.exitSobotChat(getApplicationContext());
        }

        if (v == sobot_message_center) {
            SobotApi.startMsgCenter(getApplicationContext(), DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_partnerId", ""));
        }

        if (v == sobot_clear_message_center) {
            SobotApi.clearMsgCenterList(getApplicationContext(), DemoSPUtil.getStringData(SobotDemoActivity.this, "sobot_partnerId", ""));
            ToastUtil.showToast(getApplicationContext(),"删除成功");
        }
    }
}