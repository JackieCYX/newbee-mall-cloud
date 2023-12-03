package ltd.goods.cloud.newbee.openfeign;

import ltd.common.cloud.newbee.dto.Result;
import ltd.goods.cloud.newbee.dto.NewBeeMallGoodsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "newbee-mall-cloud-goods-service", path = "/goods")
public interface NewBeeCloudGoodsServiceFeign {

    Result<NewBeeMallGoodsDTO> getGoodsDetail(@RequestParam(value = "goodsId") Long goodsId);
}
