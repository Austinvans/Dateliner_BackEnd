/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.configs.SmsRequest;
import com.austincode.dateliner.configs.SmsService;
import com.austincode.dateliner.dto.DatelinerByTimelinerDto;
import com.austincode.dateliner.dto.ObjectsByTimelinerDto;
import com.austincode.dateliner.dto.TimelinerDto;
import com.austincode.dateliner.mapper.DatelinerByTimelinerMapper;
import com.austincode.dateliner.mapper.ObjectsByTimelinerMapper;
import com.austincode.dateliner.mapper.TimelinerMapper;
import com.austincode.dateliner.model.DatelinerByTimeliner;
import com.austincode.dateliner.service.DatelinerByTimelinerService;
import com.austincode.dateliner.service.EmailSenderService;
import com.austincode.dateliner.service.ObjectsByTimelinerService;
import com.austincode.dateliner.service.PDFGeneratorService;
import com.austincode.dateliner.service.TimelinerService;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yvan Ngakeu
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/dateliner")
public class DatelinerByTimelinerController {
    
    @Autowired
    DatelinerByTimelinerService datelinerByTimelinerService;
    
    @Autowired
    DatelinerByTimelinerMapper datelinerByTimelinerMapper; 
    
    @Autowired
    TimelinerService timelinerService;
    
    @Autowired
    TimelinerMapper timelinerMapper; 
    
    @Autowired
    SmsService smsService;
    
    @Autowired
    EmailSenderService emailSenderService;
    
    @Autowired
    ObjectsByTimelinerService objectsByTimelinerService;
    
    @Autowired
    ObjectsByTimelinerMapper objectsByTimelinerMapper;
    
    
    
