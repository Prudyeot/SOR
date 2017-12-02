package teamwork.sor;

/**
 * Created by Prudence on 03/12/2017.
 */
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;

public class PdfDownloader {
    private static final int MEGABYTE = 1024 * 1024;

    public static String downloadFile (String fileUrl, File directory) {

        String downloadStatus;
        try {

            URL url = new URL (fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection ();
            urlConnection.connect ();

            InputStream inputStream = urlConnection.getInputStream ();
            FileOutputStream fileOutputStream = new FileOutputStream (directory);
            int totalSize = urlConnection.getContentLength ();

            Log.d ("PDF", "Total size: " + totalSize);
            byte[] buffer = new byte[MEGABYTE];
            int bufferLength = 0;
            while ((bufferLength = inputStream.read (buffer)) > 0) {
                fileOutputStream.write (buffer, 0, bufferLength);
            }
            downloadStatus = "success";
            fileOutputStream.close ();
        }
        catch (FileNotFoundException e) {
            downloadStatus = "FileNotFoundException";
            e.printStackTrace ();
        }
        catch (MalformedURLException e) {
            downloadStatus = "MalformedURLException";
            e.printStackTrace ();
        }
        catch (IOException e) {
            downloadStatus = "IOException";
            e.printStackTrace ();
        }
        Log.d ("PDF", "Download Status: " + downloadStatus);
        return downloadStatus;
    }


    public static void openPDFFile (Context context, Uri path) {

        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setDataAndType (path, "application/pdf");
        intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try {
            context.startActivity (intent);
        }
        catch (ActivityNotFoundException e) {
            Toast.makeText (context, context.getString (R.string.txt_no_pdf_available), Toast.LENGTH_SHORT).show ();
        }
        Loader.hideLoader ();
    }

    public static File getAppDirectory (Context context) {

        String extStorageDirectory = Environment.getExternalStorageDirectory ().toString ();
        File folder = new File (extStorageDirectory, context.getString (R.string.app_folder_name).trim ());
        if (!folder.exists ()) {
            boolean success = folder.mkdirs();
            Log.d ("Directory", "mkdirs():" + success);
        }
        return folder;
    }

}