package com.example.demo3.module.service;



import ch.qos.logback.classic.spi.IThrowableProxy;
import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.entity.Category;
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
    @Resource
    private CategoryService categoryService;

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
    public int getTotal(String gunName){
        return mapper.getTotal(gunName);
    }

    public BigInteger edit(BigInteger id,String title,String author,String images,String content,BigInteger categoryId){
        if(title!=null||author!=null||images!=null||content!=null||categoryId!=null){
            int timestamp = (int) (System.currentTimeMillis() / 1000);
            Gun gun = new Gun();
            Category category=categoryService.getById(categoryId);
            if(category != null){
                gun.setTitle(title).setAuthor(author).setImages(images).setContent(content).setUpdateTime(timestamp).setCategoryId(categoryId);
            }
            else {
                throw new RuntimeException("categoryId不存在");
            }
            if(id==null){
                gun.setCreateTime(timestamp).setIsDeleted(0);
                insert(gun);
                return gun.getId();
            }
            else {
                if(mapper.getById(id)!=null||mapper.getByCategoryId(categoryId)!=null) {
                    gun.setId(id);
                    update(gun);
                    return gun.getId();
                }
                else {
                    throw new RuntimeException("id不存在");
                }
            }
        }
        else {
            throw new RuntimeException("参数内容为空");
        }

    }
//    public int createGun(String title,String author,String images,String content){
//        if(title!=null||author!=null||images!=null||content!=null) {
//            int timestamp = (int) (System.currentTimeMillis() / 1000);
//            Gun gun = new Gun();
//            gun.setTitle(title).setAuthor(author).setImages(images).setContent(content).setCreateTime(timestamp).setUpdateTime(timestamp).setIsDeleted(0);
//            return insert(gun);
//        }
//        else return 0;
//    }
//    public int updateGun(BigInteger id,String title,String author,String images,String content){
//        if(id!=null||title!=null||author!=null||images!=null||content!=null) {
//            int timestamp = (int) (System.currentTimeMillis() / 1000);
//            Gun gun = new Gun();
//            gun.setId(id).setTitle(title).setAuthor(author).setImages(images).setContent(content).setUpdateTime(timestamp);
//            return update(gun);
//        }
//        else return 0;
//    }

    public int deleteGun(BigInteger gunId){
        if(gunId!=null) {
            int timestamp = (int) (System.currentTimeMillis() / 1000);
            return delete(gunId, timestamp);
        }
        else return 0;
    }

    public List<Gun> getInfoPage(Integer page,Integer pageSize,String gunName){

            Integer start = (page - 1) * pageSize;
            return mapper.getInfoPage(start, pageSize, gunName);

    }

    //创建时间格式转换
    public String timeText(Integer createTime) {
        if (createTime != null) {
            long timestamp = (long) createTime * 1000;
            Date date = new Date(timestamp);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置格式
            return format.format(date);
        }
        return null;
    }


}
