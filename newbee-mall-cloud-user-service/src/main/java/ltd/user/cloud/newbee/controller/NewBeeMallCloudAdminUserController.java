package ltd.user.cloud.newbee.controller;

import ltd.user.cloud.newbee.controller.param.AdminLoginParam;
import ltd.user.cloud.newbee.service.AdminUserService;
import ltd.user.cloud.newbee.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class NewBeeMallCloudAdminUserController {

    private static final Logger logger = LoggerFactory.getLogger(NewBeeMallCloudAdminUserController.class);

    @Resource
    private AdminUserService adminUserService;

    @GetMapping("/users/admin/test/{userId}")
    public String test(@PathVariable("userId") int userId) {
        String userName = "user:" + userId;
        return userName;
    }

    @PostMapping("/adminUser/login")
    public Result<String> login(@RequestBody @Valid AdminLoginParam adminLoginParam) {
        String loginResult = adminUserService.login(adminLoginParam.getUserName(),
                adminLoginParam.getPasswordMd5());
    }
}
