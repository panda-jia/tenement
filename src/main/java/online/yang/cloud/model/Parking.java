package online.yang.cloud.model;

import lombok.Data;
import lombok.ToString;

/**
 * 车位信息实体
 *
 * @author 孙嘉
 * created in 2020/2/5 14:56
 */

@Data
@ToString
public class Parking {
    private String parkId;

    private Integer parkNo;

    private String parkCarNo;

    private String parkOwner;

    private String parkOperator;

    private String parkComment;
}