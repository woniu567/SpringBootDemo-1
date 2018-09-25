package cn.wbnull.springbootdemo.util;

import com.alibaba.fastjson.JSONObject;

/**
 * JSON 工具类
 *
 * @author dukunbiao(null) 2018-07-26
 *         https://github.com/dkbnull/Util
 */
public class JSONUtils {

    private static final String WHITESPACE = "   ";

    private JSONUtils() {

    }

    /**
     * 检查JSON是否为空
     *
     * @param json 待检查JSON
     * @return true/false
     */
    public static boolean isEmpty(JSONObject json) {
        return (json == null || json.isEmpty());
    }

    /**
     * String型获取JSON value值
     *
     * @param json JSON
     * @param key  key值
     * @return value值
     */
    public static String getJSONString(JSONObject json, String key) {
        return MapUtils.getMapString(json, key);
    }

    /**
     * int型获取JSON value值
     *
     * @param json JSON
     * @param key  key值
     * @return value值
     * @throws Exception
     */
    public static int getJSONInt(JSONObject json, String key) throws Exception {
        return MapUtils.getMapInt(json, key);
    }

    /**
     * int型获取JSON value值，无法转化则返回默认值
     *
     * @param json         JSON
     * @param key          key值
     * @param defaultValue 默认值
     * @return value值
     */
    public static int getJSONInt(JSONObject json, String key, int defaultValue) {
        return MapUtils.getMapInt(json, key, defaultValue);
    }

    /**
     * double型获取JSON value值
     *
     * @param json JSON
     * @param key  key值
     * @return value值
     * @throws Exception
     */
    public static double getJSONDouble(JSONObject json, String key) throws Exception {
        return MapUtils.getMapDouble(json, key);
    }

    /**
     * double型获取JSON value值，无法转化则返回默认值
     *
     * @param json         JSON
     * @param key          key值
     * @param defaultValue 默认值
     * @return value值
     */
    public static double getJSONDouble(JSONObject json, String key, double defaultValue) {
        return MapUtils.getMapDouble(json, key, defaultValue);
    }

    /**
     * Java Bean 转JSON
     *
     * @param object 待转化Java Bean
     * @return JSONObject
     */
    public static JSONObject javaBeanToJSON(Object object) throws Exception {
        return JSONObject.parseObject(JSONObject.toJSONString(object));
    }

    /**
     * 格式化JSON字符串，不校验JSON格式合法性
     *
     * @param value 待格式化JSON字符串
     * @return 格式化后JSON字符串
     */
    public static String JSONFormat(String value) {
        StringBuffer sb = new StringBuffer();

        int length = value.length();
        int number = 0;
        char key;

        for (int i = 0; i < length; i++) {
            key = value.charAt(i);

            if ((key == '[') || (key == '{')) {
                sb.append(key);
                sb.append('\n');

                number++;
                sb.append(indent(number));

                continue;
            }

            if ((key == ']') || (key == '}')) {
                sb.append('\n');

                number--;
                sb.append(indent(number));
                sb.append(key);

                if (((i + 1) < length) && (value.charAt(i + 1) != ',') &&
                        (value.charAt(i + 1) != '}') && (value.charAt(i + 1) != ']')) {
                    sb.append('\n');
                }

                continue;
            }

            if ((key == ',')) {
                sb.append(key);
                sb.append('\n');
                sb.append(indent(number));

                continue;
            }

            sb.append(key);
        }

        return sb.toString();
    }

    private static String indent(int number) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < number; i++) {
            sb.append(WHITESPACE);
        }

        return sb.toString();
    }
}