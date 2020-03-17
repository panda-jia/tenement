package online.yang.cloud.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 房间信息实体
 *
 * @author 孙嘉
 * created in 2020/2/2 22:50
 */

@Data
@ToString
public class House {
    private String houseId;

    private String houseNo;

    private String houseUnit;

    private String houseType;

    private BigDecimal houseArea;

    private String houseBuilding;

    private String houseDate;

    private String houseOwner;

    private String houseOwnerNo;

}