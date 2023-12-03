package ltd.goods.cloud.newbee.dao;

import ltd.common.cloud.newbee.dto.PageQueryUtil;
import ltd.goods.cloud.newbee.entity.GoodsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insert(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    int getTotalGoodsCategories(PageQueryUtil pageUtil);

    List<GoodsCategory> findGoodsCategoryList(PageQueryUtil pageUtil);

    GoodsCategory selectByPrimaryKey(Long categoryId);

    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(@Param("parentIds") List<Long> parentIds,
                                                           @Param("categoryLevel") int categoryLevel,
                                                           @Param("number") int number);

    GoodsCategory selectByLevelAndName(@Param("categoryLevel") Byte categoryLevel,
                                       @Param("categoryName") String categoryName);

    int insertSelective(GoodsCategory goodsCategory);

    int updateByPrimaryKeySelective(GoodsCategory goodsCategory);

    int deleteBatch(Long[] ids);
}
