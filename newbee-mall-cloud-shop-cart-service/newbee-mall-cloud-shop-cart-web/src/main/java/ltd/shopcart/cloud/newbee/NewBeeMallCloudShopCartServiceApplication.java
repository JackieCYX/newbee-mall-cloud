package ltd.shopcart.cloud.newbee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("ltd.shopcart.cloud.newbee.dao")
@EnableFeignClients(basePackageClasses = {ltd.user.cloud.newbee.openfeign.NewBeeCloudUserServiceFeign.class})
public class NewBeeMallCloudShopCartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewBeeMallCloudShopCartServiceApplication.class, args);
    }

}
