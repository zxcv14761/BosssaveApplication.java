package com.ya.bosssave.service;


import com.ya.bosssave.pojo.Goods;

import java.util.List;

public interface IGoodService {

    void addGoods(String goods_name);

    Goods findGoodsByName(String goods_name);

    List<Goods> findAllGoods();

    Goods findGoodsById(String id);

    int delGoodsById(String id);

    int modifyGoods(String id,String goods_name);
}
