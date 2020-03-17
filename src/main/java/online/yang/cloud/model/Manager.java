package online.yang.cloud.model;

import lombok.Data;
import lombok.ToString;

/**
 * 物业管理人员信息实体
 *
 * @author 孙嘉
 * created in 2020/2/1 11:28
 */

@Data
@ToString
public class Manager {
    private String empId;

    private String empName;

    private String empPwd;

    private Integer empGender;

    private String empBorth;

    private String empPhone;
}