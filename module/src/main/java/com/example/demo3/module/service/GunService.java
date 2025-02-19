package com.example.demo3.module.service;



import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.mapper.GunMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigInteger;
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
        gun.setTitle(title).setAuthor(author).setImages(images).setContent(content).setCreate_time(timestamp).setUpdate_time(timestamp).setIs_deleted(0);

        return mapper.insert(gun);
    }

    public int updateGun(BigInteger id,String title,String author,String images,String content){
        int timestamp=(int)(System.currentTimeMillis()/1000);
        Gun gun=new Gun();
       gun.setId(id).setTitle(title).setAuthor(author).setImages(images).setContent(content).setUpdate_time(timestamp);

        return mapper.update(gun);
    }

    public int deleteGun(BigInteger gunId){return mapper.delete(gunId);}




}
