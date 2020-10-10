package daodao.util;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/10 下午5:32
 * @email: wangyuhang_mocas@163.com
 */

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *@program: daodao
 *@description:测试文件
 *@author: mocas_wang
 *@create: 2020-09-10 17:32
 */
public class GeneratorTest {
    public void testGenerator() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings=new ArrayList<String>();
        boolean overWriter=true;//指向配置文件
        File configFile=new File(GeneratorTest.class.getResource("/generatorConfig.xml").getFile());
        ConfigurationParser cp=new ConfigurationParser(warnings);
        Configuration config=cp.parseConfiguration(configFile);
        DefaultShellCallback callback=new DefaultShellCallback(overWriter);
        MyBatisGenerator myBatisGenerator=new MyBatisGenerator(config,callback,warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args)throws Exception {
        GeneratorTest generatorTest=new GeneratorTest();
        generatorTest.testGenerator();
    }
}
