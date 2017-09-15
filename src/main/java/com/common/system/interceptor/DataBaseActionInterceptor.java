package com.common.system.interceptor;


import com.common.system.entity.RcOperationLog;
import com.common.system.mapper.RcOperationLogMapper;
import com.google.gson.Gson;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * <p>数据库操作拦截器</p>
 * <p>method = "update"表示拦截增删改<p/>
 * Created by Mr.Yangxiufeng on 2017/9/14.
 * Time:15:47
 * ProjectName:Common-admin
 */
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DataBaseActionInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseActionInterceptor.class);

    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        if (invocation.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
            StatementHandler delegate = (StatementHandler) ReflectHelper.getFieldValue(statementHandler, "delegate");
            //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getFieldValue(delegate, "mappedStatement");
            //执行的方法名
            String method = mappedStatement.getSqlCommandType().name();

            BoundSql boundSql = delegate.getBoundSql();
            Object obj = boundSql.getParameterObject();

            if (!(obj instanceof RcOperationLogMapper)) {
                Object[] args = invocation.getArgs();
                Connection connection = (Connection) args[0];
                //INSERT, UPDATE, DELETE
                if ("INSERT".equals(method) || "UPDATE".equals(method) || "DELETE".equals(method)) {

                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private void saveLog(Object arg, Object obj) {

//        MappedStatement mappedStatement = (MappedStatement) arg;
//        // 执行的方法名
//        String name = mappedStatement.getSqlCommandType().name();
//        Gson gson = new Gson();
//        String change = gson.toJson(obj);
//        mappedStatement.


    }
}
