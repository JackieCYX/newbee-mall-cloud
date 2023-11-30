package ltd.user.cloud.newbee.dao;

import ltd.user.cloud.newbee.entity.AdminUserToken;

public interface NewBeeAdminUserTokenMapper {
    AdminUserToken selectByPrimaryKey(Long adminUserId);
}
