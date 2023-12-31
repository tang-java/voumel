package com.volume.up.pojo;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 检查项
 * @author 小唐
 */
public class CheckItem implements Serializable {
    private static final long serialVersionUID = 8464196403944961412L;
    private Integer id;//主键
    @NotBlank(message = "项目编码不能为空")
    private String code;//项目编码
    @NotBlank(message = "项目名称不能为空")
    private String name;//项目名称
    private String sex;//适用性别
    private String age;//适用年龄（范围），例如：20-50
    private Float price;//价格
    private String type;//检查项类型，分为检查和检验两种类型
    private String remark;//项目说明
    private String attention;//注意事项
    private Integer status;

    public CheckItem() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CheckItem(Integer id, String code, String name, String sex, String age, Float price, String type, String remark, String attention, Integer status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.price = price;
        this.type = type;
        this.remark = remark;
        this.attention = attention;
        this.status = status;
    }

    public CheckItem(Integer id, String code, String name, String sex, String age, Float price, String type, String remark, String attention) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.price = price;
        this.type = type;
        this.remark = remark;
        this.attention = attention;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
