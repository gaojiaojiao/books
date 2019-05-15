package com.lin.appapidemo.model.shixun;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_remark")

public class Remark {
    @Id
    @Column(name = "account")
    /**
     * 学号
     */
    private String account;
    @Column(name = "remark_content")
    private String remark_content;
    @Column(name = "remark_date")
    private String remark_date;
    @Column(name = "remark_type")
    private String remark_type;
    private  String date;

    public Remark(String account, String remark_content, String remark_type, String remark_date, String date) {
        this.account = account;
        this.remark_content = remark_content;
        this.remark_date = remark_date;
        this.remark_type = remark_type;
        this.date = date;

    }

}