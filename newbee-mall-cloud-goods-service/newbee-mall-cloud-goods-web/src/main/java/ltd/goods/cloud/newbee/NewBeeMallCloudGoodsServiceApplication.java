package ltd.goods.cloud.newbee;

import ltd.user.cloud.newbee.openfeign.NewBeeCloudUserServiceFeign;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {NewBeeCloudUserServiceFeign.class,
                                            ltd.goods.cloud.newbee.openfeign.NewBeeCloudGoodsServiceFeign.class})
@MapperScan("ltd.goods.cloud.newbee.dao")
public class NewBeeMallCloudGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewBeeMallCloudGoodsServiceApplication.class, args);
    }

}
