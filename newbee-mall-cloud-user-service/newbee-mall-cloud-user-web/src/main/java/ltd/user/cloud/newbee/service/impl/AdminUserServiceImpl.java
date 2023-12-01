package ltd.user.cloud.newbee.service.impl;

import ltd.common.cloud.newbee.util.NumberUtil;
import ltd.common.cloud.newbee.util.SystemUtil;
import ltd.user.cloud.newbee.dao.AdminUserMapper;
import ltd.user.cloud.newbee.dao.NewBeeAdminUserTokenMapper;
import ltd.user.cloud.newbee.entity.AdminUser;
import ltd.user.cloud.newbee.entity.AdminUserToken;
import ltd.user.cloud.newbee.service.AdminUserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private NewBeeAdminUserTokenMapper newBeeAdminUserTokenMapper;

    @Override
    public String login(String userName, String password) {
        AdminUser loginAdminUser = adminUserMapper.login(userName, password);
        if (loginAdminUser != null) {
            // 登陆后执行修改token的操作
            String token = getNewToken(System.currentTimeMillis() + "", loginAdminUser.getAdminUserId());
            AdminUserToken adminUserToken = newBeeAdminUserTokenMapper.selectByPrimaryKey(loginAdminUser.getAdminUserId());
            // 当前时间
            Date now = new Date();
            // 过期时间
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000); // 过期时间48小时
            if (adminUserToken == null) {
                adminUserToken = new AdminUserToken();
                adminUserToken.setAdminUserId(loginAdminUser.getAdminUserId());
                adminUserToken.setToken(token);
                adminUserToken.setUpdateTime(now);
                adminUserToken.setExpireTime(expireTime);
                // 新增一条token数据
                if (newBeeAdminUserTokenMapper.insertSelective(adminUserToken) > 0) {
                    return token;
                }
            } else {
                adminUserToken.setToken(token);
                adminUserToken.setUpdateTime(now);
                adminUserToken.setExpireTime(expireTime);
                //更新
                if (newBeeAdminUserTokenMapper.updateByPrimaryKeySelective(adminUserToken) > 0) {
                    //修改成功后返回
                    return token;
                }
            }
        }
        return "登陆失败";
    }

    /**
     * 获取token值
     *
     * @param timeStr
     * @param userId
     * @return
     */
    private String getNewToken(String timeStr, Long userId) {
        String src = timeStr + userId + NumberUtil.genRandomNum(6);
        return SystemUtil.genToken(src);
    }

    @Override
    public AdminUser getUserDetailById(Long loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Long loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        // 当前用户非空才可以进行更改
        if (adminUser != null) {
            // 比较原密码是否正确
            if (originalPassword.equals(adminUser.getLoginPassword())) {
                // 设置新密码并修改
                adminUser.setLoginPassword(newPassword);
                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0 && newBeeAdminUserTokenMapper.deleteByPrimaryKey(loginUserId) > 0) {
                    // 修改成功且清空当前token则返回true
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Long loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //设置新名称并修改
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean logout(Long adminUserId) {
        return newBeeAdminUserTokenMapper.deleteByPrimaryKey(adminUserId) > 0;
    }

    @Override
    public AdminUser getUserDetailByToken(String token) {
        AdminUserToken adminUserToken = newBeeAdminUserTokenMapper.selectByToken(token);
        if (adminUserToken != null) {
            return adminUserMapper.selectByPrimaryKey(adminUserToken.getAdminUserId());
        }
        return null;
    }
}
