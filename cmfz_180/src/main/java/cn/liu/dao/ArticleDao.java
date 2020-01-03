package cn.liu.dao;

import cn.liu.dto.AlbumASArticleDTO;
import cn.liu.entity.Article;
import cn.liu.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {

    //添加
    public void  insert(Article article);
    //删除
    public void  delete(String id);
    //修改
    public void update(Article article);
    //分页
    public  List<Article> queryByPager(@Param("start") int start , @Param("rows")int rows );

    //查询总条数
    public  Integer getCount();



    //根据ID查询
    public Article getSelectID(String id);

    //查询所有
    public List<Article> getSelect();

    //按要求查询指定字段
    public List<AlbumASArticleDTO> getSelectArticle();

    //通过文章ID查询上师id
    public  String getGuruID(String id) ;

}
