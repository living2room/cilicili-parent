package com.cilicili.user.controller.user;

/*
 * 人脸识别
 */
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.face.AipFace;
import com.cilicili.common.dto.FacePageBean;
import com.cilicili.common.dto.FacePageResponse;
import com.cilicili.common.dto.response.FaceSerachResponse;
import com.cilicili.common.utils.FactoryUtil;

/**
 * 人脸照片注册方法
 * 
 *
 */
@Controller
@RequestMapping("/facemanager")
public class FaceManagerController {
	// 人脸模块对象
	AipFace aipFace = FactoryUtil.getAipFace();
	private static Logger log = LoggerFactory.getLogger(FaceManagerController.class);

	/**
	 * 人脸注册
	 * 
	 * @param facePageBean 请求的参数对象
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public String addFace(FacePageBean facePageBean, HttpServletRequest request, HttpServletResponse response) {

		log.info("发送过来的参数{}", JSONObject.toJSONString(facePageBean));
		FacePageResponse facePageResponse = new FacePageResponse();

		if (facePageBean.getUser_info().equals("") || null == facePageBean.getUser_info()) {

			facePageResponse.setError_code("100");
			facePageResponse.setError_msg("用户名称为空 请填写后重试");

			return JSON.toJSONString(facePageResponse);

		} else {

			String groupId = "cilicili_user";// 记得替换成自己的或通过页面传递用户组id（由数字、字母、下划线组成），长度限制128B

			String userId = UUID.randomUUID().toString().replace("-", "").toUpperCase();// 用户id（由数字、字母、下划线组成），长度限制128B

			HashMap<String, String> options = new HashMap<String, String>();

			// 用户的资料信息
			options.put("user_info",facePageBean.getUser_info());

			// 活体检测，级别低到高：LOW，NORMAL，HIGH
			options.put("liveness_control","NORMAL");

			/*
			 * 人脸注册接口 Parameters:image -
			 * 图片信息(**总数据大小应小于10M**)，图片上传方式根据image_type来判断imageType - 图片类型
			 * **BASE64**:图片的base64值，base64编码后的图片数据，需urlencode，编码后的图片大小不超过2M；**URL**:图片的
			 * URL地址( 可能由于网络等原因导致下载图片时间过长)**；FACE_TOKEN**:
			 * 人脸图片的唯一标识，调用人脸检测接口时，会为每个人脸图片赋予一个唯一的FACE_TOKEN，
			 * 同一张图片多次检测得到的FACE_TOKEN是同一个groupId - 用户组id（由数字、字母、下划线组成），长度限制128BuserId -
			 * 用户id（由数字、字母、下划线组成），长度限制128Boptions - 可选参数对象，key: value都为string类型 options -
			 * options列表: user_info 用户资料，长度限制256Bquality_control 图片质量控制 **NONE**: 不进行控制
			 * **LOW**:较低的质量要求 **NORMAL**: 一般的质量要求 **HIGH**: 较高的质量要求 **默认
			 * NONE**liveness_control 活体检测控制 **NONE**: 不进行控制 **LOW**:较低的活体要求(高通过率 低攻击拒绝率)
			 * **NORMAL**: 一般的活体要求(平衡的攻击拒绝率, 通过率) **HIGH**: 较高的活体要求(高攻击拒绝率 低通过率) **默认NONE
			 */
			org.json.JSONObject resultObject = aipFace.addUser(facePageBean.getImgdata(), "BASE64", groupId, userId,
					options);
			
			log.info("注册返回的数据{}", resultObject.toString(2));

			return resultObject.toString();
		}
	}

	/**
	 * 人脸搜索
	 * 登录
	 * @param facePageBean 请求的参数对象
	 * @param request  
	 * @param response
	 * @return
	 */
	@PostMapping("/search")
	@ResponseBody
	public FacePageResponse searchFace(FacePageBean facePageBean,HttpServletRequest request, HttpServletResponse response){
		
		FacePageResponse facePageResponse = new FacePageResponse();
		log.info("发送过来的参数{}",JSONObject.toJSONString(facePageBean));
		
		String groupIdList = "cilicili_user";//用户组id（由数字、字母、下划线组成），长度限制128B
		
		
		HashMap<String, String> options = new HashMap<String, String>();
		
		
		
		//用户的资料信息
		//options.put("user_id","晖晖");
		
		//活体检测，级别低到高：LOW，NORMAL，HIGH
		options.put("liveness_control","NORMAL");
		
		/*
		 * 人脸搜索接口 Parameters:image -
		 * 图片信息(**总数据大小应小于10M**)，图片上传方式根据image_type来判断imageType - 图片类型
		 * **BASE64**:图片的base64值， base64编码后的图片数据，需urlencode，编码后的图片大小不超过2M；**URL**:图片的
		 * URL地址( 可能由于网络等原因导致下载图片时间过长)**；FACE_TOKEN**: 人脸图片
		 * 的唯一标识，调用人脸检测接口时，会为每个人脸图片赋予一个唯一的FACE_TOKEN，
		 * 同一张图片多次检测得到的FACE_TOKEN是同一个groupIdList - 从指定的group中进行查找 用逗号分隔，**上限20个options -
		 * 可选参数对象，key: value都为string类型 options - options列表: quality_control 图片质量控制
		 * **NONE**: 不 进行控制 **LOW**:较低的质量要求 **NORMAL**: 一般的质量要求 **HIGH**: 较高的质量要求 **默认
		 * NONE**liveness_control 活体检测控制 **NONE**: 不进 行控制 **LOW**:较低的活体要求(高通过率 低攻击拒绝率)
		 * **NORMAL**: 一般的活体要求(平衡的攻击拒绝率, 通过率) **HIGH**: 较高的活体要求(高攻击拒绝率 低通过率) **默
		 * 认NONE**   user_id 当需要对特定用户进行比对时，指定user_id进行比对。即人脸认证功能。 max_user_num
		 * 查找后返回的用户数量。返回相似度最高的几个用户，默认为1，最多返回20个。
		 */
		org.json.JSONObject resultObject = aipFace.search(facePageBean.getImgdata(), "BASE64", groupIdList, options);
		
		
		
		
		//使用fastjson处理返回的内容 直接用javabean接收 方便取值
		FaceSerachResponse faceSerachResponse = JSON.parseObject(resultObject.toString(), FaceSerachResponse.class);
		
		
		
		
		if("0".equals(faceSerachResponse.getError_code())&&"SUCCESS".equals(faceSerachResponse.getError_msg())){
			
			if(faceSerachResponse.getResult().getUser_list().get(0).getScore() > 80d){
				
				//拿到对应的账号，查密码，login()
				//faceSerachResponse.getResult().getUser_list().get(0).getUser_info();
				
				facePageResponse.setError_code(faceSerachResponse.getError_code());
				facePageResponse.setError_msg(faceSerachResponse.getError_msg());
				facePageResponse.setUser_info(faceSerachResponse.getResult().getUser_list().get(0).getUser_info());
				System.out.println("yes啊");
			}else{
				
			 	
				//走了这里面去了
				facePageResponse.setError_code("555");
				facePageResponse.setError_msg("人脸搜索失败，请重试或请先注册");
				System.out.println("人脸搜索失败，请重试或请先注册啊");
			}
		}else{
			
			facePageResponse.setError_code("500");
			facePageResponse.setError_msg(faceSerachResponse.getError_msg());
			System.out.println("50000000000啊");
		}
		
		log.info("搜索返回的数据{}",resultObject.toString(2));
		return facePageResponse;
	}
}
