package ltd.shopcart.cloud.newbee.openfeign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "newbee-mall-cloud-shop-cart-service", path = "/shop-cart")
public interface NewBeeCloudShopCartServiceFeign {
}
