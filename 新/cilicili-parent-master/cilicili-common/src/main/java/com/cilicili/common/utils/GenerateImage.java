package com.cilicili.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder; 
public class GenerateImage {

	/**
	 * 转换url:data数据为正常图片
	 * @param imgStr
	 * @param imgName
	 * @param imgPath
	 * @return
	 * by yanqingwen
	 */
	public static boolean GenerateImage(String imgStr,String imgName,String imgPath){
		//对Base64字符串解码并生成图片
		if (imgStr == null){
	        	return false;
	        } //图像数据为空
	        BASE64Decoder decoder = new BASE64Decoder();
	        try{ 
	           	//Base64解码
	           	byte[] b = decoder.decodeBuffer(imgStr);
	           	for(int i=0;i<b.length;++i){ 
	               		if(b[i]<0){
	               			//调整异常数据b[i]+=256;
	               			}
	           	}
			File headPath = new File(imgPath);
			//获取文件夹路径 
			if(!headPath.exists()){
				//判断文件夹是否创建，没有创建则创建新文件夹 
				headPath.mkdirs(); 
			}
			//定义图片路径
			String imgFilePath = imgPath+"/"+imgName+".jpg";
			//新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
	       	}
	           	catch (Exception e){
	       		return false;
		}
	}

	
}

