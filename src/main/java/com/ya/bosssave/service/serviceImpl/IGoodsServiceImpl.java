package com.ya.bosssave.service.serviceImpl;

import com.ya.bosssave.mapper.GoodsMapper;
import com.ya.bosssave.pojo.Goods;
import com.ya.bosssave.service.IGoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IGoodsServiceImpl implements IGoodService {

    @Resource
    GoodsMapper goodsMapper;

    @Override
    public void addGoods(String goods_name) {
        goodsMapper.addGoods(goods_name);
    }

    @Override
    public Goods findGoodsByName(String goods_name) {
        return goodsMapper.findGoodsByName(goods_name);
    }

    @Override
    public List<Goods> findAllGoods() {
        return goodsMapper.findAllGoods();
    }

    @Override
    public Goods findGoodsById(String id) {

        Goods goods = goodsMapper.findGoodsById(id);
        if (goods == null){
            throw new RuntimeException();
        }
        return goods;
    }

    @Override
    public int delGoodsById(String id) {
        int count = goodsMapper.delGoodsById(id);
        if (count != 1){
            throw new RuntimeException();
        }
        return count;
    }

    @Override
    public int modifyGoods(String id, String goods_name) {
        int count = goodsMapper.modifyGoods(id, goods_name);
        if (count != 1){
            throw new RuntimeException();
        }
        return count;
    }
}
