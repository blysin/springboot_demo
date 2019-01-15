package com.example.helloworld;

import com.lowagie.text.pdf.PdfReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Administrator
 * @since 2018-12-18
 */
public class TestPdf {

    @Test
    public void executorTest(){
        long t1 = System.currentTimeMillis();
        File tempFolder = new File("C:\\Users\\Administrator\\Desktop\\pdftemp");
        File pdfFile = new File("C:\\Users\\Administrator\\Desktop\\开发文档123.pdf");
        pdfToImage(pdfFile,tempFolder.getPath(),250);
        System.out.println(System.currentTimeMillis() - t1);
        String fileName = pdfFile.getName();
        fileName = fileName.substring(0, fileName.indexOf("."));
        File imgFolder = new File(tempFolder, fileName);
        System.out.println(imgFolder.getPath()+File.separator+fileName);
        System.out.println(imgFolder.listFiles().length);
    }


    public void pdfToImage(File file, String dstImgFolder, int dpi) {
        //File file = new File(PdfFilePath);
        String PdfFilePath = file.getPath();
        PDDocument pdDocument;
        try {
            String imgPDFPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            String imagePDFName = file.getName().substring(0, dot); // 获取图片文件名
            String imgFolderPath = null;
            if (dstImgFolder.equals("")) {
                imgFolderPath = imgPDFPath + File.separator + imagePDFName;// 获取图片存放的文件夹路径
            } else {
                imgFolderPath = dstImgFolder + File.separator + imagePDFName;
            }

            if (createDirectory(imgFolderPath)) {
                pdDocument = PDDocument.load(file);
                PDFRenderer renderer = new PDFRenderer(pdDocument);
                /* dpi越大转换后越清晰，相对转换速度越慢 */
                PdfReader reader = new PdfReader(PdfFilePath);
                int pages = reader.getNumberOfPages();
                StringBuffer imgFilePath = null;
                File dstFile;
                for (int i = 0; i < pages; i++) {
                    String imgFilePathPrefix = imgFolderPath + File.separator + "page";
                    imgFilePath = new StringBuffer();
                    imgFilePath.append(imgFilePathPrefix);
                    imgFilePath.append(i);
                    imgFilePath.append(".png");
                    dstFile = new File(imgFilePath.toString());
                    BufferedImage image = renderer.renderImageWithDPI(i, dpi);
                    ImageIO.write(image, "png", dstFile);
                }
                System.out.println("PDF文档转PNG图片成功！");
            } else {
                System.out.println("PDF文档转PNG图片失败：" + "创建" + imgFolderPath + "失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean createDirectory(String folder) {
        File dir = new File(folder);
        if (dir.exists()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }
}
