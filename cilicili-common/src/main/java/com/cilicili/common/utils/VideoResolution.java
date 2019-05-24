/**
 * 
 */
package com.cilicili.common.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 关于视频解析的工具包
 * 获得视频大小、格式、时长等信息
 * 帧截屏等
 * @author 李明睿
 * 2019年5月18日
 */
public class VideoResolution {
//		依赖Jar包
//		<dependency>
//    <groupId>org.bytedeco</groupId>
//    <artifactId>javacv</artifactId>
//    <version>1.4.4</version>
//    <exclusions>
//        <exclusion>
//            <groupId>org.bytedeco</groupId>
//            <artifactId>javacpp</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>flycapture</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>libdc1394</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>libfreenect</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>libfreenect2</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>librealsense</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>videoinput</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>opencv</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>tesseract</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>leptonica</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>flandmark</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>artoolkitplus</artifactId>
//        </exclusion>
//    </exclusions>
//</dependency>
//<dependency>
//    <groupId>org.bytedeco</groupId>
//    <artifactId>javacv-platform</artifactId>
//    <version>1.4.4</version>
//    <exclusions>
//        <exclusion>
//            <groupId>org.bytedeco</groupId>
//            <artifactId>javacv</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>flycapture-platform</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>libdc1394-platform</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>libfreenect-platform</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>libfreenect2-platform</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>librealsense-platform</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>videoinput-platform</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>opencv-platform</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>tesseract-platform</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>leptonica-platform</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>flandmark-platform</artifactId>
//        </exclusion>
//        <exclusion>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>artoolkitplus-platform</artifactId>
//        </exclusion>
//    </exclusions>
//</dependency>
	
	/**
	 * MultipartFile 转 File
	 * @param file
	 * @throws Exception
	 */
	public static File multipartFileToFile(MultipartFile file) throws Exception {

	    File toFile = null;
	    if(file.equals("")||file.getSize()<=0){
	        file = null;
	    }else {
	            InputStream ins = null;
	            ins = file.getInputStream();
	            toFile = new File(file.getOriginalFilename());
	            inputStreamToFile(ins, toFile);
	            ins.close();
	    }
	    return toFile;

	}

	/**
	 * File 转 MultipartFile
	 * @param file
	 * @throws Exception
	 */
	public static MultipartFile fileToMultipartFile( File file ) throws Exception {

	    FileInputStream fileInput = new FileInputStream(file);
	    MultipartFile toMultipartFile = new MockMultipartFile("file",file.getName(),"text/plain", IOUtils.toByteArray(fileInput));
	    toMultipartFile.getInputStream();
	    return toMultipartFile;
	}


