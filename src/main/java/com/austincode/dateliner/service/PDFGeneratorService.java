/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.dto.DatelinerByTimelinerDto;
import com.austincode.dateliner.dto.ObjectsByTimelinerDto;
import com.austincode.dateliner.dto.TimelinerDto;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yvan Ngakeu
 */
public class PDFGeneratorService {
    
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    //DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
    String currentDateTime = dateFormat.format(new Date());
    
    private List<DatelinerByTimelinerDto> timelinerList;
    
    private TimelinerDto timeliner;
    
    private List<ObjectsByTimelinerDto> obj;
    
    private DatelinerByTimelinerDto dateliner;
    
    
    
    public PDFGeneratorService(List<DatelinerByTimelinerDto> timelinerList, TimelinerDto timelinerDto, List<ObjectsByTimelinerDto> obj){
    //public PDFGeneratorService(List<DatelinerByTimelinerDto> timelinerList, TimelinerDto timelinerDto){
        super();
        this.timelinerList = timelinerList;
        this.timeliner = timelinerDto;
        this.obj = obj;
    }
    
    public PDFGeneratorService( DatelinerByTimelinerDto datelinerDto){
    //public PDFGeneratorService(List<DatelinerByTimelinerDto> timelinerList, TimelinerDto timelinerDto){
        super();
        this.dateliner = datelinerDto;
    }
    
    public void generate(HttpServletResponse response) throws DocumentException, IOException {
        
        // Create the Object of Document
        Document document = new Document(PageSize.A4);
        // get the document and write the response to output stream
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        // Add Font
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        
        Font fontBody = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(13);
                      
        // Create Object of Paragraph
        Paragraph paragraph = new Paragraph("CONTRAT DE CREANCE AVEC ECHEANCIERS \n", fontTiltle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        
        Paragraph paragraph1 = new Paragraph("\n Yowyob Inc. LTD \n <Coordonnées de votre société>", fontBody);
        Paragraph paragraph2 = new Paragraph("\n"+timeliner.getName()+"\n Motifs: "+ timeliner.getTimeliner_reason()+"\n Tel: "+timeliner.getTimeliner_tel()+"  Email: "+timeliner.getTimeliner_email()+"\n Yaoundé, le " + currentDateTime, fontBody);
        Paragraph paragraph4 = new Paragraph("\n Objet : Contrat de créance n° "+timeliner.getTimeliner_id(), fontBody);
        Paragraph paragraph5 = new Paragraph("Je soussigné : "+timeliner.getName()+" \n Reconnais avoir contacté une créance de " + timeliner.getTimeliner_amount() +"( XAF)  payable au plus tard le " + dateFormat.format(timeliner.getTimeliner_end_date())+ "\n Cette créance référencée en objet est échelonnée sur "+timeliner.getDateliner_number()+" échéances dont les dates et les montants sont repartis comme suit : ", fontBody);
        Paragraph paragraph6 = new Paragraph("\n Le client accepte les modalités de relance automatique et manuelle par SMS, WhatsApp, email ou appels téléphoniques.\n" +
                                            
                                            "Chaque échec de règlement est sujet à des pénalités d’un taux de "+timeliner.getPenalty()+"%, applicable sur l’échéance en cours. Le montant en devise correspondant à la pénalité est automatiquement ajouté au montant à régler à la prochaine échéance. Les pénalités s’appliquent donc au montant à payer pour l’échéance augmenté des pénalités de l’échéance passée si échec de règlement a été constaté.\n" +
                                            
                                            "Les éléments de garantie à cette créance sont les suivants avec leurs coûts estimatifs respectifs : \n \n", fontBody);
        
        Paragraph paragraph7 = new Paragraph("\n Nom et Prénom du client \n Signature", fontBody);
        Paragraph paragraph8 = new Paragraph("\n ", fontBody);
        Paragraph paragraph9 = new Paragraph("\n Dans l’hypothèse de tout contentieux ou conflits et faute de toute tentative de règlement du contentieux à l’amiable, seules les instances juridiques locales sont compétentes à résoudre les problèmes.\n" +
                                            "\n" +
                                            "En foi de quoi, ce contrat est établi pour faire valoir et servir ce que de droit.", fontBody);
        
        Paragraph paragraph10 = new Paragraph("\n Lieu : ………………………… et Date : "+currentDateTime, fontBody);
        
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph7.setAlignment(Paragraph.ALIGN_CENTER);
        // Add to the document
        document.add(paragraph);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph4);
        document.add(paragraph5);
        
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new int[] { 2, 2, 4 });
        table.setSpacingBefore(5);
        // Create Table Header
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(5);
        // Add Font
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Number", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);
        for (DatelinerByTimelinerDto datelinerByTimeliner : timelinerList) {
         table.addCell(dateFormat.format(datelinerByTimeliner.getDateliner_end_date()));
         table.addCell(String.valueOf(datelinerByTimeliner.getDateliner_number()));
         table.addCell(String.valueOf(datelinerByTimeliner.getDateliner_amount()));
        }
        
