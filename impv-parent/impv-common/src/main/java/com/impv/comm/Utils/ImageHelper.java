package com.impv.comm.Utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * 图片裁剪 修改  缩放
 */
public class ImageHelper {
    /**
     * 根据尺寸图片居中裁剪
     */
    public static void cutCenterImage(String src, String dest, int w, int h) throws IOException {
        Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader) iterator.next();
        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        int imageIndex = 0;
        Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2, w, h);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, "jpg", new File(dest));

    }

    /**
     * 图片裁剪二分之一
     */
    public static void cutHalfImage(String src, String dest) throws IOException {
        Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader) iterator.next();
        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        int imageIndex = 0;
        int width = reader.getWidth(imageIndex) / 2;
        int height = reader.getHeight(imageIndex) / 2;
        Rectangle rect = new Rectangle(width / 2, height / 2, width, height);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, "jpg", new File(dest));
    }

    /**
     * 图片裁剪通用接口
     */

    public static void cutImage(String src, String dest, int x, int y, int w, int h) throws IOException {
        Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader) iterator.next();
        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(x, y, w, h);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, "jpg", new File(dest));
        // delFile(src);

    }

    /**
     * 图片缩放
     */
    public static void zoomImage(String src, String dest, int w, int h) throws Exception {
        double wr = 0, hr = 0;
        File srcFile = new File(src);
        File destFile = new File(dest);
        BufferedImage bufImg = ImageIO.read(srcFile);
        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_AREA_AVERAGING);
        wr = w * 1.0 / bufImg.getWidth();
        hr = h * 1.0 / bufImg.getHeight();
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //delFile(src);
    }

    /**
     * 图片拷贝
     */
    public static void copyImage(String src, String dest) throws Exception {
        double wr = 0, hr = 0;
        File srcFile = new File(src);
        File destFile = new File(dest);
        BufferedImage bufImg = ImageIO.read(srcFile);
        wr = bufImg.getWidth();
        hr = bufImg.getHeight();
        Image Itemp = bufImg.getScaledInstance((int) wr, (int) hr, bufImg.SCALE_AREA_AVERAGING);
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //delFile(src);
    }

    public static void delFile(final String src) {
        class FileThread extends Thread {
            public void run() {
                while (true) {
                    File reply_f_new = new File(src);
                    if (reply_f_new.exists() == true) { //如果找到，就读取，并跳出
                        File srcFile = new File(src);
                        boolean flag = srcFile.delete();
                        while (!flag) {
                            flag = srcFile.delete();
                        }
                    }
                }
            }
        }
        FileThread t = new FileThread();
        t.start();  //线程启动，自动运行run()方法
    }

    public static void main(String[] args) throws Exception {
        String dir = "g:" + File.separator + "" + "44.jpg";
        ImageHelper.zoomImage("g:" + File.separator + "" + "44.jpg",
                "g:" + File.separator + "" + "44.jpg"
                , 200, 200);
//    	ImageHelper.cutImage(dir, dir,0, 0, 100, 100);
    }
} 