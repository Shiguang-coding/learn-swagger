package com.shiguang.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinConverter {

    public static String toPinyin(String chinese) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuilder pinyin = new StringBuilder();
        try {
            for (char ch : chinese.toCharArray()) {
                if (Character.toString(ch).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(ch, format);
                    if (pinyinArray != null) {
                        pinyin.append(pinyinArray[0]);
                    }
                } else {
                    pinyin.append(ch);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        return pinyin.toString();
    }

    public static void main(String[] args) {
//        String name = "张三";
//        String pinyin = toPinyin(name);
//        System.out.println(pinyin); // 输出: zhangsan

        String name = "王二麻子";
        String pinyin = toPinyin(name);
        System.out.println(pinyin); // 输出: zhangsan
    }
}
