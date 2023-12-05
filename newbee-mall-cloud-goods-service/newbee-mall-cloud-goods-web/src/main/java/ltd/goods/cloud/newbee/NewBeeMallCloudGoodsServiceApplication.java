package ltd.goods.cloud.newbee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {ltd.user.cloud.newbee.openfeign.NewBeeCloudAdminUserServiceFeign.class,
                                            ltd.goods.cloud.newbee.openfeign.NewBeeCloudGoodsServiceFeign.class})
@MapperScan("ltd.goods.cloud.newbee.dao")
public class NewBeeMallCloudGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewBeeMallCloudGoodsServiceApplication.class, args);
    }

}
