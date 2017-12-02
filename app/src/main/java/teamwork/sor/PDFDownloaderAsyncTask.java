package teamwork.sor;

/**
 * Created by Prudence on 03/12/2017.
 */
import java.io.File;
import java.util.ArrayList;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;


public class PDFDownloaderAsyncTask extends AsyncTask<ArrayList<String>, Void, String> {

    private boolean isDownloadingPdf = false;

    private File    file;
    private Context context;

    public PDFDownloaderAsyncTask (Context context, File file) {

        this.file = file;
        this.context = context;
        this.isDownloadingPdf = false;
    }

    public boolean isDownloadingPdf () {

        return this.isDownloadingPdf;
    }

    @Override
    protected void onPreExecute () {

        super.onPreExecute ();
        //show loader etc
    }

    @Override
    protected String doInBackground (ArrayList<String>... params) {

        isDownloadingPdf = true;
        File file = new File (params[0].get (0));
        String fileStatus = PdfDownloader.downloadFile (params[0].get (1), file);
        return fileStatus;
    }

    @Override
    protected void onPostExecute (String result) {

        super.onPostExecute (result);
        Loader.hideLoader ();
        if (!TextUtils.isEmpty (result) && result.equalsIgnoreCase (context.getString (R.string.txt_success))) {
            showPdf ();
        }
        else {
            isDownloadingPdf = false;
            Toast.makeText (context, context.getString (R.string.error_could_not_download_pdf), Toast.LENGTH_LONG).show ();
            file.delete ();
        }
    }

    @Override
    protected void onCancelled () {

        isDownloadingPdf = false;
        super.onCancelled ();
        //Loader.hideLoader ();
    }

    @Override
    protected void onCancelled (String s) {

        isDownloadingPdf = false;
        super.onCancelled (s);
        //Loader.hideLoader ();
    }

    private void showPdf () {

        new Handler ().postDelayed (new Runnable () {
            @Override
            public void run () {

                isDownloadingPdf = false;
                openPDFFile (context, Uri.fromFile (file));
            }
        }, 1000);
    }
}