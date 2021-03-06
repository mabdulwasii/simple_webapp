/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totagotech.mailService;

import com.google.gson.Gson;
import com.totagotech.Main.AppConstant;
import com.totagotech.Main.MailServer;
import com.totagotech.Main.SSLFix;
import com.totagotech.data.BatchItemClass;
import com.totagotech.data.BatchItemRequest;
import com.totagotech.data.payrollData.Payroll;
import com.totagotech.data.payrollData.Result;
import com.totagotech.data.response.BatchHeaderResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.mail.EmailException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import static com.totagotech.Main.AppConstant.*;

/**
 * REST Web Service
 *
 * @author Totago_User3
 */
@Path("recieve")
public class ApiResource {

    public static StringBuilder msg;
     private static int count = 82;

    String[] shortMonths = new DateFormatSymbols().getShortMonths();

    @Context
    private UriInfo context;

    // Generate random integers in range 0 to 999
    /*static Date date= new Date();
    static long time = date.getTime();
    static String text = String.valueOf(time);

    static String numbers = text.substring(Math.max(0, text.length() - 7));

    static int trnsactionBatchItemId = Integer.parseInt(text.substring(Math.max(0, text.length() - 6)));
*/


    /**
     * Creates a new instance of ApiResource
     */

    public ApiResource() {
    }

    @POST
    @Path("payrollData")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response recievePayrollData(String data) throws IOException, EmailException {

        msg = new StringBuilder(PAYROLL_MSG);

        System.out.println("MailServer receive payroll data");
        System.out.println(data);

        Payroll payroll = new Gson().fromJson(data, Payroll.class);
        List<Result> results = payroll.getResults();

        System.out.println(results);

        String path = AppConstant.PAYROLL_PATH;
        String desc = "Payroll excel file";
        Calendar cal = Calendar.getInstance();
        int monthNumber = cal.get(Calendar.MONTH);
        String month = shortMonths[monthNumber];
        String name = "Payroll-" + month + ".xlsx";
        writePayrollToXcel(results, path);



        //mailServer = new MailServer("sikolomi@boi.ng", "Kindly find the attached Payroll excel file", "PAYROLL EXCEL File", path, desc, name);
        //mailServer = new MailServer("mabdulwasii@gmail.com", "Kindly find the attached Payroll excel file", "PAYROLL EXCEL File", path, desc, name);
        new MailServer(PAYROLL_EMAIL, msg.toString(), "PAYROLL EXCEL File", path, desc, name);
        new MailServer(PAYROLL_EMAIL1, msg.toString(), "PAYROLL EXCEL File", path, desc, name);
        new MailServer(PAYROLL_EMAIL2, msg.toString(), "PAYROLL EXCEL File", path, desc, name);

        return Response.ok(data).build();
    }

    @POST
    @Path("netToBank")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response recieveNetToBank(String data) throws IOException, EmailException {
        System.out.println("MailServiceResource == My data is  " + data);

        com.totagotech.data.netToBank.Example example = new Gson().fromJson(data, com.totagotech.data.netToBank.Example.class);

        List<com.totagotech.data.netToBank.Result> results;
        results = example.getResults();
        System.out.println(results);

        String path = NET_TO_BANK_PATH;
        String desc = "Payroll excel file";
        Calendar cal = Calendar.getInstance();
        int monthNumber = cal.get(Calendar.MONTH);
        String month = shortMonths[monthNumber  ];
        String name = "Net to bank -" + month + ".xlsx";
        writeNetToBankToXcel(results, path);

        //mailServer = new MailServer("sikolomi@boi.ng", "Kindly find the attached Net to Bank excel file", "NET TO BANK EXCEL file", path, desc, name);
        //mailServer = new MailServer("mabdulwasii@gmail.com", "Kindly find the attached Net to Bank excel file", "NET TO BANK EXCEL file", path, desc, name);
        new MailServer(NET_TO_BANK_EMAIL, NET_TO_BANK_MSG, "NET TO BANK EXCEL file", path, desc, name);
        new MailServer(NET_TO_BANK_EMAIL1, NET_TO_BANK_MSG, "NET TO BANK EXCEL file", path, desc, name);
        new MailServer(NET_TO_BANK_EMAIL2, NET_TO_BANK_MSG, "NET TO BANK EXCEL file", path, desc, name);

        return Response.ok(data).build();
    }
    
