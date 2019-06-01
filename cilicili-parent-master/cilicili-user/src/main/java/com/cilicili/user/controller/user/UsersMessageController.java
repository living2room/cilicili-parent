package com.cilicili.user.controller.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cilicili.user.domain.user.UsersMessage;
import com.cilicili.user.service.impl.user.UsersMessageServiceImpl;

@Controller
@RequestMapping("/message")
public class UsersMessageController {

	@Resource
	private UsersMessageServiceImpl usersMessageServiceImpl;

	@RequestMapping("/toUsersMessage")
	public String toUsersMessage() {

		return "redirect:/message/usersMessage";
	}
	
	/* 图片编辑器的测试 */
	@RequestMapping("/toindex")
	public String toindex() {
		
		return "upload-cropped-image-to-server";
	}

	/*
	 * 通过昵称查它的所有基本信息(usersMessage)
	 */
	@RequestMapping("/usersMessage")
	public String usersMessage(@RequestParam("userName") String userName, Model model) {

		try {
			UsersMessage usersMessage = this.usersMessageServiceImpl.findByUserName(userName);
            System.out.println("haha"+usersMessage);
			int age = usersMessage.getAge();
			String home = usersMessage.getHome();
			String idCard = usersMessage.getIdCard();
			String imgUrl = usersMessage.getImgUrl();
			String phone = usersMessage.getPhone();
			int qq = usersMessage.getQq();
			int sex = usersMessage.getSex();

			Date createTime = usersMessage.getCreateTime();
			Date lastLoginTime = usersMessage.getLastLoginTime();
  
			model.addAttribute("age", age);
			model.addAttribute("home", home);
			model.addAttribute("idCard", idCard);
			model.addAttribute("imgUrl", imgUrl);
			model.addAttribute("phone", phone);
			model.addAttribute("qq", qq);
			model.addAttribute("sex", sex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "usersMessage";
	}

	// 更改用户基本信息
	@RequestMapping("/updateUsersMessage")
	public String updateUsersMessage(UsersMessage usersMessage, Model model) {

		int count = this.usersMessageServiceImpl.upUsersMessage(usersMessage);
		System.out.println("12121111212121212188" + count);
		int age = usersMessage.getAge();
		String home = usersMessage.getHome();
		String idCard = usersMessage.getIdCard();
		String imgUrl = usersMessage.getImgUrl();
		String phone = usersMessage.getPhone();
		int qq = usersMessage.getQq();
		int sex = usersMessage.getSex();

		System.out.println("0:"+age);
		System.out.println("1:"+home);
		System.out.println("3:"+phone);
		/*
		 * Date createTime = usersMessage.getCreateTime(); Date lastLoginTime =
		 * usersMessage.getLastLoginTime();
		 */

		// model.addAttribute(attributeName, attributeValue);
		return "usersMessage";
	}
	  
	@RequestMapping("/upLoad")
	//@RequestParam(required = true)
	 public Map<String , Object> upLoad(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		//UsersMessage usersMessage,
		 if(file.isEmpty()) {
			 System.out.println("哈哈哈啊哈");
			 //return "文件为空";
		 }
		     //获取文件名
			 String fileName = file.getOriginalFilename();
			 //文件后缀名
			 String  suffilName= fileName.substring(fileName.lastIndexOf("."));
			 //文件上传路径
			 String filePath = "C:\\Users\\Administrator\\Desktop\\5.23整sql\\cilicili-parent-master\\cilicili-web\\src\\main\\resources\\static\\images";
			// C:\Users\Administrator\Desktop\5.23整sql\cilicili-parent-master\cilicili-web\src\main\resources\static\images
			 //使用随机字符作为文件上传名称
			String fileName1 = (UUID.randomUUID()+"").replace("-","")+suffilName;
			 //上传后文件的全名
			
			
		 String Url = filePath+fileName1; 
		 //File dest = new File(filePath+fileName1);
		/* System.out.println("路径"+Url); */
		 
			 
			 //找到服务器的真实名称
			 String real = request.getServletContext().getRealPath("/static/images/");
			 //所以上传文件的完整路径
			 String realFileName = real+fileName1;
			 System.out.println("服务器的路径"+realFileName);
			 File dest = new File(realFileName);
			 
			 try {
				file.transferTo(dest);
				IOUtils.copy(new FileInputStream(new File(realFileName)),new FileOutputStream(new File(filePath+fileName1)));
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				 
			 
			 //检测目录是否存在
			 if(!dest.getParentFile().exists()) {
				 dest.getParentFile().mkdirs();
			 }
			 Map<String,Object> map = new HashMap<>();
			 try {
				 file.transferTo(dest);
				 //往数据库加路径 
				 //int count = this.usersMessageServiceImpl.upUsersMessage(usersMessage);
				 //return "上传成功";
				 map.put("code", 1);
				 map.put("imgPath", Url);
				 return map;
			 }catch (Exception e) {
				e.printStackTrace();
			}  
			 map.put("Url", Url);
			 
			 //return "上传失败";
			 return map;
			 //return userService.updUserProfile(profile);
		 
	 }
}
