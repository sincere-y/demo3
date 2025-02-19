package com.example.demo3.module.service;



import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.mapper.GunMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class GunService {
    @Resource
    private GunMapper mapper;
    public Gun getGunInfoById(BigInteger id){
        return mapper.getById(id);
    }

    public List<Gun> getAllGunInfo(){return mapper.getAll();}


    public int createGun(String title,String author,String images,String content){
        int timestamp=(int)(System.currentTimeMillis()/1000);
        Gun gun=new Gun();
        gun.setTitle(title).setAuthor(author).setImages(images).setContent(content).setCreateTime(timestamp).setUpdateTime(timestamp).setIsDeleted(0);

        return mapper.insert(gun);
    }

    public int updateGun(BigInteger id,String title,String author,String images,String content){
        int timestamp=(int)(System.currentTimeMillis()/1000);
        Gun gun=new Gun();
       gun.setId(id).setTitle(title).setAuthor(author).setImages(images).setContent(content).setUpdateTime(timestamp);

        return mapper.update(gun);
    }

    public int deleteGun(BigInteger gunId){
        int timestamp=(int)(System.currentTimeMillis()/1000);
        Gun gun=new Gun();
        gun.setUpdateTime(timestamp);
        return mapper.delete(gunId);
    }


    //时间格式转换
    public String timeText(){
        Date date=new Date();
        long timestamp=date.getTime();
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置格式
        return format.format(timestamp);
    }



}
