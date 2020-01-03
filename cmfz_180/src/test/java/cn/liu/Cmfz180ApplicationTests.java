package cn.liu;


import cn.liu.dao.AdminDao;
import cn.liu.dao.BannerDao;
import cn.liu.dao.GuruDao;
import cn.liu.dto.AlbumASArticleDTO;
import cn.liu.dto.BannerDTO;
import cn.liu.dto.GuruDTO;
import cn.liu.dto.UserDTO;
import cn.liu.entity.*;
import cn.liu.service.*;
import cn.liu.util.PrintSize;


import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest(classes = Cmfz180Application.class)
@RunWith(SpringRunner.class)
public class Cmfz180ApplicationTests {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private ChapterService chapterService;


    @Test
    public void contextLoads() {
        Admin liut = adminDao.selectUserName("liut");
        System.out.println(liut);
    }


    @Test
    public void test1() {
        Banner banner = new Banner();
        banner.setId(UUID.randomUUID().toString());
        banner.setTitle("captcha.jpg");
        banner.setImg("captcha.jpg");
        banner.setCreate_date(new Date());
        banner.setStatus("0");
        System.out.println(banner);
        bannerDao.insert(banner);
        banner.setId(UUID.randomUUID().toString());
        banner.setTitle("favicon.ico");
        banner.setImg("favicon.ico");
        banner.setCreate_date(new Date());
        banner.setStatus("0");
        System.out.println(banner);
        bannerDao.insert(banner);
        banner.setId(UUID.randomUUID().toString());
        banner.setTitle("shouye.jpg");
        banner.setImg("shouye.jpg");
        banner.setCreate_date(new Date());
        banner.setStatus("0");
        System.out.println(banner);
        bannerDao.insert(banner);


    }

    @Test
    public void test2() {
        bannerDao.delete("dba37f1f-c84d-4842-97d3-13113a9e39ee");
    }


    @Test
    public void test3() {
        List<Banner> banners = bannerDao.queryByPager(1, 2);
        banners.forEach(banner -> System.out.println(banner));
    }

    @Test
    public void test4() {
        String s = bannerDao.selectID("c8c4428b-f4f4-449c-aeee-4f5efcbe9512");
        System.out.println(s);

    }

    @Test
    public void test5() {
        //Map<String, Object> stringObjectMap = chapterService.queryByPager(0, 1, "966d834b-51b3-4da4-b313-0535f3129507");

        Integer count = chapterService.getCount("966d834b-51b3-4da4-b313-0535f3129507");
        System.out.println(count);
    }

    @Test
    public void test7() {
        long a = 71562;
        String printSize = PrintSize.getPrintSize(a);
        System.out.println(printSize);
    }

    @Autowired
    private GuruDao guruDao;

    @Autowired
    private GuruService guruService;

    @Test
    public void test8() {
        List<Integer> list = new ArrayList<>();
        String[] add = {"day1", "day2", "day3", "day4", "day5", "day6", "day7"};
        Guru guru = new Guru();
        guru.setId("18");
        guru.setCreate_date(new Date());
        guruService.getInsert(guru);

        for (int i = 0; i < 7; i++) {
            HashMap<String, Object> map = new HashMap<>();
            Integer selectDTO = guruService.getSelectDTO(i);

            list.add(selectDTO);
            StringBuilder a = new StringBuilder("");
            for (Integer integer : list) {

                integer.toString();
            }

        }

        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-f203c05bb195458bb2f1c31ecb7edac9");
        goEasy.publish("liutao", list.toString());

    }


    @Test
    public void test9() {
        List<GuruDTO> selectMonth = guruService.getSelectMonth();
        for (GuruDTO guruDTO : selectMonth) {
            System.out.println(guruDTO);
        }
    }

    @Test
    public void test10() throws ParseException {
        Guru guru = new Guru();
        guru.setId("37");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("2019-12-29");
        guru.setCreate_date(parse);
        guruService.getInsert(guru);
        List<Integer> list = new ArrayList<>();
        List<GuruDTO> selectMonth = guruService.getSelectMonth();
        for (int i = 1; i <= 12; i++) {
            boolean F = true ;
            for (GuruDTO guruDTO : selectMonth) {
                if (guruDTO.getMonth() == i) {
                    list.add(guruDTO.getCount());
                    F = false ;
                    break;
                }
            }

            if(F)list.add(0);


        }
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-f203c05bb195458bb2f1c31ecb7edac9");
        goEasy.publish("liutao", list.toString());

    }


    @Test
    public void  testss(){

        HashMap<String, String> map = new HashMap<>();
        map.put("1","2");
        map.put("3","4");
        System.out.println(map);
        String a = map.toString() ;
        String s = a.replaceAll("=", ":");
        System.out.println(s);
    }


    @Autowired
    private  UserService userService ;
    @Test
    public  void  ceshi(){
        User user = new User();
        user.setId("10");
        user.setAddress("新疆");
        userService.getInsert(user);
        List<Map<String , Object>> list = new ArrayList<>() ;

        List<UserDTO> selectAdd = userService.getSelectAdd();
        for (UserDTO userDTO : selectAdd) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name" , '"'+userDTO.getAddress()+'"');
            map.put("value",userDTO.getCount());
            list.add(map);
        }
        System.out.println(list.toString().replaceAll("=",":"));
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-f203c05bb195458bb2f1c31ecb7edac9");
        goEasy.publish("liutao", list.toString().replaceAll("=",":"));
    }

    @Autowired
    private BannerService bannerService ;

    @Autowired
    private AlbumService albumService ;


    @Autowired
    private ArticleService articleService  ;
    @Test
    public  void  test11(){
        List<AlbumASArticleDTO> selectArticle = articleService.getSelectArticle();
        for (AlbumASArticleDTO albumASArticleDTO : selectArticle) {

            System.out.println(albumASArticleDTO);
        }
    }






}
