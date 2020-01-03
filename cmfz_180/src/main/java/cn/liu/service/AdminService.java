package cn.liu.service;

import cn.liu.entity.Admin;

import javax.validation.constraints.Max;
import java.util.List;

public interface AdminService {
    public Admin login(Admin admin);

    public List<Admin> getSelectAll();


}
