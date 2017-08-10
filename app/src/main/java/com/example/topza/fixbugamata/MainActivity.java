package com.example.topza.fixbugamata;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.topza.fixbugamata.dao.DefaultResponseDao;
import com.example.topza.fixbugamata.dao.NewsDao;
import com.example.topza.fixbugamata.http.HttpManager;
import com.github.oliveiradev.lib.RxPhoto;
import com.github.oliveiradev.lib.shared.TypeRequest;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Uri result;
    private MultipartBody.Part part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPhoto.requestUri(MainActivity.this, TypeRequest.GALLERY)
                        .doOnNext(new Action1<Uri>() {
                            @Override
                            public void call(Uri uri) {
                                imageView.setImageURI(uri);
                                result = uri;
                            }
                        })
                        .subscribe();
            }
        });

    }

    public void sendImage(View v) {
        File file = new File(getPath(result));
        RequestBody filePart = RequestBody.create(MediaType.parse("image/*"), file);
        part = MultipartBody.Part.createFormData("file", file.getName(), filePart);

        HttpManager.getInstance().getService().SendReportIncident(part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DefaultResponseDao>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Send Image Complete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Test", e.getMessage());
                        Log.d("Test", e.toString());
                    }

                    @Override
                    public void onNext(DefaultResponseDao defaultResponseDao) {
                        Toast.makeText(MainActivity.this, defaultResponseDao.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private String getPath(Uri uri) {
        String[] data = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, uri, data, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}


