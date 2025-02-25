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
        return mapper.delete(gunId,timestamp);
    }

    public int getInfoTotal(){
        return mapper.getTotal();
    }

    public List<Gun> getInfoPage(Integer start,Integer pageSize){
        return mapper.getPageSizeData(start,pageSize);
    }

    public Integer getStratData(Integer page,Integer pageSize){
        return (page-1)*pageSize;

    }

    //创建时间格式转换
    public String timeText(Integer createTime){

        long timestamp = (long) createTime * 1000;
        Date date=new Date(timestamp);

        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置格式
        return format.format(date);
    }



}