    private static void writePayrollToXcel(List<Result> results, String filePath) throws IOException {
        String[] COLUMNs = {"tran_dt", "value_dt", "origin_bus_unit", "acct_bus_unit",
            "svce_channel", "acct_no", "txn_code", "dr_cr", "tran_ref", "narrative",
            "txn_ccy", "acct_ccy", "acct_amt", "chq_no"};

        System.out.println("Creating excel file...");
        try (Workbook workbook = new XSSFWorkbook()) {

            int transactionBatchId = getTransactionBatchId();

            msg.append(transactionBatchId);

            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Payroll");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            System.out.println("MailServiceResource === Creating the headers");

            int rowIdx = 1;
            for (Result result : results) {

                Row row = sheet.createRow(rowIdx++);

                BatchItemClass batchItemClass = new BatchItemClass();
                BatchItemRequest batchItemRequest = new BatchItemRequest();
                batchItemRequest.setTransactionBatchItemId(transactionBatchId - 23);
                batchItemRequest.setTransactionBatchId(transactionBatchId);
                batchItemRequest.setEventCode(result.getTxnCode());
                batchItemRequest.setAccountNumber(result.getAcctNo());
                batchItemRequest.setTransactionCurrencyCode(result.getTxnCcy());
                batchItemRequest.setTransactionAmount(result.getAcctAmt());
                batchItemRequest.setNarrative(result.getNarrative());
                batchItemRequest.setReference(result.getTranRef());
                batchItemRequest.setDebitCreditIndicator(result.getDrCr());
                batchItemRequest.setExchangeRate(1);
                batchItemRequest.setAccountCurrencyAmount(result.getAcctAmt());
                batchItemRequest.setAccountCurrencyCode(result.getAcctCcy());
                batchItemRequest.setValueDate(result.getValueDt());
//                batchItemRequest.setBICCode("999");
                batchItemRequest.setChequeNumber(result.getChqNo());
//                batchItemRequest.setItemTypeCode("836");
//                batchItemRequest.setTrackingNumber(0);
                batchItemRequest.setPayerAccountNumber(result.getAcctNo());
//                batchItemRequest.setBeneficiaryId(0);
//                batchItemRequest.setFundTransferTypeCode("NIBSS2");
//                batchItemRequest.setPaymentMethodCode("A");
//                batchItemRequest.setSettlementAccounNumber("5000017784");
                batchItemRequest.setUserId("SYSTEM");
                batchItemRequest.setCreatedBy("SYSTEM");

                batchItemClass.setRequest(batchItemRequest);
                String batchItemJsonData = new Gson().toJson(batchItemClass);

                System.out.println(batchItemJsonData);
                sendBatchItem(batchItemJsonData);

                row.createCell(0).setCellValue(result.getTranDt());
                row.createCell(1).setCellValue(result.getValueDt());
                row.createCell(2).setCellValue(result.getOriginBusUnit());
                row.createCell(3).setCellValue(result.getAcctBusUnit());
                row.createCell(4).setCellValue(result.getSvceChannel());
                row.createCell(5).setCellValue(result.getAcctNo());
                row.createCell(6).setCellValue(result.getTxnCode());
                row.createCell(7).setCellValue(result.getDrCr());
                row.createCell(8).setCellValue(result.getTranRef());
                row.createCell(9).setCellValue(result.getNarrative());
                row.createCell(10).setCellValue(result.getTxnCcy());
                row.createCell(11).setCellValue(result.getAcctCcy());
                row.createCell(12).setCellValue(result.getAcctAmt());
                row.createCell(13).setCellValue(result.getChqNo());
            }

            System.out.println("MailServiceResource === File body created");

            File file = new File(filePath);

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }
        }

        System.out.println("MailServiceResource === File created in url " + filePath);
    }
    
    private static void writeNetToBankToXcel(List<com.totagotech.data.netToBank.Result> results, String filePath) throws IOException {
        String[] COLUMNs = {"Id", "Staff Number", "Staff Name", "Bank Name", "Account Number", "Amount", "Remarks"};
        System.out.println("Creating excel file...");
        try (Workbook workbook = new XSSFWorkbook()) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("NetToBank");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);
            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }
            System.out.println("Creating the headers");
            int rowIdx = 1;

            System.out.println("Net to Bank ArrayList " + results);

            if (results.size() > 0) {
                Collections.sort(results, new Comparator<com.totagotech.data.netToBank.Result>() {
                    @Override
                    public int compare(final com.totagotech.data.netToBank.Result object1, final com.totagotech.data.netToBank.Result object2) {
                        String sNull = "null";
                        //String bankName = object1.getBankName();

                        if (object1.getBankName() == null || object1.getBankName().isEmpty() || object1.getBankName().equals(sNull)) {
                            object1.setBankName("zN/A");
                        }
                        if (object2.getBankName() == null || object2.getBankName().isEmpty() || object2.getBankName().equals(sNull)) {
                            object2.setBankName("zN/A");
                        }
                        return object1.getBankName().compareTo(object2.getBankName());
                    }
                });
            }

            for (com.totagotech.data.netToBank.Result result : results) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(result.getId());
                row.createCell(1).setCellValue(result.getStaffNumber());
                row.createCell(2).setCellValue(result.getStaffName());
                row.createCell(3).setCellValue(result.getBankName());
                row.createCell(4).setCellValue(result.getAccountNumber());
                row.createCell(5).setCellValue(result.getAmount());
                row.createCell(6).setCellValue(result.getRemark());
            }

            System.out.println("MailServiceResource === File body created");

            File file = new File(filePath);

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }
        }

        System.out.println("MailServiceResource === File created in url " + filePath);
    }

