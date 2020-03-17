package online.yang.cloud.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 缴费信息实体
 *
 * @author 孙嘉
 * created in 2020/2/1 11:28
 */

@Data
@ToString
public class Cost {
    private String costId;

    private String costType;

    private String costStart;

    private String costEnd;

    private BigDecimal costSum;

    private String costOwner;

    private String costOwnerNo;

    private String costOperator;

    private String costOperatorNo;

    private Integer costStatus;
}