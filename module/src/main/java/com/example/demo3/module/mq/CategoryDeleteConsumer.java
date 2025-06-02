package com.example.demo3.module.mq;

import com.example.demo3.common.entity.Gun;
import com.example.demo3.module.service.GunService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Component
public class CategoryDeleteConsumer {

    @Resource
    private GunService gunService;

    @RabbitListener(queues = "category.delete.queue")
    public void handleCategoryDelete(String categoryIdStr) {
        try {
            BigInteger categoryId = new BigInteger(categoryIdStr);

             List<Gun> guns = gunService.getGunsByCategoryIds(List.of(categoryId));
             for (Gun gun : guns) {
                 gunService.deleteGun(gun.getId());
             }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
