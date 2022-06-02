package com.example.springbootdemo.utilMBGenerator.tags;

public class TagsCo {

    /**
     * 自动生成代码的标志(重新生成代码时覆盖此段代码)
     */
    public final static String[] autoGenTags                 = new String[] { "@ibatorgenerated", "@abatorgenerated", "@mbggenerated", "@mbg.generated"
    };
    /**
     * 已删除成员的标志(重新生成代码时不重新生成此成员列表) 放在类上面， @mbg.removedMember 属性1,属性2,...
     */
    public final static String[] removedMemberTags           = new String[] { "@mbg.removedMember"
    };
    /**
     * 不覆盖文件(重新生成代码时不覆盖此文件) 该注解放在文件顶部
     */
    public final static String[] dontOverWriteFileTags       = new String[] { "@mbg.dontOverWriteFile"
    };
    /**
     * 不覆盖注解(重新生成代码时不覆盖原来的注解) 放在对应的类或字段上
     */
    public final static String[] dontOverWriteAnnotationTags = new String[] { "@mbg.dontOverWriteAnnotation"
    };
    /**
     * 不覆盖extends(重新生成代码时不覆盖extends) 放在类上
     */
    public final static String[] dontOverWriteExtendsTags    = new String[] { "@mbg.dontOverWriteExtends"
    };
    /**
     * 不覆盖implements(重新生成代码时不覆盖implements) 放在类上
     */
    public final static String[] dontOverWriteImplementsTags = new String[] { "@mbg.dontOverWriteImplements"
    };
}
