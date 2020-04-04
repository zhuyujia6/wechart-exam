package com.zyj.wechartexam.webCtr.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换器
 * 备注：一般我们通过表单拿到的日期都是字符串，但是数据库存储的是日期类型，每次单独转换很麻烦，写一个统一的日期转换器
 *       将字符串自动转换为日期类型。
 */
@Component
public class DateConverter implements Converter<String, Date> {
    public static Logger logger = LoggerFactory.getLogger(DateConverter.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 实现转换的方法
     * @param s
     * @return
     */
    @Override
    public Date convert(String s) {
        if(s==null || s.length()==0) {
            return null;
        }
        Date date = null;
        try {
            date = sdf.parse(s);//将字符串转为日期,ctrl+alt+t
        } catch (ParseException e){
            logger.error("转换的参数"+s+"为日期值时出错："+e.getMessage());
        }
        return date;
    }
}
