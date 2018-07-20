package com.song.util;

import chrriis.dj.nativeswing.swtimpl.NativeComponent;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import com.google.common.collect.Lists;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Song on 2018/05/17.
 */
@Data
public class Html2ImgUtil extends JPanel {
    /**
     * DJNativeSwing-SWT.jar
     */
    private static final long serialVersionUID = 1L;
    // 行分隔符
    final static public String LS = System.getProperty("line.separator", "/n");
    // 文件分割符
    final static public String FS = System.getProperty("file.separator", "//");
    //以javascript脚本获得网页全屏后大小
    final static StringBuffer jsDimension;

    static {
        jsDimension = new StringBuffer();
        jsDimension.append("var width = 0;").append(LS);
        jsDimension.append("var height = 0;").append(LS);
        jsDimension.append("if(document.documentElement) {").append(LS);
        jsDimension.append(
                "  width = Math.max(width, document.documentElement.scrollWidth);")
                .append(LS);
        jsDimension.append(
                "  height = Math.max(height, document.documentElement.scrollHeight);")
                .append(LS);
        jsDimension.append("}").append(LS);
        jsDimension.append("if(self.innerWidth) {").append(LS);
        jsDimension.append("  width = Math.max(width, self.innerWidth);")
                .append(LS);
        jsDimension.append("  height = Math.max(height, self.innerHeight);")
                .append(LS);
        jsDimension.append("}").append(LS);
        jsDimension.append("if(document.body.scrollWidth) {").append(LS);
        jsDimension.append(
                "  width = Math.max(width, document.body.scrollWidth);")
                .append(LS);
        jsDimension.append(
                "  height = Math.max(height, document.body.scrollHeight);")
                .append(LS);
        jsDimension.append("}").append(LS);
        jsDimension.append("return width + ':' + height;");
    }

    //DJNativeSwing组件请于http://djproject.sourceforge<a href="http://lib.csdn.net/base/dotnet" class='replace_word' title=".NET知识库" target='_blank' style='color:#df3434; font-weight:bold;'>.NET</a>/main/index.html下载
    public Html2ImgUtil(final String url, final int maxWidth, final int maxHeight,final List<BufferedImage> image) {
        super(new BorderLayout());
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        final String fileName = "D:\\" + System.currentTimeMillis() + ".jpg";
        final JWebBrowser webBrowser = new JWebBrowser(null);
        webBrowser.setBarsVisible(false);
        webBrowser.navigate(url);
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        add(webBrowserPanel, BorderLayout.CENTER);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
        webBrowser.addWebBrowserListener(
                new WebBrowserAdapter() {
                    // 监听加载进度
                    public void loadingProgressChanged(WebBrowserEvent e) {
                        // 当加载完毕时
                        if (e.getWebBrowser().getLoadingProgress() == 100) {
                            String result = (String) webBrowser
                                    .executeJavascriptWithResult(jsDimension.toString());
                            int index = result == null ? -1 : result.indexOf(":");
                            NativeComponent nativeComponent = webBrowser
                                    .getNativeComponent();
                            Dimension originalSize = nativeComponent.getSize();
                            Dimension imageSize = new Dimension(Integer.parseInt(result
                                    .substring(0, index)), Integer.parseInt(result
                                    .substring(index + 1)));
                            imageSize.width = Math.max(originalSize.width, imageSize.width + 50);
                            imageSize.height = Math.max(originalSize.height, imageSize.height + 50);
                            nativeComponent.setSize(imageSize);
                            BufferedImage bufferedImage = image.get(0);
                            bufferedImage = new BufferedImage(imageSize.width, imageSize.height, BufferedImage.TYPE_INT_RGB);
                            nativeComponent.paintComponent(bufferedImage);
                            nativeComponent.setSize(originalSize);
                            // 当网页超出目标大小时
//                            if (imageSize.width > maxWidth || imageSize.height > maxHeight) {
//                                //截图部分图形
//                                image = image.getSubimage(0, 0, maxWidth, maxHeight);
//                        /*此部分为使用缩略图
//                        int width = image.getWidth(), height = image
//                            .getHeight();
//                         AffineTransform tx = new AffineTransform();
//                        tx.scale((double) maxWidth / width, (double) maxHeight
//                                / height);
//                        AffineTransformOp op = new AffineTransformOp(tx,
//                                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//                        //缩小
//                        image = op.filter(image, null);*/
//                            }
//                            try {
////                                // 输出图像
////                                ImageIO.write(image, "jpg", new File(fileName));
////                                ByteArrayOutputStream os = new ByteArrayOutputStream();
////                                ImageIO.write(image, "jpg", os);
////                                ByteArrayInputStream inputStream = new ByteArrayInputStream(os.toByteArray());
////                            } catch (IOException ex) {
////                                ex.printStackTrace();
////                            }
                            // 退出操作
                            System.exit(0);
                        }
                    }
                }
        );
        add(panel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) throws Exception{
        NativeInterface.open();
        String url = "http://h5.gonet.com.cn/h5site/demo26/";
        final String fileName = "D:\\" + System.currentTimeMillis() + ".jpg";
        BufferedImage image = new BufferedImage(1,1,1);
        ArrayList<BufferedImage> list = Lists.newArrayList(image);
        Html2ImgUtil comp = new Html2ImgUtil(url, 1000, 6000,list);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // SWT组件转Swing组件，不初始化父窗体将无法启动webBrowser
                JFrame frame = new JFrame("以DJ组件保存指定网页截图");
                // 加载指定页面，最大保存为640x480的截图
//                String url = "https://mp.weixin.qq.com/s?src=11&timestamp=1526709601&ver=885&signature=Pi1h3RiIUEJf7y2Qj8xuX9F0a9GPkOiq3BED*TKFb35jrIomVNoWRSpuAeWxDjLxvNn6P3VU2kw5q1ZZNBWntGgPvojEH4dKupx5VE7PtCRO*lzJYwu-OGhIV1wim8ZM&new=1";
//              String url = "https://invest.meixinglobal.com/product/productDetailMb.html?product_id=3326696&channel_code=3108714&product_type=1";

//              String url = "https://terminal.meixinglobal.com/#/productWhite/productDetails?product_id=3241910&partner_id=3114908&product_type=1";
                frame.getContentPane().add(comp, BorderLayout.CENTER);
                frame.setSize(1000, 6000);
                // 仅初始化，但不显示
                frame.invalidate();
                frame.pack();
                frame.setVisible(false);
            }
        });
        NativeInterface.runEventPump();

        ImageIO.write(list.get(0), "jpg", new File(fileName));
    }
}
