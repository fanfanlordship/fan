package org.fan.cloud.auth.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @description: 图片相关的工具类
 * @author: fanfanlordship
 * @create: 2019-07-06 14:14
 */
public final class ImageUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtil.class);

    private ImageUtil() {
    }

    /**
     * 生成验证码图片流
     *
     * @param verCode
     * @param response
     * @throws IOException
     */
    public static void getVerCodeImage(String verCode, HttpServletResponse response) throws IOException {
        int width = 100;
        int height = 40;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics graphics = image.getGraphics();
        //Graphics类的样式
        graphics.setColor(getRandColor(200, 250));
        graphics.setFont(new Font("Times New Roman", 0, 28));
        graphics.fillRect(0, 0, width, height);
        //绘制干扰线
        for (int i = 0; i < 40; i++) {
            graphics.setColor(getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(20);
            int y1 = random.nextInt(20);
            graphics.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        for (int i = 0; i < verCode.length(); i++) {
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            graphics.drawString(String.valueOf(verCode.charAt(i)), 20 * i + 6, 28);
        }
        graphics.dispose();

        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
    }

    public static String encryCaptcha2Base64(String captcha) {
        int width = 100;
        int height = 40;
        Random random = new Random();

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics graphics = image.getGraphics();
        //Graphics类的样式
        graphics.setColor(getRandColor(200, 250));
        graphics.setFont(new Font("Times New Roman", 0, 28));
        graphics.fillRect(0, 0, width, height);
        //绘制干扰线
        for (int i = 0; i < 40; i++) {
            graphics.setColor(getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(20);
            int y1 = random.nextInt(20);
            graphics.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        for (int i = 0; i < captcha.length(); i++) {
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            graphics.drawString(String.valueOf(captcha.charAt(i)), 20 * i + 6, 28);
        }
        graphics.dispose();


        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "JPEG", stream);
            byte[] bytes = Base64.encodeBase64(stream.toByteArray());
            String base64 = new String(bytes);
            return "data:image/jpeg;base64," + base64;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
