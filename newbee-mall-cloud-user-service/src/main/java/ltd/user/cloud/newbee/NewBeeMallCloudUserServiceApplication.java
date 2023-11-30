package ltd.user.cloud.newbee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NewBeeMallCloudUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewBeeMallCloudUserServiceApplication.class, args);
    }

}
