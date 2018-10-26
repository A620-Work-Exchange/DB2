package util;

import domain.enumeration.BundleType;

public class TypeStringConverter {
    public static String convertTypeToString(BundleType bundleType) {
        switch (bundleType) {
            case Call:
                return "电话套餐";
            case SMS:
                return "短信套餐";
            case Local:
                return "本地流量套餐";
            case Domestic:
                return "国内流量套餐";
        }
        return "基础服务";
    }
}
