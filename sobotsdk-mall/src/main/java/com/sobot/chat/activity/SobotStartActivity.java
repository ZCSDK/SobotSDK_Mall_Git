package com.sobot.chat.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sobot.chat.R;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.enumtype.CustomerState;
import com.sobot.chat.api.enumtype.SobotChatTitleDisplayMode;
import com.sobot.chat.api.model.ConsultingContent;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.listener.HyperlinkListener;
import com.sobot.chat.listener.SobotLeaveMsgListener;
import com.sobot.chat.listener.SobotViewListener;
import com.sobot.chat.utils.ChatUtils;
import com.sobot.chat.utils.LogUtils;
import com.sobot.chat.utils.ResourceUtils;
import com.sobot.chat.utils.SobotOption;
import com.sobot.chat.utils.ToastUtil;
import com.sobot.chat.utils.ZhiChiConstant;

import java.util.HashMap;
import java.util.Map;

public class SobotStartActivity extends Activity implements OnClickListener{

	private EditText et_uid, et_appkey,et_receptionistId,et_sobotCode;
	private TextView unread_msg_num;
	private Button startOfflingMsg;
	private Button exitSobot;
	private Button disSobotChannel;
	private CheckBox isArtificialIntelligence,isUseVoice,isShowSatisfaction,nike_param,
			isShowNikename,showNotification,sobot_receptionistId_must,sobot_evaluationCompletedExit,showRobotVioce;
	private RadioGroup rg_initModeType;
	private MyReceiver receiver;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.isDebug = true;
		setContentView(ResourceUtils.getIdByName(this, "layout",
				"sobot_start_activity"));

		et_appkey = (EditText) findViewById(ResourceUtils.getIdByName(this,
				"id", "sobot_appkey"));
		et_uid = (EditText) findViewById(ResourceUtils.getIdByName(this,
				"id", "sobot_partnerId"));
		sobot_receptionistId_must = (CheckBox) findViewById(ResourceUtils.getIdByName(this,
				"id", "sobot_receptionistId_must"));
		sobot_evaluationCompletedExit = (CheckBox) findViewById(ResourceUtils.getIdByName(this,
				"id", "sobot_evaluationCompletedExit"));
		et_receptionistId = (EditText) findViewById(ResourceUtils.getIdByName(this,
				"id", "sobot_receptionistId"));
		et_sobotCode = (EditText) findViewById(ResourceUtils.getIdByName(this,
				"id", "sobot_robot_code"));
		unread_msg_num = (TextView) findViewById(ResourceUtils.getIdByName(this,
				"id", "unread_msg_num"));
		startOfflingMsg = (Button) findViewById(ResourceUtils.getIdByName(this,
				"id", "sobot_btnStartOfflingMsg"));
		startOfflingMsg.setTag(false);

		exitSobot = (Button) findViewById(ResourceUtils.getIdByName(this,
				"id", "sobot_btnExitSobot"));
		disSobotChannel = (Button) findViewById(ResourceUtils.getIdByName(this,
				"id", "disSobotChannel"));
		isArtificialIntelligence = (CheckBox) findViewById(ResourceUtils.getIdByName(this,
				"id", "isArtificialIntelligence"));
		showRobotVioce = (CheckBox) findViewById(ResourceUtils.getIdByName(this,
				"id", "showRobotVioce"));
		isUseVoice = (CheckBox) findViewById(ResourceUtils.getIdByName(this,
				"id", "isUseVoice"));
		showNotification = (CheckBox) findViewById(ResourceUtils.getIdByName(this,
				"id", "showNotification"));
		isShowSatisfaction = (CheckBox) findViewById(ResourceUtils.getIdByName(this,
				"id", "isShowSatisfaction"));
		rg_initModeType = (RadioGroup)findViewById(ResourceUtils.getIdByName(this,"id",
				"rg_initModeType"));

		startOfflingMsg.setOnClickListener(this);
		exitSobot.setOnClickListener(this);
		disSobotChannel.setOnClickListener(this);

