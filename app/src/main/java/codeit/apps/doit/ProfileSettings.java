package codeit.apps.doit;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;

import java.io.IOException;

public class ProfileSettings extends AppCompatActivity {
    private final int GALLARY_REQ_CODE = 1000;
    private MaterialCardView SelectPhoto;
    private Uri ImageUri;
    ImageView imgProfile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        SelectPhoto= findViewById(R.id.profileimg);
        imgProfile= findViewById(R.id.imgprofile);
        SelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImageFromGallery();
            }

        });
    }
    private void PickImageFromGallery() {
        Intent iGallery= new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallery, GALLARY_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK){

            if(requestCode==GALLARY_REQ_CODE){
                // for Gallery

                imgProfile.setImageURI(data.getData());
            }
        }
    }
}