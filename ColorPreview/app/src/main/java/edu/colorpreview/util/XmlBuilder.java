package edu.colorpreview.util;

import android.content.Context;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import edu.colorpreview.model.Design;

public class XmlBuilder {
    public static void shareXML(Design design) {
        //TODO:  使用隐式INTENT分享你生成的XML
    }

    public static File generateXML(Context context, Design design) {
        File file = new File(context.getCacheDir().getAbsolutePath() + File.separator + "colors.xml");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document document = db.newDocument();
            document.setXmlStandalone(true);

            Element resources = document.createElement("resources");
            resources.appendChild(addColor(document, "primaryColor", design.p));
            resources.appendChild(addColor(document, "primaryLightColor", design.pl));
            resources.appendChild(addColor(document, "primaryDarkColor", design.pd));
            resources.appendChild(addColor(document, "secondaryColor", design.s));
            resources.appendChild(addColor(document, "secondaryLightColor", design.sl));
            resources.appendChild(addColor(document, "secondaryDarkColor", design.sd));
            resources.appendChild(addColor(document, "primaryTextColor", design.tp));
            resources.appendChild(addColor(document, "secondaryTextColor", design.ts));
            document.appendChild(resources);

            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.transform(new DOMSource(document), new StreamResult(file));

            Toast.makeText(context, "生成成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "生成失败", Toast.LENGTH_SHORT).show();
            return null;
        }

        return file;
    }

    private static Element addColor(Document document, String name, String content) {
        Element color = document.createElement("color");
        color.setTextContent(content);
        color.setAttribute("name", name);
        return color;
    }
}
