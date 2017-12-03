package teamwork.sor;

/**
 * Created by Prudence on 02/12/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;

import teamwork.sor.model.Offender;


public class Tab1 extends Fragment {
    private FirebaseDatabase mref;
    private DatabaseReference offendersRef;
    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;
    private String uid;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    static String event_key;
    private   StorageReference storageRef;
private Context context;
 /*   public void onStart(){
//        startActivity(new Intent(context,PDFActivity.class));
        super.onStart();
        //mRootRef= FirebaseDatabase.getInstance().getReference();
//      offendersRef=mRootRef.child("offenders");
  //      offendersRef.keepSynced(true);

        FirebaseRecyclerAdapter<Offender, Viewholder> theAdapter = new FirebaseRecyclerAdapter<Offender, Viewholder>(Offender.class,R.layout.custom_view,Viewholder.class,offendersRef) {


            @Override
            protected void populateViewHolder(   final Viewholder viewholder,final Offender offender, final int position) {






                StorageReference ref = FirebaseStorage.getInstance().getReferenceFromUrl(offender.getImage());
                viewholder.name.setText(offender.getName());
                viewholder.sex.setText(offender.getSex());
                viewholder.offence.setText(offender.getOffence());
                event_key=getRef(position).getKey();

                Glide.with(context)
                        .using(new FirebaseImageLoader())
                        .load(ref)
                        .into(viewholder.image);
            viewholder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                     viewholder.image.setDrawingCacheEnabled(true);
                   viewholder.image.buildDrawingCache();
                        final Bitmap bitmap =  viewholder.image.getDrawingCache();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] data = baos.toByteArray();


                        Intent i = new Intent(context, PDFView.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable("eventObj", (Serializable) offender);
                        mBundle.putInt("position", position);
                        mBundle.putSerializable("pushid",event_key);
                        mBundle.putByteArray("data",data);
                        i.putExtras(mBundle);
                        context.startActivity(i);

                        Log.d("checking", "past the intent");
                    }
                });






            }

        };


        mRecyclerView.setAdapter(theAdapter);

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);
        //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //mRootRef = firebaseDatabase.getReference();
       // mAuth = FirebaseAuth.getInstance();
        //FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        //storageRef = firebaseStorage.getReference();


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // since I can connect from multiple devices, we store each connection instance separately
// any time that connectionsRef's value is null (i.e. has no children) I am offline
      //uid = mAuth.getCurrentUser().getUid();

        return rootView;
    }
    public class FirebaseImageLoader implements StreamModelLoader<StorageReference> {

        @Override
        public DataFetcher<InputStream> getResourceFetcher(StorageReference model, int width, int height) {
            return new FirebaseStorageFetcher(model);
        }

        private class FirebaseStorageFetcher implements DataFetcher<InputStream> {

            private StorageReference mRef;

            FirebaseStorageFetcher(StorageReference ref) {
                mRef = ref;
            }

            @Override
            public InputStream loadData(Priority priority) throws Exception {
                return Tasks.await(mRef.getStream()).getStream();
            }

            @Override
            public void cleanup() {
                // No cleanup possible, Task does not expose cancellation
            }

            @Override
            public String getId() {
                return mRef.getPath();
            }

            @Override
            public void cancel() {
                // No cancellation possible, Task does not expose cancellation
            }
        }
    }


    public static class Viewholder extends RecyclerView.ViewHolder{
        TextView name, sex, offence;
        ImageView image;
        CardView cardView;
        public  Viewholder(View v) {
            super(v);

            name= v.findViewById(R.id.name);
            offence=v.findViewById(R.id.offence);
            sex=v.findViewById(R.id.sex);
            cardView=v.findViewById(R.id.cardView_row);





        }

    }

}
