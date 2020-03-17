package online.yang.cloud.model;

import lombok.Data;
import lombok.ToString;

/**
 * 业主信息实体
 *
 * @author 孙嘉
 * created in 2020/2/1 11:28
 */

@Data
@ToString
public class Owner {
    private String ownerId;

    private String ownerNo;

    private String ownerName;

    private Integer ownerGender;

    private String ownerBorth;

    private String ownerWorkstation;

    private String ownerPhone;
}