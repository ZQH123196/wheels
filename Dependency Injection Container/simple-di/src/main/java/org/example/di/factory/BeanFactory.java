package org.example.di.factory;

import org.example.use.bean.User;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactory {

    String workDir = Paths.get("./").toAbsolutePath().toString();
    // 以类型为基准
    Map<Class<?>, BeanDefinition> beanDefinitionMapByType = new HashMap<>();


    /**
     * 扫描 bean 并注册相应的信息
     * 默认扫描路径就是从当前文件夹递归直到最深处
     */
    public void init() {
        List<BeanDefinition> beanDefinitionList = findAllBean(workDir);
        setBeanDefinitionMap(beanDefinitionList);
    }

    private void setBeanDefinitionMap(List<BeanDefinition> beanDefinitionList) {
    }



    Map<Class<?>, Object> instanceCacheByType = new HashMap<>();
    Map<String, Object> instanceCacheByName = new HashMap<>();
    /**
     * 启动，并创建启动时所必须的 bean
     */
    public void start() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        generateBeanInstance2Cache();
    }

    private void generateBeanInstance2Cache() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {



        for (BeanDefinition beanDefinition : beanDefinitionMapByType.values()) {
            // 不是单例的不需要缓存，将非单例的直接跳过
            if (!beanDefinition.getScope().equals(BeanDefinition.Scope.SINGLETON)) {
                continue;
            }
            // 懒加载就直接跳过
            if (beanDefinition.isLazy()) {
                continue;
            }


            Class<?> clazz = beanDefinition.getClazz();
            Object instance = clazz.getDeclaredConstructor().newInstance();
            instanceCacheByType.put(clazz, instance);
            instanceCacheByName.put(beanDefinition.getBeanName(), instance);
        }

    }


    private List<BeanDefinition> findAllBean(String workDir) {

        return null;
    }

    public Object getBean(Class<User> userClass) {
        return instanceCacheByType.get(userClass);
    }

    public Object getBean(String beanName) {
        return instanceCacheByName.get(beanName);
    }
}
