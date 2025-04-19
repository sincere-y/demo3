package com.example.demo3.app.domain;






import com.example.demo3.module.entity.GunTagRelation;
import com.example.demo3.module.entity.Tag;
import lombok.Data;

import java.util.List;

@Data
public class GunInfoVo {

    private List<String> images;
    private String title;
    private String author;
    private String createTime;
    private List<BaseContentValueVo> content;
    private String categoryName;
    private String categoryImage;
    private List<Tag> tag;

}
