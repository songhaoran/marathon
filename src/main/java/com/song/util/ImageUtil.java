package com.song.util;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Song on 2018/05/25.
 */
@Slf4j
public class ImageUtil {
    private static Integer ROTATION_ANGLE =360; //弧度

    private static Integer LOAD_NETWORK = 0; //图片来源网络

    private static Integer LOAD_LOCAL = 1;  //图片来源本地

    //测试
//    public static void main(String[] args) {
//
////获取背景图
//        BufferedImage bgImg = imgLoadUrl("图片路径", LOAD_NETWORK);
////需要叠加的图片列表
//        List<ImgBean> imgBeans = new ArrayList<ImgBean>();
////需要添加的文字列表
//        List<TextBean> textBeans = new ArrayList<TextBean>();
//
////获取圆形图片 //margin=null 则不设置图片背景
//        BufferedImage circularImg = getCircularImg(imgLoadUrl("图片路径", LOAD_NETWORK), 30, 30, ROTATION_ANGLE, 20, Color.WHITE);
//
//        imgBeans.add(imgParameter(circularImg,true, null, 200, 300, 50));
//
//        imgBeans.add(imgParameter(imgLoadUrl("图片路径", LOAD_NETWORK),true, null, 20, 30, 50));
//
//        textBeans.add(textParameter("需要输入的文字", true, null, 12, Color.white, new Font("微软雅黑", Font.PLAIN, 30)));
//
//        try {
//            ImageIO.write(createImg(bgImg, textBeans, imgBeans), "jpg", new File("d:/testImg.png"));
//        } catch (IOException e) {
//            log.error("error main", e);
//        }
//
//    }



    public static BufferedImage createImg(BufferedImage bgImg,List<TextBean> textBeans,List<ImgBean> imgBeans){
        Graphics2D g = bgImg.createGraphics();
//去锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//图片叠加
        imgSuperposition(g, bgImg.getWidth(), imgBeans);
//添加文字
        textAddImg(g, bgImg.getWidth(), textBeans);
        return bgImg;

    }

    /**
     * 图片叠加
     * @param g
     * @param imgBeans
     * @return
     * 2017年3月12日
     * jianhui
     */
    private static Graphics2D imgSuperposition(Graphics2D g,Integer bgImgWidth, List<ImgBean> imgBeans){
        try {
            for(int i =0;i< imgBeans.size();i++){
                ImgBean imgBean = imgBeans.get(i);
                if(imgBean.isCenter()){ //是否居中判断
                    int x = (bgImgWidth-imgBean.getW())/2;
                    g.drawImage(imgBeans.get(i).getImg(),x, imgBean.getY(),imgBean.getW(), imgBean.getH(),null);
                }else{
                    g.drawImage(imgBeans.get(i).getImg(),imgBean.getX(), imgBean.getY(),imgBean.getW(), imgBean.getH(),null);
                }
            }
        } catch (Exception e) {
            log.error("error imgSuperposition", e);
        }
        return g;
    }

    /**
     * 添加文字
     * @param g
     * @param bgImgWidth
     * @param textBeans
     * @return
     * 2017年3月12日
     * jianhui
     */
    private static Graphics2D textAddImg(Graphics2D g,Integer bgImgWidth, List<TextBean> textBeans){
        try {
            for(int i=0;i<textBeans.size();i++){
                TextBean textBean = textBeans.get(i);
                g.setColor(textBean.getColor());
                g.setFont(textBean.getFont());
                if(textBean.isCenter()){ //是否居中判断
                    int x = g.getFontMetrics().stringWidth(textBean.getName());
                    g.drawString(textBean.getName(),(bgImgWidth-x)/2 , textBean.getY());
                }else{
                    g.drawString(textBean.getName(),textBean.getX(), textBean.getY());
                }
            }
        } catch (Exception e) {
            log.error("error textAddImg", e);
        }

        return g;
    }


