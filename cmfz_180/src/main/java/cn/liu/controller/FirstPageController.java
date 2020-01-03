package cn.liu.controller;

import cn.liu.dto.AlbumASArticleDTO;
import cn.liu.dto.BannerDTO;
import cn.liu.entity.Album;
import cn.liu.service.AlbumService;
import cn.liu.service.ArticleService;
import cn.liu.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequestMapping("first_page")
public class FirstPageController {

    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping("test")
    public Map<String, List<Map<String, Object>>> first_page(String uid, String type) {
        HashMap<String, List<Map<String, Object>>> objectObjectHashMap = new HashMap<>();
        if ("all".equals(type)) {
            //当type = all 时，需要擦查询轮播图和专辑
            List<BannerDTO> selectBannerDTO = bannerService.getSelectBannerDTO();
            List<Map<String, Object>> BannerList = new ArrayList<>();
            for (BannerDTO bannerDTO : selectBannerDTO) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("thumbnail", bannerDTO.getThumbnail());
                map.put("desc", bannerDTO.getDesc());
                map.put("id", bannerDTO.getId());
                BannerList.add(map);
            }
            objectObjectHashMap.put("header", BannerList);

            //body
            List<Map<String, Object>> AlbumASArticleList = new ArrayList<>();
            List<AlbumASArticleDTO> selectAlbum = albumService.getSelectAlbum();
            for (AlbumASArticleDTO albumASArticleDTO : selectAlbum) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("thumbnail", albumASArticleDTO.getThumbnail());
                map.put("title", albumASArticleDTO.getTitle());
                map.put("author", albumASArticleDTO.getAuthor());
                map.put("type", albumASArticleDTO.getType());
                map.put("set_count", albumASArticleDTO.getSet_count());
                map.put("create_date", albumASArticleDTO.getCreate_date());
                AlbumASArticleList.add(map);
            }
            List<AlbumASArticleDTO> selectArticle = articleService.getSelectArticle();
            for (AlbumASArticleDTO albumASArticleDTO : selectArticle) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("thumbnail", albumASArticleDTO.getThumbnail());
                map.put("title", albumASArticleDTO.getTitle());
                map.put("author", albumASArticleDTO.getAuthor());
                map.put("type", albumASArticleDTO.getType());
                map.put("set_count", albumASArticleDTO.getSet_count());
                map.put("create_date", albumASArticleDTO.getCreate_date());
                AlbumASArticleList.add(map);
            }

            objectObjectHashMap.put("body", AlbumASArticleList);


            return objectObjectHashMap;


        } else if ("wen".equals(type)) {
            List<BannerDTO> selectBannerDTO = bannerService.getSelectBannerDTO();
            List<Map<String, Object>> AlbumASArticleList = new ArrayList<>();
            List<AlbumASArticleDTO> selectAlbum = albumService.getSelectAlbum();
            for (AlbumASArticleDTO albumASArticleDTO : selectAlbum) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("thumbnail", albumASArticleDTO.getThumbnail());
                map.put("title", albumASArticleDTO.getTitle());
                map.put("author", albumASArticleDTO.getAuthor());
                map.put("type", albumASArticleDTO.getType());
                map.put("set_count", albumASArticleDTO.getSet_count());
                map.put("create_date", albumASArticleDTO.getCreate_date());
                AlbumASArticleList.add(map);
            }
            objectObjectHashMap.put("body", AlbumASArticleList);
            return objectObjectHashMap;


        } else if ("si".equals(type)) {
            List<Map<String, Object>> AlbumASArticleList = new ArrayList<>();
            List<AlbumASArticleDTO> selectArticle = articleService.getSelectArticle();
            for (AlbumASArticleDTO albumASArticleDTO : selectArticle) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("thumbnail", albumASArticleDTO.getThumbnail());
                map.put("title", albumASArticleDTO.getTitle());
                map.put("author", albumASArticleDTO.getAuthor());
                map.put("type", albumASArticleDTO.getType());
                map.put("set_count", albumASArticleDTO.getSet_count());
                map.put("create_date", albumASArticleDTO.getCreate_date());
                AlbumASArticleList.add(map);
            }
            objectObjectHashMap.put("body", AlbumASArticleList);
            return objectObjectHashMap;

        }

        return null;
    }

}
