package com.yicen.flutter_service_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicen.flutter_service_demo.entity.Province;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProvinceMapper extends BaseMapper<Province> {


    List<Province> selectAll();

    @Select("select bs_city.*,bp.* from bs_city left join bs_province bp on bs_city.PROVINCE_CODE = bp.PROVINCE_CODE")

    @Results({
            @Result(column = "PROVINCE_ID", property = "id", id = true),
            @Result(column = "PROVINCE_NAME", property = "name"),
            @Result(column = "PROVINCE_CODE", property = "code"),
            @Result(column = "cityList", property = "City", javaType = List.class,
                    many = @Many(
                            select = "com.yicen.flutter_service_demo.mapper.CityMapper.queryById"
                    )
            )
    })
    List<Province> test();
}
