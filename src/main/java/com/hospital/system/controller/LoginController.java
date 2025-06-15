package com.hospital.system.controller;

import com.hospital.system.entity.User;
import com.hospital.system.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // 处理登录表单提交
    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session, // 我们需要用到 HttpSession
                               Model model) {

        System.out.println("--- processLogin 方法被成功调用！接收到用户名: " + username + " ---");

        Optional<User> userOpt = userService.login(username, password);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // 登录成功！将用户信息存入 Session，作为“通行证”
            session.setAttribute("loggedInUser", user);

            // 根据角色跳转到不同页面
            if ("ROLE_ADMIN".equals(user.getRole())) {
                return "redirect:/admin/dashboard"; // 跳转到管理员主页
            } else if ("ROLE_DOCTOR".equals(user.getRole())) {
                return "redirect:/patient/list"; // 跳转到医生主页
            }
        }

        // 登录失败，返回登录页并显示错误信息
        model.addAttribute("loginError", "用户名或密码错误！");
        return "login";
    }

    // 处理登出请求
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 销毁Session，即撕掉“通行证”
        return "redirect:/login";
    }

    // 在 LoginController.java 中
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard() {
        return "admin_dashboard";
    }
}