package online.yang.cloud.mapper;

import online.yang.cloud.model.Vilage;

public interface VilageMapper {
    int deleteByPrimaryKey(String vilageId);

    int insert(Vilage record);

    int insertSelective(Vilage record);

    Vilage selectByPrimaryKey(String vilageId);

    int updateByPrimaryKeySelective(Vilage record);

    int updateByPrimaryKey(Vilage record);
}