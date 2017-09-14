package com.common.system.interceptor;


import com.common.system.entity.RcOperationLog;
import com.common.system.service.RcOperationLogService;
import com.common.system.shiro.ShiroFactory;
import com.common.system.shiro.ShiroKit;
import com.common.system.shiro.ShiroUser;
import com.google.gson.Gson;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;

/**<p>数据库操作拦截器</p>
 * <p>method = "update"表示拦截增删改<p/>
 * Created by Mr.Yangxiufeng on 2017/9/14.
 * Time:15:47
 * ProjectName:Common-admin
 */
@Intercepts({@Signature(method = "update",type = Executor.class,args = {MappedStatement.class,Object.class})})
@Component
public class DataBaseActionInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseActionInterceptor.class);

    private Properties properties;

    //无法实例化Spring bean ，放弃使用
//    @Autowired
//    private RcOperationLogService operationLogService;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        // 获取执行的方法
        if (args.length > 1) {
            // 传入的对象
            Object obj = args[1];
            if (obj instanceof RcOperationLog) {
                // 若是日志对象 则直接跳过
                return invocation.proceed();
            }
            saveLog(args[0], obj);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    private void saveLog(Object arg, Object obj) {

        MappedStatement mappedStatement = (MappedStatement) arg;
        // 执行的方法名
        String name = mappedStatement.getSqlCommandType().name();
        Gson gson = new Gson();
        String change = gson.toJson(obj);

    }
}
