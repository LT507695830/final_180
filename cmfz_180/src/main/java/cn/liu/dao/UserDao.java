package cn.liu.dao;

import cn.liu.entity.User;
import cn.liu.dto.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //添加
    public void insert(User user);
    //删除
    public  void  delete(String id );
    //修改
    public  void  update(User user);

    //查询所有
    public List<User> getSelectAll();

    //查询所有用户
    public Integer getCount();


    //分页
    public  List<User> queryByPager(@Param("start") int start , @Param("rows")int rows );

    //根据地区分组

    public  List<UserDTO> getSelectAdd();


}