        PdfPTable table1 = new PdfPTable(2);
        table1.setWidthPercentage(100f);
        table1.setWidths(new int[] { 4, 4 });
        table1.setSpacingBefore(5);
        // Create Table Header
        PdfPCell cell1 = new PdfPCell();
        cell1.setBackgroundColor(Color.GRAY);
        cell1.setPadding(5);
        
        font.setColor(Color.WHITE);
        cell1.setPhrase(new Phrase("Nom", font));
        table1.addCell(cell1);
        cell1.setPhrase(new Phrase("Montant Estimatif", font));
        table1.addCell(cell1);
        for (ObjectsByTimelinerDto objByTimeliner : obj) {
         table1.addCell(objByTimeliner.getGuarantee_label());
         table1.addCell(String.valueOf(objByTimeliner.getGuarantee_amount()));
        }
        
       /* Paragraph paragraph11 =new ListItem();
        for(int i=0; i<obj.size(); i++){
            int j=i++;
          paragraph11 = new ListItem(j+". "+obj.get(i).getGuarantee_label()+" ( "+obj.get(i).getGuarantee_amount()+ " XAF) \n", fontBody);   
        }*/
        
        // Add table to document
        document.add(paragraph8);
        document.add(table);
        document.add(paragraph6);
        document.add(table1);
        document.add(paragraph9);
        document.add(paragraph10);
        document.add(paragraph7);
        document.close();
    }
    
    public void generateBill(HttpServletResponse response) throws DocumentException, IOException {
        
        
        
        // Create the Object of Document
        /* nom de la structure 
           nom du client 
           date 
           montant
           signature
           numero echeances
        */
        Document document = new Document(PageSize.A5);
        
        // get the document and write the response to output stream
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        // Add Font
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        
        Font fontBody = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(13);
                      
        // Create Object of Paragraph
        Paragraph paragraph = new Paragraph("CONTRAT DE CREANCE AVEC ECHEANCIERS \n", fontTiltle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        
        
        
        Paragraph paragraph1 = new Paragraph("\n Yowyob Inc. LTD \n <Coordonnées de votre société>", fontBody);
        Paragraph paragraph2 = new Paragraph("\n"+dateliner.getName()+"\n Motifs: "+ dateliner.getTimeliner_reason()+"\n Tel: "+dateliner.getTimeliner_tel()+"  Email: "+dateliner.getTimeliner_email()+"\n Yaoundé, le " + currentDateTime, fontBody);
        Paragraph paragraph3 = new Paragraph("\n Facture de Echéance N : "+ dateliner.getDateliner_number() , fontBody);
        Paragraph paragraph4 = new Paragraph("\n Montant: "+dateliner.getDateliner_amount(), fontBody);
        Paragraph paragraph5 = new Paragraph("\n Payer le : "+currentDateTime, fontBody);
        
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        
        document.close();
    }
    

}
