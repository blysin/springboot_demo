package com.example.helloworld;

import com.example.helloworld.Service.HelloSerivce;
import com.lowagie.text.pdf.PdfReader;
import lombok.extern.log4j.Log4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j
public class DemoApplicationTests {
	@Autowired
	private HelloSerivce helloSerivce;

	@Test
	public void contextLoads() {
		System.out.println(helloSerivce.hello());
	}


}
