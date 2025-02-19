package com.example.demo3.module.app.domain;

import com.example.demo3.module.entity.Gun;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Accessors(chain = true)
public class ListVo {
    private BigInteger gunId;
    private String image;
    private String title;


}
