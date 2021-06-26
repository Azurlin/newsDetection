package com.ldu.newsdisplay.user.controller;

import com.ldu.newsdisplay.user.domain.User;
import com.ldu.newsdisplay.user.service.UserService;
import com.ldu.newsdisplay.utils.ControllerResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Resource
    private UserService userService;

    @GetMapping("/user/query")
    public ControllerResponse getUserInfo(@RequestBody User user){
        try {
            List<User> resList = userService.queryUser(user);
            return ControllerResponse.success(resList);
        }catch (Exception e){
            return ControllerResponse.fail();
        }
    }
    @GetMapping("/user/login")
    public ControllerResponse getUserInfoByUserName(HttpServletResponse response, @RequestParam(value = "userName") String userName,
                                                    @RequestParam(value = "userPassword") String userPassword){
        try {
            User res = userService.queryUserByUserName(userName,userPassword);
            if(res != null){
                Cookie userCookie = new Cookie("userId",res.getUserId().toString());
                userCookie.setMaxAge(1000);
                userCookie.setHttpOnly(false);
                userCookie.setPath("/");
                //response.setHeader("Set-cookie");
                response.setHeader("sameSite","Lax");
                //userCookie.setSecure(true);
                response.addCookie(userCookie);
            }
            return ControllerResponse.success(res);
        }catch (Exception e){
            return ControllerResponse.fail();
        }
    }
    @PostMapping("/user/add")
    public ControllerResponse insertUser(@RequestBody User user){
        try {
            Integer res = userService.insertUser(user); //返回 1 成功注册 0用户名重复
            return ControllerResponse.success(res);
        }catch (Exception e){
            return ControllerResponse.fail();
        }
    }
    @PostMapping("/user/delete")
    public ControllerResponse deleteUser(@RequestBody User user){
        try {
            userService.deleteUser(user);
            return ControllerResponse.success();
        }catch (Exception e){
            return ControllerResponse.fail();
        }
    }
    @PostMapping("/user/modify")
    public ControllerResponse updateUser(@RequestBody User user){
        try {
            userService.updateUser(user);
            return ControllerResponse.success();
        }catch (Exception e){
            return ControllerResponse.fail();
        }
    }


}
