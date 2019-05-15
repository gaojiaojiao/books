package com.lin.appapidemo.model.shixun;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_borrowrecord")
public class Borrowrecord {
    @Id
    @Column(name = "bid")
    private int bid;
    @Column(name = "rid")
    private int rid;
    @Column(name = "raccount")
    private String raccount;
    @Column(name = "aid")
    private int aid;
    @Column(name = "sid")
    private int sid;
    @Column(name = "time")
    private String time;
    @Column(name = "backtime")
    private String backtime;
    @Column(name = "inttime")
    private String inttime;
    @Transient
    private Reader reader;
    @Transient
    private Album album;
    @Transient
    private Subalbum subalbum;

    public Borrowrecord(int rid, String raccount, int aid, int sid, String time, String backtime,String inttime) {
        this.rid = rid;
        this.raccount = raccount;
        this.aid = aid;
        this.sid = sid;
        this.time = time;
        this.backtime = backtime;
        this.inttime = inttime;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRaccount() {
        return raccount;
    }

    public void setRaccount(String raccount) {
        this.raccount = raccount;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBacktime() {
        return backtime;
    }

    public void setBacktime(String backtime) {
        this.backtime = backtime;
    }

    public String getInttime() {
        return inttime;
    }

    public void setInttime(String inttime) {
        this.inttime = inttime;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Subalbum getSubalbum() {
        return subalbum;
    }

    public void setSubalbum(Subalbum subalbum) {
        this.subalbum = subalbum;
    }
}
