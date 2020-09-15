package daodao.service;

import daodao.entity.TbItemParam;
import daodao.entity.pojo.TaotaoResult;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/15 上午11:02
 * @email: wangyuhang_mocas@163.com
 */

public interface itemParamService {

    TaotaoResult getItemParamByCid(long cid);
    TaotaoResult insertItemParam(TbItemParam itemParam);
}
