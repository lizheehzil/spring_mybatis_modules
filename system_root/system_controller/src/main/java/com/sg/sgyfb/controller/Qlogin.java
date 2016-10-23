package com.sg.sgyfb.controller;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lizhe on 2016/10/5.
 */
@Controller
public class Qlogin {

    @RequestMapping("login.shtml")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("afterlogin.shtml")
    public String afterLogin(HttpServletRequest request){
        try {
            AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);

            String accessToken   = null,
                    openID        = null;
            long tokenExpireIn = 0L;
            if (accessTokenObj.getAccessToken().equals("")) {
//                我们的网站被CSRF攻击了或者用户取消了授权
//                做一些数据统计工作
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                System.out.println("欢迎"+openID+"用户++++++++++++++++++++++++++++++++++++++++++++");

                UserInfo userInfo = new UserInfo(accessToken,openID);
                System.out.println(userInfo.getUserInfo().getNickname());
            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
