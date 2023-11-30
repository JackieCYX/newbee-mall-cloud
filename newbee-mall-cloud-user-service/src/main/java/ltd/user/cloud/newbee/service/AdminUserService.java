package ltd.user.cloud.newbee.service;

public interface AdminUserService {

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    String login(String userName, String password);
}
