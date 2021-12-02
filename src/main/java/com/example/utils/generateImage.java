package com.example.utils;
import java.io.*;

import com.example.pojo.User;
import org.apache.commons.codec.binary.Base64;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;


public class generateImage {

    public static String generateImageToPath(String imgStr)
    {  //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return null;
        try
        {
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            String newfilename = System.currentTimeMillis() + UUID.randomUUID().toString().replace(".", "").substring(0, 6) + ".jpg" ;
            String fileLocation = "/static/picture/";
            String filePath = "http://localhost:8080"
                    + fileLocation + newfilename;
            String imgFilePath = "H:\\学习\\java\\spring-mybatis\\src\\main\\resources\\static\\picture\\"+System.currentTimeMillis() + UUID.randomUUID().toString().replace(".", "").substring(0, 6)+".jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return imgFilePath;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    public static User saveImage(String name, String id, MultipartFile file, String msg,Model model)
    {
        try {
            String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
            String basePath = staticPath + "/picture";
            String imagePath = basePath + name + id + "." + fileExtension;
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(imagePath));
            out.write(file.getBytes());
            out.flush();
            out.close();
            User people = new User();
            people.setId(Integer.parseInt(id));
            people.setName(name);
            AI.add_user(imagePath,id);
            return people;
        }
        catch (Exception e)
        {
            model.addAttribute("msg","上传失败");
            return null;
        }
    }
}