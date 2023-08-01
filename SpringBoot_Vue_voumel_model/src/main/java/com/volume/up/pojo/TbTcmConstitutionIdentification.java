package com.volume.up.pojo;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/31 20:49:03
 */
public class TbTcmConstitutionIdentification {
    private Integer id;
    private String constitutionName;
    private String constitutionDesc;
    private String suitableFood;
    private String noSuitableFood;
    private String suitableCrowd;
    private String doSing;
    private String efficacy;
    private String chineseMedicineName;

    public TbTcmConstitutionIdentification() {
    }

    public TbTcmConstitutionIdentification(Integer id, String constitutionName, String constitutionDesc, String suitableFood, String noSuitableFood, String suitableCrowd, String doSing, String efficacy, String chineseMedicineName) {
        this.id = id;
        this.constitutionName = constitutionName;
        this.constitutionDesc = constitutionDesc;
        this.suitableFood = suitableFood;
        this.noSuitableFood = noSuitableFood;
        this.suitableCrowd = suitableCrowd;
        this.doSing = doSing;
        this.efficacy = efficacy;
        this.chineseMedicineName = chineseMedicineName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConstitutionName() {
        return constitutionName;
    }

    public void setConstitutionName(String constitutionName) {
        this.constitutionName = constitutionName;
    }

    public String getConstitutionDesc() {
        return constitutionDesc;
    }

    public void setConstitutionDesc(String constitutionDesc) {
        this.constitutionDesc = constitutionDesc;
    }

    public String getSuitableFood() {
        return suitableFood;
    }

    public void setSuitableFood(String suitableFood) {
        this.suitableFood = suitableFood;
    }

    public String getNoSuitableFood() {
        return noSuitableFood;
    }

    public void setNoSuitableFood(String noSuitableFood) {
        this.noSuitableFood = noSuitableFood;
    }

    public String getSuitableCrowd() {
        return suitableCrowd;
    }

    public void setSuitableCrowd(String suitableCrowd) {
        this.suitableCrowd = suitableCrowd;
    }

    public String getDoSing() {
        return doSing;
    }

    public void setDoSing(String doSing) {
        this.doSing = doSing;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getChineseMedicineName() {
        return chineseMedicineName;
    }

    public void setChineseMedicineName(String chineseMedicineName) {
        this.chineseMedicineName = chineseMedicineName;
    }

    @Override
    public String toString() {
        return "TbTcmConstitutionIdentification{" +
                "id=" + id +
                ", constitutionName='" + constitutionName + '\'' +
                ", constitutionDesc='" + constitutionDesc + '\'' +
                ", suitableFood='" + suitableFood + '\'' +
                ", noSuitableFood='" + noSuitableFood + '\'' +
                ", suitableCrowd='" + suitableCrowd + '\'' +
                ", doSing='" + doSing + '\'' +
                ", efficacy='" + efficacy + '\'' +
                ", chineseMedicineName='" + chineseMedicineName + '\'' +
                '}';
    }
}
