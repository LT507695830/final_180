package cn.liu.service.impl;

import cn.liu.dao.UserDao;
import cn.liu.entity.User;
import cn.liu.dto.UserDTO;
import cn.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao ;
    @Override
    public void getInsert(User user) {
        userDao.insert(user);
    }

    @Override
    public void getDelete(String id) {
        userDao.delete(id);
    }

    @Override
    public void getUpdate(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> getSelectAll() {
        return userDao.getSelectAll();
    }

    @Override
    public Integer getCount() {
        return userDao.getCount();
    }

    @Override
    public Map<String, Object> queryByPager(int rows, int page) {
        //需要返回当前页数 ， 键为；page
        Map<String ,Object> map = new HashMap<>();
        map.put("page" , page);
        //需要返回当前总条数
        Integer count = getCount();
        map.put("records",count);

        //需要返回当前页展示的数据
        //从第几条开始（page-1）*rows(每页展示多少条数据)
        List<User> users = userDao.queryByPager((page-1)* rows , rows);
        map.put("rows",users);

        //需要返回总页数
        Integer totalPage = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",totalPage);
        return map;
    }

    @Override
    public List<UserDTO> getSelectAdd() {
        return userDao.getSelectAdd();
    }
}
