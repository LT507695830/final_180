package cn.liu.service;

import cn.liu.dto.AlbumASArticleDTO;
import cn.liu.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    //添加
    public void getInsert(Album album);

    //删除
    public void getDelete(String id);

    //修改
    public  void getUpdate(Album album);

    //分页查询
    public Map<String, Object> queryByPager(int rows, int page);

    //查询总条数
    public  Integer getCount();

    public List<Album> getSelectAll();

    public  void albumExport(List<Album> list);


    public  List<AlbumASArticleDTO> getSelectAlbum();

}
