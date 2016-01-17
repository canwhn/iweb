package net.canwhn.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.sword.wechat4j.WechatSupport;
import org.sword.wechat4j.response.ArticleResponse;
import org.sword.wechat4j.response.MusicResponse;
import org.sword.wechat4j.response.VideoResponse;

import com.alibaba.fastjson.JSON;

import net.canwhn.common.TulingJsonEntity;
import net.canwhn.common.WebUrl;
import net.canwhn.util.HttpClientUtil;
import net.canwhn.util.JdbcUtils;


/**
 * 微信服务桩
 * @author ChengNing
 * @date   2015年1月7日
 */
public class MyWechat extends WechatSupport {
	
	private static Logger logger = Logger.getLogger(MyWechat.class);

	public MyWechat(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 文本消息
	 */
	@Override
	protected void onText() {
		String content = super.wechatRequest.getContent().trim();
		
		if(content.equals("1")){
			responseText("页面跳转链接!<a href=\"http://isino.ngrok.cc/iweb/web/test.html\">点击预览</a>");
		}
		else if(content.equals("2")){
			responseText("测试信息是否可保存到数据库!<a href=\"http://isino.ngrok.cc/iweb/index.jsp\">信息收集</a>");
		}
		else if(content.equals("3")){
			responseNew("Canwhn's Studio", "welcome to canwhn's studio", "http://7fvkic.com1.z0.glb.clouddn.com/birdsofprey1.jpg", 
					"http://canwhn.sourceforge.net");
		}
		else{
			/*responseText("你好，你的输入为 " + content + "\n"
					+ "请按照如下操作输入:\n"
					+ "1 文本\n"
					+ "2 图文\n");*/
			String fromUserName = wechatRequest.getFromUserName();
			String msgId = wechatRequest.getMsgId();
			String msgType = wechatRequest.getMsgType();
			String createTime = wechatRequest.getCreateTime();
			logger.info(content);
			String response = HttpClientUtil.post(WebUrl.TURING_URL, content);
			TulingJsonEntity entity = JSON.parseObject(response, TulingJsonEntity.class);
			String code = entity.getCode();
			switch(code){
				case "100000":
					break;
				case "200000":
					break;	
				case "302000":
					break;			
				case "308000":
					break;
				case "40001":
					break;
				case "40002":
					break;	
				case "40003":
					break;	
				case "40004":
					break;	
				case "40005":
					break;
				case "40006":
					break;
				case "40007":
					break;
				default:
					break;
			}
			responseText(entity.getText());
			JdbcUtils jdbcUtils = new JdbcUtils();
			jdbcUtils.getConnection();

			/*******************增*********************/
			String sql = "insert into weixin_msg (content, fromUserName,msgId,msgType,createTime,response,time) values (?,?,?,?,?,?,?)";
			List<Object> params = new ArrayList<Object>();
			params.add(content);
			params.add(fromUserName);
			params.add(msgId);
			params.add(msgType);
			params.add(createTime);
			params.add(response);
			params.add(new Date());
			try {
				boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
				System.out.println(flag);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 图片消息
	 */
	@Override
	protected void onImage() {
		String picUrl = wechatRequest.getPicUrl();
		String MediaId = wechatRequest.getMediaId();
		String MsgId = wechatRequest.getMsgId();
		
		String result = "图片消息picUrl:" + picUrl + ", MediaId:" + MediaId + ", MsgId:" + MsgId;
		logger.info(result);
		responseText(result);
		//responseImage(mediaId);
	}

	/**
	 * 语音消息
	 */
	@Override
	protected void onVoice() {
		String Format = wechatRequest.getFormat();
		String MediaId = wechatRequest.getMediaId();//视频消息媒体id，可以调用多媒体文件下载接口拉取数据
		String MsgId = wechatRequest.getMsgId();
		
		String result = "语音消息Format:" + Format + ", MediaId:" + MediaId + ", MsgId:" + MsgId;
		logger.info(result);
		responseText(result);	
		//responseVoice(mediaId);
		
		//回复音乐消息
//		MusicResponse music = new MusicResponse();
//		music.setTitle(title);
//		music.setDescription(description);
//		music.setMusicURL(musicURL);
//		music.setHQMusicUrl(hQMusicUrl);
//		music.setThumbMediaId(thumbMediaId);
//		responseMusic(music);
//		
//		responseMusic(title, description, musicURL, hQMusicUrl, thumbMediaId);
	}

	/**
	 * 视频消息
	 */
	@Override
	protected void onVideo() {
		String ThumbMediaId = wechatRequest.getThumbMediaId();
		String MediaId = wechatRequest.getMediaId();//语音消息媒体id，可以调用多媒体文件下载接口拉取数据
		String MsgId = wechatRequest.getMsgId();
		
		String result = "视频消息ThumbMediaId:" + ThumbMediaId + ", MediaId:" + MediaId + ", MsgId:" + MsgId;
		logger.info(result);
		responseText(result);
		
		//回复视频消息
//		VideoResponse video = new VideoResponse();
//		video.setTitle(title);
//		video.setDescription(description);
//		video.setMediaId(mediaId);
//		responseVideo(video);
//		
//		responseVideo(mediaId, title, description);
	}
	
	/**
	 * 地理位置消息
	 */
	@Override
	protected void onLocation() {
		String Location_X = wechatRequest.getLocation_X();
		String Location_Y = wechatRequest.getLocation_Y();
		String Scale = wechatRequest.getScale();
		String Label = wechatRequest.getLabel();
		String MsgId = wechatRequest.getMsgId();
		
		String result = "地理位置消息Location_X:" + Location_X + ", Location_Y:" + Location_Y + 
				", Scale:" + Scale + ", Label:" + Label + 
				", MsgId:" + MsgId;
		logger.info(result);
		responseText(result);	
	}
	/**
	 * 链接消息
	 */
	@Override
	protected void onLink() {
		String Title = wechatRequest.getTitle();
		String Description = wechatRequest.getDescription();
		String Url = wechatRequest.getUrl();
		String MsgId = wechatRequest.getMsgId();
		
		String result = "链接消息Title:" + Title + ", Description:" + Description + 
				", Url:" + Url + 
				", MsgId:" + MsgId;
		logger.info(result);
		responseText(result);	
	}
	
	
	/**
	 * 未知消息类型，错误处理
	 */
	@Override
	protected void onUnknown() {
		String msgType = wechatRequest.getMsgType();
		
		String result = "未知消息msgType:" + msgType;
		logger.info(result);
		responseText(result);

	}

	/**
	 * 扫描二维码事件
	 */
	@Override
	protected void scan() {
		String FromUserName = wechatRequest.getFromUserName();
		String Ticket = wechatRequest.getTicket();
		
		String result = "扫描二维码事件FromUserName:" + FromUserName + ", Ticket:" + Ticket;
		logger.info(result);
		responseText(result);
	}

	/**
	 * 订阅事件
	 */
	@Override
	protected void subscribe() {
		String FromUserName = wechatRequest.getFromUserName();
		//用户未关注时扫描二维码事件,会多一个EventKey和Ticket节点
		String Ticket = wechatRequest.getTicket();

		String result = "订阅事件FromUserName:" + FromUserName;
		if(StringUtils.isNotBlank(Ticket)){
			result = "扫描带场景值二维码事件FromUserName:" + FromUserName + ", Ticket:" + Ticket;
		}
		logger.info(result);
		responseText(result);
	}
	
	/**
	 * 取消订阅事件
	 */
	@Override
	protected void unSubscribe() {
		String FromUserName = wechatRequest.getFromUserName();
		String result = "取消订阅事件FromUserName:" + FromUserName;
		logger.info(result);
		responseText(result);
	}
	
	/**
	 * 点击菜单跳转链接时的事件推送
	 */
	@Override
	protected void view() {
		String link = super.wechatRequest.getEventKey();
		logger.info("点击菜单跳转链接时的事件推送link:" + link);
		responseText("点击菜单跳转链接时的事件推送link:" + link);
	}

	/**
	 * 自定义菜单事件
	 */
	@Override
	protected void click() {
		String key = super.wechatRequest.getEventKey();
		logger.info("自定义菜单事件eventKey:" + key);
		responseText("自定义菜单事件eventKey:" + key);
	}
	
	/**
	 * 上报地理位置事件
	 */
	@Override
	protected void location() {
		String Latitude = wechatRequest.getLatitude();
		String Longitude = wechatRequest.getLongitude();
		String Precision = wechatRequest.getPrecision();
		String result = "上报地理位置事件Latitude:" + Latitude + ", Longitude:" + Longitude + ", Precision:" + Precision;
		logger.info(result);
		responseText(result);
	}
	
	/**
	 * 模板消息发送成功推送事件
	 */
	@Override
	protected void templateMsgCallback() {
		String MsgID = wechatRequest.getMsgId();
		String Status = wechatRequest.getStatus();
		String result = "模板消息发送成功推送事件MsgID:" + MsgID + ", Status:" + Status;
		logger.info(result);
	}
	/**
	 * 弹出地理位置选择器的事件
	 */
	@Override
	protected void locationSelect() {
		String Location_X = wechatRequest.getSendLocationInfo().getLocation_X();
		String Location_Y = wechatRequest.getSendLocationInfo().getLocation_Y();
		String Scale = wechatRequest.getSendLocationInfo().getScale();
		String Label = wechatRequest.getSendLocationInfo().getLabel();
		String Poiname = wechatRequest.getSendLocationInfo().getPoiname();
		String result = "弹出地理位置选择器的事件Location_X:" + Location_X + 
				", Location_Y:" + Location_Y+ 
				", Scale:" + Scale+ 
				", Label:" + Label+ 
				", Poiname:" + Poiname;
		logger.info(result);
		responseText(result);
	}
	/**
	 * 弹出拍照或者相册发图的事件
	 */
	@Override
	protected void picPhotoOrAlbum() {
		String Count = wechatRequest.getSendPicsInfo().getCount();
		String PicMd5Sum = "";
		if(StringUtils.isNotBlank(Count) && !Count.equals("0")){
			PicMd5Sum = wechatRequest.getSendPicsInfo().getItem().get(0).getPicMd5Sum();
		}
		String result = "弹出系统拍照发图的事件Count:" + Count + ", PicMd5Sum:" + PicMd5Sum;
		logger.info(result);
		responseText(result);
	}
	/**
	 * 弹出系统拍照发图的事件
	 */
	@Override
	protected void picSysPhoto() {
		String Count = wechatRequest.getSendPicsInfo().getCount();
		String result = "弹出系统拍照发图的事件Count:" + Count ;
		logger.info(result);
		responseText(result);
	}
	/**
	 * 弹出微信相册发图器的事件推送
	 */
	@Override
	protected void picWeixin() {
		String Count = wechatRequest.getSendPicsInfo().getCount();
		String result = "弹出系统拍照发图的事件Count:" + Count ;
		logger.info(result);
		responseText(result);
	}
	/**
	 * 扫码推事件
	 * 
	 */
	@Override
	protected void scanCodePush() {
		String ScanType = wechatRequest.getScanCodeInfo().getScanType();
		String ScanResult = wechatRequest.getScanCodeInfo().getScanResult();
		String result = "扫码推事件ScanType:" + ScanType + ", ScanResult:" + ScanResult;
		logger.info(result);
		responseText(result);
	}
	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件
	 */
	@Override
	protected void scanCodeWaitMsg() {
		String ScanType = wechatRequest.getScanCodeInfo().getScanType();
		String ScanResult = wechatRequest.getScanCodeInfo().getScanResult();
		String result = "扫码推事件ScanType:" + ScanType + ", ScanResult:" + ScanResult;
		logger.info(result);
		responseText(result);
	}

	@Override
	protected void onShortVideo() {
		String result = "onShortVideo:";
		logger.info(result);
		responseText(result);
	}

	@Override
	protected void kfCreateSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void kfCloseSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void kfSwitchSession() {
		// TODO Auto-generated method stub
		
	}
	



}
