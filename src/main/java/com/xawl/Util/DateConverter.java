package com.xawl.Util;/**
 * Created by doter on 2017/8/1.
 */

import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: doter
 * \* Date: 2017/8/1
 * \* Time: 11:46
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            return new Date(dateFormat.parse(source).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}