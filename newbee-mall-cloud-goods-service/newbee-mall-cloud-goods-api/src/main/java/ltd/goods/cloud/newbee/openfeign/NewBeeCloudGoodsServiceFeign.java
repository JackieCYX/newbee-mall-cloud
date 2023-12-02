package ltd.goods.cloud.newbee.openfeign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "newbee-mall-cloud-goods-service", path = "/goods")
public interface NewBeeCloudGoodsServiceFeign {

}
