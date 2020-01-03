package cn.liu.controller;

import cn.liu.entity.Article;
import cn.liu.service.AlbumService;
import cn.liu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequestMapping("Article")
public class ArticleController {
    @Autowired
    private ArticleService articleService ;

    @ResponseBody
    @RequestMapping("queryByPager")
    /*
     * rows : 每页显示的条数
     * page : 当前页
     * */
    public Map<String, Object> queryByPager(Integer rows, Integer page) {


        Map<String, Object> map = articleService.queryByPager(rows, page);

        return map;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Map<String, Object> edit(Article article, String oper, String img , String[] id, HttpSession session) {

        Map<String, Object> map = new HashMap<>();

        if ("add".equals(oper)) {
            System.out.println(article);
            String ID = UUID.randomUUID().toString() ;
            Date date = new Date();
            article.setId(ID);
            article.setCreate_date(date);
            articleService.getInsert(article);

        } else if ("edit".equals(oper)) {
            articleService.getUpdate(article);

        } else if ("del".equals(oper)) {
            articleService.getDelete(article.getId());
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("selectID")
    public Article selectID(String id){
        return  articleService.getSelectID(id);

    }


}
