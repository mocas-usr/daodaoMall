package rest.dao.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/25 下午4:57
 * @email: wangyuhang_mocas@163.com
 */

import rest.dao.JedisClient;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-25 16:57
 */
public class jedisClient  implements JedisClient {
    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public String set(String key, String value) {
        return null;
    }

    @Override
    public String hget(String hkey, String key) {
        return null;
    }

    @Override
    public long hset(String hkey, String key, String value) {
        return 0;
    }

    @Override
    public long incr(String key) {
        return 0;
    }

    @Override
    public long expire(String key, int second) {
        return 0;
    }

    @Override
    public long ttl(String key) {
        return 0;
    }

    @Override
    public long del(String key) {
        return 0;
    }

    @Override
    public long hdel(String hkey, String key) {
        return 0;
    }
}
