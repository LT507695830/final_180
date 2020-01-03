package cn.liu.service;

import cn.liu.dto.BannerDTO;
import cn.liu.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    //添加
    public void getInsert(Banner banner);
    //删除
    public void getDelete(String id);

    //修改
    public void getUpdate(Banner banner);

    //分页查询
    public Map<String, Object> queryByPager(int rows, int page);

    //查询总条数
    public  Integer getCount();

    //批量删除
    public void getDeleteAll(String[] id);

    //查询图片
    public String getselectImg(String id);

    //查询指定字段
    public List<BannerDTO> getSelectBannerDTO();

}
