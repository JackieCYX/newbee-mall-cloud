package ltd.user.cloud.newbee.controller;

import ltd.user.cloud.newbee.config.annotation.TokenToAdminUser;
import ltd.user.cloud.newbee.controller.param.AdminLoginParam;
import ltd.user.cloud.newbee.controller.param.UpdateAdminNameParam;
import ltd.user.cloud.newbee.controller.param.UpdateAdminPasswordParam;
import ltd.user.cloud.newbee.entity.AdminUser;
import ltd.user.cloud.newbee.entity.AdminUserToken;
import ltd.user.cloud.newbee.service.AdminUserService;
import ltd.user.cloud.newbee.util.Result;
import ltd.user.cloud.newbee.util.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
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
        logger.info("manage login api,adminName={},loginResult={}", adminLoginParam.getUserName(), loginResult);

        // 登录成功
        if (!StringUtils.isEmpty(loginResult) && loginResult.length() == 32) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        // 登录失败
        return ResultGenerator.genFailResult(loginResult);
    }

    @PostMapping("/adminUser/profile")
    public Result profile(@TokenToAdminUser AdminUserToken adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        AdminUser adminUserEntity = adminUserService.getUserDetailById(adminUser.getAdminUserId());
        if (adminUserEntity != null) {
            adminUserEntity.setLoginPassword("******");
            Result result = ResultGenerator.genSuccessResult();
            result.setData(adminUserEntity);
            return result;
        }
        return ResultGenerator.genFailResult("无此用户数据");
    }

    @PutMapping("/adminUser/password")
    public Result passwordUpdate(@RequestBody @Valid UpdateAdminPasswordParam adminPasswordParam,
                                 @TokenToAdminUser AdminUserToken adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        if (adminUserService.updatePassword(adminUser.getAdminUserId(), adminPasswordParam.getOriginalPassword(), adminPasswordParam.getNewPassword())) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("DB ERROR");
        }
    }

    @PutMapping("/adminUser/name")
    public Result nameUpdate(@RequestBody @Valid UpdateAdminNameParam adminNameParam,
                             @TokenToAdminUser AdminUserToken adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        if (adminUserService.updateName(adminUser.getAdminUserId(), adminNameParam.getLoginUserName(), adminNameParam.getNickName())) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("DB ERROR");
        }
    }

    @DeleteMapping("/logout")
    public Result logout(@TokenToAdminUser AdminUserToken adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        adminUserService.logout(adminUser.getAdminUserId());
        return ResultGenerator.genSuccessResult();
    }
}
