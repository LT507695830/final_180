package cn.liu.service;

import cn.liu.dto.AlbumASArticleDTO;
import cn.liu.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    //添加
    public  void getInsert(Article article);
    //删除
    public  void getDelete(String id);
    //修改
    public  void  getUpdate(Article article);
    //分页
    public Map<String ,Object>  queryByPager(int rows, int page);
    //查询总条数

    public Integer getCount() ;

    //查询文章
    public  Article getSelectID(String id);

    public List<Article> getSelectAll();

    public void  articleExport(List<Article> list);


    public List<AlbumASArticleDTO> getSelectArticle();
}
