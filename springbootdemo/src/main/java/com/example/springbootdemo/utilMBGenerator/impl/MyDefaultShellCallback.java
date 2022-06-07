package com.example.springbootdemo.utilMBGenerator.impl;

import com.example.springbootdemo.utilMBGenerator.tags.TagsCo;
import com.example.springbootdemo.utilMBGenerator.util.MergeJavaFileUtils;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileNotFoundException;

public class MyDefaultShellCallback extends DefaultShellCallback {
    public MyDefaultShellCallback(boolean overwrite) {
        super(overwrite);
    }

    /**
     * 是否支持代码合并
     *
     * @return
     */
    @Override
    public boolean isMergeSupported() {
        return true;
    }

    @Override
    public String mergeJavaFile(String newFileSource, File existingFile, String[] javadocTags, String fileEncoding) throws ShellException {
        System.out.println("------------------------");
        //return super.mergeJavaFile(newFileSource, existingFile, javadocTags, fileEncoding);
        try {
            return MergeJavaFileUtils.merge(newFileSource, existingFile, javadocTags, TagsCo.removedMemberTags, TagsCo.dontOverWriteFileTags, TagsCo.dontOverWriteAnnotationTags,
                    TagsCo.dontOverWriteExtendsTags, TagsCo.dontOverWriteImplementsTags);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ShellException(e);
        }
    }
}
