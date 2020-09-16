package rest.pojo;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/16 下午4:32
 * @email: wangyuhang_mocas@163.com
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-16 16:32
 */
public class catNode {
    @JsonProperty("n")
    private  String name;
    @JsonProperty("u")
    private String url;
    @JsonProperty("i")
    private List<?> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