public static int getTransactionBatchId() {

        SSLFix.execute();

        ++count;
        String  batchCode = String.format("%05d", count);
    System.out.println(" TransactionBatchCode:  " + batchCode);

    //String batchHeaderRequestBody = "{\"Request\":{\"TransactionBatchCode\":\"" + batchCode + "\",\"TransactionBatchDescription\":\"fdfdgd\",\"BusinessUnitCode\":\"001\",\"NextRunDate\":\"12/08/2019\",\"EffectiveDate\":\"12/09/2019\",\"UserId\":\"SYSTEM\",\"CreatedBy\":\"SYSTEM\"}}";
    LocalDate date = LocalDate.now();
    DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("dd/MM/yy");
    String useDate = date.format(newPattern);

    String batchHeaderRequestBody = "    {\n" +
            "            \n" +
            "              \"Request\" : {\n" +
            "            \n" +
            "                \"TransactionBatchCode\" : \""+ batchCode + "\",\n" +
            "            \n" +
            "                \"TransactionBatchDescription\" : \"New batch code " + batchCode + " \" ,\n" +
            "            \n" +
            "                \"BusinessUnitCode\" : \"001\",\n" +
            "            \n" +
            "                \"NextRunDate\" : \"" + useDate +"\",\n" +
            "            \n" +
            "                \"EffectiveDate\" : \"" + useDate + "\",\n" +
            "            \n" +
            "                \"UserId\" : \"SYSTEM\",\n" +
            "            \n" +
            "                \"CreatedBy\" : \"SYSTEM\"\n" +
            "            \n" +
            "              }\n" +
            "            \n" +
            "            }";

    System.out.println("BATCH HEADER BODY ====  " + batchHeaderRequestBody);

    JSONObject jSONObject = new JSONObject(batchHeaderRequestBody);

    System.out.println("BEFORE API CALL ====  " + jSONObject);

        HttpResponse<JsonNode> batchHeaderRespone = Unirest
                .post("http://192.168.207.34:7101/SBProject/RubikonProxyRestService/BatchHeaderUpload")
//                .post("http://172.16.47.3/SBProject/RubikonProxyRestService/BatchHeaderUpload")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(jSONObject.toString())
                .asJson();
        
        System.out.println("AFTER API CALL ====  " + batchHeaderRespone.getBody());

        System.out.println("GET TRANSACTION BATCH ID ======== Response Code = " + batchHeaderRespone.getStatus()
                + " Description ==" + batchHeaderRespone.getStatusText()
                + " The Batch header response Data is = " + batchHeaderRespone.getBody());

        BatchHeaderResponse headerResponse = new Gson().fromJson(batchHeaderRespone.getBody().getObject().getJSONObject("Response").toString(), BatchHeaderResponse.class);
        int transactionBatchId = headerResponse.getTransactionBatchId();
        System.out.println(transactionBatchId);
        if(transactionBatchId == 0){
            ++count;
            getTransactionBatchId();
        }
        return transactionBatchId;
    }

    private static void sendBatchItem(String batchItemJsonData) {

        JSONObject jSONObject = new JSONObject(batchItemJsonData);

        System.out.println("BEFORE API CALL ====  " + jSONObject);
        SSLFix.execute();
        HttpResponse<JsonNode> batchItemRespone = null;
        try {
            batchItemRespone = Unirest
                    .post("http://192.168.207.34:7101/SBProject/RubikonProxyRestService/BatchItemUpload")
//                    .post("http://172.16.47.3/SBProject/RubikonProxyRestService/BatchItemUpload")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(jSONObject)
                    .asJson();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SEND BATCH ITEM FAILED: " + e.getMessage());
            System.out.println("SEND BATCH ITEM ERROR MSG " + batchItemRespone.getParsingError());
        }

        System.out.println("AFTER API CALL " + batchItemRespone.getBody());

        System.out.println("sendBatchItem ======== Response Code = " + batchItemRespone.getStatus()
                + " Description ==" + batchItemRespone.getStatusText()
                + " The sendBatchItem response body = " + batchItemRespone.getBody().getObject().toString());

    }

}
