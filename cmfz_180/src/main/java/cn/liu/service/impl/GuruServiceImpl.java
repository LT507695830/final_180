package cn.liu.service.impl;

import cn.liu.dao.GuruDao;
import cn.liu.entity.Guru;
import cn.liu.dto.GuruDTO;
import cn.liu.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("guruService")
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDao guruDao ;

    @Override
    public void getInsert(Guru guru) {
        guruDao.insert(guru);
    }

    @Override
    public void getDelete(String id) {
        guruDao.delee(id);
    }

    @Override
    public void getUpdate(Guru guru) {
        guruDao.update(guru);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Guru> getSelectAll() {
        return guruDao.getSelect();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryByPager(int rows, int page) {
        //需要返回当前页数 ， 键为；page
        Map<String ,Object> map = new HashMap<>();
        map.put("page" , page);
        //需要返回当前总条数
        Integer count = getCount();
        map.put("records",count);

        //需要返回当前页展示的数据
        //从第几条开始（page-1）*rows(每页展示多少条数据)
        List<Guru> gurus = guruDao.queryByPager((page-1)* rows , rows);
        map.put("rows",gurus);

        //需要返回总页数
        Integer totalPage = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",totalPage);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getSelectDTO(Integer fate) {
        return guruDao.getSelectDTO(fate);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getCount() {
        return guruDao.getCount();
    }

    @Override
    public List<GuruDTO> getSelectMonth() {
        return guruDao.getSelectMonth();
    }
}
