package cn.liu.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.liu.dao.AlbumDao;
import cn.liu.dto.AlbumASArticleDTO;
import cn.liu.entity.Album;

import cn.liu.service.AlbumService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("albumService")
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;


    @Override
    public void getInsert(Album album) {
        albumDao.insert(album);
    }

    @Override
    public void getDelete(String id) {
        albumDao.delete(id);
    }

    @Override
    public void getUpdate(Album album) {
        albumDao.update(album);
    }

    @Override
    public Map<String, Object> queryByPager(int rows, int page) {


        System.out.println(
                rows +"_________________"+page
        );
        //需要返回当前页数 ， 键为；page
        Map<String ,Object> map = new HashMap<>();
        map.put("page" , page);
        //需要返回当前总条数
        Integer count = getCount();
        map.put("records",count);

        //需要返回当前页展示的数据
        //从第几条开始（page-1）*rows(每页展示多少条数据)
        List<Album> albums = albumDao.queryByPager((page - 1) * rows, rows);
        map.put("rows",albums);

        //需要返回总页数
        Integer totalPage = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",totalPage);
        return map;
    }

    @Override
    public Integer getCount() {
        return albumDao.getCount();
    }

    @Override
    public List<Album> getSelectAll() {
        return albumDao.getSelect();
    }

    @Override
    public void albumExport(List<Album> list) {


        for (Album album : list) {
            album.setImg("D:\\final_180\\cmfz_180\\src\\main\\webapp\\upload\\video\\img\\"+album.getImg());
        }


        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑","专辑空间"),
                Album.class, list);
        try {
            workbook.write(new FileOutputStream("D:/cmfz-album.xls"));
        }catch (Exception e){

        }finally {
            try {
                workbook.close();
            }catch (Exception e){}
        }
    }

    @Override
    public List<AlbumASArticleDTO> getSelectAlbum() {
        String s = "http://localhost:80/cmfz/upload/video/img/";
        //当执行这个serive时 ， type == 0 ， 并查询对应专辑集数
        List<AlbumASArticleDTO> selectAlbumDTO = albumDao.getSelectAlbumDTO();
        for (AlbumASArticleDTO albumASArticleDTO : selectAlbumDTO) {
            albumASArticleDTO.setType(0);
            albumASArticleDTO.setSet_count(albumDao.getCount());

            albumASArticleDTO.setThumbnail(s+albumASArticleDTO.getThumbnail());
        }


        return selectAlbumDTO;
    }


}
