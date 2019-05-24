/**
 * 
 */
package com.cilicili.common.utils;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

/**
 * 合并多张图片
 * 
 * @author 李明睿 2019年5月18日
 */
public class PictureMerge {

	private Graphics2D g = null;
	/**
	 * orientation 横向
	 */
	public static final int orientation = 0;
	/**
	 * portrait 纵向
	 */
	public static final int portrait = 1;

	/**
	 * 导入图片到缓存区
	 * 
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

	/**合并多张图片
	 * @param status 横向还是纵向
	 * @param bis 多张图片
	 * @return
	 */
	public BufferedImage Merge(int status, BufferedImage... bis) {
		// W:72 H:41
		int w = 0;
		int h = 0;
		int height =0;
		int width = 0;
		for (int i = 0; i < bis.length; i++) {
			if (status == orientation) {
				width = width + bis[i].getWidth();
			} else if (status == portrait) {
				height = height + bis[i].getHeight();
			} else {
				new Exception("status异常;你到底想往哪边合并啊");
			}
		}
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		for (int j = 0; j < bis.length; j++) {
			if (status == orientation) {
				g.drawImage(bis[j], w, h, null);
				w = w + bis[j].getWidth();
			} else if (status == portrait) {
				g.drawImage(bis[j], w, h, null);
				height = height + bis[j].getHeight();
			} else {
				new Exception("status异常;你到底想往哪边合并啊");
			}
		}
		g.dispose();

		return image;
	}

	/**
	 * 生成新图片保存到本地
	 * 
	 * @param newImage 新图片路径
	 * @param img      合并后的图片流
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

		tm.writeImageLocal("C:/new1.jpg", tm.Merge(orientation, d,b));
		// 将多张图片合在一起
		System.out.println("success");
	}

	/**
	 * @param orientation2
	 * @param bufferedImageList
	 * @return
	 */
	public BufferedImage Merge(int status,
			List<BufferedImage> bis) {
		// W:72 H:41
				int w = 0;
				int h = 0;
				int height =0;
				int width = 0;
				for (int i = 0; i < bis.size(); i++) {
					if (status == orientation) {
						width = width + bis.get(i).getWidth();
					} else if (status == portrait) {
						height = height + bis.get(i).getHeight();
					} else {
						new Exception("status异常;你到底想往哪边合并啊");
					}
				}
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				g = image.createGraphics();
				for (int j = 0; j < bis.size(); j++) {
					if (status == orientation) {
						g.drawImage(bis.get(j), w, h, null);
						w = w + bis.get(j).getWidth();
					} else if (status == portrait) {
						g.drawImage(bis.get(j), w, h, null);
						height = height + bis.get(j).getHeight();
					} else {
						new Exception("status异常;你到底想往哪边合并啊");
					}
				}
				g.dispose();

				return image;
	}

	/**
	 * @param newPicPath
	 * @param bufferedImageList
	 */

}
