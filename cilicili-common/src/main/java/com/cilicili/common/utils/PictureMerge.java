/**
 * 
 */
package com.cilicili.common.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 合并多张图片
 * @author 李明睿 2019年5月18日
 */
public class PictureMerge {
	
	 private Graphics2D g = null;  
	 private static final int orientation = 0;
	 private static final int portrait = 1;
	 //orientation 横向
	 //portrait 纵向

     

	    /**导入图片到缓存区
	     * @param imgName 图片路径
	     * @return 
	     */
	    public BufferedImage loadImageLocal(String imgName) {  
	        try {  
	            return ImageIO.read(new File(imgName));  
	        } catch (IOException e) {  
	            System.out.println(e.getMessage());  
	        }  
	        return null;  
	    } 
	    /**合并多张图片为一张图片，指定其高和宽
	     * @param bi 第一张图片
	     * @param bis 其他图片
	     * @param height 指定图片高度
	     * @param width 指定图片宽度
	     * @param status 合并图片的方向 orientation=0 and portrait=1.
	     * @return
	     */
	    public BufferedImage Merge(BufferedImage bi,int height, int width,int status,BufferedImage... bis) {  
	    	//W:72 H:41
	    	int w = 0;  int h = 0;
	    	g = bi.createGraphics();
	    	g.drawImage(bi, w, h,width, height, null);
	    	for (int i = 0; i < bis.length; i++) {
	    		try {  
	    			if (status == orientation ) {w = w + width;}
	    			else if(status == portrait){h = h + height;}
	    			else { new Exception("status异常;你到底想往哪边合并啊");}
	    			g.drawImage(bis[i], w, h,width, height, null);  
	    		} catch (Exception e) {  
	    			System.out.println(e.getMessage());  
	    		}  
			}
	    	g.dispose();  
	  
	        return bi;  
	    } 
	    
	    public BufferedImage Merge(BufferedImage b, BufferedImage d) {  
	        
	        try {  
	            int w = b.getWidth();  
	            int h = b.getHeight();  
	  
	            g = d.createGraphics();  
	            g.drawImage(b, 300, -800, w, h, null);  
	            g.dispose();  
	        } catch (Exception e) {  
	            System.out.println(e.getMessage());  
	        }  
	        return d;  
	    } 
	    
	    
	    /**合成多张图片为一张图片,指定其宽
	     * @param bi 第一张图片
	     * @param width 指定宽度
	     * @param bis 其他图片
	     * @param status 合并图片的方向 orientation=0 and portrait=1.
	     * @return
	     */
	    public BufferedImage MergeWidth(BufferedImage bi,int width,int status,BufferedImage... bis) {  
	    	//W:72 H:41
	    	int w = 0;  int h = 0;
	    	g = bi.createGraphics();
	    	int height = bi.getHeight();
	    	g.drawImage(bi, w, h,width, height, null);
	    	for (int i = 0; i < bis.length; i++) {
	    		try {  
	    			if (status == orientation ) {w = w + width;}
	    			else if(status == portrait){h = h + height;}
	    			else { new Exception("status异常;你到底想往哪边合并啊");}
	    			g.drawImage(bis[i], w, h, width, height, null);  
	    		} catch (Exception e) {  
	    			System.out.println(e.getMessage());  
	    		}  
			}
	    	g.dispose();  
	  
	        return bi;  
	    } 
	    /**请看MergeWidth
	     * @param bi
	     * @param height
	     * @param bis
	     * @param status 合并图片的方向 orientation=0 and portrait=1.
	     * @return
	     */
	    public BufferedImage MergeHeight(BufferedImage bi,int height,int status,BufferedImage... bis) {  
	    	//W:72 H:41
	    	int w = 0;  int h = 0;
	    	g = bi.createGraphics();
	    	int width = bi.getWidth();
	    	g.drawImage(bi, w, h,width, height, null);
	    	for (int i = 0; i < bis.length; i++) {
	    		try {  
	    			if (status == orientation ) {w = w + width;}
	    			else if(status == portrait){h = h + height;}
	    			else { new Exception("status异常;你到底想往哪边合并啊");}
	    			g.drawImage(bis[i], w, h, width, height, null);  
	    		} catch (Exception e) {  
	    			System.out.println(e.getMessage());  
	    		}  
			}
	    	g.dispose();  
	  
	        return bi;  
	    } 
	    
	    
	    /**生成新图片保存到本地
	     * @param newImage 新图片路径
	     * @param img 合并后的图片流
	     */
	    public void writeImageLocal(String newImage, BufferedImage img) {  
	        if (newImage != null && img != null) {  
	            try {  
	                File outputfile = new File(newImage);  
	                ImageIO.write(img, "jpg", outputfile);  
	            } catch (IOException e) {  
	                System.out.println(e.getMessage());  
	            }  
	        }  
	    } 
	        
	    public static void main(String[] args) {  
	        
	    	PictureMerge tm = new PictureMerge();  
	  
	        BufferedImage d = tm.loadImageLocal("E:\\video\\【北酱】恋与制作人BGM三连发 主题曲+日常+悲剧收场 最后1P有彩蛋\\video1.jpg");  
	        BufferedImage b = tm.loadImageLocal("E:\\video\\【北酱】恋与制作人BGM三连发 主题曲+日常+悲剧收场 最后1P有彩蛋\\video2.jpg");
	        
	        tm.writeImageLocal("C:/new1.jpg", tm.Merge(d, b));
	        //将多张图片合在一起    
	        System.out.println("success");  
	    } 
}
