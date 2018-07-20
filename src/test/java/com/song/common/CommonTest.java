//package com.song.common;
//
//import com.alibaba.fastjson.JSON;
//import com.song.bean.req.UnifiedorderReq;
//import com.song.util.Hrml2ImgUtil2;
//import com.song.util.Html2ImgUtil;
//import com.song.util.ImageUtil;
//import com.song.util.MyDateUtil;
//import gui.ava.html.image.generator.HtmlImageGenerator;
//import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.geometry.Coordinate;
//import net.coobird.thumbnailator.geometry.Positions;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.junit.Test;
//
//import javax.imageio.ImageIO;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import java.awt.*;
//import java.awt.geom.Ellipse2D;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.net.URL;
//import java.net.URLConnection;
//import java.security.MessageDigest;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * Created by Song on 2018/05/15.
// */
//public class CommonTest {
//
//    @Test
//    public void testDate() {
//        Date nowTime = Calendar.getInstance().getTime();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
//        System.out.println("monthStart:" + format.format(MyDateUtil.getMonthStartTime(nowTime)));
//        System.out.println("monthEnd:" + format.format(MyDateUtil.getMonthEndTime(nowTime)));
//        System.out.println("quarterStart:" + format.format(MyDateUtil.getQuarterStartTime(nowTime)));
//        System.out.println("quarterEnd:" + format.format(MyDateUtil.getQuarterEndTime(nowTime)));
//        System.out.println("yearStart:" + format.format(MyDateUtil.getYearStartTime(nowTime)));
//        System.out.println("yearEnd:" + format.format(MyDateUtil.getYearEndTime(nowTime)));
//    }
//
////    @Test
////    public void html2Img() {
////        Html2ImgUtil.html2Img("https://www.hao123.com/");
////        System.out.println();
////    }
//
//    public static String getHtmlContent(String filePath, String charset) {
//
//        String line = null;
//        StringBuilder sb = new StringBuilder();
//        BufferedReader reader = null;
//
//        try {
//            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), charset));
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("读取HTML文件，获取字符内容异常");
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("关闭流异常");
//            }
//        }
//        return sb.toString();
//    }
//
//
//    public static void main(String[] args) throws Exception {
//        String url = "https://terminal.meixinglobal.com/#/productWhite/productDetails?product_id=3241910&partner_id=1288&product_type=1";
//        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
//        Connection connect = Jsoup.connect(url);
//
////        imageGenerator.loadUrl(url);
//        String html = connect.get().html();
//        imageGenerator.loadHtml(html);
//        Thread.sleep(8000);
//        imageGenerator.getBufferedImage();
//        Thread.sleep(8000);
//        imageGenerator.saveAsImage("D:\\1.jpg");
//        //imageGenerator.saveAsHtmlWithMap("hello-world.html", saveImageLocation);
//        //不需要转换位图的，下面三行可以不要
////        BufferedImage sourceImg = ImageIO.read(new File("D:\\1.png"));
////        sourceImg = transform_Gray24BitMap(sourceImg);
////        ImageIO.write(sourceImg, "BMP", new File(saveImageLocation));
//    }
//
//    @Test
//    public void html2Img2() {
//        String url = "https://terminal.meixinglobal.com/#/productWhite/productDetails?product_id=3241910&partner_id=3114908&product_type=1";
//        Hrml2ImgUtil2.printUrlScreen2jpg("D:\\3.jpg", url, 1000, 1000);
//        System.out.println();
//    }
//
//    @Test
//    public void circleImg() {
//        ImageUtil.getCircularImg(100,100,1);
//    }
//
//
//    @Test
//    public void getCircle() throws Exception{
//        String imgUrl = "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIeBpdVhicUzABsic7A0VF0wibibMPIIfuHhkN20FI9md66AASiamYGoMibj0Ncicf7kFL7ITTtG0bVicygVg/132";
//        URL url = new URL(imgUrl);
//        URLConnection urlConnection = url.openConnection();
//        BufferedImage bi1 = ImageIO.read(urlConnection.getInputStream());
//
//        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
//        BufferedImage image = new BufferedImage(bi1.getWidth(), bi1.getHeight(),
//                BufferedImage.TYPE_INT_ARGB);
//
//        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
//
//        Graphics2D g2 = image.createGraphics();
//        image = g2.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getHeight(), Transparency.TRANSLUCENT);
//        g2 = image.createGraphics();
//        g2.setComposite(AlphaComposite.Clear);
//        g2.fill(new Rectangle(image.getWidth(), image.getHeight()));
//        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
//        g2.setClip(shape);
//        // 使用 setRenderingHint 设置抗锯齿
//        g2.drawImage(bi1, 0, 0, null);
//        g2.dispose();
//
//        try {
//            ImageIO.write(image, "PNG", new File("d:/2.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getCircle2() throws Exception{
//        String imgUrl = "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIeBpdVhicUzABsic7A0VF0wibibMPIIfuHhkN20FI9md66AASiamYGoMibj0Ncicf7kFL7ITTtG0bVicygVg/132";
//        URL url = new URL(imgUrl);
//        URLConnection urlConnection = url.openConnection();
//        BufferedImage bi1 = ImageIO.read(urlConnection.getInputStream());
//        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
//        BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2 = bi2.createGraphics();
//        bi2 = g2.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getWidth(), Transparency.TRANSLUCENT);
//        g2.dispose();
//
//        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
//        g2 = bi2.createGraphics();
////        g2.setBackground(Color.);
//        g2.fill(new Rectangle(bi2.getWidth(), bi2.getHeight()));
//        g2.setClip(shape);
//        // 使用 setRenderingHint 设置抗锯齿
//        g2.drawImage(bi1, 0, 0, null);
//        g2.dispose();
//
//        try {
//            ImageIO.write(bi2, "jpg", new File("d:/3.png"));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public BufferedImage getCircle4() throws Exception{
//        String imgUrl = "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIeBpdVhicUzABsic7A0VF0wibibMPIIfuHhkN20FI9md66AASiamYGoMibj0Ncicf7kFL7ITTtG0bVicygVg/132";
//        URL url = new URL(imgUrl);
//        URLConnection urlConnection = url.openConnection();
//        BufferedImage bi1 = ImageIO.read(urlConnection.getInputStream());
//        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
//        BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2 = bi2.createGraphics();
//        bi2 = g2.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getWidth(), Transparency.TRANSLUCENT);
//        g2.dispose();
//
//        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
//        g2 = bi2.createGraphics();
////        g2.setBackground(Color.);
//        g2.fill(new Rectangle(bi2.getWidth(), bi2.getHeight()));
//        g2.setClip(shape);
//        // 使用 setRenderingHint 设置抗锯齿
//        g2.drawImage(bi1, 0, 0, null);
//        g2.dispose();
//
//        return bi2;
//    }
//
//    public BufferedImage getCircle5() throws Exception {
//        String imgUrl = "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIeBpdVhicUzABsic7A0VF0wibibMPIIfuHhkN20FI9md66AASiamYGoMibj0Ncicf7kFL7ITTtG0bVicygVg/132";
//        URL url = new URL(imgUrl);
//        URLConnection urlConnection = url.openConnection();
//        BufferedImage bi1 = ImageIO.read(urlConnection.getInputStream());
//
//        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
//        BufferedImage image = new BufferedImage(bi1.getWidth(), bi1.getHeight(),
//                BufferedImage.TYPE_INT_ARGB);
//
//        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
//
//        Graphics2D g2 = image.createGraphics();
//        image = g2.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getHeight(), Transparency.TRANSLUCENT);
//        g2 = image.createGraphics();
//        g2.setComposite(AlphaComposite.Clear);
//        g2.fill(new Rectangle(image.getWidth(), image.getHeight()));
//        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
//        g2.setClip(shape);
//        // 使用 setRenderingHint 设置抗锯齿
//        g2.drawImage(bi1, 0, 0, null);
//        g2.dispose();
//
//        return image;
//    }
//
//    @Test
//    public void compound() throws Exception{
//        //下载水印图
//        String qrcodeImgUrl = "https://file.duobei360.com/media/20180525/88f157a4-7a7c-4500-8f73-b4b75184f34f.png";
//        String avatar = "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIeBpdVhicUzABsic7A0VF0wibibMPIIfuHhkN20FI9md66AASiamYGoMibj0Ncicf7kFL7ITTtG0bVicygVg/132";
//        URL url = new URL(avatar);
//        URLConnection urlConnection = url.openConnection();
//        BufferedImage watermarkBufferedImage = ImageIO.read(urlConnection.getInputStream());
//        watermarkBufferedImage = getCircle5();
//        ImageIO.write(watermarkBufferedImage, "PNG", new File("d:/water.png"));
//
//        URL qrurl = new URL(qrcodeImgUrl);
//        URLConnection qrurlConnection = qrurl.openConnection();
//        BufferedImage backgroundFufferedImage = ImageIO.read(qrurlConnection.getInputStream());
//        ImageIO.write(backgroundFufferedImage, "PNG", new File("d:/back.png"));
//
//        //水印图尺寸
//        int markWidth = watermarkBufferedImage.getWidth();
//        int markHeight = watermarkBufferedImage.getHeight();
//        //水印图目标尺寸
//        Integer targetHeight = 192;
//        Integer targetWidth = 192;
//
//        double heightTimes = targetHeight * 1.0 / markHeight;
//        double widthTimes = targetWidth * 1.0 / markWidth;
////        double times;
////        if ((markHeight - targetHeight) * (markWidth - targetWidth) >= 0) {
////            times = heightTimes > targetWidth * 1.0 / markWidth ? heightTimes : widthTimes;
////        } else {
////            times = heightTimes > 1 ? heightTimes : widthTimes;
////        }
//        //将水印图按照目标尺寸放大或缩小
//        watermarkBufferedImage = Thumbnails.of(watermarkBufferedImage)
//                .size(192, 192)
//                .asBufferedImage();
//        //裁剪水印图
//        watermarkBufferedImage = Thumbnails.of(watermarkBufferedImage)
//                .sourceRegion(Positions.CENTER, markWidth, markHeight)
//                .size(targetWidth, targetHeight)
//                .asBufferedImage();
//        //合成图片
//        File targetFile = new File("D:\\99.png");
//        backgroundFufferedImage= Thumbnails.of(backgroundFufferedImage)
//                .size(backgroundFufferedImage.getWidth(), backgroundFufferedImage.getHeight())
//                .watermark(new Coordinate(119, 121), watermarkBufferedImage, 1)
//                .asBufferedImage();
//        ImageIO.write(backgroundFufferedImage, "PNG", new File("d:/target.png"));
//    }
//
//    @Test
//    public void getCircle3() throws Exception{
//        String imgUrl = "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIeBpdVhicUzABsic7A0VF0wibibMPIIfuHhkN20FI9md66AASiamYGoMibj0Ncicf7kFL7ITTtG0bVicygVg/132";
//        URL url = new URL(imgUrl);
//        URLConnection urlConnection = url.openConnection();
//        BufferedImage bi1 = ImageIO.read(urlConnection.getInputStream());
//        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
//        BufferedImage waterImg = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_INT_RGB);
//
//        // ---------- 增加下面的代码使得背景透明 -----------------
//        Graphics2D graphics = waterImg.createGraphics();
//        waterImg = graphics.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getWidth(), Transparency.TRANSLUCENT);
//        graphics.dispose();
//        // ---------- 背景透明代码结束 -----------------
//
//        graphics = waterImg.createGraphics();
//
//
//
//        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
//        graphics.setBackground(Color.WHITE);
//        graphics.fill(new Rectangle(bi1.getWidth(), bi1.getHeight()));
//        graphics.setClip(shape);
//        // 使用 setRenderingHint 设置抗锯齿
//        graphics.drawImage(bi1, 0, 0, null);
//        graphics.dispose();
//
//        try {
//            ImageIO.write(waterImg, "jpg", new File("d:/3.png"));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getXmlStr() {
//        UnifiedorderReq req = new UnifiedorderReq();
//        req.setAppid("11111");
//        req.setAttach("2222");
//        req.setBody("中国");
//        req.setDetail("消息");
//        req.setDevice_info("33333");
//
//        JAXBContext context;
//        try {
//            context = JAXBContext.newInstance(UnifiedorderReq.class);
//            Marshaller mar = context.createMarshaller();
//            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//
//            StringWriter writer = new StringWriter();
//
//            mar.marshal(req, writer);
//
//            System.out.println(writer.toString());
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void object2Map() throws Exception{
//        UnifiedorderReq req = new UnifiedorderReq();
//        // TODO: 2018/6/14 获取appid，户号
//
//        req.setAppid("");
//        req.setMch_id("");
//        req.setNonce_str(UUID.randomUUID().toString().replaceAll("-",""));
//        req.setBody("我是商品体");
//        req.setOut_trade_no("123456");
//        req.setTotal_fee(1000);
//        req.setSpbill_create_ip("127.0.0.1");
//        req.setNotify_url("www.songhaoran.cn");
//        req.setTrade_type("JSAPI");
//
//        String jsonString = JSON.toJSONString(req);
//        HashMap paramMap = JSON.parseObject(jsonString, HashMap.class);
//        System.out.println();
//
//        ArrayList keyList = new ArrayList(paramMap.keySet());
//        Collections.sort(keyList);
//
//        StringBuilder builder = new StringBuilder();
//        keyList.forEach(key -> builder.append(key).append("=").append(paramMap.get(key).toString()).append("&"));
//
//        String stringSignTemp = builder.append("key=").append("15896325").toString();
//
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        String sign = md.digest(stringSignTemp.getBytes()).toString().toUpperCase();
//        System.out.println(sign);
//    }
//}
