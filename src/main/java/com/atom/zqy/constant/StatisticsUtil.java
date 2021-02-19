package com.atom.zqy.constant;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description
 * @Date 2021/1/18 10:29
 */
public class StatisticsUtil {
    //按 "_"拼接
    private static final String SPLIT = "_";
    private static final String REDIS_KEY_PRE = "cmb:mau:";
    //活动级别统计
    private static final String ACTIVITY_STATIS_COUNTER = REDIS_KEY_PRE + "activity_statis:";
    //每小时pv统计
    private static final String PREFIX_PERHOUR_PV = "staPv:hash:perhour:";
    //每天pv统计
    private static final String PREFIX_PERDAY_PV = "staPv:hash:perday:";
    //每小时uv统计
    private static final String PREFIX_PERHOUR_UV = "staUv:hash:perhour:";
    //每天uv统计
    private static final String PREFIX_PERDAY_UV = "staPv:hash:perday:";
    //SET 去重统计uv
    private static final String SET_PERHOUR_UV = "staUv:set:perhour:";
    private static final String SET_PERDAY_UV = "staUv:set:perhour:";
    //月度统计pv
    private static final String PREFIX_PERMONTH_PV = "staMonthPV:hash:";
    //月度统计uv
    private static final String PREFIX_PERMONTH_UV = "staMonthUV:hash:";
    //月度uv set去重
    private static final String SET_PERMONTH_UV = "staMonthUv:set:";
    //总统计pv
    private static final String PREFIX_TOTAL_PV = "staTotalPV:hash:";
    //总统计uv
    private static final String PREFIX_TOTAL_UV = "staTotalUV:hash:";
    //uv set去重
    private static final String SET_TOTAL_UV = "staTotalUv:set:";


    /*
    每小时pv统计的key
    hash key staPv:hash:perHour:busiPlatform_busiType_busiId
        field:20160718_14_@PeopleUserType
        value:countValue
     */
    public static String getPreHourPvKey(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + PREFIX_PERHOUR_PV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }

    /*
    每天次数统计
    hash key staPv:hash:perday:busiPlatform_busiType_busiId
        field:20160718_@PeopleUserType
        value:countValue
     */
    public static String getPreDayPvKey(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + PREFIX_PERDAY_PV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }

    /*
    每小时人数统计
    hash key staUv:hash:perhour:busiPlatform_busiType_busiId
        field:20160718_14_@PeopleUserType
        value:countValue
     */
    public static String getPreHourUvKey(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + PREFIX_PERHOUR_UV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }

    /*
    每天人数统计
    hash key staUv:hash:perday:busiPlatform_busiType_busiId
        field:20160718_@PeopleUserType
        value:countValue
     */
    public static String getPreDayUvKey(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + PREFIX_PERDAY_UV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }


    /*
    每小时人数统计(set)
    set key staUv:set:perhour:busiPlatform_busiType_busiId
        value:userId
     */
    public static String getPreHourKeySet(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + SET_PERHOUR_UV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }

    /*
    每天人数统计（set）
    hash staUv:set:perday:busiPlatform_busiType_busiId
        value:userId
     */
    public static String getPreDayKeySet(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + SET_PERDAY_UV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }


    /*
    每月次数统计
    hash staPv:hash:permonth:busiPlatform_busiType_busiId
        field:20160718_@PeopleUserType
        value:countValue
     */
    public static String getPreMonthPvKey(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + PREFIX_PERMONTH_PV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }


    /*
    每月人数统计
    hash staUv:hash:permonth:busiPlatform_busiType_busiId
        field:20160718_@PeopleUserType
        value:countValue
     */
    public static String getPreMonthUvKey(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + PREFIX_PERMONTH_UV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }

    /*
    每月人数统计(set)
    hash staUv:set:permonth:busiPlatform_busiType_busiId
        value:countValue
     */
    public static String getPreMonthKeySet(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + SET_PERMONTH_UV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }

    /*
    次数总统计
    hash staTotalPV:hash:busiPlatform_busiType_busiId
        field:20160718_@PeopleUserType
        value:countValue
     */
    public static String getTotalPvKey(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + PREFIX_TOTAL_PV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }


    /*
    人数总统计
    hash staTotalUV:hash:busiPlatform_busiType_busiId
        field:20160718_@PeopleUserType
        value:countValue
     */
    public static String getTotalUvKey(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + PREFIX_TOTAL_UV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }

    /*
    人数总统计(set)
    hash staTotalUv:set:busiPlatform_busiType_busiId
        value:countValue
     */
    public static String getTotalKeySet(int busiPlatform,int busiType, int busiId) {
        return ACTIVITY_STATIS_COUNTER + SET_TOTAL_UV + busiPlatform + SPLIT + busiType + SPLIT +busiId;
    }


}
