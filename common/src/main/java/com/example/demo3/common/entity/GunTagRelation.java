package com.example.demo3.common.entity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;


@Data
@Accessors(chain = true)
public class GunTagRelation {
        private BigInteger id;


        private Integer createTime;


        private Integer updateTime;


        private Integer isDeleted;


        private BigInteger gunId;


        private BigInteger tagId;



}