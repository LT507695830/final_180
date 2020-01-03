package cn.liu.controller;


import it.sauronsoftware.jave.Encoder;
import cn.liu.entity.Chapter;
import cn.liu.service.ChapterService;
import cn.liu.util.PrintSize;
import it.sauronsoftware.jave.MultimediaInfo;
import jdk.nashorn.internal.ir.GetSplitState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequestMapping("Chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService ;
    @ResponseBody
    @RequestMapping("queryByPager")
    /*
     * rows : 每页显示的条数
     * page : 当前页
     * */
    public Map<String, Object> queryByPager(Integer rows, Integer page , String albumId) {
        System.out.println(albumId);

        Map<String, Object> map = chapterService.queryByPager(rows, page,albumId);
        return map;
    }
/*
    "<embed style='width:100%;height:100px'  loop='-1' autostart='false' src='${pageContext.request.contextPath}/upload/video/img/" + cellvalue + "'/>";
*/
    @ResponseBody
    @RequestMapping("edit")
    public Map<String, Object> edit(Chapter chapter, String oper, String src , String[] id, HttpSession session , String albumId) {
        Map<String, Object> map = new HashMap<>();

        if ("add".equals(oper)) {
            //只需创建一个ID即可
            String iD = UUID.randomUUID().toString() ;
            chapter.setId(iD);
            chapter.setAlbum_id(albumId);
            chapterService.getInsert(chapter);

            map.put("chapter_id",iD);

            return map ;
        } else if ("edit".equals(oper)) {
            if("".equals(src)){
                //为空表示没有修改音频
                //让img为null
                chapter.setSrc(null);
                chapterService.getUpdate(chapter);
                map.put("chapter_id" , "");
                return  map ;
            }else {
                chapter.setSrc(null);
                chapterService.getUpdate(chapter);
                map.put("chapter_id" , chapter.getId());
                return  map ;
            }
        } else if ("del".equals(oper)) {

            chapterService.getDelete(chapter.getId());

        }
        return null;
    }

    @ResponseBody
    @RequestMapping("ChapterUpload")
    public  void chapterUpload(MultipartFile src , String chapter_id , HttpSession session) throws Exception {

        if("".equals(chapter_id)){
            return;
        }

        //文件大小
        long size = src.getSize();
        //规定格式
        DecimalFormat format = new DecimalFormat("0.00");
        String str = String.valueOf(size);
        Double dd = Double.valueOf(str)/1024/1024;
        String sizes = format.format(dd)+"MB";
        //获取路径

        String realPath = session.getServletContext().getRealPath("/upload/video");


        File file1 = new File(realPath);

        if (!file1.exists()) {
            file1.mkdirs();
        }
       ///获取音频时长



        try {
            String FileName = new Date().getTime() + "_" + src.getOriginalFilename();
            src.transferTo(new File(realPath, FileName));
            Chapter chapter = new Chapter();
            Encoder encoder = new Encoder();
            MultimediaInfo info = encoder.getInfo(new File(realPath+"/"+FileName));


            long duration = info.getDuration();
            chapter.setDuration(duration/60000+":"+(duration/1000-duration/60000*60));
            chapter.setId(chapter_id);
            chapter.setSrc(FileName);
            chapter.setSize(sizes);

            chapterService.getUpdate(chapter);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    @ResponseBody
    @RequestMapping("ChapterDownload")
    public  void chapterDownload(String chapter_id, HttpServletRequest request , HttpServletResponse response){
        System.out.println(
                chapter_id
        );
        //文件名
        String src = chapterService.getSelectID(chapter_id);
        //文件所在路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/video");
        //获取输出流 , 输入流
        ServletOutputStream outputStream = null ;
        FileInputStream fileInputStream = null ;

        try {
            outputStream = response.getOutputStream();
            fileInputStream = new FileInputStream(new File(realPath, src));
            String encode = URLEncoder.encode(src, "UTF-8");
            //设置下载的文件名
            response.setHeader("content-disposition","attchment;filename="+encode);

            byte[] bytes = new byte[2048];
            while(true){
                int i = fileInputStream.read(bytes,0,bytes.length);
                if(i==-1)break;
                outputStream.write(bytes,0,i);
            }


        }catch (Exception e){

        }finally {
            try {
                if(outputStream !=null)outputStream.close();
            }catch (Exception e){}

            try {
                if(fileInputStream !=null)  fileInputStream.close();
            }catch (Exception e){}

        }


        //设置编码格式

    }




}
