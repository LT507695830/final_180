package cn.liu.service.impl;

import cn.liu.annotation.AddCache;
import cn.liu.annotation.ClearCache;
import cn.liu.dao.BannerDao;
import cn.liu.dto.BannerDTO;
import cn.liu.entity.Banner;
import cn.liu.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("BannerService")
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao ;
    @Override
    @ClearCache
    public void getInsert(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    @ClearCache
    public void getDelete(String id) {
        bannerDao.delete(id);
    }

    @Override
    @ClearCache
    public void getUpdate(Banner banner) {
        bannerDao.update(banner);
    }

    @Override
    @AddCache
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
        List<Banner> banners = bannerDao.queryByPager((page-1)* rows , rows);
        map.put("rows",banners);

        //需要返回总页数
        Integer totalPage = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",totalPage);
        return map;
    }

    @Override
    @AddCache
    public Integer getCount() {


        return bannerDao.getCount();
    }

    @Override
    @ClearCache
    public void getDeleteAll(String[] id) {
        bannerDao.deleteAll(id);
    }

    @Override
    @AddCache
    public String getselectImg(String id) {
        return  bannerDao.selectID(id);
    }


    @Override
    @AddCache
    public List<BannerDTO> getSelectBannerDTO() {

        String s = "http://localhost:80/cmfz/upload/img/";

        List<BannerDTO> BannerDTOs = bannerDao.getSelectBannerDTO();
        for (BannerDTO bannerDTO : BannerDTOs) {
            bannerDTO.setThumbnail(s+bannerDTO.getThumbnail());
        }
        return BannerDTOs;
    }
}
