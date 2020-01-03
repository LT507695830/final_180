package cn.liu.dao;

import cn.liu.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import javax.security.auth.callback.CallbackHandler;
import java.util.List;

public interface ChapterDao {
    //添加
    public  void insert(Chapter chapter);
    //删除
    public  void delete(String id) ;
    //修改
    public  void update(Chapter chapter);
    //分页
    public List<ChapterDao> queryByPager(@Param("start")int start , @Param("rows")int rows,@Param("id")String id);

    //查询该专辑下的总数据
    public  int getCountAlbumID(String id);

    //通过ID获取专辑
    public String getSelectID(String id);

    //通过专辑ID查询专辑、

}
