package com.lin.appapidemo.model.shixun;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_log")

public class Log {
    @Id
    @Column(name = "log_id")
    private int log_id;
    @Column(name = "log_content")
    private String log_content;
    @Column(name = "log_account")
    private String log_account;
    @Column(name = "log_data")
    private String log_data;

    public Log(int log_id, String log_content, String log_account, String log_data) {

        this.log_account = log_account;
        this.log_content = log_content;
        this.log_data = log_data;
        this.log_id = log_id;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getLog_content() {
        return log_content;
    }

    public void setLog_content(String log_content) {
        this.log_content = log_content;
    }

    public String getLog_account() {
        return log_account;
    }

    public void setLog_account(String log_account) {
        this.log_account = log_account;
    }

    public String getLog_data() {
        return log_data;
    }

    public void setLog_data(String log_data) {
        this.log_data = log_data;
    }
}
