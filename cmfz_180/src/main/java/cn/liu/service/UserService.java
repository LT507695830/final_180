package cn.liu.service;

import cn.liu.entity.User;
import cn.liu.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    //添加
    public void getInsert(User user);
    //删除
    public  void  getDelete(String id );
    //修改
    public  void  getUpdate(User user);

    //查询所有
    public List<User> getSelectAll();

    //查询所有用户
    public Integer getCount();


    //分页
    public Map<String, Object> queryByPager(int rows, int page);

    //根据地区分组

    public  List<UserDTO> getSelectAdd();
}