    public Date current_date;
    public Date end_date;
    public Date db;
    public Date da;
    
    
    // L'AJOUT D'UN ECHEANCIER 
    @PostMapping("/datelinerByTimeliner")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody DatelinerByTimelinerDto datelinerByTimelinerDto) {
        DatelinerByTimeliner datelinerByTimeliner = datelinerByTimelinerMapper.toDatelinerByTimeliner(datelinerByTimelinerDto);
        datelinerByTimelinerService.save(datelinerByTimeliner);
    }
    
    // LISTE DES ECHEANCIERS fixedRate=86400000
    @GetMapping("/datelinerByTimeliners")
    @ResponseStatus(HttpStatus.OK)
    public List<DatelinerByTimelinerDto> findAll() {
        return datelinerByTimelinerMapper.modelsToDtos(datelinerByTimelinerService.findAll());
    }
    
    @GetMapping("/pdf/timeliners/{id}")
    public void generator(HttpServletResponse response, @PathVariable("id") UUID id) throws DocumentException, IOException {
     response.setContentType("application/pdf");
     DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
     String currentDateTime = dateFormat.format(new Date());
     TimelinerDto timelinerDto = timelinerMapper.toTimelinerDto(timelinerService.findOne(id));
     String headerkey = "Content-Disposition";
     String headervalue = "attachment; filename=Contract_"+timelinerDto.getName()+"_"+currentDateTime+".pdf";
     response.setHeader(headerkey, headervalue);
     List<DatelinerByTimelinerDto> datelinerByTimelinerDto = datelinerByTimelinerMapper.modelsToDtos(datelinerByTimelinerService.findOneTimeliner(id));
     List<ObjectsByTimelinerDto> obj = objectsByTimelinerMapper.modelsToDtos(objectsByTimelinerService.findOneTimeliner(id));
     PDFGeneratorService generetorUser = new PDFGeneratorService(datelinerByTimelinerDto, timelinerDto, obj);
     //generetorUser.setDatelinerByTimeliner(datelinerByTimelinerDto);
     generetorUser.generate(response);
    }
    
    @GetMapping("/pdf/timeliners/{id1}/{id2}")
    public void generatorBill(HttpServletResponse response, @PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2) throws DocumentException, IOException {
     response.setContentType("application/pdf");
     DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
     String currentDateTime = dateFormat.format(new Date());
     String headerkey = "Content-Disposition";
     DatelinerByTimelinerDto datelinerByTimelinerDto = datelinerByTimelinerMapper.toDatelinerByTimelinerDto(datelinerByTimelinerService.findOne(id1, id2));
     String headervalue = "attachment; filename=Contract_"+datelinerByTimelinerDto.getName()+"_"+currentDateTime+".pdf";
     response.setHeader(headerkey, headervalue);
     PDFGeneratorService generetorUser = new PDFGeneratorService(datelinerByTimelinerDto);
     //generetorUser.setDatelinerByTimeliner(datelinerByTimelinerDto);
     generetorUser.generateBill(response);
    }
    
    @Scheduled(fixedRate=50000)
    public void checkStatus(){
        current_date = new Date();
        List<DatelinerByTimelinerDto> timeliners;
        timeliners = datelinerByTimelinerMapper.modelsToDtos(datelinerByTimelinerService.findAll());
        for(int i=0; i<timeliners.size(); i++){
            end_date = timeliners.get(i).getDateliner_end_date();
            String status = timeliners.get(i).getDateliner_status();
            if(end_date.before(current_date)){
                if("OnGoing".equals(status)){  
                    changeDatelineStatusToExpired(timeliners.get(i).getTimeliner(), timeliners.get(i).getDateliner_id());
                    System.out.println("Status updated on dateliner : " + timeliners.get(i).getDateliner_id());
                }
                else{  
                    System.out.println("No updates found");
                }
            }     
        }
        
    }
    
    //@Scheduled(fixedRate=86400000)
    @Scheduled(fixedRate=86400000)
    public void sendReminder(){
        current_date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());
        List<DatelinerByTimelinerDto> timeliners;
        timeliners = datelinerByTimelinerMapper.modelsToDtos(datelinerByTimelinerService.findAll());
        for(int i=0; i<timeliners.size(); i++){
            end_date = timeliners.get(i).getDateliner_end_date();
            Calendar c = Calendar.getInstance();
            c.setTime(end_date);
            c.add(Calendar.DATE, -3);
            db=c.getTime();
            Calendar ca = Calendar.getInstance();
            ca.setTime(end_date);
            ca.add(Calendar.DATE, +1);
            da=ca.getTime();
            String date_before = dateFormat.format(db);
            String present_date = dateFormat.format(end_date);
            String date_after = dateFormat.format(da);
            //String name = timeliners.get(i).getName();
            if(date_before.equals(currentDateTime)/*&& timeliners.get(i).getReminder_mean_one().equals("Email")*/){
                String to =timeliners.get(i).getTimeliner_email();
                String subject = " Yowyob Dateliner ";
                String message = " Your Dateliner is soon coming to expiration think about it ";
                emailSenderService.sendEmail(to, subject, message);
                System.out.println("Email send to : " + timeliners.get(i).getName());
            }
            else if(present_date.equals(currentDateTime)/*&& timeliners.get(i).getReminder_mean_two().equals("Email")*/)
            {  
                String to =timeliners.get(i).getTimeliner_email();
                String subject = " Yowyob Dateliner ";
                String message = " Your Dateliner has reached expiration date think about it ";
                emailSenderService.sendEmail(to, subject, message);
                System.out.println("Email send to : " + timeliners.get(i).getName());
            }
            else if(date_after.equals(currentDateTime)/*&& timeliners.get(i).getReminder_mean_three().equals("Email")*/)
            {  
                String to =timeliners.get(i).getTimeliner_email();
                String subject = " Yowyob Dateliner ";
                String message = " Your Dateliner has passed the expiration date think about it ";
                emailSenderService.sendEmail(to, subject, message);
                System.out.println("Email send to : " + timeliners.get(i).getName());
            }
            else
            {  
                System.out.println("No message send " + date_before);
                System.out.println("No message send " + currentDateTime);
            }     
        }
        
    }
    
    @Scheduled(fixedRate=86400000) 
    public void sendReminderSms(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());
        List<DatelinerByTimelinerDto> timeliners;
        timeliners = datelinerByTimelinerMapper.modelsToDtos(datelinerByTimelinerService.findAll());
        for(int i=0; i<timeliners.size(); i++){
            end_date = timeliners.get(i).getDateliner_end_date();
            Calendar c = Calendar.getInstance();
            c.setTime(end_date);
            c.add(Calendar.DATE, -3);
            db=c.getTime();
            Calendar ca = Calendar.getInstance();
            ca.setTime(end_date);
            ca.add(Calendar.DATE, +1);
            da=ca.getTime();
            String date_before = dateFormat.format(db);
            String present_date = dateFormat.format(end_date);    
            String date_after = dateFormat.format(da);
            //String name = timeliners.get(i).getName();
            if(date_before.equals(currentDateTime)/*&& timeliners.get(i).getReminder_mean_one().equals("SMS")*/){
                String phone ="+"+timeliners.get(i).getTimeliner_tel();
                //String subject = " Yowyob Dateliner ";
                String message = " Your Dateliner is soon coming to expiration think about it ";
                SmsRequest s = new SmsRequest(phone, message);
                smsService.sendSms(s);
                System.out.println("Sms send to : " + timeliners.get(i).getName());
            }
            else if(present_date.equals(currentDateTime)/*&& timeliners.get(i).getReminder_mean_two().equals("SMS")*/)
            {  
                String phone ="+"+timeliners.get(i).getTimeliner_tel();
                //String subject = " Yowyob Dateliner ";
                String message = " Last Day to pay yout Dateliner ";
                SmsRequest s = new SmsRequest(phone, message);
                smsService.sendSms(s);
                System.out.println("Sms send to : " + timeliners.get(i).getName());
            }
            else if(date_after.equals(currentDateTime)/*&& timeliners.get(i).getReminder_mean_three().equals("SMS")*/)
            {  
                String phone ="+"+timeliners.get(i).getTimeliner_tel();
                //String subject = " Yowyob Dateliner ";
                String message = " Your Dateliner is expired prepare for charges ";
                SmsRequest s = new SmsRequest(phone, message);
                smsService.sendSms(s);
                System.out.println("Sms send to : " + timeliners.get(i).getName() + " Phone : " + date_after);
            }
            else
            {  
                System.out.println("No message send by sms" + date_before);
                System.out.println("No message send by sms" + currentDateTime);
            }     
        }
        
    }
    
    //RECHERCHER UN ECHEANCIER PAR SON ID
    @GetMapping("/datelinerByTimeliner/{id1}/{id2}")
    @ResponseStatus(HttpStatus.OK)
    public DatelinerByTimelinerDto findOne(@PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2) {
        return datelinerByTimelinerMapper.toDatelinerByTimelinerDto(datelinerByTimelinerService.findOne(id1, id2));
    }
    
    //RECHERCHER UN ECHEANCIER PAR SON ID
    @GetMapping("/datelinerByTimeliner/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<DatelinerByTimelinerDto> findByT(@PathVariable("id") UUID id) {
        return datelinerByTimelinerMapper.modelsToDtos(datelinerByTimelinerService.findOneTimeliner(id));
    }
    
    // LISTE DES CLIENTS
    @GetMapping("/datelinerByTimeliner")
    @ResponseStatus(HttpStatus.OK)
    public List<DatelinerByTimelinerDto> findCustomers(String name) {
        return datelinerByTimelinerMapper.modelsToDtos(datelinerByTimelinerService.findByName(name));
    }
    
    // MODIFIER LES INFOS D'UN ECHEANCIER
    @PutMapping("/datelinerByTimeliner/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody DatelinerByTimelinerDto datelinerByTimelinerDto){
        DatelinerByTimeliner datelinerByTimeliner = datelinerByTimelinerMapper.toDatelinerByTimeliner(datelinerByTimelinerDto);
        datelinerByTimeliner.setTimeliner(id);
        datelinerByTimelinerService.save(datelinerByTimeliner);
    }
    
    
    public void changeStatusToExpired(@PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2){
        DatelinerByTimeliner datelinerByTimeliner = datelinerByTimelinerService.findOne(id1, id2);
        datelinerByTimeliner.setTimeliner(id1);
        datelinerByTimeliner.setDateliner_id(id2);
        datelinerByTimeliner.setTimeliner_status("Expired");
        datelinerByTimelinerService.save(datelinerByTimeliner);
    }
    
    public void changeTimelinerStatusToDone(@PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2){
        DatelinerByTimeliner datelinerByTimeliner = datelinerByTimelinerService.findOne(id1, id2);
        datelinerByTimeliner.setTimeliner(id1);
        datelinerByTimeliner.setDateliner_id(id2);
        datelinerByTimeliner.setTimeliner_status("Done");
        datelinerByTimelinerService.save(datelinerByTimeliner);
    }
    
    
    public void changeDatelineStatusToExpired(@PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2){
        DatelinerByTimeliner datelinerByTimeliner = datelinerByTimelinerService.findOne(id1, id2);
        datelinerByTimeliner.setTimeliner(id1);
        datelinerByTimeliner.setDateliner_id(id2);
        datelinerByTimeliner.setDateliner_status("Expired");
        datelinerByTimelinerService.save(datelinerByTimeliner);
    }
    
    // MODIFIER LES INFOS D'UN ECHEANCIER
    @PatchMapping("/datelinerByTimeliner/done/{id1}/{id2}")
    @ResponseStatus(HttpStatus.OK)
    public void updatestatusdone(@PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2){
        DatelinerByTimeliner datelinerByTimeliner = datelinerByTimelinerService.findOne(id1, id2);
        datelinerByTimeliner.setTimeliner(id1);
        datelinerByTimeliner.setDateliner_id(id2);
        datelinerByTimeliner.setDateliner_status("Done");
        datelinerByTimelinerService.save(datelinerByTimeliner);
    }
    
    
    
    // SUPPRIMMER UN ECHEANCIER PAR SON ID 
    @DeleteMapping("/datelinerByTimeliner/{id1}/{id2}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2) {
        datelinerByTimelinerService.deleteById(id1, id2);       
    }
    
    // SUPPRIMMER UN ECHEANCIER PAR SON ID 
    @DeleteMapping("/datelinerByTimeliner/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTimeliner(@PathVariable("id") UUID id) {
        datelinerByTimelinerService.deleteByTimliner(id);       
    }
    
    @DeleteMapping("/datelinerByTimeliner")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllTimeliners() {
        datelinerByTimelinerService.deleteAll();      
    }
    
}
