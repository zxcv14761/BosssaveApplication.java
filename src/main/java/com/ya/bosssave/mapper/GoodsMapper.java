package com.ya.bosssave.mapper;

import com.ya.bosssave.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {

    void addGoods(String goods_name);

    Goods findGoodsByName(String goods_name);
    Goods findGoodsById(String id);

    List<Goods> findAllGoods();

    int modifyGoods(String id,String goods_name);

    int delGoodsById(String id);

}
