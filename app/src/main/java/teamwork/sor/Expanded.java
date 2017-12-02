package teamwork.sor;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import teamwork.sor.model.Offender;

public class Expanded extends AppCompatActivity {
    private Context context;
TextView name,sex,offence,offenceCount,residence,docket;
    private  byte[] data;
    ImageView image;
    private String push;
    private Offender offenderObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=Expanded.this;


        image=(ImageView) findViewById(R.id.imageView);
        name= (TextView) findViewById(R.id.name);
        sex= (TextView) findViewById(R.id.sex);
        offence= (TextView) findViewById(R.id.offence);
        offenceCount= (TextView) findViewById(R.id.offence_count);
        residence= (TextView) findViewById(R.id.residence);
        docket= (TextView) findViewById(R.id.docket);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          offenderObj = (Offender) getIntent().getSerializableExtra("eventObj");
            final int position = getIntent().getIntExtra("position", 0);
            data = getIntent().getByteArrayExtra("data");
            push = (String) getIntent().getSerializableExtra("pushid");

            image.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));

            name.setText(offenderObj.getName());
            sex.setText(offenderObj.getSex());
            offence.setText(offenderObj.getOffence());
            offenceCount.setText(offenderObj.getOffenceCount());
            residence.setText(offenderObj.getResidence());
            docket.setText(offenderObj.getDocket());

            docket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //get pdf download on case



handleViewPdf();
                }
            });



        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void downloadPdfs() {
        String pdfUrl=offenderObj.getPdfUrl();



    }
    private void handleViewPdf () {

        File folder = getAppDirectory(context);
        String fileName = "test.pdf";// getPdfFileName(pdfUrl);
        File pdfFile = new File(folder, fileName);

        if (pdfFile.exists () && pdfFile.length () > 0) {
            openPDFFile (context, Uri.fromFile(pdfFile));
        }
        else {
            if (pdfFile.length () == 0) {
                pdfFile.delete ();
            }
            try {
                pdfFile.createNewFile ();
            }
            catch (IOException e) {
                e.printStackTrace ();
            }
            ArrayList<String> fileNameAndURL = new ArrayList<> ();
            fileNameAndURL.add (pdfFile.toString ());
            fileNameAndURL.add (pdfUrl);
            fileNameAndURL.add (fileName);
            if (pdfDownloaderAsyncTask == null) {
                pdfDownloaderAsyncTask = new PDFDownloaderAsyncTask (context, pdfFile);
            }
            if (hasInternetConnection (context)) {
                if (!pdfDownloaderAsyncTask.isDownloadingPdf ()) {
                    pdfDownloaderAsyncTask = new PDFDownloaderAsyncTask (context, pdfFile);
                    pdfDownloaderAsyncTask.execute (fileNameAndURL);
                }
            }
            else {
                //show error
            }
        }
    }


}
