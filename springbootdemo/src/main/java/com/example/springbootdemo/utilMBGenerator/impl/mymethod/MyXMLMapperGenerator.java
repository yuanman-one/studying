package com.example.springbootdemo.utilMBGenerator.impl.mymethod;

import com.example.springbootdemo.utilMBGenerator.impl.mymethod.xmlmapper.AddElementGenerator;
import com.example.springbootdemo.utilMBGenerator.impl.mymethod.xmlmapper.DeleteByIdElementGenerator;
import com.example.springbootdemo.utilMBGenerator.impl.mymethod.xmlmapper.ModifyByIdElementGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByPrimaryKeyElementGenerator;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

public class MyXMLMapperGenerator extends XMLMapperGenerator {
    @Override
    protected XmlElement getSqlMapElement() {
        //return super.getSqlMapElement();
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString(
                "Progress.12", table.toString())); //$NON-NLS-1$
        XmlElement answer = new XmlElement("mapper"); //$NON-NLS-1$
        String namespace = introspectedTable.getMyBatis3SqlMapNamespace();
        answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
                namespace));

        context.getCommentGenerator().addRootComment(answer);

        addResultMapWithoutBLOBsElement(answer);
        addResultMapWithBLOBsElement(answer);
        addExampleWhereClauseElement(answer);
        addMyBatis3UpdateByExampleWhereClauseElement(answer);
        addBaseColumnListElement(answer);
        addBlobColumnListElement(answer);
        //自定义sql
        addAddElement(answer);
        addModifyByIdElement(answer);
        addDeleteByIdElement(answer);
        //addInsertElement(answer);
//        addSelectByExampleWithBLOBsElement(answer);
//        addSelectByExampleWithoutBLOBsElement(answer);
//        addSelectByPrimaryKeyElement(answer);
        //addDeleteByPrimaryKeyElement(answer);
//        addDeleteByExampleElement(answer);

//        addInsertSelectiveElement(answer);
        //addCountByExampleElement(answer);
//        addUpdateByExampleSelectiveElement(answer);
//        addUpdateByExampleWithBLOBsElement(answer);
//        addUpdateByExampleWithoutBLOBsElement(answer);
       // addUpdateByPrimaryKeySelectiveElement(answer);
//        addUpdateByPrimaryKeyWithBLOBsElement(answer);
//        addUpdateByPrimaryKeyWithoutBLOBsElement(answer);

        return answer;
    }

    private void addDeleteByIdElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new DeleteByIdElementGenerator(false);
        this.initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    private void addAddElement(XmlElement parentElement) {
        //if (introspectedTable.getRules().generateInsert()) {
        AbstractXmlElementGenerator elementGenerator = new AddElementGenerator(false);
        //AbstractXmlElementGenerator elementGenerator = new InsertElementGenerator(false);
        initializeAndExecuteGenerator(elementGenerator, parentElement);
        //}
    }

    protected void addModifyByIdElement(XmlElement parentElement) {
        //super.addUpdateByPrimaryKeySelectiveElement(parentElement);
        AbstractXmlElementGenerator elementGenerator = new ModifyByIdElementGenerator();
        this.initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
}
