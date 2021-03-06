package com.lin.appapidemo.controller.shixun;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.appapidemo.mapper.shixun.*;
import com.lin.appapidemo.model.shixun.*;
import com.lin.appapidemo.util.DateTimeUtil;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excise")
public class ExciseController {
    @Autowired(required = false)
    private ReaderMapper readerMapper;
    @Autowired(required = false)
    private AlbumMapper albumMapper;
    @Autowired(required = false)
    private SubalbumMapper subalbumMapper;
    @Autowired(required = false)
    private BorrowrecordMapper borrowrecordMapper;
    @Autowired(required = false)
    private LogMapper logMapper;
    @Autowired(required = false)
    private RemarkMapper remarkMapper;

    private static Logger logger= LoggerFactory.getLogger("log");
    private static Reader nowReader;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> login(@RequestParam("account")String account,@RequestParam("password")String password){
        Map<String,Object> map=new HashMap<>();
        Reader reader=readerMapper.selectWholeByAccount(account);
        if(reader!=null){
            System.out.println("hahaha"+reader.getPassword().equals(password));
            if(reader.getPassword().equals(password)){
                System.out.println("hahaha"+reader.getPassword().equals(password));
                map.put("result","yes");
                map.put("loginUser",reader);
                if(reader.getCondi()==0){
                    map.put("condi",0);
                }else if(reader.getCondi()==1){
                    map.put("condi",1);
                }else{
                    map.put("condi",2);
                }
                logger.info(reader.getName()+"成功登陆");
                nowReader=reader;
                return map;
            }
        }
        map.put("result","no");
        return map;
    }
    @RequestMapping(value = "/Register",method = RequestMethod.POST)
    public Map<String,Object> egister(@RequestParam("account")String account, @RequestParam("password")String password, @RequestParam("name")String name, @RequestParam("sex")String sex, @RequestParam("condi")int condi, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Reader reader = new Reader(account,password,name,sex, DateTimeUtil.getDate(),condi);
        if(readerMapper.selectWholeByAccount(account)==null){
            map.put("result","yes");
            map.put("loginUser",reader);
            map.put("reader",readerMapper.selectByAccount(account));
            logger.info(reader.getName()+"成功注册");
            nowReader=reader;
            return map;
            //            result.getData(null,ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
        }else{
            map.put("ResultMsg","注册失败");
            map.put("resultCode","-11");
        }
//        session.setAttribute("register",result);
        return map;
    }
    @RequestMapping(value = "/getAllReaders",method = RequestMethod.POST)
    public Map<String,Object> getAllReaders(@RequestParam("account")String account,@RequestParam("currentPage")int currentPage){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        List<Reader> list=readerMapper.selectByAccount(account);
        PageInfo<Reader> pageInfo=new PageInfo<>(list);
        map.put("readers",list);
        map.put("pageInfo",pageInfo);
        return map;
    }

    @RequestMapping(value = "/getAllAlbums",method = RequestMethod.POST)
    public Map<String,Object> getAllAlbums(@RequestParam("title")String title,@RequestParam("currentPage")int currentPage){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        List<Album> list=albumMapper.selectByTitle(title);
        PageInfo<Album> pageInfo=new PageInfo<>(list);
        map.put("pageInfo",pageInfo);
        map.put("albums",list);
        return map;
    }

    @RequestMapping(value = "/getAllRemark",method = RequestMethod.POST)
    public Map<String,Object> getAllRemark(@RequestParam("account")String account,@RequestParam("currentPage")int currentPage){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        List<Remark> list=remarkMapper. selectByAccount(account);
        PageInfo<Remark> pageInfo=new PageInfo<>(list);
        map.put("pageInfo",pageInfo);
        map.put("remark",list);
        return map;
    }

    @RequestMapping(value = "/addRemark",method = RequestMethod.POST)
    public Map<String,Object> addRemark(@RequestParam("account")String account,@RequestParam("remark_content")String remark_content,@RequestParam("remark_type")int remark_type){
        Map<String,Object> map=new HashMap<>();
        remarkMapper.insert(new Remark(account,remark_content,remark_type,DateTimeUtil.getDate()));
        logger.info(nowReader.getName()+"成功添加反馈"+remark_content);
        map.put("status","ok");
        return map;
    }


    @RequestMapping(value = "/getAllBorrowRecords",method = RequestMethod.POST)
    public Map<String,Object> getAllBorrowRecords(@RequestParam("raccount")String raccount,@RequestParam("currentPage")int currentPage){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        List<Borrowrecord> list=borrowrecordMapper.selectAllInfoByRaccount(raccount);
        PageInfo<Borrowrecord> pageInfo=new PageInfo<>(list);
        map.put("pageInfo",pageInfo);
        map.put("borrowrecords",list);
        return map;
    }


//    @RequestMapping(value = "/addReader",method = RequestMethod.POST)
//    public Map<String,Object> addReader(@RequestParam("account")String account,@RequestParam("name")String name,@RequestParam("sex")String sex,@RequestParam("condi")int condi){
//        Map<String,Object> map=new HashMap<>();
//        if(readerMapper.selectWholeByAccount(account)!=null){
//            map.put("status","no");
//        }else{
//            readerMapper.insert(new Reader(account,account,name,sex, DateTimeUtil.getDate(),condi));
//            switch (condi){
//                case 0:
//                    logger.info(nowReader.getName()+"成功添加读者"+name);
//                    break;
//                case 1:
//                    logger.info(nowReader.getName()+"成功添加管理员"+name);
//                    break;
//                case 2:
//                    logger.info(nowReader.getName()+"成功添加超级管理员"+name);
//                    break;
//            }
//            map.put("status","ok");
//        }
//        return map;
//    }

