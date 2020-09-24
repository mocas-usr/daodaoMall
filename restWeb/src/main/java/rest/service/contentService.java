package rest.service;

import rest.entity.TbContent;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/24 下午3:34
 * @email: wangyuhang_mocas@163.com
 */
public interface contentService {
    List<TbContent>    getContentList(long contentCid);

}
