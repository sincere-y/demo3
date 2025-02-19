package com.example.demo3.app.domain;



import java.math.BigInteger;

public class GunListVo {

    private BigInteger gunId;
    private String image;
    private String title;
    private Integer createTime;

    public BigInteger getGunId() {
        return gunId;
    }

    public void setGunId(BigInteger gunId) {
        this.gunId = gunId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }
}
