package daodao.entity.pojo;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/13 上午9:00
 * @email: wangyuhang_mocas@163.com
 */

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-13 09:00
 */
public class EUtreeNode {
    private long id;
    private String text;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
