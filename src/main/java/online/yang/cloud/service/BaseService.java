package online.yang.cloud.service;

/**
 * @author .
 * created in 2020/1/31 21:12
 */

public interface BaseService<T> {

    T findById(String id);

    int add(T t);

    int update(T t);

    int delete(String id);

}
