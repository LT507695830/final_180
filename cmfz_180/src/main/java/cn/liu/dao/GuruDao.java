package cn.liu.dao;


import cn.liu.entity.Guru;
import cn.liu.dto.GuruDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuruDao {

    //添加
    public  void  insert(Guru guru);

    //修改
    public void  update(Guru guru);

    //删除
    public void delee(String id );

    //查询所有
    public List<Guru> getSelect();


    //查询当前指定天数前注册的人数
    public  Integer getSelectDTO(Integer fate);


    //分页
    public  List<Guru> queryByPager(@Param("start") int start , @Param("rows")int rows );

    //查询所有条数
    public Integer getCount() ;

    //查询每个月的注册人数
    public List<GuruDTO> getSelectMonth();

    //通过上师ID插询头像
    public  String  getImg(String id);
}
