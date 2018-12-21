package learn.springboot.springboothelloworld.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageUtils {

    public static void main(String[] args) {
        try {
            //读取第一张图片
            String onePath = "/Users/liuwei/Desktop/daka/images/image1.jpg";
            File fileOne = new File(onePath);

            String twoPath = "/Users/liuwei/Desktop/daka/images/image2.jpg";
            File fileTwo = new File(twoPath);

            //生成新图片
            //BufferedImage imageNew = marge(fileTwo, fileOne, 30, 30, 1F);

            //生成带文字带图片
            String text = "hello world";
            BufferedImage imageWord = addWord(text);
            File outFile = new File("/Users/liuwei/Desktop/daka/images/word.png");

            ImageIO.write(imageWord, "png", outFile);
        }  catch(Exception e)  {
            e.printStackTrace();
        }
    }

    public static BufferedImage marge(File file, File waterFile, int x, int y, float alpha) throws Exception {
        // 获取底图
        BufferedImage buffImg = ImageIO.read(file);
        // 获取层图
        BufferedImage waterImg = ImageIO.read(waterFile);
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = buffImg.createGraphics();
        int waterImgWidth = waterImg.getWidth();// 获取层图的宽度
        int waterImgHeight = waterImg.getHeight();// 获取层图的高度
        // 在图形和图像中实现混合和透明效果
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        // 绘制
        g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
        g2d.dispose();// 释放图形上下文使用的系统资源
        return buffImg;
    }

    public static BufferedImage addWord(String text) {

        try {
            int width = 200;
            int height = 200;
            // 创建BufferedImage对象
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 获取Graphics2D
            Graphics2D g2d = image.createGraphics();
            // 画图
            g2d.setBackground(new Color(255,255,255));
            //g2d.setPaint(new Color(0,0,0));
            //设置字体颜色
            g2d.setColor(Color.black);
            g2d.clearRect(0, 0, width, height);
            Font font=new Font("宋体",Font.PLAIN,16);
            g2d.setFont(font);
            // 抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 计算文字长度，计算居中的x点坐标
            //FontMetrics fm = g2d.getFontMetrics(font);
            /*int textWidth = fm.stringWidth(text);
            int widthX = (width - textWidth) / 2;*/
            // 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
            g2d.drawString(text, 10, 100);
            // 释放对象
            g2d.dispose();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }





}
