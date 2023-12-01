package ltd.user.cloud.newbee.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "newbee-mall-cloud-user-service", path = "/users")
public interface NewBeeCloudAdminUserServiceFeign {

    @GetMapping(value = "/admin/{token}")
    String getAdminUserByToken(@PathVariable(value = "token") String token);
}
