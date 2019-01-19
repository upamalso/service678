package com.dialog.service678.services;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ServiceHelper {

    public String generateServiceXml(ArrayList nodeList) {
        String serviceXml = null;

        Element root = new Element("SERVICE");
        root.setAttribute("NAME", "NAME");
        Document doc = new Document();

        nodeList.forEach((n) -> {
            LinkedHashMap m = (LinkedHashMap) n;
            if (m.get("type").equals("ASSIGN")) {
                root.addContent(createAssignNode(m));
            } else if (m.get("type").equals( "FUNCTION")) {
                root.addContent(createFunctionNode(m));
            } else if (m.get("type").equals("BRANCH")) {
                root.addContent(createBranchNode(m));
            } else if (m.get("type").equals("RETURN")) {
                root.addContent(createReturnNode(m));
            } else if (m.get("type").equals("DEFAULT")) {
                root.addContent(createDefaultNode(m));
            }
        });
        doc.setRootElement(root);
        XMLOutputter outter = new XMLOutputter();
        outter.setFormat(Format.getPrettyFormat());
        try {
            serviceXml = outter.outputString(doc);
            outter.output(doc, new FileWriter(new File("myxml.xml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return serviceXml;
    }

    private Element createAssignNode(HashMap<String, Object> node) {
        Element root = new Element("BLOCK");
        root.setAttribute("ID", node.get("id").toString());
        root.setAttribute("TYPE", "ASSIGN");

        ArrayList variableList = (ArrayList) node.get("variables");
        variableList.forEach((v) -> {
            Element childVariable = new Element("VARIABLE");
            LinkedHashMap m = (LinkedHashMap) v;
            childVariable.setAttribute("NAME", m.get("name").toString());
            childVariable.addContent(m.get("value").toString());
            root.addContent(childVariable);
        });

        Element childNextNode = new Element("NEXT-NODE");
        childNextNode.addContent(node.get("next-node").toString());
        root.addContent(childNextNode);
        return root;
    }

    private Element createFunctionNode(HashMap<String, Object> node) {
        Element root = new Element("BLOCK");
        root.setAttribute("ID", node.get("id").toString());
        root.setAttribute("TYPE", "FUNC");

        Element childClass = new Element("CLASS");
        Element childMethod = new Element("METHOD");
        Element childNextNode = new Element("NEXT-NODE");

        childClass.addContent(node.get("class").toString());
        childMethod.addContent(node.get("method").toString());
        childNextNode.addContent(node.get("next-node").toString());

        root.addContent(childClass);
        root.addContent(childMethod);
        root.addContent(childNextNode);

        ArrayList paramList = (ArrayList) node.get("param");
        paramList.forEach((v) -> {
            Element childPram = new Element("PARAM");
            LinkedHashMap m = (LinkedHashMap) v;
            childPram.setAttribute("NAME", m.get("name").toString());
            childPram.addContent(m.get("value").toString());
            root.addContent(childPram);
        });
        return root;
    }

    private Element createBranchNode(HashMap<String, Object> node) {
        Element root = new Element("BLOCK");
        root.setAttribute("ID", node.get("id").toString());
        root.setAttribute("TYPE", "BRANCH");

        ArrayList caseList = (ArrayList) node.get("cases");
        caseList.forEach((v) -> {
            LinkedHashMap<String, Object> variablesMap = (LinkedHashMap) v;

            Element childCase = new Element("CASE");
            Element childExpression = new Element("EXPRESSION");
            Element childNextNode = new Element("NEXT-NODE");

            childCase.setAttribute("ID", variablesMap.get("id").toString());
            childExpression.addContent(variablesMap.get("expression").toString());
            childNextNode.addContent(variablesMap.get("next-node").toString());

            childCase.addContent(childExpression);
            childCase.addContent(childNextNode);

            ArrayList variablesList = (ArrayList) variablesMap.get("variables");
            variablesList.forEach((variable) -> {
                Element childPram = new Element("VARIABLE");
                LinkedHashMap m = (LinkedHashMap) variable;
                childPram.setAttribute("NAME", m.get("name").toString());
                childPram.addContent(m.get("value").toString());
                childCase.addContent(childPram);
            });
            root.addContent(childCase);
        });

        LinkedHashMap<String, Object> defaultMap = (LinkedHashMap) node.get("default");
        Element childDefault = new Element("DEFAULT");
        Element childNextNode = new Element("NEXT-NODE");

        childNextNode.addContent(defaultMap.get("next-node").toString());
        childDefault.addContent(childNextNode);

        ArrayList variablesList = (ArrayList) defaultMap.get("variables");
        variablesList.forEach((variable) -> {
            Element childVariable = new Element("VARIABLE");
            LinkedHashMap m = (LinkedHashMap) variable;
            childVariable.setAttribute("NAME", m.get("name").toString());
            childVariable.addContent(m.get("value").toString());
            childDefault.addContent(childVariable);
        });
        root.addContent(childDefault);

        return root;
    }

    private Element createReturnNode(HashMap<String, Object> node) {
        Element root = new Element("BLOCK");
        root.setAttribute("ID", node.get("id").toString());
        root.setAttribute("TYPE", "RETURN");

        Element childDefault = new Element("DEFAULT");
        ArrayList variablesList = (ArrayList)node.get("output-params");

        variablesList.forEach((outputParam) -> {
            Element childOutputParam = new Element("OUTPUTPARAMS");
            LinkedHashMap m = (LinkedHashMap) outputParam;
            childOutputParam.setAttribute("NAME", m.get("name").toString());
            childOutputParam.addContent(m.get("value").toString());
            childDefault.addContent(childOutputParam);
        });

        return root;
    }

    private Element createDefaultNode(HashMap<String, Object> node) {
        Element root = new Element("BLOCK");
        root.setAttribute("ID", node.get("id").toString());
        root.setAttribute("TYPE", "DEFAULT");
        return root;
    }
}
