package ltd.user.cloud.newbee.openfeign;

import ltd.common.cloud.newbee.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "newbee-mall-cloud-user-service", path = "/users")
public interface NewBeeCloudAdminUserServiceFeign {

    @GetMapping(value = "/admin/{token}")
    Result getAdminUserByToken(@PathVariable(value = "token") String token);
}
