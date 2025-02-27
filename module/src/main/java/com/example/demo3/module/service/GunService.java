package com.example.demo3.module.service;



import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.mapper.GunMapper;
import org.apache.ibatis.annotations.Param;
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
    public Gun getById(BigInteger id){
        return mapper.getById(id);
    }
    public Gun extractById(BigInteger id){
        return mapper.extractById(id);
    };
    public List<Gun> getAllInfo(){
        return mapper.getAllInfo();
    }
    public int insert(Gun gun){
        return mapper.insert(gun);
    }
    public int update(Gun gun){
        return mapper.update(gun);
    }
    public int delete(BigInteger gunId,int timestamp){
        return mapper.delete(gunId,timestamp);
    }
    public int getTotal(){
        return mapper.getTotal();
    }

    public int createGun(String title,String author,String images,String content){
        int timestamp=(int)(System.currentTimeMillis()/1000);
        Gun gun=new Gun();
        gun.setTitle(title).setAuthor(author).setImages(images).setContent(content).setCreateTime(timestamp).setUpdateTime(timestamp).setIsDeleted(0);

        return insert(gun);
    }

    public int updateGun(BigInteger id,String title,String author,String images,String content){
        int timestamp=(int)(System.currentTimeMillis()/1000);
        Gun gun=new Gun();
       gun.setId(id).setTitle(title).setAuthor(author).setImages(images).setContent(content).setUpdateTime(timestamp);

        return update(gun);
    }

    public int deleteGun(BigInteger gunId){
        int timestamp=(int)(System.currentTimeMillis()/1000);
        Gun gun=new Gun();
        gun.setUpdateTime(timestamp);
        return delete(gunId,timestamp);
    }

    public List<Gun> getInfoPage(Integer page,Integer pageSize,String gunName){

            Integer start = (page - 1) * pageSize;
            return mapper.getInfoPage(start,pageSize,gunName);
    }

    //创建时间格式转换
    public String timeText(Integer createTime){

        long timestamp = (long) createTime * 1000;
        Date date=new Date(timestamp);

        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置格式
        return format.format(date);
    }


}
