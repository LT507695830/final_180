package cn.liu.dao;

import cn.liu.dto.AlbumASArticleDTO;
import cn.liu.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {

    //添加
    public  void insert(Album album);

    //删除
    public void delete(String id) ;
    //修改
    public void update(Album album);

    //分页
    public List<Album> queryByPager(@Param("start") int start , @Param("rows")int rows);

    //查询总条数
    public  Integer getCount();

    //查询所有
    public List<Album> getSelect();


    //查询指定字段
    public List<AlbumASArticleDTO> getSelectAlbumDTO();

    public  Integer getNumber(String id);

}
