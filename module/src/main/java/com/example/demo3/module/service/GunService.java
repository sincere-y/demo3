package com.example.demo3.module.service;


import com.alibaba.fastjson.JSON;
import com.example.demo3.module.dto.ArticleContentDto;
import com.example.demo3.module.dto.GunDto;
import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.entity.Category;
import com.example.demo3.module.entity.GunTagRelation;
import com.example.demo3.module.entity.Tag;
import com.example.demo3.module.mapper.GunMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GunService {
    @Resource
    private GunMapper mapper;
    @Resource
    private CategoryService categoryService;
@Resource
private TagService tagService;
@Resource
private GunTagRelationService gunTagRelationService;



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

    @Transactional(rollbackFor = Exception.class)
    public BigInteger edit(BigInteger id,String title,String author,String images,String content,BigInteger categoryId,String[] tagsString){
        List<BigInteger> tagIds=new ArrayList<>();
        if(tagsString!=null&&tagsString.length>0){
            for (String part : tagsString) {
                Tag tagInfo = tagService.getByName(part);

                if (tagInfo != null) {
                    tagIds.add(tagInfo.getId());
                } else {
                    BigInteger tagId = tagService.edit(part);
                    tagIds.add(tagId);
                }
            }
        }else {
            throw new RuntimeException("tag为空");
        }
        if(title!=null&&author!=null&&images!=null&&content!=null&&categoryId!=null){
            int timestamp = (int) (System.currentTimeMillis() / 1000);
            Gun gun = new Gun();
            Category category=categoryService.getById(categoryId);
            if(category != null){
                try {
                    List<ArticleContentDto> checkContents = JSON.parseArray(content, ArticleContentDto.class);
                    for(ArticleContentDto checkContent:checkContents){
                        if(!ArticleDefine.isArticleContentType(checkContent.getType())){
                            throw new RuntimeException("article content is error");
                        }
                    }
                } catch (Exception cause) {
                    // ignores
                    throw new RuntimeException("article content is error");
                }

                gun.setTitle(title).setAuthor(author).setImages(images).setContent(content).setUpdateTime(timestamp).setCategoryId(categoryId);
            }
            else {
                throw new RuntimeException("categoryId不存在");
            }
            if(id==null){
                gun.setCreateTime(timestamp).setIsDeleted(0);
                insert(gun);
                BigInteger gunId=gun.getId();
                for (BigInteger tagId : tagIds) {
                    gunTagRelationService.edit(gunId, tagId);
                }
                return gunId;
            }
            else {
                if(mapper.getById(id)!=null) {
                    gun.setId(id);
                    update(gun);
                    List<GunTagRelation> gunTagRelations = gunTagRelationService.getByGunId(id);
                    List<BigInteger> currentTagIds = new ArrayList<>();
                    for (GunTagRelation currentTag : gunTagRelations) {
                        currentTagIds.add(currentTag.getTagId());
                    }
                    for (BigInteger tagId : tagIds) {
                        if (!currentTagIds.contains(tagId)) {
                            gunTagRelationService.edit(id, tagId);
                        }
                    }
                    for (GunTagRelation currentTag : gunTagRelations) {
                        BigInteger currentTagId = currentTag.getTagId();
                        if (!tagIds.contains(currentTagId)) {
                            gunTagRelationService.delete(currentTag.getId());
                        }
                    }

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


    public int deleteGun(BigInteger gunId){
        if(gunId!=null) {
            int timestamp = (int) (System.currentTimeMillis() / 1000);
            return delete(gunId, timestamp);
        }
        else return 0;
    }

    public List<Gun> getInfoPage(Integer page,Integer pageSize,String gunName){
        if(page!=null&&pageSize!=null) {
            List<Integer> ids=categoryService.getCategoryId(gunName);
            StringBuilder resultIds = new StringBuilder();
            if (ids != null) {
                for (int i = 0; i < ids.size(); i++) {
                    if (i > 0) {
                        resultIds.append(",");
                    }
                    resultIds.append(ids.get(i));
                }
            }
            String categoryIds = resultIds.toString();
            Integer start = (page - 1) * pageSize;
            return mapper.getInfoPage(start, pageSize,gunName, categoryIds);
        }
        else {
            throw new RuntimeException("参数内容为空");
        }
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

    //base64解码
    public String decodeURLParam(String encodedValue) {
        try {
            String decodedValue = URLDecoder.decode(encodedValue, "UTF-8");
            return decodedValue;
        } catch (Exception e) {
            // 解码出错时的异常处理
            e.printStackTrace();
        }
        return null;
    }

    public List<GunDto> getGunDtoList(Integer page, Integer pageSize, String gunName){
        if(page!=null&&pageSize!=null) {
//            List<Integer> ids=categoryService.getCategoryId(gunName);
//            StringBuilder resultIds = new StringBuilder();
//            if (ids != null) {
//                for (int i = 0; i < ids.size(); i++) {
//                    if (i > 0) {
//                        resultIds.append(",");
//                    }
//                    resultIds.append(ids.get(i));
//                }
//            }
//            String categoryIds = resultIds.toString();
            Integer start = (page - 1) * pageSize;
            return mapper.getGunDtoList(start,pageSize,gunName);
        }
        else {
            throw new RuntimeException("参数内容为空");
        }

    }


    public int  updateImageUrl(BigInteger gunId,String url){

        return mapper.updateImageUrl(gunId,url);

    }
    public float calculateAspectRatio(String imageUrl) {
        Pattern pattern = Pattern.compile("_(\\d+)x(\\d+)");
        Matcher matcher = pattern.matcher(imageUrl);
        if (matcher.find()) {
            int width = Integer.parseInt(matcher.group(1));
            int height = Integer.parseInt(matcher.group(2));
            if (height == 0) {
                return 0;
            }
            return (float) width / height;
        }
        return 0;
    }

   public List<Gun> getGunsByCategoryIds(List<BigInteger> ids){

       StringBuilder resultIds = new StringBuilder();
       if (ids != null) {
           for (int i = 0; i < ids.size(); i++) {
               if (i > 0) {
                   resultIds.append(",");
               }
               resultIds.append(ids.get(i));
           }
       }
       String categoryIds = resultIds.toString();
        return mapper.getGunsByCategoryIds(categoryIds);
   }
}
