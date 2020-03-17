package online.yang.cloud.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 报修信息实体
 *
 * @author 孙嘉
 * created in 2020/2/1 11:28
 */

@Data
@ToString
public class Repair {
    private String repairId;

    private String repairHouse;

    private String repairContent;

    private Date repairDate;

    private String repairOperator;

    private String repairOperatorNo;

    private Integer repairStatus;

    private Integer repairOwnerStatus;

    private String repairComment;
}