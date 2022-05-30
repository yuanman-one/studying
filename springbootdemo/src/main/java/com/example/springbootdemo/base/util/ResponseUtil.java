package com.example.springbootdemo.base.util;


import com.example.springbootdemo.base.constant.EduConstant;
import org.springframework.ui.ModelMap;

/**
 * 接口返回标准
 *
 */
public class ResponseUtil {

    // 成功
    private final static Integer SUCCESS = 0;

    // 统一错误
    private final static Integer ERROR = 500;

    private final static String REQUEST_SUCCESS = "操作成功!";

    /**
     * 请求数据成功、正确信息返回
     */
    public static ModelMap retCorrectModel(Object data)
    {
        return retInfo(SUCCESS, data, REQUEST_SUCCESS);
    }

    /**
     * 请求数据成功、正确信息返回
     */
    public static String retCorrectJson(Object data)
    {
        return retInfoJson(SUCCESS, data, REQUEST_SUCCESS);
    }


    /**
     * 请求数据成功、正确信息返回
     */
    public static ModelMap retCorrectInfo(String msg)
    {
        return retInfo(SUCCESS, null, msg);
    }


    /**
     * 校验错误信息返回
     */
    public static ModelMap retErrorInfo(String msg)
    {
        return retInfo(ERROR, null, msg);
    }

    /**
     * 校验错误信息返回
     */
    public static String retErrorJson(String msg)
    {
        return retInfoJson(ERROR, null, msg);
    }

    /**
     * 校验错误信息返回
     */
    public static ModelMap retErrorInfo(Integer errCode, String msg)
    {
        return retInfo(errCode, null, msg);
    }


    /**
     * @notes: 返回格式标准
     */
    private static ModelMap retInfo(Integer code, Object data, String msg)
    {
        ModelMap result = new ModelMap();
        result.put(EduConstant.RESULT_CODE, code);
        result.put(EduConstant.RESULT_DATA, data);
        result.put(EduConstant.RESULT_MSG, msg);
        return result;
    }

    /**
     * @notes: 返回格式标准, json
     */
    public static String retInfoJson(Integer code, Object data, String msg)
    {
        //return JSON.toJSONString(retInfo(code, data, msg), SerializerFeature.WriteMapNullValue);
        return msg;
    }
}
