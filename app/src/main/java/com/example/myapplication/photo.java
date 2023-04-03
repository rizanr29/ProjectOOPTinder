import android.app.Activity;import android.content.Intent;
import android.net.Uri;import android.os.Bundle;
import android.provider.MediaStore;import android.widget.ImageView;
public class ProfilePhotoUploadActivity extends Activity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_photo_upload);
        mImageView = (ImageView) findViewById(R.id.imageView);
        // Launch the image picker when the user taps the ImageView       
 mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            // Update the ImageView with the selected image            mImageView.setImageURI(uri);
        }    }
}