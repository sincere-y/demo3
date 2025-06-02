package com.example.demo3.category.mq;

import com.example.demo3.category.feign.GunFeign;
import com.example.demo3.common.entity.Gun;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Component
public class CategoryDeleteConsumer {

    @Resource
    private GunFeign gunFeign;

    @RabbitListener(queues = "category.delete.queue")
    public void handleCategoryDelete(String categoryIdStr) {
        try {
            BigInteger categoryId = new BigInteger(categoryIdStr);

             List<Gun> guns = gunFeign.getGunsByCategoryIds(List.of(categoryId));
             for (Gun gun : guns) {
                 gunFeign.deleteGun(gun.getId());
             }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
