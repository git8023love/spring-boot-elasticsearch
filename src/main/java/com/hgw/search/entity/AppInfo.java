package com.hgw.search.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "app_info")
public class AppInfo implements Serializable {
    private static final long serialVersionUID = 5476860657063278715L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String appName;

    private String shortShortNote;

    private String keywords;

    private String oldAppName;

    private String scolor;

    private Integer itemId;

    private String sourceId;

    private Integer appSource;

    private Integer isRemd;

    private Integer isFull;

    private Integer sort;

    private Integer type;

    private String typeName;

    private String icon;

    private Integer platform;

    private Integer downloadTmp;

    private Integer downloadCount;

    private Integer weekDlCountBase;

    private Integer weekDlCount;

    private Integer monthDlCountBase;

    private Integer monthDlCount;

    private Integer yesterdayDlCount;

    private Integer remd;

    private Integer remdTwo;

    private Integer remdThree;

    private Integer necessary;

    private Integer hot;

    private Integer latest;

    private Integer remdIpad;

    private Integer remdTwoIpad;

    private Integer remdThreeIpad;

    private Integer necessaryIpad;

    private Integer hotIpad;

    private Integer latestIpad;

    private Integer tomc;

    private Integer havePkage1;

    private Integer havePkage2;

    private Integer havePkage3;

    private Integer havePkage4;

    private Integer isSignOk;

    private Integer isUpdate;

    private String callUrl;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private Integer playStatus;

    private String adIos;

    private String shortShortNote2;

    private Integer downShelves;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getShortShortNote() {
        return shortShortNote;
    }

    public void setShortShortNote(String shortShortNote) {
        this.shortShortNote = shortShortNote;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getOldAppName() {
        return oldAppName;
    }

    public void setOldAppName(String oldAppName) {
        this.oldAppName = oldAppName;
    }

    public String getScolor() {
        return scolor;
    }

    public void setScolor(String scolor) {
        this.scolor = scolor;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getAppSource() {
        return appSource;
    }

    public void setAppSource(Integer appSource) {
        this.appSource = appSource;
    }

    public Integer getIsRemd() {
        return isRemd;
    }

    public void setIsRemd(Integer isRemd) {
        this.isRemd = isRemd;
    }

    public Integer getIsFull() {
        return isFull;
    }

    public void setIsFull(Integer isFull) {
        this.isFull = isFull;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getDownloadTmp() {
        return downloadTmp;
    }

    public void setDownloadTmp(Integer downloadTmp) {
        this.downloadTmp = downloadTmp;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getWeekDlCountBase() {
        return weekDlCountBase;
    }

    public void setWeekDlCountBase(Integer weekDlCountBase) {
        this.weekDlCountBase = weekDlCountBase;
    }

    public Integer getWeekDlCount() {
        return weekDlCount;
    }

    public void setWeekDlCount(Integer weekDlCount) {
        this.weekDlCount = weekDlCount;
    }

    public Integer getMonthDlCountBase() {
        return monthDlCountBase;
    }

    public void setMonthDlCountBase(Integer monthDlCountBase) {
        this.monthDlCountBase = monthDlCountBase;
    }

    public Integer getMonthDlCount() {
        return monthDlCount;
    }

    public void setMonthDlCount(Integer monthDlCount) {
        this.monthDlCount = monthDlCount;
    }

    public Integer getYesterdayDlCount() {
        return yesterdayDlCount;
    }

    public void setYesterdayDlCount(Integer yesterdayDlCount) {
        this.yesterdayDlCount = yesterdayDlCount;
    }

    public Integer getRemd() {
        return remd;
    }

    public void setRemd(Integer remd) {
        this.remd = remd;
    }

    public Integer getRemdTwo() {
        return remdTwo;
    }

    public void setRemdTwo(Integer remdTwo) {
        this.remdTwo = remdTwo;
    }

    public Integer getRemdThree() {
        return remdThree;
    }

    public void setRemdThree(Integer remdThree) {
        this.remdThree = remdThree;
    }

    public Integer getNecessary() {
        return necessary;
    }

    public void setNecessary(Integer necessary) {
        this.necessary = necessary;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getLatest() {
        return latest;
    }

    public void setLatest(Integer latest) {
        this.latest = latest;
    }

    public Integer getRemdIpad() {
        return remdIpad;
    }

    public void setRemdIpad(Integer remdIpad) {
        this.remdIpad = remdIpad;
    }

    public Integer getRemdTwoIpad() {
        return remdTwoIpad;
    }

    public void setRemdTwoIpad(Integer remdTwoIpad) {
        this.remdTwoIpad = remdTwoIpad;
    }

    public Integer getRemdThreeIpad() {
        return remdThreeIpad;
    }

    public void setRemdThreeIpad(Integer remdThreeIpad) {
        this.remdThreeIpad = remdThreeIpad;
    }

    public Integer getNecessaryIpad() {
        return necessaryIpad;
    }

    public void setNecessaryIpad(Integer necessaryIpad) {
        this.necessaryIpad = necessaryIpad;
    }

    public Integer getHotIpad() {
        return hotIpad;
    }

    public void setHotIpad(Integer hotIpad) {
        this.hotIpad = hotIpad;
    }

    public Integer getLatestIpad() {
        return latestIpad;
    }

    public void setLatestIpad(Integer latestIpad) {
        this.latestIpad = latestIpad;
    }

    public Integer getTomc() {
        return tomc;
    }

    public void setTomc(Integer tomc) {
        this.tomc = tomc;
    }

    public Integer getHavePkage1() {
        return havePkage1;
    }

    public void setHavePkage1(Integer havePkage1) {
        this.havePkage1 = havePkage1;
    }

    public Integer getHavePkage2() {
        return havePkage2;
    }

    public void setHavePkage2(Integer havePkage2) {
        this.havePkage2 = havePkage2;
    }

    public Integer getHavePkage3() {
        return havePkage3;
    }

    public void setHavePkage3(Integer havePkage3) {
        this.havePkage3 = havePkage3;
    }

    public Integer getHavePkage4() {
        return havePkage4;
    }

    public void setHavePkage4(Integer havePkage4) {
        this.havePkage4 = havePkage4;
    }

    public Integer getIsSignOk() {
        return isSignOk;
    }

    public void setIsSignOk(Integer isSignOk) {
        this.isSignOk = isSignOk;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getCallUrl() {
        return callUrl;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(Integer playStatus) {
        this.playStatus = playStatus;
    }

    public String getAdIos() {
        return adIos;
    }

    public void setAdIos(String adIos) {
        this.adIos = adIos;
    }

    public String getShortShortNote2() {
        return shortShortNote2;
    }

    public void setShortShortNote2(String shortShortNote2) {
        this.shortShortNote2 = shortShortNote2;
    }

    public Integer getDownShelves() {
        return downShelves;
    }

    public void setDownShelves(Integer downShelves) {
        this.downShelves = downShelves;
    }
}
