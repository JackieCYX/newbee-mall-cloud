package ltd.common.cloud.newbee.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdminUserToken implements Serializable {
    private Long adminUserId;

    private String token;

    private Date updateTime;

    private Date expireTime;
}
