package cn.liu.controller;

import cn.liu.dao.BannerDao;
import cn.liu.entity.Banner;
import cn.liu.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Component
@RequestMapping("Banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;



    @ResponseBody
    @RequestMapping("queryByPager")
    /*
     * rows : 每页显示的条数
     * page : 当前页
     * */
    public Map<String, Object> queryByPager(Integer rows, Integer page) {


        Map<String, Object> map = bannerService.queryByPager(rows, page);

        return map;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Map<String, Object> edit(Banner banner, String oper, String img ,String[] id, HttpSession session) {

        Map<String, Object> map = new HashMap<>();

        if ("add".equals(oper)) {
            String ID = UUID.randomUUID().toString();
            banner.setId(ID);
            bannerService.getInsert(banner);
            map.put("bannerId", ID);
            return map;
        } else if ("edit".equals(oper)) {

            //判断 img是否为空
            System.out.println(img);
            System.out.println(banner+"--------------------------------");
            if("".equals(img)){
                banner.setImg(null);
                bannerService.getUpdate(banner);
                map.put("bannerId" ,null);
                return map;
            }else {
                System.out.println(
                        banner
                );
                //若为空，则将图片不修改
                banner.setImg(null);
                bannerService.getUpdate(banner);


                map.put("bannerId" ,banner.getId());
                return map ;
            }
        } else if ("del".equals(oper)) {

            for (String s : id) {
                String s1 = bannerService.getselectImg(s);
                File file = new File(session.getServletContext().getRealPath("/upload/img/"+s1));
                file.delete();
            }
            //删除图片
            bannerService.getDeleteAll(id);
        }
        return null;
    }


    @RequestMapping("bannerUpload")
    public void bannerUpload(MultipartFile img, String bannerId, HttpSession session) {
        System.out.println(img.getOriginalFilename()+"-------------------------------");
        System.out.println(bannerId+"-----------------------------");

        boolean a = "".equals(bannerId) ;
        System.out.println(a);
        if (a)return;
        System.out.println("liutao");
        //获取路径
        String realPath = session.getServletContext().getRealPath("/upload/img");
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        try {
            String FileName = new Date().getTime() + "_" + img.getOriginalFilename();
            Banner banner = new Banner();
            banner.setId(bannerId);
            banner.setImg(FileName);
            System.out.println(bannerId);
            bannerService.getUpdate(banner);
            img.transferTo(new File(realPath, FileName));
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
