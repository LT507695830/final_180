package cn.liu.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.liu.dao.ArticleDao;
import cn.liu.dao.GuruDao;
import cn.liu.dto.AlbumASArticleDTO;
import cn.liu.entity.Album;
import cn.liu.entity.Article;
import cn.liu.service.ArticleService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao ;


    @Override
    public void getInsert(Article article) {
        articleDao.insert(article);
    }

    @Override
    public void getDelete(String id) {
        articleDao.delete(id);
    }

    @Override
    public void getUpdate(Article article) {
        articleDao.update(article);
    }

    @Override
    public Map<String, Object> queryByPager(int rows, int page) {

        //当前页数
        //需要返回当前页数 ， 键为；page
        Map<String ,Object> map = new HashMap<>();
        map.put("page" , page);
        //需要返回当前总条数
        Integer count = getCount();
        map.put("records",count);

        //需要返回当前页展示的数据
        //从第几条开始（page-1）*rows(每页展示多少条数据)
        List<Article> articles = articleDao.queryByPager((page - 1) * rows, rows);
        map.put("rows",articles);

        //需要返回总页数
        Integer totalPage = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",totalPage);


        return map;
    }

    @Override
    public Integer getCount() {
        return articleDao.getCount();
    }

    @Override
    public Article getSelectID(String id) {

        return  articleDao.getSelectID(id);
    }

    @Override
    public List<Article> getSelectAll() {
        return articleDao.getSelect()   ;
    }

    @Override
    public void articleExport(List<Article> list) {



        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("文章","文章空间"),
                Article.class, list);
        try {
            workbook.write(new FileOutputStream("D:/cmfz-article.xls"));
        }catch (Exception e){

        }finally {
            try {
                workbook.close();
            }catch (Exception e){}
        }
    }
    @Autowired
    private  GuruDao guruDao ;


    @Override
    public List<AlbumASArticleDTO> getSelectArticle() {
        String s = "http://localhost:80/cmfz/upload/video/img/";
        List<AlbumASArticleDTO> selectArticle = articleDao.getSelectArticle();
        for (AlbumASArticleDTO albumASArticleDTO : selectArticle) {
            albumASArticleDTO.setType(1);
            String guruID = articleDao.getGuruID(albumASArticleDTO.getId());
            String img = guruDao.getImg(guruID);
            albumASArticleDTO.setThumbnail(s+img);
        }
        return selectArticle;
    }
}
