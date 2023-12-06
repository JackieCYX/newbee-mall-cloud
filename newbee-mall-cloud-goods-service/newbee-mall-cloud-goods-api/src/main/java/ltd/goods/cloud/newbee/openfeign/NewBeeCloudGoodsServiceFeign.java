package ltd.goods.cloud.newbee.openfeign;

import ltd.common.cloud.newbee.dto.Result;
import ltd.goods.cloud.newbee.dto.NewBeeMallGoodsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "newbee-mall-cloud-goods-service", path = "/goods")
public interface NewBeeCloudGoodsServiceFeign {

    @GetMapping(value = "/admin/goodsDetail")
    Result<NewBeeMallGoodsDTO> getGoodsDetail(@RequestParam(value = "goodsId") Long goodsId);

    @GetMapping(value = "/admin/listByGoodsIds")
    Result<List<NewBeeMallGoodsDTO>> listByGoodsIds(@RequestParam(value = "goodsIds") List<Long> goodsId);
}
