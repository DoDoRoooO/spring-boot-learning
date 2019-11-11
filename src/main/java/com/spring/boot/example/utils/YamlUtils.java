package com.spring.boot.example.utils;

import com.spring.boot.example.model.Invoice;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.reader.UnicodeReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * spring 使用snakeyaml解析yaml文件
 */
public class YamlUtils {

    public static Map<String, Object> yamlHandler(String path) {
        Map<String, Object> result = new LinkedHashMap<>();
        Resource resource = new ClassPathResource(path);
        try (Reader reader = new UnicodeReader(resource.getInputStream())) {
            Yaml yaml = new Yaml();
            return yaml.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Map<String, Object> loadString(String yamlstr) {
        Yaml yaml = new Yaml();
        return (Map) yaml.load(yamlstr);
    }

    public static Map<String, Object> loadFile(String path) {
        try (InputStream in = YamlUtils.class.getClassLoader().getResourceAsStream(path)) {
            Yaml yaml = new Yaml();
            Map<String, Object> map = yaml.load(in);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Map<String, Object>> loadAllFile(String path) {
        try (InputStream in = YamlUtils.class.getClassLoader().getResourceAsStream((path))) {
            Yaml yaml = new Yaml();
            //Object 为LinkedHashMap
            Iterable<Object> objects = yaml.loadAll(in);
            List result = new ArrayList();
            objects.forEach(o -> result.add(o));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static Object loadFileToObject(String path) {
        try (InputStream in = YamlUtils.class.getClassLoader().getResourceAsStream((path))) {
            Yaml yaml = new Yaml();
            return  yaml.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        //读取字符串  ""转义 ''不转义
        System.out.println(YamlUtils.loadString("name: \'aliya\\t天使\'"));

        //读取文件
        System.out.println(YamlUtils.loadFile("yaml-example/any-object-example.yaml"));

        //读取文件片段
        System.out.println(YamlUtils.loadAllFile("yaml-example/part-example.yaml"));

        //读取文件转对象
        System.out.println(YamlUtils.loadFileToObject("yaml-example/person-example.yaml"));

        Yaml yaml = new Yaml(new Constructor(Invoice.class));
        Invoice invoice = yaml.load(YamlUtils.class.getClassLoader().getResourceAsStream("yaml-example/example2_27.yaml"));
        System.out.println(invoice);

        yaml = new Yaml();
        String output = yaml.dump(invoice);
        System.out.println(output);

        System.out.println(YamlUtils.yamlHandler("yaml-example/example2_27.yaml"));

    }
}
