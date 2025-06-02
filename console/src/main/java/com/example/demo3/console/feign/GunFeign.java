package com.example.demo3.console.feign;

import com.example.demo3.common.dto.GunDto;
import com.example.demo3.common.entity.Gun;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

@FeignClient(name = "gun",contextId = "GunFeign")
public interface GunFeign {
    @RequestMapping("/gun/getGunById")
     Gun getById(@RequestParam(name ="id") BigInteger id);
    @RequestMapping("/gun/extractById")
     Gun extractById(@RequestParam(name ="id")BigInteger id);
    @RequestMapping("/gun/allInfo")
     List<Gun> getAllInfo();

    @RequestMapping("/gun/total")
     int getTotal(@RequestParam(name ="gunName")String gunName);

    @RequestMapping("/gun/edit")
     BigInteger edit(@RequestParam(name ="id")BigInteger id,
                           @RequestParam(name ="title")String title,
                           @RequestParam(name ="author")String author,
                           @RequestParam(name ="images")String images,
                           @RequestParam(name ="content")String content,
                           @RequestParam(name ="categoryId")BigInteger categoryId,
                           @RequestParam(name ="arrTags")String[] arrTags);

    @RequestMapping("/gun/delete")
     int deleteGun(@RequestParam(name ="gunId")BigInteger gunId);
    @RequestMapping("/gun/infoPage")
     List<Gun> getInfoPage(@RequestParam(name ="page")Integer page,
                                 @RequestParam(name ="pageSize")Integer pageSize,
                                 @RequestParam(name ="gunName")String gunName);


    @RequestMapping("/gun/updateImageUrl")
     int  updateImageUrl(@RequestParam(name ="gunId")BigInteger gunId,
                               @RequestParam(name ="url")String url);
    @RequestMapping("/gun/calculateAspectRatio")
     float calculateAspectRatio(@RequestParam(name ="imageUrl")String imageUrl) ;
    @RequestMapping("/gun/gunsByCategoryIds")
    List<Gun> getGunsByCategoryIds(@RequestParam(name ="ids")List<BigInteger> ids);
    @RequestMapping("/timeText")
     String timeText(@RequestParam(name ="createTime")Integer createTime) ;

    @RequestMapping("/gun/getGunDtoList")
    List<GunDto> getGunDtoList(@RequestParam(name ="page")Integer page,
                               @RequestParam(name ="pageSize")Integer pageSize,
                               @RequestParam(name ="gunName")String gunName);
}
