package daodao.entity.pojo;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/12 下午3:22
 * @email: wangyuhang_mocas@163.com
 */

import java.util.List;

/**
 *@program: daodao
 *@description:datagrid数据pojo
 *@author: mocas_wang
 *@create: 2020-09-12 15:22
 */
public class EUDataGridResult {

    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
