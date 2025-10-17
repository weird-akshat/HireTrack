package com.hiretrack.message_extractor.service;

import com.hiretrack.message_extractor.entity.SourceMessage;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class PptReaderService {

    public String extractText(SourceMessage sourceMessage){
        StringBuilder res = new StringBuilder();
        try(ByteArrayInputStream bis= new ByteArrayInputStream(sourceMessage.getFileData());
            XMLSlideShow ppt= new XMLSlideShow(bis)) {

            ppt.getSlides().forEach(slide->{
                for (XSLFShape shape: slide.getShapes()){
                    if (shape instanceof XSLFTextShape){
                        XSLFTextShape textShape = (XSLFTextShape) shape;
                        res.append(textShape.getText());
                    }
                }

            });
            return res.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
