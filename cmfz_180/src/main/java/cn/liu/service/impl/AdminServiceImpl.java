package cn.liu.service.impl;

import cn.liu.dao.AdminDao;
import cn.liu.entity.Admin;
import cn.liu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao ;
    @Override
    public Admin login(Admin admin) {
        //通过账号查询返回一个管理员对象
        Admin admin1 = adminDao.selectUserName(admin.getUsername());
        //根据返回的对象和用户输入的密码对比
        if(admin1.getPassword().equals(admin.getPassword())){
            //若密码正确，将查询的对象返回
            return  admin1 ;

        }
        //若不正确，返回null
        return null;
    }

    @Override
    public List<Admin> getSelectAll() {
        return adminDao.getSelect();
    }
}