    /**
     * 图片圆形处理  返回处理后的图片
     * @param width 宽
     * @param height 高
     * @param rotationAngle 弧度
     * @return
     * @throws Exception
     * 2017年3月12日
     * jianhui
     */
    public static BufferedImage getCircularImg(Integer width, Integer height,Integer rotationAngle){
        try {
            String imgUrl = "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIeBpdVhicUzABsic7A0VF0wibibMPIIfuHhkN20FI9md66AASiamYGoMibj0Ncicf7kFL7ITTtG0bVicygVg/132";
            URL url = new URL(imgUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedImage bufferedImage = ImageIO.read(urlConnection.getInputStream());
//
//            Thumbnails.of(url)
//                    .size(imgActivity.getWidth(), imgActivity.getHeight())
//                    .asBufferedImage();
            BufferedImage circularImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = circularImg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Float(0, 0, width, height, rotationAngle,rotationAngle));
            g2.setComposite(AlphaComposite.SrcAtop);
            g2.drawImage(bufferedImage, 0, 0, width, height, null);
            g2.dispose();
            Thumbnails.of(circularImg)
                    .size(width,height)
                    .toFile(new File("D:\\1.png"));
            ImageIO.write(circularImg, "1.png", new File("D:\\\\1.png"));
            return circularImg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 圆形图片添加圆形背景
     * @param circularImg 传入的图
     * @param margin 边距
     * @param color 背景颜色
     * @return
     * @throws Exception
     * 2017年3月12日
     * jianhui
     */
    public static BufferedImage getCircularImgByBorder(BufferedImage circularImg, Integer margin, Color color){
        BufferedImage circularImgByBorder = new BufferedImage(circularImg.getWidth()+margin, circularImg.getHeight()+margin,circularImg.getType());
        Graphics2D g = circularImgByBorder.createGraphics();
//去锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color == null ? Color.WHITE : color);
        g.fill(new RoundRectangle2D.Float(0, 0, circularImg.getWidth()+margin, circularImg.getHeight()+margin, ROTATION_ANGLE,ROTATION_ANGLE));
        g.setComposite(AlphaComposite.SrcAtop);
        g.drawImage(circularImg, margin/2, margin/2,circularImg.getWidth(), circularImg.getHeight(), null);
        g.dispose();
        return circularImgByBorder;
    }

    /**
     * 获取图片
     * @param imgUrl 图片路径
     * @param type 图片来源：0=网络/ 1=本地
     * @return
     * 2017年3月12日
     * jianhui
     */
    public static BufferedImage imgLoadUrl(String imgUrl,Integer type){
        try {
            if(LOAD_NETWORK.equals(type)){
                return ImageIO.read(new URL(imgUrl));
            }else if(LOAD_LOCAL.equals(type)){
                return ImageIO.read(new File(imgUrl));
            }
        } catch (Exception e) {
            log.error("error imgLoadUrl", e);
        }
        return null;
    }

    /**
     * 图片设置参数
     * @param img 图片
     * @param center 是否居中
     * @param x 如果居中 此值可以不用传
     * @param y 图片y轴初始位置
     * @param w 图片宽
     * @param h 图片高
     * @return
     * 2017年3月12日
     * jianhui
     */
    public static ImgBean imgParameter(BufferedImage img,boolean center,Integer x,Integer y,Integer w,Integer h){
        try {
            ImgBean imgBean = new ImgBean();
            imgBean.setImg(img);
            imgBean.setCenter(center);
            imgBean.setX(x == null ? 0:x);
            imgBean.setY(y == null ? 0:y);
            imgBean.setW(w == null ?img.getWidth():w);
            imgBean.setH(h == null ?img.getHeight():h);
            return imgBean;
        } catch (Exception e) {
            log.error("error imgParameter", e);
        }
        return null;

    }
    /**
     * 文字设置参数
     * @param name 传入文字
     * @param center 是否居中
     * @param x x轴位置
     * @param y y轴位置
     * @param color 颜色
     * @param font 画笔
     * @return
     * 2017年3月12日
     * jianhui
     */
    public static TextBean textParameter(String name,boolean center,Integer x,Integer y,Color color,Font font){
        try {
            TextBean textBean = new TextBean();
            textBean.setName(name);
            textBean.setCenter(center);
            textBean.setX(x == null ? 0:x);
            textBean.setY(y == null ? 0:y);
            textBean.setColor(color == null ? Color.WHITE:color);
            textBean.setFont(font == null ? new Font("微软雅黑", Font.PLAIN, 30):font);
        } catch (Exception e) {
            log.error("error textParameter", e);
        }
        return null;
    }


    /**
     * 图片设置类
     * title imgParameter
     * @date 2017年3月12日
     * @author jianhui
     */
    public static class ImgBean{
        /** 图片buffer*/
        private BufferedImage img;
        /** 图片x轴标*/
        private int x;
        /** 图片y轴坐标*/
        private int y;
        /** 图片宽*/
        private int w;
        /** 图片高*/
        private int h;
        /** 图片是否居中*/
        private boolean center;

        public BufferedImage getImg() {
            return img;
        }
        public void setImg(BufferedImage img) {
            this.img = img;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
        public int getW() {
            return w;
        }
        public void setW(int w) {
            this.w = w;
        }
        public int getH() {
            return h;
        }
        public void setH(int h) {
            this.h = h;
        }
        public boolean isCenter() {
            return center;
        }
        public void setCenter(boolean center) {
            this.center = center;
        }

    }

    /**
     * 文字bean
     * title TextBean
     * @date 2017年3月12日
     * @author jianhui
     *
     *
     */
    public static class TextBean{
        /** 输入的文字*/
        private String name;
        /** 文字是否居中*/
        private boolean center;
        /** 文字的x轴位置*/ //居中此值不用传
        private int x;
        /** 文字的y轴位置 */ //注：文字的位置计算是以文字的底部开始的，右下角，不像图片从左上角
        private int y;
        /** 文字的颜色*/
        private Color color;
        /** 画笔*/
        private Font font;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public boolean isCenter() {
            return center;
        }
        public void setCenter(boolean center) {
            this.center = center;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
        public Color getColor() {
            return color;
        }
        public void setColor(Color color) {
            this.color = color;
        }
        public Font getFont() {
            return font;
        }
        public void setFont(Font font) {
            this.font = font;
        }


    }

}
