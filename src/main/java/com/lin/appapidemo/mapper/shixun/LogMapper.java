package com.lin.appapidemo.mapper.shixun;

import com.lin.appapidemo.model.shixun.Borrowrecord;
import com.lin.appapidemo.model.shixun.Log;
import com.lin.appapidemo.util.MyMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface LogMapper extends MyMapper<Log> {


    @Select("<script> "+
            "select * from t_log" +
            " </script> "
    )
    @Results({
            @Result(id=true,column="log_id",property = "log_id"),
            @Result(column = "log_content",property = "log_content"),
            @Result(column = "log_account",property = "log_account"),
            @Result(column = "log_data",property = "log_data"),
    })
    List<Log> selectAllLog();
}
