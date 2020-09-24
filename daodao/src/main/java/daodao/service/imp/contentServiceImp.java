package daodao.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/24 下午2:29
 * @email: wangyuhang_mocas@163.com
 */

import daodao.dao.TbContentMapper;
import daodao.entity.TbContent;
import daodao.entity.pojo.TaotaoResult;
import daodao.service.contentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-24 14:29
 */
@Service
public class contentServiceImp implements contentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public TaotaoResult insertContent(TbContent content) {
        //补全pojo
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        return TaotaoResult.ok();
    }
}
