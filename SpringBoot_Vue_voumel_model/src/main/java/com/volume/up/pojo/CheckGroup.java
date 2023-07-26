package com.volume.up.pojo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 检查组
 * @author 小唐
 */
public class CheckGroup implements Serializable {
    private static final long serialVersionUID = 8636726004544896618L;
    private Integer id;//主键
    @NotBlank(message = "检查组编码不能为空")
    private String code;//编码
    @NotBlank(message = "检查组名称不能为空")
    private String name;//名称
    private String helpCode;//助记
    private String sex;//适用性别
    private String remark;//介绍
    private String attention;//注意事项
    private List<CheckItem> checkItems;//一个检查组合包含多个检查项
    private int status;

    public CheckGroup() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CheckGroup(Integer id, @NotBlank(message = "检查组编码不能为空") String code, @NotBlank(message = "检查组名称不能为空") String name, String helpCode, String sex, String remark, String attention, List<CheckItem> checkItems, int status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.helpCode = helpCode;
        this.sex = sex;
        this.remark = remark;
        this.attention = attention;
        this.checkItems = checkItems;
        this.status = status;
    }

    public List<CheckItem> getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(List<CheckItem> checkItems) {
        this.checkItems = checkItems;
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

    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
}
