package com.example.demo.config;

import com.example.demo.vo.EnumVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 枚举配置类
 */
@Slf4j
@Component
public class DictEnumConfig {

    // 通同匹配
    private static final String RESOURCE_PATTERN = "/**/*Enum.class";

    // 扫描的包名
    private static final String BASE_PACKAGES = "com.example.demo.enums";

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    @Bean(name = "enumConfig")
    public Map<String, List<EnumVO>> enumConfig() {
        Map<String, List<EnumVO>> enumMap = new HashMap<>();

        try {
            // 根据classname生成class对应的资源路径,需要扫描的包路径
            //ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
            String pattern = ClassUtils.convertClassNameToResourcePath(BASE_PACKAGES) + RESOURCE_PATTERN;
            // 获取classname的IO流资源
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            // MetadataReaderFactory接口 ，MetadataReader的工厂接口。允许缓存每个MetadataReader的元数据集
            MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);

            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    // 通过class资源（resource）生成MetadataReader
                    MetadataReader reader = readerFactory.getMetadataReader(resource);
                    // 获取class名
                    String className = reader.getClassMetadata().getClassName();
                    Class<?> clz = Class.forName(className);

                    if (!clz.isEnum()) {
                        continue;
                    }

                    // 将枚举类名首字母转小写，去掉末尾的Enum
                    enumMap.put(clz.getSimpleName(), this.enumToList(clz));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return enumMap;
    }

    public List<EnumVO> enumToList(Class<?> dictEnum) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<EnumVO> list = new ArrayList<>();

        Method valuesMethod = dictEnum.getMethod("values");
        Object[] values = (Object[]) valuesMethod.invoke(null);

        for (Object obj : values) {
            EnumVO enumVO = new EnumVO();
            BeanUtils.copyProperties(obj, enumVO);
            list.add(enumVO);
        }

        return list;
    }
}
