package cn.liu.service;

import cn.liu.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    //添加
    public void getInsert(Chapter chapter);
    //修改
    public  void getUpdate(Chapter chapter);
    //删除
    public void  getDelete(String id);
    //分页查询
    //分页查询
    public Map<String, Object> queryByPager(int rows, int page , String album_id);
    //查询音频数

    public  Integer getCount(String album_id);


    public  String getSelectID(String id);
}
