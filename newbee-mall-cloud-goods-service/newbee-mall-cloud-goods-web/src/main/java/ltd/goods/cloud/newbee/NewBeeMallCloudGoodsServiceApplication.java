package ltd.goods.cloud.newbee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("ltd.goods.cloud.newbee.dao")
public class NewBeeMallCloudGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewBeeMallCloudGoodsServiceApplication.class, args);
    }

}