		findViewById(ResourceUtils.getIdByName(this, "id", "sobot_btnStart"))
				.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {
						unread_msg_num.setText("");
						Information info = new Information();
//                        SobotApi.initPlatformUnion(getApplicationContext(),"aaaa");
						info.setAppkey(et_appkey.getText().toString());/* 必填 */
//						info.setTitleImgId(R.drawable.sobot_delete_hismsg_normal);//设置头部为背景图片
						info.setColor("");/* 选填 */
						info.setUid(et_uid.getText().toString());/* 必填 */
						info.setUname("");/*用户昵称*/
						info.setRealname("");/* 用户姓名，选填 */
						info.setTel("13613440946");/* 用户电话，选填 */
						info.setEmail("122675799@qq.com");/* 用户邮箱，选填 */
						info.setFace("");/*自定义头像 http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg*/
						info.setQq("");/*用户QQ*/
						info.setRemark("这个人很勤奋，留下很多知识");/*用户备注*/
						info.setVisitTitle("");/*对话页标题*/
						info.setVisitUrl("");/*对话页路径*/
						info.setShowSatisfaction(isShowSatisfaction.isChecked());//关闭时是否弹出满意度评价。
						// 设置为关闭时，点击关闭是否弹出满意度评价。默认true，弹出，false不弹满意度。直接弹是否关闭的dialog
//						info.setArtificialIntelligence(true);
//						info.setArtificialIntelligenceNum(3);/* 未知问题或者向导问题出现 几次时，显示转人工按钮 */
//						info.setTransferKeyWord("转人工");//设置转人工关键字
						info.setSkillSetId("");/* 预设技能组编号，选填 */
						info.setSkillSetName("");/* 预设技能组名称 */
						info.setUseVoice(isUseVoice.isChecked());/*是否使用语音功能*/
						Map<String,String> customInfo = new HashMap<>();
						customInfo.put("技能1", "打麻将");
						info.setCustomInfo(customInfo);/* 自定义用户资料 */
						info.setInitModeType(getInitModeType());/* 客服模式控制  -1不控制 按照服务器后台设置的模式运行
						1仅机器人 2仅人工
						3机器人优先 4人工优先*/

						info.setRobotCode(et_sobotCode.getText().toString());
						if (!TextUtils.isEmpty(et_receptionistId.getText().toString())) {
							LogUtils.i("指定客服不为空");
							info.setReceptionistId(et_receptionistId.getText().toString());//指定客服id
						}

						if (sobot_receptionistId_must.isChecked()){
							info.setTranReceptionistFlag(1);//转接类型(0-可转入其他客服，1-必须转入指定客服)
						} else {
							info.setTranReceptionistFlag(0);
						}

						Map<String,String> customerFields = new HashMap<>();
						customerFields.put("weixin","55555");
						customerFields.put("weibo","66666");
						customerFields.put("sex","女");
						customerFields.put("birthday","2017-05-18");
						info.setCustomerFields(customerFields);

						//设置返回离线
						/*SobotOption.sobotViewListener = new SobotViewListener() {

							@Override
							public void onChatActClose(CustomerState customerState) {
								if(customerState == CustomerState.Queuing){
									SobotApi.disSobotChannel(getApplicationContext());
									ChatUtils.userLogout(getApplicationContext());
								}
							}
						};*/

						//设置跳转留言
/*
						SobotApi.setSobotLeaveMsgListener(new SobotLeaveMsgListener() {
							@Override
							public void onLeaveMsg() {
								ToastUtil.showToast(getApplicationContext(),"11111111111111111111");
							}
						});
*/

						ConsultingContent consultingContent = new ConsultingContent(); //咨询内容
						consultingContent.setSobotGoodsTitle("乐视超级电视 S50 Air 全配版50英寸2D智能LED黑色（Letv S50 Air）"); //咨询内容标题
						consultingContent.setSobotGoodsImgUrl("http://www.lishui.com/uploads/140301/1-14030111052Y17.jpg"); //咨询内容图片 选填
						consultingContent.setSobotGoodsFromUrl("https://www.baidu.com/admin/order/detail/302"); //发送内容
						consultingContent.setSobotGoodsDescribe("乐视超级电视 S50 Air 全配版50英寸2D智能LED黑色（Letv S50 Air）乐视超级电视 S50 Air 全配版50英寸2D智能LED黑色（Letv S50 Air）");
						consultingContent.setSobotGoodsLable("￥2150");
						info.setConsultingContent(consultingContent); //可以设置为null
						//设置超链接的监听
						SobotApi.setHyperlinkListener(new HyperlinkListener() {
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
						});

//                        SobotApi.setCustomAdminHelloWord(getApplicationContext(),"自定义客服欢迎语");//自定义客服欢迎语,默认为空 （如果传入，优先使用该字段）
//                        SobotApi.setCustomRobotHelloWord(getApplicationContext(),"自定义机器人欢迎语");//自定义机器人欢迎语,默认为空 （如果传入，优先使用该字段）
//                        SobotApi.setCustomUserTipWord(getApplicationContext(),"自定义用户超时提示语");//自定义用户超时提示语,默认为空 （如果传入，优先使用该字段）
//						   SobotApi.setCustomAdminTipWord(getApplicationContext(),"自定义客服超时提示语");//自定义客服超时提示语,默认为空 （如果传入，优先使用该字段）
//						   SobotApi.setCustomAdminNonelineTitle(getApplicationContext()," 自定义客服不在线的说辞");// 自定义客服不在线的说辞,默认为空 （如果传入，优先使用该字段）
//                        SobotApi.setCustomUserOutWord(getApplicationContext()," 自定义用户超时下线提示语");// 自定义用户超时下线提示语,默认为空 （如果传入，优先使用该字段）

						info.setUseRobotVoice(showRobotVioce.isChecked());
						//设置标题显示模式
						SobotApi.setChatTitleDisplayMode(getApplicationContext(),
								SobotChatTitleDisplayMode.Default,"");
						//设置隐藏几分钟之前的历史消息
						//time-查询时间(例:100-表示从现在起前100分钟的会话)  默认为0  表示不隐藏历史记录
						SobotApi.hideHistoryMsg(getApplicationContext(),0);
						//设置是否开启消息提醒
						SobotApi.setNotificationFlag(getApplicationContext(),showNotification
								.isChecked(),R.drawable.sobot_logo_small_icon,R.drawable.sobot_logo_icon);
						//配置用户提交人工满意度评价后释放会话
						SobotApi.setEvaluationCompletedExit(getApplicationContext(),sobot_evaluationCompletedExit.isChecked());
						SobotApi.startSobotChat(SobotStartActivity.this, info);
					}
				});
		//注册广播获取新收到的信息和未读消息数
		if (receiver == null) {
			receiver = new MyReceiver();
		}
		IntentFilter filter = new IntentFilter();
		filter.addAction(ZhiChiConstant.sobot_unreadCountBrocast);
		registerReceiver(receiver, filter);
	}

	private int getInitModeType(){
		int resutlt = -1;
		int id = rg_initModeType.getCheckedRadioButtonId();
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

	@Override
	public void onClick(View v) {
		if(v == startOfflingMsg){
			boolean isConn = (Boolean)startOfflingMsg.getTag();
			// 开启通道接受离线消息，开启后会将消息以广播的形式发送过来
			// 如果无需此功能那么可以不做调用
			if (isConn){
				// 关闭通道,清除当前会话缓存
				startOfflingMsg.setText("开启离线消息");
				SobotApi.disSobotChannel(getApplicationContext());
			} else {
				startOfflingMsg.setText("关闭离线消息");
				// 开启通道接收离线消息，开启后会将消息以广播的形式发送过来
				// 如果无需此功能那么可以不做调用
				SobotApi.initSobotChannel(getApplicationContext(),et_uid.getText().toString());
			}
			startOfflingMsg.setTag(!isConn);
		}

		if(v == exitSobot){
			// 退出智齿客服、断开与服务器的连接,
			SobotApi.exitSobotChat(getApplicationContext());
		}

		if(v == disSobotChannel){
			//断开与服务器的连接
			SobotApi.disSobotChannel(getApplicationContext());
		}
	}

	//设置广播获取新收到的信息和未读消息数
	class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int noReadNum = intent.getIntExtra("noReadCount", 0);
			String content = intent.getStringExtra("content");
			//未读消息数
			unread_msg_num.setText(noReadNum + "");
			//新消息内容
			LogUtils.i("新消息内容:"+content);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		//获取未读消息数并显示
		String unreadNum = SobotApi.getUnreadMsg(getApplicationContext(),et_uid.getText().toString()) > 0 ?
				SobotApi.getUnreadMsg(getApplicationContext(),et_uid.getText().toString()) + "":"";
		unread_msg_num.setText(unreadNum);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}
}