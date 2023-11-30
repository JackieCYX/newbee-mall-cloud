package ltd.user.cloud.newbee.dao;

import ltd.user.cloud.newbee.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {

    /**
     * 登陆方法
     *
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(@Param("userName") String userName, @Param("password") String password);
}
