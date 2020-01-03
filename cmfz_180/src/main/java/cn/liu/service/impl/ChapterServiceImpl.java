package cn.liu.service.impl;

import cn.liu.dao.ChapterDao;
import cn.liu.entity.Chapter;
import cn.liu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("chapterService")
public class ChapterServiceImpl implements ChapterService{

    @Autowired
    private ChapterDao chapterDao ;
    @Override
    public void getInsert(Chapter chapter) {
        chapterDao.insert(chapter);
    }

    @Override
    public void getUpdate(Chapter chapter) {
        chapterDao.update(chapter);
    }

    @Override
    public void getDelete(String id) {
        chapterDao.delete(id);
    }

    @Override
    public Map<String, Object> queryByPager(int rows, int page, String album_id){
        System.out.println(
                rows +"_________________"+page
        );
        //需要返回当前页数 ， 键为；page
        Map<String ,Object> map = new HashMap<>();
        map.put("page" , page);
        //需要返回当前总条数
        Integer count = getCount(album_id);
        map.put("records",count);

        //需要返回当前页展示的数据
        //从第几条开始（page-1）*rows(每页展示多少条数据)
        List<ChapterDao> chapterDaos = chapterDao.queryByPager((page - 1) * rows, rows, album_id);
        map.put("rows",chapterDaos);

        //需要返回总页数
        Integer totalPage = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",totalPage);
        return map;
    }

    @Override
    public Integer getCount(String album_id) {
        return chapterDao.getCountAlbumID(album_id);
    }


    @Override
    public String getSelectID(String id) {
        return chapterDao.getSelectID(id);
    }
}
