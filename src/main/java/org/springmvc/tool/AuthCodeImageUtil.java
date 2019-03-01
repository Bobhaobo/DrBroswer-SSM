package org.springmvc.tool;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

public class AuthCodeImageUtil {
    public static final char[] CHARS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','2', '3', '4', '5', '6', '7', '8',
            '9','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm',
            'n', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private ByteArrayInputStream image;// 图像
    private String str;// 验证码
    /**
     *  构造方法调用初始化属性方法
     */
    private AuthCodeImageUtil() {
        init();
    }

    /**
     * 取得RandomNumUtil实例
     */
    public static AuthCodeImageUtil Instance() {
        return new AuthCodeImageUtil();
    }

    /**
     * 取得验证码图片
     */
    public ByteArrayInputStream getImage() {
        return this.image;
    }

    /**
     * 取得图片的验证码
     */
    public String getString() {
        return this.str;
    }

    /**
     * 初始化属性否具体方法
     */
    private void init() {
        // 在内存中创建图象
        int width = 75, height = 24;
        //设置图形的高度和宽度，以及RGB类型
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        // 随机产生255条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 255; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码(4位数字)
        StringBuffer sRand = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(CHARS[random.nextInt(CHARS.length-1)]);//从字符数组中随机产生一个字符
            sRand.append(rand);
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 15 * i + 8, 20);   //将随机生成的文字绘制到图片内
        }
        // 赋值验证码
        this.str = sRand.toString();

        // 图象生效
        g.dispose();
        //下面将生成的图形转变为图片
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayInputStream input = null;
        try {
            ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
            ImageIO.write(image, "JPEG", imageOut);//将图像按JPEG格式写入到imageOut中，即存入到output的字节流中
            imageOut.close();//关闭写入流
            input = new ByteArrayInputStream(output.toByteArray());//input读取output中的图像信息
        } catch (Exception e) {
            System.out.println("验证码图片产生出现错误：" + e.toString());
        }

        this.image = input;/* 赋值图像 */
    }
    /*
     * 给定范围获得随机颜色
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
