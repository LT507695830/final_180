package cn.liu.controller;

import cn.liu.entity.Album;
import cn.liu.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.rmi.MarshalledObject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequestMapping("Album")
public class AlbumController {


    @Autowired
    private AlbumService albumService ;
    @ResponseBody
    @RequestMapping("queryByPager")
    /*
     * rows : 每页显示的条数
     * page : 当前页
     * */
    public Map<String, Object> queryByPager(Integer rows, Integer page) {


        Map<String, Object> map = albumService.queryByPager(rows, page);
        System.out.println(map);

        return map;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Map<String, Object> edit(Album album, String oper, String img , String[] id, HttpSession session) {

        Map<String, Object> map = new HashMap<>();

        if ("add".equals(oper)) {
            //id由UUID生成
            String iD = UUID.randomUUID().toString();
            album.setId(iD);
            //专辑下数量默认为0
            album.setCount(0);
            //时间为当前系统时间
            album.setCreate_date(new Date());
            //图片为空
            albumService.getInsert(album);
            System.out.println(album);
            System.out.println("添加");
            map.put("albumID" , iD);
            return map;
        } else if ("edit".equals(oper)) {
            System.out.println("修改方法");
        } else if ("del".equals(oper)) {
            System.out.println("删除方法");

        }
        return null;
    }


    @ResponseBody
    @RequestMapping("AlbumUpload")

    public  void albumUpload(MultipartFile img , String  albumID ,HttpSession session ){
        System.out.println(albumID);
        System.out.println(img.getOriginalFilename());

        System.out.println("正在上传图片");
        //获取路径
        String realPath = session.getServletContext().getRealPath("/upload/video/img");
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        try {
            String FileName = new Date().getTime() + "_" + img.getOriginalFilename();
            Album album = new Album();
            album.setId(albumID);
            album.setImg(FileName);
            System.out.println(albumID);
            albumService.getUpdate(album);
            img.transferTo(new File(realPath, FileName));
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
