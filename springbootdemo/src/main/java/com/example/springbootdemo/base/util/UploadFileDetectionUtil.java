package com.example.springbootdemo.base.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 上传文件检测可执行文件
 *
 * @author yuanman
 */
@Slf4j
public class UploadFileDetectionUtil {

    private static String[] excludeFileExtName = {
            "xls", "xlsx", "jpg", "png"
    };

    private static String OS = System.getProperty("os.name").toLowerCase();

    private static String WINDOWS_EXECUTE_HEADER = "4d5a";

    private static String LINUX_EXECUTE_HEADER = "7f454c46";

    /**
     * 校验通过返回true
     * @param multipartFileList
     * @return
     * @throws IOException
     */
    public static boolean checkFileAllowedUploads(List<MultipartFile> multipartFileList) throws IOException {
        if (CollectionUtils.isEmpty(multipartFileList)) {
            return true;
        }
        Boolean boo = true;
        for (MultipartFile file : multipartFileList) {
            String originalFilename = file.getOriginalFilename();
            //获取文件后缀名，不带.号
            String suffix = originalFilename.substring(originalFilename.lastIndexOf('.')+1);
            InputStream inputStream = file.getInputStream();
            log.info("上传的文件名：{}",originalFilename);
            boo = checkFileAllowedUpload(suffix, inputStream);
            if ("false".equals(boo.toString())){
                //不包含excludeFileExtName后缀则返回false
                return boo;
            }
        }
        return boo;
    }

    public static Boolean checkFileAllowedUpload(String fileExtName, InputStream fileInputStream) {
        //不包含返回false
        if (!Arrays.asList(excludeFileExtName).contains(fileExtName)) {
            return false;
        }
        if (fileInputStream != null) {
            try {
                // 判断操作系统
                if (OS.indexOf("windows") >= 0) {
                    byte[] bytes = new byte[2];
                    fileInputStream.read(bytes, 0, 2);
                    if (encodeHexString(bytes).equals(WINDOWS_EXECUTE_HEADER)) {
                        return false;
                    }
                } else if (OS.indexOf("linux") >= 0) {
                    byte[] bytes = new byte[4];
                    fileInputStream.read(bytes, 0, 4);
                    if (encodeHexString(bytes).equals(LINUX_EXECUTE_HEADER)) {
                        return false;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    private static String encodeHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


}
