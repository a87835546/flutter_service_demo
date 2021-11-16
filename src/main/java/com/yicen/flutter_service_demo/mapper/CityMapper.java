package com.yicen.flutter_service_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicen.flutter_service_demo.entity.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CityMapper extends BaseMapper<City> {


    @Select("select bs_city.*,bp.PROVINCE_NAME,bp.PROVINCE_CODE from bs_city left join bs_province bp on bs_city.PROVINCE_CODE = bp.PROVINCE_CODE")
    @Results({
            @Result(column = "CITY_ID", property = "id", id = true),
            @Result(column = "CITY_NAME", property = "name"),
            @Result(column = "SHORT_NAME", property = "shortName"),
            @Result(column = "PROVINCE_CODE", property = "provinceCode"),
            @Result(column = "PROVINCE_NAME", property = "provinceName"),
            @Result(column = "CITY_CODE", property = "code"),
            @Result(column = "id", property = "courses", javaType = List.class,
                    many = @Many(
                            select = "com.usst.mapper.CourseMapper.queryCoursesByStuId"
                    )
            )
    })
    public List<City> getAll();

    @Select("select * from bs_city where PROVINCE_CODE = #{provinceCode}")
    @Results({
            @Result(column = "CITY_ID", property = "id", id = true),
            @Result(column = "CITY_NAME", property = "name"),
            @Result(column = "CITY_CODE", property = "code"),
    })
    public List<City> queryById(String provinceCode);
}
