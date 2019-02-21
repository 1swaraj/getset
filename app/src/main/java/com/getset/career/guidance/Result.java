package com.getset.career.guidance;

import android.Manifest;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.himanshugoel.WordCloud;
import com.himanshugoel.WordCloudClick;
import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.app.ProgressDialog;

import spencerstudios.com.bungeelib.Bungee;

public class Result extends AppCompatActivity {

    List<String> arrayString;
    WordCloud wordCloud;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private static final String TAG = "PdfCreatorActivity";
    private File pdfFile;
    private ProgressDialog progressBar;

    String x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        setContentView(R.layout.activity_result);

        x=readFromFile(this);


        String[] arrOfStr = x.split(";");
        String interest="";
        for (String a : arrOfStr) {
            if(a.contains("Interest Test :"))
                interest=a.substring("Interest Test :".length());
        }
        wordCloud = (WordCloud) findViewById(R.id.wordCloud);

        arrayString= Arrays.asList(interest.split(","));
        wordCloud.create(arrayString);

        wordCloud.setRandomSize(70, 130);

        wordCloud.setRandomTextColor();
        wordCloud.setRandomFonts();



        wordCloud.setOnWordClickListener(new WordCloudClick() {
            @Override
            public void onWordClick(View widget, int index) {
                Toast.makeText(getApplicationContext(), arrayString.get(index), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.personalityreport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Result.this.startActivity(new Intent(Result.this, PersonalityReport.class));
                //Bungee.zoom(Result.this);  //fire the zoom animation
                try {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Result.this).create();
                    progressBar = ProgressDialog.show(Result.this, "Loading", "Preparing you for your future...\nPreparing your report...");
                    Toast.makeText(Result.this, "Preparing your result please wait", Toast.LENGTH_LONG).show();
                    createPdfWrapper();
                    if (progressBar.isShowing()) {
                        progressBar.dismiss();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private String readFromFile(Context context) {
        String contents="";
        try {
            File file = new File(context.getFilesDir(), "config.txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            int length = (int) file.length();
            byte[] bytes = new byte[length];
            FileInputStream in = new FileInputStream(file);
            try {
                in.read(bytes);
            } finally {
                in.close();
            }
            contents = new String(bytes);
        }
        catch (Exception e)
        {
            Log.e("PDFCreator", "createPDFError:" + e);

        }
        return contents;
    }

    private void createPdfWrapper() throws FileNotFoundException,DocumentException{

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }else {
            createPdf();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    public static Bitmap loadBitmapFromView(View view) {

        // width measure spec
        int widthSpec = View.MeasureSpec.makeMeasureSpec(
                view.getMeasuredWidth(), View.MeasureSpec.EXACTLY);
        // height measure spec
        int heightSpec = View.MeasureSpec.makeMeasureSpec(
                view.getMeasuredHeight(), View.MeasureSpec.EXACTLY);
        // measure the view
        view.measure(widthSpec, heightSpec);
        // set the layout sizes
        view.layout(view.getLeft(), view.getTop(), view.getMeasuredWidth() + view.getLeft(), view.getMeasuredHeight() + view.getTop());
        // create the bitmap
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        // create a canvas used to get the view's image and draw it on the bitmap
        Canvas c = new Canvas(bitmap);
        // position the image inside the canvas
        c.translate(-view.getScrollX(), -view.getScrollY());
        // get the canvas
        view.draw(c);

        return bitmap;
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }
        pdfFile = new File(docsFolder.getAbsolutePath(),"GetSet_Result.pdf");
        try {
            pdfFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream output = new FileOutputStream(pdfFile);
        com.itextpdf.text.Rectangle rect = new com.itextpdf.text.Rectangle(PageSize.A4.getWidth(),PageSize.A4.getHeight());
        Document document = new Document(rect,0,0,0,0);
        PdfWriter.getInstance(document, output);
        document.open();
        document.addAuthor("GetSet Ventures");
        document.addTitle("GetSet Ventures - Comprehensive Career Report");

        Drawable myDrawable = getResources().getDrawable(R.drawable.report_front_page);
        Bitmap bitmap = ((BitmapDrawable) myDrawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image frontpage = null;
        try {
            frontpage = Image.getInstance(stream.toByteArray());
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin()) / frontpage.getWidth()) * 100;
            frontpage.scalePercent(scaler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        document.add(frontpage);
        document.newPage();

        myDrawable = getResources().getDrawable(R.drawable.secondpage);
        bitmap = ((BitmapDrawable) myDrawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image secondpage = null;
        try {
            secondpage = Image.getInstance(stream.toByteArray());
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin()) / secondpage.getWidth()) * 100;
            secondpage.scalePercent(scaler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.add(secondpage);
        document.newPage();


        myDrawable = getResources().getDrawable(R.drawable.thirdpage);
        bitmap = ((BitmapDrawable) myDrawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image thirdpage = null;
        try {
            thirdpage = Image.getInstance(stream.toByteArray());
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin()) / thirdpage.getWidth()) * 100;
            thirdpage.scalePercent(scaler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.add(thirdpage);


        document.setMargins(20,20,20,20);
        document.newPage();
        float fntSize, lineSpacing;
        fntSize = 30f;
        lineSpacing = 10f;
        Paragraph p = new Paragraph(new Phrase(lineSpacing,"Careers you have shown interest in\n",
                FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize,BaseColor.WHITE)));
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(80);
        table.getDefaultCell().setBorderWidth(0f);
        wordCloud.setDrawingCacheEnabled(true);
        bitmap = loadBitmapFromView(wordCloud);
        wordCloud.destroyDrawingCache();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image wordcloud = null;
        try {
            wordcloud = Image.getInstance(stream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }

        PdfPCell cell = new PdfPCell(p);
        cell.setPaddingBottom(12f);
        cell.setPaddingTop(2f);
        cell.setPaddingLeft(2f);
        cell.setPaddingRight(2f);
        cell.setBackgroundColor(new BaseColor(194, 206, 94));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        table.addCell(wordcloud);
        document.add(table);

        document.newPage();


        myDrawable = getResources().getDrawable(R.drawable.resback);
        bitmap = ((BitmapDrawable) myDrawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image back = null;
        try {
            back = Image.getInstance(stream.toByteArray());
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin()) / back.getWidth()) * 100;
            back.scalePercent(scaler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        back.setAbsolutePosition(0,0);
        document.add(new Paragraph(x));
        document.add(back);

        document.newPage();
        document.close();


        previewPdf();


    }

    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        //testIntent.setType("application/pdf");
        //List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        //if (list.size() > 0) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Intent intent_chooser = Intent.createChooser(intent, "Open File");
            startActivity(intent_chooser);
        }
        catch (Exception e) {
            Toast.makeText(this, "Download a PDF Viewer to see the generated report.\nYou can view the file in Documents (Storage)", Toast.LENGTH_LONG).show();
        }

            //}else{
        //    Toast.makeText(this,"Download a PDF Viewer to see the generated report.\nYou can view the file in Documents (Storage)",Toast.LENGTH_LONG).show();
        //}
    }
}