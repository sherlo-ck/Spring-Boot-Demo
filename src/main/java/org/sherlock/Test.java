package org.sherlock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    public static void main(String[] args) {
        /**
         * 1.
         * 时间：6789,总数：9592

         // 求100000以内的质数
         long start = System.currentTimeMillis();
         int count = 0;
         boolean numberFlag = true;
         for (int i = 2;i <= 100000;i++) {
         for (int j = 2;j < i;j++) {
         if (i % j == 0) {
         numberFlag = false;
         }
         }
         if (numberFlag) {
         count++;
         }
         numberFlag = true;
         }
         long end = System.currentTimeMillis();
         log.info("时间：{},总数：{}", end - start, count);
         */

        /**
         * 时间：722,总数：9592
         // 求100000以内的质数
         long start = System.currentTimeMillis();
         int count = 0;
         boolean numberFlag = true;
         for (int i = 2; i <= 100000; i++) {
         for (int j = 2; j < i; j++) {
         if (i % j == 0) {
         numberFlag = false;
         break;
         }
         }
         if (numberFlag) {
         count++;
         }
         numberFlag = true;
         }
         long end = System.currentTimeMillis();
         log.info("时间：{},总数：{}", end - start, count);
         */

        /**
         *时间：7,总数：9592

         long start = System.currentTimeMillis();
         int count = 0;
         boolean numberFlag = true;
         for (int i = 2; i <= 100000; i++) {
         for (int j = 2; j <= Math.sqrt(i); j++) {
         if (i % j == 0) {
         numberFlag = false;
         break;
         }
         }
         if (numberFlag) {
         count++;
         }
         numberFlag = true;
         }
         long end = System.currentTimeMillis();
         log.info("时间：{},总数：{}", end - start, count);
         */

        long start = System.currentTimeMillis();
        int count = 0;
        boolean numberFlag = true;
        for (int i = 2; i <= 100000; i++) {
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    numberFlag = false;
                    break;
                }
            }
            if (numberFlag) {
                count++;
            }
            numberFlag = true;
        }
        long end = System.currentTimeMillis();
        log.info("时间：{},总数：{}", end - start, count);
    }
}
