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
    private String account;
    @Column(name = "remark_content")
    private String remark_content;
    @Column(name = "remark_date")
    private String remark_date;
    @Column(name = "remark_type")
    private int remark_type;

    public Remark(String account, String remark_content, int remark_type,String remark_date) {
        this.account = account;
        this.remark_content = remark_content;
        this.remark_date = remark_date;
        this.remark_type = remark_type;
    }



    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRemark_content() {
        return remark_content;
    }

    public void setRemark_content(String remark_content) {
        this.remark_content = remark_content;
    }

    public int getRemark_type() {
        return remark_type;
    }

    public void setRemark_type(int remark_type) {
        this.remark_type = remark_type;
    }


    public String getRemark_date() {
        return remark_date;
    }

    public void setRemark_date(String remark_date) {
        this.remark_date= remark_date;
    }
}
