package teamwork.sor;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class PDFActivity extends AppCompatActivity {
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        PDFView pdfView = (PDFView)findViewById(R.id.pdf_view);
        StorageReference docket = storageReference.child("dockets/orange.pdf");

        try {
            File file = File.createTempFile("orange","pdf");
            docket.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {


                }
            });
            pdfView.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // pdfView.fromUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/sorfirebaseproject.appspot.com/o/A5%20FLYER%20alt%202.pdf?alt=media&token=927b06e9-1f71-420e-a652-a1d7b1eb26c4"));

    }
}
