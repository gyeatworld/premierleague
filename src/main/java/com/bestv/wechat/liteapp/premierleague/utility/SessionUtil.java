package com.bestv.wechat.liteapp.premierleague.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;

/**
 * @author Vem
 * @version 1.0
 * @date 创建时间：2017年9月8日 下午5:18:57
 * @parameter
 * @return
 * @since
 */

public class SessionUtil {

    private static Logger logger = LogManager.getLogger(SessionUtil.class);
    // Generate a byte array containing a session identifier

    /**
     * 执行系统命令, 返回执行结果
     *
     * @param //cmd 需要执行的命令
     * @param //dir 执行命令的子进程的工作目录, null 表示和当前主进程工作目录相同
     */
    public static String generate3rdSession() throws Exception {
        StringBuilder result = new StringBuilder();
        StringBuilder error = new StringBuilder();
        Process process = null;
        BufferedReader bufrIn = null;
        BufferedReader bufrError = null;

        String cmd = "head -n 80 /dev/urandom | tr -dc A-Za-z0-9 | head -c 168";
        String[] runCmd = {"/bin/sh", "-c", cmd};
        try {
            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            process = Runtime.getRuntime().exec(runCmd);

            // 方法阻塞, 等待命令执行完成（成功会返回0）
            process.waitFor();

            // 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
            bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
            bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // 读取输出
            String line = null;
            while ((line = bufrIn.readLine()) != null) {
                result.append(line).append('\n');
            }
            String errorLine = null;
            if (bufrError.readLine() != null) {
                while ((errorLine = bufrError.readLine()) != null) {
                    error.append(errorLine).append('\n');
                }
                logger.error("generate3rdSession()存在错误",error.toString());
            }
        } finally {
            closeStream(bufrIn);
            closeStream(bufrError);

            // 销毁子进程
            if (process != null) {
                process.destroy();
            }
        }

        // 返回执行结果
        return result.toString();
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                // nothing
            }
        }
    }

}
