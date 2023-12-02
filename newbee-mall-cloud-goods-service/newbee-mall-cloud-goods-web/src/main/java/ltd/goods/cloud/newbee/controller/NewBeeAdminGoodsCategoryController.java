package ltd.goods.cloud.newbee.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.common.cloud.newbee.dto.Result;
import ltd.common.cloud.newbee.dto.ResultGenerator;
import ltd.goods.cloud.newbee.config.annotation.TokenToAdminUser;
import ltd.goods.cloud.newbee.entity.LoginAdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "v1", tags = "后台管理系统分类模块接口")
@RequestMapping("/goods/admin")
public class NewBeeAdminGoodsCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(NewBeeAdminGoodsCategoryController.class);

    @GetMapping("/testLoginAdminUser")
    @ApiOperation(value = "测试", notes = "测试")
    public Result testLoginAdminUser(@TokenToAdminUser LoginAdminUser adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        return ResultGenerator.genSuccessResult();
    }
}
