	package com.qa.hubspot.listeners;

	import com.lowagie.text.Chunk;
	import com.lowagie.text.Document;
	import com.lowagie.text.DocumentException;
	import com.lowagie.text.Font;
	import com.lowagie.text.FontFactory;
	import com.lowagie.text.Paragraph;
	import com.lowagie.text.pdf.PdfPCell;
	import com.lowagie.text.pdf.PdfPTable;
	import com.lowagie.text.pdf.PdfWriter;
	import java.awt.Color;
	import java.io.FileOutputStream;
	import java.util.Date;
	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.Set;
	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	public class pdfListener implements ITestListener {
	   private Document document = null;
	   PdfPTable successTable = null;
	   PdfPTable failTable = null;
	   private HashMap<Integer, Throwable> throwableMap = null;
	   private int nbExceptions = 0;

	   public pdfListener() {
	      log("JyperionListener()");
	      this.document = new Document();
	      this.throwableMap = new HashMap();
	   }

	   public void onTestSuccess(ITestResult result) {
	      log("onTestSuccess(" + result + ")");
	      if (this.successTable == null) {
	         this.successTable = new PdfPTable(new float[]{0.3F, 0.3F, 0.1F, 0.3F});
	         Paragraph p = new Paragraph("PASSED TESTS", new Font(2, 12.0F, 1));
	         p.setAlignment(1);
	         PdfPCell cell = new PdfPCell(p);
	         cell.setColspan(4);
	         cell.setBackgroundColor(Color.GREEN);
	         this.successTable.addCell(cell);
	         cell = new PdfPCell(new Paragraph("Class"));
	         cell.setBackgroundColor(Color.LIGHT_GRAY);
	         this.successTable.addCell(cell);
	         cell = new PdfPCell(new Paragraph("Method"));
	         cell.setBackgroundColor(Color.LIGHT_GRAY);
	         this.successTable.addCell(cell);
	         cell = new PdfPCell(new Paragraph("Time (ms)"));
	         cell.setBackgroundColor(Color.LIGHT_GRAY);
	         this.successTable.addCell(cell);
	         cell = new PdfPCell(new Paragraph("Exception"));
	         cell.setBackgroundColor(Color.LIGHT_GRAY);
	         this.successTable.addCell(cell);
	      }

	      PdfPCell cell = new PdfPCell(new Paragraph(result.getTestClass().toString()));
	      this.successTable.addCell(cell);
	      cell = new PdfPCell(new Paragraph(result.getMethod().getMethodName().toString()));
	      this.successTable.addCell(cell);
	      cell = new PdfPCell(new Paragraph("" + (result.getEndMillis() - result.getStartMillis())));
	      this.successTable.addCell(cell);
	      Throwable throwable = result.getThrowable();
	      if (throwable != null) {
	         this.throwableMap.put(new Integer(throwable.hashCode()), throwable);
	         ++this.nbExceptions;
	         Paragraph excep = new Paragraph((new Chunk(throwable.toString(), new Font(2, 12.0F, 4))).setLocalGoto("" + throwable.hashCode()));
	         cell = new PdfPCell(excep);
	         this.successTable.addCell(cell);
	      } else {
	         this.successTable.addCell(new PdfPCell(new Paragraph("")));
	      }

	   }

	   public void onTestFailure(ITestResult result) {
	      log("onTestFailure(" + result + ")");
	      if (this.failTable == null) {
	         this.failTable = new PdfPTable(new float[]{0.3F, 0.3F, 0.1F, 0.3F});
	         this.failTable.setTotalWidth(20.0F);
	         Paragraph p = new Paragraph("FAILED TESTS", new Font(2, 12.0F, 1));
	         p.setAlignment(1);
	         PdfPCell cell = new PdfPCell(p);
	         cell.setColspan(4);
	         cell.setBackgroundColor(Color.RED);
	         this.failTable.addCell(cell);
	         cell = new PdfPCell(new Paragraph("Class"));
	         cell.setBackgroundColor(Color.LIGHT_GRAY);
	         this.failTable.addCell(cell);
	         cell = new PdfPCell(new Paragraph("Method"));
	         cell.setBackgroundColor(Color.LIGHT_GRAY);
	         this.failTable.addCell(cell);
	         cell = new PdfPCell(new Paragraph("Time (ms)"));
	         cell.setBackgroundColor(Color.LIGHT_GRAY);
	         this.failTable.addCell(cell);
	         cell = new PdfPCell(new Paragraph("Exception"));
	         cell.setBackgroundColor(Color.LIGHT_GRAY);
	         this.failTable.addCell(cell);
	      }

	      PdfPCell cell = new PdfPCell(new Paragraph(result.getTestClass().toString()));
	      this.failTable.addCell(cell);
	      cell = new PdfPCell(new Paragraph(result.getMethod().getMethodName().toString()));
	      this.failTable.addCell(cell);
	      cell = new PdfPCell(new Paragraph("" + (result.getEndMillis() - result.getStartMillis())));
	      this.failTable.addCell(cell);
	      Throwable throwable = result.getThrowable();
	      if (throwable != null) {
	         this.throwableMap.put(new Integer(throwable.hashCode()), throwable);
	         ++this.nbExceptions;
	         Paragraph excep = new Paragraph((new Chunk(throwable.toString(), new Font(2, 12.0F, 4))).setLocalGoto("" + throwable.hashCode()));
	         cell = new PdfPCell(excep);
	         this.failTable.addCell(cell);
	      } else {
	         this.failTable.addCell(new PdfPCell(new Paragraph("")));
	      }

	   }

	   public void onTestSkipped(ITestResult result) {
	      log("onTestSkipped(" + result + ")");
	   }

	   public void onStart(ITestContext context) {
	      log("onStart(" + context + ")");

	      try {
	         PdfWriter.getInstance(this.document, new FileOutputStream(context.getName() + ".pdf"));
	      } catch (Exception var5) {
	         var5.printStackTrace();
	      }

	      this.document.open();
	      Paragraph p = new Paragraph(context.getName() + " TESTNG RESULTS", FontFactory.getFont("Helvetica", 20.0F, 1, new Color(0, 0, 255)));

	      try {
	         this.document.add(p);
	         this.document.add(new Paragraph((new Date()).toString()));
	      } catch (DocumentException var4) {
	         var4.printStackTrace();
	      }

	   }

	   public void onFinish(ITestContext context) {
	      log("onFinish(" + context + ")");

	      try {
	         if (this.failTable != null) {
	            log("Added fail table");
	            this.failTable.setSpacingBefore(15.0F);
	            this.document.add(this.failTable);
	            this.failTable.setSpacingAfter(15.0F);
	         }

	         if (this.successTable != null) {
	            log("Added success table");
	            this.successTable.setSpacingBefore(15.0F);
	            this.document.add(this.successTable);
	            this.successTable.setSpacingBefore(15.0F);
	         }
	      } catch (DocumentException var20) {
	         var20.printStackTrace();
	      }

	      Paragraph p = new Paragraph("EXCEPTIONS SUMMARY", FontFactory.getFont("Helvetica", 16.0F, 1, new Color(255, 0, 0)));

	      try {
	         this.document.add(p);
	      } catch (DocumentException var19) {
	         var19.printStackTrace();
	      }

	      Set<Integer> keys = this.throwableMap.keySet();

	      assert keys.size() == this.nbExceptions;

	      Iterator var5 = keys.iterator();

	      while(var5.hasNext()) {
	         Integer key = (Integer)var5.next();
	         Throwable throwable = (Throwable)this.throwableMap.get(key);
	         Chunk chunk = new Chunk(throwable.toString(), FontFactory.getFont("Helvetica", 12.0F, 1, new Color(255, 0, 0)));
	         chunk.setLocalDestination("" + key);
	         Paragraph throwTitlePara = new Paragraph(chunk);

	         try {
	            this.document.add(throwTitlePara);
	         } catch (DocumentException var18) {
	            var18.printStackTrace();
	         }

	         StackTraceElement[] elems = throwable.getStackTrace();
	         String exception = "";
	         StackTraceElement[] var14 = elems;
	         int var13 = elems.length;

	         for(int var12 = 0; var12 < var13; ++var12) {
	            StackTraceElement ste = var14[var12];
	            Paragraph throwParagraph = new Paragraph(ste.toString());

	            try {
	               this.document.add(throwParagraph);
	            } catch (DocumentException var17) {
	               var17.printStackTrace();
	            }
	         }
	      }

	      this.document.close();
	   }

	   public static void log(Object o) {
	   }

	   public void onTestStart(ITestResult result) {
	   }

	   public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	   }
	}