    @RequestMapping(value = "/addReader",method = RequestMethod.POST)
    public Map<String,Object> addReader(@RequestParam("account")String account,@RequestParam("name")String name,@RequestParam("sex")String sex,@RequestParam("condi")int condi){
        Map<String,Object> map=new HashMap<>();
        if(readerMapper.selectWholeByAccount(account)!=null){
            map.put("status","no");
        }else{
            readerMapper.insert(new Reader(account,account,name,sex, DateTimeUtil.getDate(),condi));
            /****************
             *
             */
            if(condi==0){
                logger.info(nowReader.getName()+"成功添加读者"+name);
            }else if(condi==1){
                logger.info(nowReader.getName()+"成功添加管理员"+name);
            }else{
                logger.info(nowReader.getName()+"成功添加超级管理员"+name);
            }
            /*****************/
            map.put("status","ok");
        }
        return map;
    }
    @RequestMapping(value = "/addAlbum",method = RequestMethod.POST)
    public Map<String,Object> addAlbum(@RequestParam("title")String title,@RequestParam("author")String author,@RequestParam("publisher")String publisher,@RequestParam("publishtime")String publishtime,@RequestParam("descri")String descri){
        Map<String,Object> map=new HashMap<>();
        albumMapper.insert(new Album(title,author,publisher,publishtime,0,descri,DateTimeUtil.getDate()));
        logger.info(nowReader.getName()+"成功添加书籍"+title);
        map.put("status","ok");
        return map;
    }
    @RequestMapping(value = "/addSubAlbum",method = RequestMethod.POST)
    public Map<String,Object> addSubAlbum(@RequestParam("aid")int aid,@RequestParam("number")String number){
        Map<String,Object> map=new HashMap<>();
        if(subalbumMapper.selectByNumber(number)!=null){
            map.put("status","no");
        }else{
            Album album=albumMapper.selectById(aid);
            album.setNum(album.getNum()+1);
            albumMapper.updateByPrimaryKey(album);
            subalbumMapper.insert(new Subalbum(aid,number,0,DateTimeUtil.getDate()));
            map.put("status","ok");
        }
        return map;
    }
    @RequestMapping(value = "/borrow",method = RequestMethod.POST)
    public Map<String,Object> borrow(@RequestParam("aid")int aid,@RequestParam("rid")int rid,@RequestParam("raccount")String raccount){
        Map<String,Object> map=new HashMap<>();
        if(rid!=0){
            Album album=albumMapper.selectById(aid);
            int count=0;
            //找到可借的那本书编号id
            int enableborrowSAid=0;
            for(int i=0,len=album.getSubalbums().size();i<len;i++){
                if(album.getSubalbums().get(i).getCondi()==0){
                    enableborrowSAid=album.getSubalbums().get(i).getSid();
                    count++;
                }
            }
            if(count!=0){
                map.put("status","ok");
                Borrowrecord borrowrecord=new Borrowrecord(rid,raccount,aid,enableborrowSAid,DateTimeUtil.getDate(),DateTimeUtil.getDateAfter15(),DateTimeUtil.getDateNumber());
                borrowrecordMapper.insert(borrowrecord);
                Subalbum subalbum=subalbumMapper.selectById(enableborrowSAid);
                subalbum.setCondi(1);
                subalbumMapper.updateByPrimaryKey(subalbum);
            }else{
                map.put("status","no");
            }
        }
        return map;
    }
    @RequestMapping(value = "/reback",method = RequestMethod.POST)
    public Map<String,Object> reback(@RequestParam("bid")int bid,@RequestParam("sid")int sid){
        Map<String,Object> map=new HashMap<>();
        if(bid!=0&&sid!=0){
            borrowrecordMapper.delete(borrowrecordMapper.selectByPrimaryKey(bid));
            Subalbum subalbum=subalbumMapper.selectByPrimaryKey(sid);
            subalbum.setCondi(0);
            subalbumMapper.updateByPrimaryKey(subalbum);
            map.put("status","yes");
        }else{
            map.put("status","no");
        }

        return map;
    }


    @RequestMapping(value ="/getAllLog" ,method = RequestMethod.POST)
    public Map<String,Object> getAllLog(@RequestParam("currentPage")int currentPage){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        List<Log> logList=logMapper.selectAllLog();
        PageInfo<Log> pageInfo=new PageInfo<>(logList);
        map.put("pageInfo",pageInfo);
        map.put("logList",logList);
        map.put("status","yes");
        return map;
    }
}

