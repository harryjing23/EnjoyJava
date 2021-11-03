package com.harry.myapplication.rxjava;

import java.util.Date;
import java.util.List;

/**
 * Created on 2021/9/27.
 *
 * @author harry
 */
public class ItemBean {
    private Data data;
    private int errorCode;
    private String errorMsg;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Copyright 2021 json.cn
     */
    public static class Data {

        private int curPage;
        private List<Datas> datas;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getCurPage() {
            return curPage;
        }

        public void setDatas(List<Datas> datas) {
            this.datas = datas;
        }

        public List<Datas> getDatas() {
            return datas;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getOffset() {
            return offset;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public boolean getOver() {
            return over;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }

    }

    public static class Datas {

        private String apkLink;
        private int audit;
        private String author;
        private boolean canEdit;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String descMd;
        private String envelopePic;
        private boolean fresh;
        private String host;
        private int id;
        private Date link;
        private Date niceDate;
        private Date niceShareDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int realSuperChapterId;
        private int selfVisible;
        private long shareDate;
        private String shareUser;
        private int superChapterId;
        private String superChapterName;
        private List<Tags> tags;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public String getApkLink() {
            return apkLink;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public int getAudit() {
            return audit;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor() {
            return author;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }

        public boolean getCanEdit() {
            return canEdit;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public boolean getCollect() {
            return collect;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDescMd(String descMd) {
            this.descMd = descMd;
        }

        public String getDescMd() {
            return descMd;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public boolean getFresh() {
            return fresh;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getHost() {
            return host;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setLink(Date link) {
            this.link = link;
        }

        public Date getLink() {
            return link;
        }

        public void setNiceDate(Date niceDate) {
            this.niceDate = niceDate;
        }

        public Date getNiceDate() {
            return niceDate;
        }

        public void setNiceShareDate(Date niceShareDate) {
            this.niceShareDate = niceShareDate;
        }

        public Date getNiceShareDate() {
            return niceShareDate;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getOrigin() {
            return origin;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setRealSuperChapterId(int realSuperChapterId) {
            this.realSuperChapterId = realSuperChapterId;
        }

        public int getRealSuperChapterId() {
            return realSuperChapterId;
        }

        public void setSelfVisible(int selfVisible) {
            this.selfVisible = selfVisible;
        }

        public int getSelfVisible() {
            return selfVisible;
        }

        public void setShareDate(long shareDate) {
            this.shareDate = shareDate;
        }

        public long getShareDate() {
            return shareDate;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public String getShareUser() {
            return shareUser;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setTags(List<Tags> tags) {
            this.tags = tags;
        }

        public List<Tags> getTags() {
            return tags;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return userId;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getVisible() {
            return visible;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public int getZan() {
            return zan;
        }

    }

    public static class Tags {

        private String name;
        private String url;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

    }
}
