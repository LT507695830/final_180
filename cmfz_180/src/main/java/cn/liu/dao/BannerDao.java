package cn.liu.dao;

import cn.liu.dto.BannerDTO;
import cn.liu.entity.Banner;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface BannerDao {
    //添加
    public void insert(Banner banner);

    //修改
    public void update(Banner banner);

    //删除
    public void delete(String id);

    //查询所有
    public List<Banner> select();

    //分页
    public  List<Banner> queryByPager(@Param("start") int start , @Param("rows")int rows );

    //查询总条数
    public  Integer getCount();


    //批量删除
    public void deleteAll(String[] id) ;

    //通过ID查询图片
    public String selectID(String ID);


    //查询指定字段
    public List<BannerDTO> getSelectBannerDTO();
}
