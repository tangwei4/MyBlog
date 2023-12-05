package com.suye.personalblog.admincontroller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.suye.personalblog.StaticField;
import com.suye.personalblog.service.LogMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@Validated
public class LoginController {
    @Value("${admin.name}")
    private String name;
    @Value("${admin.password}")
    private String password;
    @Autowired
    private LogMessageService logMessageService;
    @Autowired
    private DefaultKaptcha captchaProducer;

    @RequestMapping("/admin/logins")
    public ModelAndView login(Model model) {
        return new ModelAndView("admin/login", "login", model);
    }

    @RequestMapping("/admin/login")
    @ResponseBody
    public String loginVerification(HttpServletRequest request,
                                    @RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    @RequestParam("captcha") String captcha,
                                    Model model) {
        if (!username.equalsIgnoreCase(name)) {
            return "{\n" + "  \"msg\":\"账户不存在\"\n" + "}";
        }

        if (!password.equalsIgnoreCase(this.password)) {
            return "{\n" + "  \"msg\":\"密码不正确\"\n" + "}";
        }

        String storedCaptcha = (String) request.getSession().getAttribute("kaptcha");
        if (storedCaptcha == null || !storedCaptcha.equalsIgnoreCase(captcha)) {
            return "{\n" + "  \"msg\":\"验证码输入有误\"\n" + "}";
        }

        request.getSession().setAttribute("isLogin", "success");
        logMessageService.addALog(StaticField.LOGIN_ACTION);
        return "{\n" + "  \"success\":\"1\"\n" + "}";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        logMessageService.addALog(StaticField.LOGOUT_ACTION);
        return "redirect:/index";
    }

    @GetMapping("/captcha")
    public void handleRequest(HttpServletResponse response,HttpServletRequest request) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        //每次刷新重复吧验证码加入到session
        request.getSession().setAttribute("kaptcha", capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

}
