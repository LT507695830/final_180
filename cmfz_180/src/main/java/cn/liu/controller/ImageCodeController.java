package cn.liu.controller;

import cn.liu.util.ValidateImageCodeUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RequestMapping("Image")
@Component
public class ImageCodeController {

    @RequestMapping("code")
    public void getImage(HttpServletResponse response , HttpServletRequest request){
        // 1.回执验证码中的字符
        String code = ValidateImageCodeUtils.getSecurityCode();

        HttpSession session = request.getSession();
        session.setAttribute("Code" , code);
        // 2. 通过生成的字符回执图片
        BufferedImage image = ValidateImageCodeUtils.createImage(code);

        // 3.通过图片的输出流,写到页面
        //     参数1 图片  参数2 格式 参数3 输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream  = response.getOutputStream();
            ImageIO.write(image,"png",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
