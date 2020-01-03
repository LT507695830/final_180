package cn.liu.dao;

import cn.liu.entity.Admin;

import java.util.List;

public interface AdminDao {
    //通过账户查询
    public Admin selectUserName(String UserName);

    //查询所有
    public List<Admin> getSelect();

}
