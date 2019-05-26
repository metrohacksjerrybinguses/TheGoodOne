package com.binguses.jerry;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.binguses.jerry.Calories;

import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.env.Logger;
import org.tensorflow.lite.examples.classification.tflite.Classifier;

import java.io.IOException;
import java.util.List;

/*This page is the home page that opens when the user opens the app.
From this page the user can take a picture of food
 */
public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PERMISSIONS_REQUEST = 1;

    private Classifier.Model model = Classifier.Model.QUANTIZED;
    private Classifier.Device device = Classifier.Device.CPU;
    private int numThreads = -1;

    private static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    private android.graphics.Bitmap imageBitmap;
    private Bitmap croppedBitmap;
    private Classifier classifier;
    private static final Logger LOGGER = new Logger();
    private Handler handler;
    private HandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!hasPermission()) {
            requestPermission();
        }

        Button btnCamera = findViewById(R.id.camera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCamera();
            }
        });
        recreateClassifier(model, device, numThreads);
        croppedBitmap =
                Bitmap.createBitmap(
                        classifier.getImageSizeX(), classifier.getImageSizeY(), Bitmap.Config.ARGB_8888);

        handlerThread = new HandlerThread("inference");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());

    }

    //Opens the camera
    public void onCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    //Saves Bitmap
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            if (imageBitmap != null) {
                Log.wtf("Bitmap", imageBitmap.getConfig().toString());
            }
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, classifier.getImageSizeX(), classifier.getImageSizeY(), false);
            //imageView.setImageBitmap(imageBitmap);

        }

        // Start ML here
        processImage();
    }

    public void goToCalories(String s) {
        Intent intent = new Intent(this, Calories.class);
        Scraper scraper = new Scraper(s);
        double cal = scraper.crawl();
        intent.putExtra("objCal",cal);
        intent.putExtra("objName", s);
        startActivity(intent);
    }

    protected void processImage() {
//        rgbFrameBitmap.setPixels(getRgbBytes(), 0, previewWidth, 0, 0, previewWidth, previewHeight);
//        final Canvas canvas = new Canvas(croppedBitmap);
//        canvas.drawBitmap(rgbFrameBitmap, frameToCropTransform, null);
        Log.wtf("Results", "pre classifier");
        runInBackground(
                new Runnable() {
                    @Override
                    public void run() {
                        if (classifier != null) {

                            final List<Classifier.Recognition> results = classifier.recognizeImage(imageBitmap);
//                            lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;
//                            cropCopyBitmap = Bitmap.createBitmap(croppedBitmap);

                            // Deal with results now
                            Log.wtf("Results", results.get(0).getTitle()+" "+Float.toString(results.get(0).getConfidence()));
                            goToCalories(results.get(0).getTitle());
                        } else {
                            Log.wtf("Results", "null classifier");
                        }
                    }
                });
    }

    protected synchronized void runInBackground(final Runnable r) {
        if (handler != null) {
            handler.post(r);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            final int requestCode, final String[] permissions, final int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            } else {
                requestPermission();
            }
        }
    }

    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(PERMISSION_CAMERA) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (shouldShowRequestPermissionRationale(PERMISSION_CAMERA)) {
                Toast.makeText(
                        this,
                        "Camera permission is required for this demo",
                        Toast.LENGTH_LONG)
                        .show();
            }
            requestPermissions(new String[]{PERMISSION_CAMERA}, PERMISSIONS_REQUEST);
        }
    }


    private void recreateClassifier(Classifier.Model model, Classifier.Device device, int numThreads) {
        if (classifier != null) {
            LOGGER.d("Closing classifier.");
            classifier.close();
            classifier = null;
        }
        if (device == Classifier.Device.GPU && model == Classifier.Model.QUANTIZED) {
            LOGGER.d("Not creating classifier: GPU doesn't support quantized models.");
            runOnUiThread(
                    () -> {
                        Toast.makeText(this, "GPU does not yet supported quantized models.", Toast.LENGTH_LONG)
                                .show();
                    });
            return;
        }
        try {
            LOGGER.d(
                    "Creating classifier (model=%s, device=%s, numThreads=%d)", model, device, numThreads);
            classifier = Classifier.create(this, model, device, numThreads);
        } catch (IOException e) {
            LOGGER.e(e, "Failed to create classifier.");
        }
    }
}
