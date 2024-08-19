package com.zillionwon.spider;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 文件写入器
 * @author InwardFlow
 */
@Slf4j
public class FilePrinter {
    private String filePath;
    // 锁对象，用于同步写入操作
    private final Lock lock = new ReentrantLock();

    public FilePrinter(String filePath) {
        this.filePath = filePath;
    }

    // 向文件写入内容的线程安全方法
    public void printToFile(String content) {
        lock.lock(); // 获取锁
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            log.error("爬虫输出错误，错误信息：{}", e.getMessage());
        } finally {
            lock.unlock(); // 释放锁
        }
    }
}