	public static File  inputStreamToFile(InputStream ins, File file) {
	    try {
	        OutputStream os = new FileOutputStream(file);
	        int bytesRead = 0;
	        byte[] buffer = new byte[8192];
	        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
	            os.write(buffer, 0, bytesRead);
	        }
	        os.close();
	        ins.close();
	        return file;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return file;
	}
	    /**
     * 获取视频时长，单位为秒
     *
     * @param video 源视频文件
     * @return 时长（s）
     */
    public static long getVideoDuration(File video) {
        long duration = 0L;
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();
            duration = ff.getLengthInTime() / (1000 * 1000);
            ff.stop();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        return duration;
    }
    /**获取视频总帧数
     * @param video
     * @return 总帧数
     */
    public static long getVideoLengthFrames(File video) {
        long LengthFrames = 0L;
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();
            LengthFrames = ff.getLengthInFrames();
            ff.stop();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        return LengthFrames;
    }
    /**
     * 截取视频获得中间帧的图片
     *
     * @param video   源视频文件
     * @param picPath 截图存放路径
     */
    public static void getVideoMiddlePic(File video, String picPath) {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();

            int i = 0;
            int length = ff.getLengthInFrames();
            int middleFrame = length / 2;
            Frame frame = null;
            while (i < length) {
                frame = ff.grabFrame();
                if ((i > middleFrame) && (frame.image != null)) {
                    break;
                }
                i++;
            }

            // 截取的帧图片
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage srcImage = converter.getBufferedImage(frame);
            int srcImageWidth = srcImage.getWidth();
            int srcImageHeight = srcImage.getHeight();

            // 对截图进行等比例缩放(缩略图)
            int width = 480;
            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

            File picFile = new File(picPath);
            ImageIO.write(thumbnailImage, "jpg", picFile);

            ff.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 截取视频获得指定帧的图片
     * @param video   源视频文件
     * @param picPath 截图存放路径
     * @param iFrame 指定视频帧数
     */
    public static void getVideoPic(File video, String picPath, long iFrame) {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();

            int i = 0;
            int length = ff.getLengthInFrames();
            Frame frame = null;
            while (i < length) {
                frame = ff.grabFrame();
                if ((i > iFrame) && (frame.image != null)) {
                    break;
                }
                i++;
            }

            // 截取的帧图片
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage srcImage = converter.getBufferedImage(frame);
            int srcImageWidth = srcImage.getWidth();
            int srcImageHeight = srcImage.getHeight();

            // 对截图进行等比例缩放(缩略图)
            int width = 480;
            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

            File picFile = new File(picPath);
            ImageIO.write(thumbnailImage, "jpg", picFile);

            ff.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param video
     * @return 获取视频格式
     */
    public static String getVideoFormat(File video) {
    	String fullname = video.getName();
    	String[] strings = fullname.split(".");//abc.abc.mp4
		return strings[strings.length-1];
    }

	/**字节转换处理类
	 * @param size
	 * @return
	 */
	private static String getPrintSize(long size) {
		//如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
		if (size < 1024) {
			return String.valueOf(size) + "B";
		} else {
			size = size / 1024;
		}
		//如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
		//因为还没有到达要使用另一个单位的时候
		//接下去以此类推
		if (size < 1024) {
			return String.valueOf(size) + "KB";
		} else {
			size = size / 1024;
		}
		if (size < 1024) {
			//因为如果以MB为单位的话，要保留最后1位小数，
			//因此，把此数乘以100之后再取余
			size = size * 100;
			return String.valueOf((size / 100)) + "."
					+ String.valueOf((size % 100)) + "MB";
		} else {
			//否则如果要以GB为单位的，先除于1024再作同样的处理
			size = size * 100 / 1024;
			return String.valueOf((size / 100)) + "."
					+ String.valueOf((size % 100)) + "GB";
		}
	}
    /** 获取视频名
     * @param video
     * @return
     */
    public static String getVideoName(File video) {
		return video.getName();
    }
    /**获取视频全路径名
     * @param video
     * @return
     */
    public static String getVideoPath(File video) {
    	return video.getParent();
    }
    
    /**获取视频大小Long字节
     * @param video
     * @return
     */
    public static long getVideoSizeLong(File video) {
		return video.length();
    }
    
    /**获取视频大小String文本
     * @param video
     * @return
     */
    public static String getVideoSizeString(File video) {
    	long sizeLong = getVideoSizeLong(video);
    	return getPrintSize(sizeLong);
    }
    
    /**获取视频大部分信息
     * @param video
     * @return 
     * 		返回VideoName，Path，Format，FrameLength，Size，Duration(s)的Map集合
     */
    public static Map<String,Object> getVideoInfo(File video){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("VideoName",	 getVideoName(video));
    	map.put("Path", getVideoPath(video));
    	map.put("Format", getVideoFormat(video));
    	map.put("FrameLength", getVideoLengthFrames(video));
    	map.put("Size", getVideoSizeString(video));
    	map.put("Duration(s)", getVideoDuration(video));
		return map;
    }
    
    
    
    
    public static void main(String[] args) {
//        String videoPath = ResourceUtils.CLASSPATH_URL_PREFIX + "video.mp4";
        File video = null;
        String videoPath = "E:\\video\\【北酱】恋与制作人BGM三连发 主题曲+日常+悲剧收场 最后1P有彩蛋\\"
        		+ "1.恋与制作人 主题曲(Av52797360,P1).mp4";
        try {
			video = ResourceUtils.getFile(videoPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String picPath1 = "E:\\video\\【北酱】恋与制作人BGM三连发 主题曲+日常+悲剧收场 最后1P有彩蛋\\video1.jpg";
        String picPath2 = "E:\\video\\【北酱】恋与制作人BGM三连发 主题曲+日常+悲剧收场 最后1P有彩蛋\\video2.jpg";
        getVideoPic(video, picPath1,5);
        getVideoPic(video, picPath2,50);

        long duration = getVideoDuration(video);
        System.out.println("videoDuration = " + duration);
    	   
    }
}
