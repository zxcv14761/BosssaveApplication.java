package com.ya.bosssave.controller;

import com.ya.bosssave.pojo.Goods;
import com.ya.bosssave.service.IGoodService;
import com.ya.bosssave.util.jwtTokenValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value = "myApi", tags = "商品API接口")
public class GoodsController {


    @Resource
    IGoodService goodService;

    @ApiOperation("新增商品")
    @jwtTokenValidation
    @PostMapping("/goods/add")
    public ResponseEntity<Object> test2(@ApiParam("goods_name")  String goods_name) {
        try {

            goodService.addGoods(goods_name);
            Goods goods = goodService.findGoodsByName(goods_name);
            return ResponseEntity.status(HttpStatus.OK).body(goods);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 系統錯誤");
        }
    }

    @ApiOperation("取得所有商品")
    @jwtTokenValidation
    @GetMapping("/goods")
    public ResponseEntity<Object> test3() {
        try {

            List<Goods> allGoods = goodService.findAllGoods();
            return ResponseEntity.status(HttpStatus.OK).body(allGoods);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 系統錯誤");
        }
    }

    @ApiOperation("取得指定商品")
    @jwtTokenValidation
    @GetMapping("/goods/{id}")
    public ResponseEntity<Object> test4(@ApiParam("goods_id") @PathVariable String id) {
        try {

            Goods goods = goodService.findGoodsById(id);
            return ResponseEntity.status(HttpStatus.OK).body(goods);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 系統錯誤");
        }
    }

    @ApiOperation("更新指定商品")
    @jwtTokenValidation
    @PutMapping("/goods/{id}")
    public ResponseEntity<Object> test5(@ApiParam("goods") @PathVariable String id, String goods_name) {
        try {
            //更新商品區
            goodService.modifyGoods(id, goods_name);
            Goods goods = new Goods();
            goods.setId(Integer.valueOf(id));
            goods.setName(goods_name);

            return ResponseEntity.status(HttpStatus.OK).body(goods);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 系統錯誤");
        }
    }
    @ApiOperation("刪除指定商品")
    @jwtTokenValidation
    @DeleteMapping("/goods/{id}")
    public ResponseEntity<Object> test6(@ApiParam("goods") @PathVariable String id) {
        try {
            //更新商品區
            goodService.delGoodsById(id);
            return ResponseEntity.status(HttpStatus.OK).body("成功");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 系統錯誤");
        }
    }

}
