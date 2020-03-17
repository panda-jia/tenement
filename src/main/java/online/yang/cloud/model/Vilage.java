package online.yang.cloud.model;

import java.math.BigDecimal;

/**
 * 小区信息实体
 *
 * @author 孙嘉
 * created in 2020/2/1 11:28
 */

public class Vilage {
    private String vilageId;

    private String vilageName;

    private String vilageDate;

    private BigDecimal vilageArea;

    private String vilageLocation;

    private String vilagePrincipal;

    private String vilagePhone;

    public String getVilageId() {
        return vilageId;
    }

    public void setVilageId(String vilageId) {
        this.vilageId = vilageId == null ? null : vilageId.trim();
    }

    public String getVilageName() {
        return vilageName;
    }

    public void setVilageName(String vilageName) {
        this.vilageName = vilageName == null ? null : vilageName.trim();
    }

    public String getVilageDate() {
        return vilageDate;
    }

    public void setVilageDate(String vilageDate) {
        this.vilageDate = vilageDate == null ? null : vilageDate.trim();
    }

    public BigDecimal getVilageArea() {
        return vilageArea;
    }

    public void setVilageArea(BigDecimal vilageArea) {
        this.vilageArea = vilageArea;
    }

    public String getVilageLocation() {
        return vilageLocation;
    }

    public void setVilageLocation(String vilageLocation) {
        this.vilageLocation = vilageLocation == null ? null : vilageLocation.trim();
    }

    public String getVilagePrincipal() {
        return vilagePrincipal;
    }

    public void setVilagePrincipal(String vilagePrincipal) {
        this.vilagePrincipal = vilagePrincipal == null ? null : vilagePrincipal.trim();
    }

    public String getVilagePhone() {
        return vilagePhone;
    }

    public void setVilagePhone(String vilagePhone) {
        this.vilagePhone = vilagePhone == null ? null : vilagePhone.trim();
    }
}