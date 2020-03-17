package online.yang.cloud.utils;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author .
 * created in 2020/2/1 10:57
 */

@Data
@ToString
public class PageInfo<T> {

    private Integer page;
    private Integer limit;
    private Integer count;
    private List<T> data;

}
