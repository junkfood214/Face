package com.example.utils;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class AI {

    public static JSONObject search_face(String image) {
        String APP_ID = "25023966";
        String API_KEY = "74vu0E5192sGEwf20sZl70rb";
        String SECRET_KEY = "T8eaBIi1dPzBCQMsygBux97MsHUGDyc3";
//         初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

//         可选：设置网络连接参数
//        建立连接的超时时间（单位：毫秒）
        client.setConnectionTimeoutInMillis(2000);
//        通过打开的连接传输数据的超时时间（单位：毫秒）
        client.setSocketTimeoutInMillis(60000);

//         可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

//         调用接口
        HashMap<String, String> options = new HashMap<String, String>();
//        user_info 用作唯一标识符
//        options.put("user_info", "use_info");
//        不重要
        options.put("match_threshold", "80");
//        不重要
//        options.put("liveness_control", "LOW");
//        REPLACE代表替换，APPEND代表添加到后面，默认APPEND
        options.put("action_type", "REPLACE");
//        image为保存图片的base64码
//        String image =  Base64.getEncoder().encodeToString(data);
//        图片的保存方式，这里用base64
        String imageType = "BASE64";
//        用户组号
        String groupId = "1";
//        用户id
//        String userId = "liyunlong";
        return client.search(image, imageType, groupId, options);
//        JSONObject res = client.search(image, imageType, groupId, options);
//        System.out.println(res.toString(2));
    }
    public static boolean delete_face(String user_id) {
        String APP_ID = "25023966";
        String API_KEY = "74vu0E5192sGEwf20sZl70rb";
        String SECRET_KEY = "T8eaBIi1dPzBCQMsygBux97MsHUGDyc3";
//         初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

//         可选：设置网络连接参数
//        建立连接的超时时间（单位：毫秒）
        client.setConnectionTimeoutInMillis(2000);
//        通过打开的连接传输数据的超时时间（单位：毫秒）
        client.setSocketTimeoutInMillis(60000);

//         可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

//         调用接口
        HashMap<String, String> options = new HashMap<String, String>();
//        用户组号
        String groupId = "1";
//        用户id
//        return client.search(image, imageType, groupId, options);
        JSONObject res = client.deleteUser(groupId, user_id, options);
        return true;
//        System.out.println(res.toString(2));
    }
    public static boolean add_user(String imgPath,String user_id) {
        String APP_ID = "25023966";
        String API_KEY = "74vu0E5192sGEwf20sZl70rb";
        String SECRET_KEY = "T8eaBIi1dPzBCQMsygBux97MsHUGDyc3";
//         初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

//         可选：设置网络连接参数
//        建立连接的超时时间（单位：毫秒）
        client.setConnectionTimeoutInMillis(2000);
//        通过打开的连接传输数据的超时时间（单位：毫秒）
        client.setSocketTimeoutInMillis(60000);

//         可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

//         调用接口
        InputStream in=null;
        byte[] data=null;
//        读取图片
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
//        BASE64Encoder encoder = new BASE64Encoder();
//        image为保存图片的base64码

        String image =  Base64.getEncoder().encodeToString(data);

        HashMap<String, String> options = new HashMap<String, String>();
//        用户组号
        String groupId = "1";
        String imageType = "BASE64";
//        用户id
        JSONObject res = client.addUser(image, imageType, groupId, user_id, options);
        return true;
//        System.out.println(res.toString(2));
    }
    public static boolean update_user(String imgPath,String user_id) {
        String APP_ID = "25023966";
        String API_KEY = "74vu0E5192sGEwf20sZl70rb";
        String SECRET_KEY = "T8eaBIi1dPzBCQMsygBux97MsHUGDyc3";
//         初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

//         可选：设置网络连接参数
//        建立连接的超时时间（单位：毫秒）
        client.setConnectionTimeoutInMillis(2000);
//        通过打开的连接传输数据的超时时间（单位：毫秒）
        client.setSocketTimeoutInMillis(60000);

//         可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

//         调用接口
        InputStream in=null;
        byte[] data=null;
//        读取图片
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
//        BASE64Encoder encoder = new BASE64Encoder();
//        image为保存图片的base64码

        String image =  Base64.getEncoder().encodeToString(data);

        HashMap<String, String> options = new HashMap<String, String>();
//        用户组号
        String groupId = "1";
        String imageType = "BASE64";
//        用户id
        JSONObject res = client.updateUser(image, imageType, groupId, user_id, options);
        return true;
//        System.out.println(res.toString(2));
    }

}

