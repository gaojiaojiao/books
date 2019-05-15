package com.lin.appapidemo.mapper.shixun;

import com.lin.appapidemo.model.shixun.Remark;
import com.lin.appapidemo.util.MyMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RemarkMapper extends MyMapper<Remark> {


    @Select("<script> "+
            "select * from t_remark" +
            " </script> "
    )
    @Results({
            @Result(column = "remark_content",property = "remark_content"),
            @Result(column = "account",property = "account"),
            @Result(column = "remark_date",property = "remark_date"),
            @Result(column = "remark_type",property = "remark_type"),
    })
    List<Remark> selectByAccount(@Param("account") String account);
}
