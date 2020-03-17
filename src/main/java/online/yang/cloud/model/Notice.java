package online.yang.cloud.model;

import java.util.Date;

public class Notice {
    private String noticeId;

    private String noticeContent;

    private Date noticeDate;

    private String noticeCreater;

    private String noticeCreaterNo;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeCreater() {
        return noticeCreater;
    }

    public void setNoticeCreater(String noticeCreater) {
        this.noticeCreater = noticeCreater == null ? null : noticeCreater.trim();
    }

    public String getNoticeCreaterNo() {
        return noticeCreaterNo;
    }

    public void setNoticeCreaterNo(String noticeCreaterNo) {
        this.noticeCreaterNo = noticeCreaterNo == null ? null : noticeCreaterNo.trim();
    }
}