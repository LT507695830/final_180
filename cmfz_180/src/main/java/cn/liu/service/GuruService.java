package cn.liu.service;

import cn.liu.entity.Guru;
import cn.liu.dto.GuruDTO;

import java.util.List;
import java.util.Map;

public interface GuruService {
    //添加
    public void getInsert(Guru guru) ;

    //删除
    public void  getDelete(String id) ;

    //修改
    public  void  getUpdate(Guru guru);

    //查询所有
    public List<Guru> getSelectAll();

    //分页
    public Map<String, Object> queryByPager(int rows, int page);

    //查询当前指定天数前注册的人数
    public  Integer getSelectDTO(Integer fate);

    public  Integer getCount();


    public  List<GuruDTO> getSelectMonth();

}
