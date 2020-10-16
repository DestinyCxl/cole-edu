package com.cole.service.cms.controller.admin;

import com.cole.common.base.result.R;
import com.cole.service.cms.entity.Ad;
import com.cole.service.cms.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Cxl
 * @since: 2020-09-29
 **/
//@CrossOrigin //解决跨域问题
@Api(description = "广告推荐")
@RestController
@RequestMapping("/api/cms/ad")
public class ApiAdController {

    @Autowired
    private AdService adService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 4.redis测试删除数据
     * @param key
     * @return
     */
    @DeleteMapping("/remove-test/{key}")
    public R removeAd(@PathVariable String key){
        Boolean delete = redisTemplate.delete(key);
        System.out.println(delete);//是否删除成功
        Boolean hasKey = redisTemplate.hasKey(key);
        System.out.println(hasKey);//key是否存在
        return R.ok();
    }

    /**
     * 3.redis测试取出数据
     * @param key
     * @return
     */
    @GetMapping("/get-test/{key}")
    public R getAd(@PathVariable String key){
        Ad ad = (Ad)redisTemplate.opsForValue().get(key);
        return R.ok().data("ad", ad);
    }

    /**
     * 2.redis测试存入数据
     * @param ad
     * @return
     */
    @PostMapping("/save-test")
    public R saveAd(@RequestBody Ad ad){
        //redisTemplate.opsForValue().set("ad1", ad);
        redisTemplate.opsForValue().set("index::ad", ad);
        return R.ok();
    }
    /**
     * 1.根据推荐位id显示广告推荐
     * @param adTypeId
     * @return
     */
    @ApiOperation("根据推荐位id显示广告推荐")
    @GetMapping("/list/{adTypeId}")
    public R listByAdTypeId(@ApiParam(value = "推荐位id", required = true) @PathVariable String adTypeId) {

        List<Ad> ads = adService.selectByAdTypeId(adTypeId);
        return R.ok().data("items", ads);
    }
//
//    redisTemplate.opsForValue(); //操作字符串
//    redisTemplate.opsForHash(); //操作hash
//    redisTemplate.opsForList(); //操作list
//    redisTemplate.opsForSet(); //操作set
//    redisTemplate.opsForZSet(); //操作有序set
}