package com.wfx.quality.award;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanxin.wfx on 14-10-11.
 */
public class ApplicationDO {
    private Long applicationId;
    private Long contentId;
    private Integer baomingType;
    private Integer serviceType;
    private String itemCategory;
    private Long userId;
    private String nick;
    private Long shopId;
    private String shopName;
    private Long shopCategory;
    private Long itemId;
    private String itemName;
    private String metaValue;
    private Integer status;
    private Integer step;
    private Long option = Long.valueOf(0L);
    private Long price;
    private Long blockId;
    private Long adGroupIdOld;
    private Date gmtCreate;
    private Date gmtModified;
    private String gmtSubmitInfostr;
    private Date gmtSubmitInfo;
    private Integer remarkFlag = Integer.valueOf(0);
    private String executekey;
    private Map<String, String> featureMap;
    private Integer departId;
    private Long groupId;
    private Double xpScores;
    private String expectedMetaValue;
    private Integer formType;
    private Date approveTime;
    private Date downshelfTime;
    private boolean is1212Tag;
    private String filterQuality;
    private Double minPriceDiscount;
    private Double minProDiscount;
    private Double promotion;
    private Double rowPrice;
    private Long sold30;
    private Double minPrice30;
    private Long itemStock;
    private Integer ssi;
    private Double sqs;
    private Double scs;
    private Double oms;
    private Integer ric;
    private String ot;
    private Integer bcType = Integer.valueOf(-1);

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Integer getBaomingType() {
        return baomingType;
    }

    public void setBaomingType(Integer baomingType) {
        this.baomingType = baomingType;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(Long shopCategory) {
        this.shopCategory = shopCategory;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMetaValue() {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Long getOption() {
        return option;
    }

    public void setOption(Long option) {
        this.option = option;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public Long getAdGroupIdOld() {
        return adGroupIdOld;
    }

    public void setAdGroupIdOld(Long adGroupIdOld) {
        this.adGroupIdOld = adGroupIdOld;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getGmtSubmitInfostr() {
        return gmtSubmitInfostr;
    }

    public void setGmtSubmitInfostr(String gmtSubmitInfostr) {
        this.gmtSubmitInfostr = gmtSubmitInfostr;
    }

    public Date getGmtSubmitInfo() {
        return gmtSubmitInfo;
    }

    public void setGmtSubmitInfo(Date gmtSubmitInfo) {
        this.gmtSubmitInfo = gmtSubmitInfo;
    }

    public Integer getRemarkFlag() {
        return remarkFlag;
    }

    public void setRemarkFlag(Integer remarkFlag) {
        this.remarkFlag = remarkFlag;
    }

    public String getExecutekey() {
        return executekey;
    }

    public void setExecutekey(String executekey) {
        this.executekey = executekey;
    }

    public Map<String, String> getFeatureMap() {
        return featureMap;
    }

    public void setFeatureMap(Map<String, String> featureMap) {
        this.featureMap = featureMap;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Double getXpScores() {
        return xpScores;
    }

    public void setXpScores(Double xpScores) {
        this.xpScores = xpScores;
    }

    public String getExpectedMetaValue() {
        return expectedMetaValue;
    }

    public void setExpectedMetaValue(String expectedMetaValue) {
        this.expectedMetaValue = expectedMetaValue;
    }

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Date getDownshelfTime() {
        return downshelfTime;
    }

    public void setDownshelfTime(Date downshelfTime) {
        this.downshelfTime = downshelfTime;
    }

    public boolean isIs1212Tag() {
        return is1212Tag;
    }

    public void setIs1212Tag(boolean is1212Tag) {
        this.is1212Tag = is1212Tag;
    }

    public String getFilterQuality() {
        return filterQuality;
    }

    public void setFilterQuality(String filterQuality) {
        this.filterQuality = filterQuality;
    }

    public Double getMinPriceDiscount() {
        return minPriceDiscount;
    }

    public void setMinPriceDiscount(Double minPriceDiscount) {
        this.minPriceDiscount = minPriceDiscount;
    }

    public Double getMinProDiscount() {
        return minProDiscount;
    }

    public void setMinProDiscount(Double minProDiscount) {
        this.minProDiscount = minProDiscount;
    }

    public Double getPromotion() {
        return promotion;
    }

    public void setPromotion(Double promotion) {
        this.promotion = promotion;
    }

    public Double getRowPrice() {
        return rowPrice;
    }

    public void setRowPrice(Double rowPrice) {
        this.rowPrice = rowPrice;
    }

    public Long getSold30() {
        return sold30;
    }

    public void setSold30(Long sold30) {
        this.sold30 = sold30;
    }

    public Double getMinPrice30() {
        return minPrice30;
    }

    public void setMinPrice30(Double minPrice30) {
        this.minPrice30 = minPrice30;
    }

    public Long getItemStock() {
        return itemStock;
    }

    public void setItemStock(Long itemStock) {
        this.itemStock = itemStock;
    }

    public Integer getSsi() {
        return ssi;
    }

    public void setSsi(Integer ssi) {
        this.ssi = ssi;
    }

    public Double getSqs() {
        return sqs;
    }

    public void setSqs(Double sqs) {
        this.sqs = sqs;
    }

    public Double getScs() {
        return scs;
    }

    public void setScs(Double scs) {
        this.scs = scs;
    }

    public Double getOms() {
        return oms;
    }

    public void setOms(Double oms) {
        this.oms = oms;
    }

    public Integer getRic() {
        return ric;
    }

    public void setRic(Integer ric) {
        this.ric = ric;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public Integer getBcType() {
        return bcType;
    }

    public void setBcType(Integer bcType) {
        this.bcType = bcType;
    }
}
