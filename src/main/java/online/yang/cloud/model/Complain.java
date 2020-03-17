package online.yang.cloud.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Complain {
    private String complainId;

    private String complainTitle;

    private String complainContent;

    private Date complainDate;

    private String complainAdminer;

    private String complainNo;

    private String complainStatus;

    private String complainComment;
}