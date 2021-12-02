package com.example.controller;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collection;

import static com.example.utils.generateImage.generateImageToPath;
import static com.example.utils.generateImage.saveImage;

import com.example.utils.AI;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;

    public static final String APP_ID = "25023966";
    public static final String API_KEY = "74vu0E5192sGEwf20sZl70rb";
    public static final String SECRET_KEY = "T8eaBIi1dPzBCQMsygBux97MsHUGDyc3";

    @RequestMapping(value="/addUser")
    public String addUser(
            @RequestParam("name") String name,
            @RequestParam("id") String id,
            @RequestParam("file") MultipartFile file,
            Model model
    )
    {
        User people = saveImage(name,id,file,"上传失败",model);
        if(people!=null)
        {
            userMapper.addUser(people);
            return "redirect:/users";
        }
        else
        {
            return "add";
        }
    }

    @RequestMapping("/check")
    public String check(
    @RequestParam("file") String file,
            Model model)
    {
//        保存图片
        generateImageToPath(file);
        String result=AI.search_face(file).get("result").toString();
//        根据图片查询信息
        if(result!="null")
        {
            JSONObject res=new JSONObject(result);
            result=(String)res.get("user_list").toString();
            String result1="";
            int n=result.length();
            for(int i=1;i<n-1;i++)
                result1 +=result.charAt(i);
            result=result1;
            res=new JSONObject(result);
            result=(String)res.get("user_id").toString();
            int id=0;
            for(int i=0;i<result.length();i++)
            {
                id*=10;
                id+=(result.charAt(i)-'0');
            }
//        返回查询信息
            System.out.println(id);
            model.addAttribute("user",userMapper.queryUserById(id));
        }
        else
        {
            model.addAttribute("msg","识别失败");
        }
        return "index";
    }

    @RequestMapping("/user/{id}")
    public String search(@PathVariable("id")Integer id, Model model)
    {
//        根据id查询信息
        User user = userMapper.queryUserById(id);
//        添加信息
        model.addAttribute("user",user);
//        返回更新页面
        return "details";
    }

    @RequestMapping("/update")
    public String updateUser(
            @RequestParam("name") String name,
            @RequestParam("id") String id,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws Exception
    {
        User people = saveImage(name,id,file,"修改失败",model);
        if(people!=null)
        {
            userMapper.updateUser(people);
            return "redirect:/users";
        }
        else
        {
            return "add";
        }
    }

    @RequestMapping("/delUser/{id}")
    public String delUser(
            @PathVariable("id")Integer id
    )
    {
        userMapper.deleteUser(id);
        AI.delete_face(id.toString());
        return "redirect:/users";
    }


    @RequestMapping("/users")
    public String list(Model model)
    {
        Collection<User> users =  userMapper.queryUserList();
        model.addAttribute("users",users);
        return "list";
    }

    @RequestMapping("/toAdd")
    public String toAdd()
    {
        return "add";
    }

    @RequestMapping("/camera")
    public String camera()
    {
        return "index";
    }

}
