package com.github.gcacace.signaturepad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import it.gcacace.signaturepad.R;

public class DrawOnBitmapActivity extends Activity implements View.OnClickListener {
    DrawableImageView choosenImageView;
    Button choosePicture;
    //Button savePicture;
    Bitmap bmp;
    Bitmap alteredBitmap;

    private static final String STATE_CURRENT_PAGE_INDEX = "current_page_index";

   // private static final String FILENAME = "sample.pdf";

    private ParcelFileDescriptor mFileDescriptor;

    private PdfRenderer mPdfRenderer;

    private PdfRenderer.Page mCurrentPage;

    private DrawableImageView mImageView;

    private Button mButtonPrevious;

    private Button mButtonNext;

    private int mPageIndex;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //choosenImageView = (DrawableImageView) this.findViewById(R.id.ChoosenImageView);
        choosePicture = (Button) this.findViewById(R.id.ChoosePictureButton);
        //savePicture = (Button) this.findViewById(R.id.SavePictureButton);
       // savePicture.setOnClickListener(this);
        choosePicture.setOnClickListener(this);

        mImageView = (DrawableImageView)findViewById(R.id.image);
        mButtonPrevious = (Button)findViewById(R.id.previous);
        mButtonNext = (Button)findViewById(R.id.next);
        // Bind events.
        mButtonPrevious.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);

        mPageIndex = 0;
        // If there is a savedInstanceState (screen orientations, etc.), we restore the page index.
        if (null != savedInstanceState)
        {
            mPageIndex = savedInstanceState.getInt(STATE_CURRENT_PAGE_INDEX, 0);
        }
    }

    public void onClick(View v)
    {
        if (v == choosePicture)
        {
           /* Intent choosePictureIntent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(choosePictureIntent, 0);*/

            Intent intent = new Intent();
            intent.setType("*/*");
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), 0);
        }

        switch (v.getId()) {
            case R.id.previous: {
                // Move to the previous page
                showPage(mCurrentPage.getIndex() - 1);
                break;
            }
            case R.id.next: {
                // Move to the next page
                showPage(mCurrentPage.getIndex() + 1);
                break;
            }
        }
        /*else if (v == savePicture)
        {
            if (alteredBitmap != null)
            {
                ContentValues contentValues = new ContentValues(3);
                contentValues.put(MediaStore.Audio.Media.DISPLAY_NAME, "Draw On Me");

                Uri imageFileUri = getContentResolver().insert(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contentValues);
                try {
                    OutputStream imageFileOS = getContentResolver()
                            .openOutputStream(imageFileUri);
                    alteredBitmap
                            .compress(Bitmap.CompressFormat.JPEG, 90, imageFileOS);
                    Toast t = Toast
                            .makeText(this, "Saved!", Toast.LENGTH_SHORT);
                    t.show();

                } catch (Exception e) {
                    Log.v("EXCEPTION", e.getMessage());
                }
            }
        }*/
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK)
        {
            Uri uri = intent.getData();
            File tempFile = null;
            String filePath_uploadFile = "";
            try
            {
                if(uri != null)
                {
                    Cursor cursor = getContentResolver().query(uri, null, null, null, null, null);

                    try
                    {
                        if(cursor != null && cursor.moveToFirst())
                        {

                            filePath_uploadFile = cursor.getString(
                                    cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));

                            tempFile = FileUtils.saveTempFile(filePath_uploadFile, this, uri);

                        }

                    }
                    finally
                    {
                        if(cursor != null)
                        {
                            cursor.close();
                        }
                    }
                }
                openRenderer(getApplicationContext(),"sample.pdf",tempFile);
                showPage(mPageIndex);
            }
            catch (Exception e) {
                Log.v("ERROR", e.toString());
            }


        }
       /* if (resultCode == RESULT_OK) {
            Uri imageFileUri = intent.getData();
            try {
                BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
                bmpFactoryOptions.inJustDecodeBounds = true;
                bmp = BitmapFactory
                        .decodeStream(
                                getContentResolver().openInputStream(
                                        imageFileUri), null, bmpFactoryOptions);

                bmpFactoryOptions.inJustDecodeBounds = false;
                bmp = BitmapFactory
                        .decodeStream(
                                getContentResolver().openInputStream(
                                        imageFileUri), null, bmpFactoryOptions);

                alteredBitmap = Bitmap.createBitmap(bmp.getWidth(),
                        bmp.getHeight(), bmp.getConfig());

                choosenImageView.setNewImage(alteredBitmap, bmp);
            }
            catch (Exception e) {
                Log.v("ERROR", e.toString());
            }
        }*/

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStart() {
        super.onStart();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStop() {
        try {
            closeRenderer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != mCurrentPage) {
            outState.putInt(STATE_CURRENT_PAGE_INDEX, mCurrentPage.getIndex());
        }
    }

    /**
     * Sets up a {@link android.graphics.pdf.PdfRenderer} and related resources.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openRenderer(Context context,String FILENAME,File file) throws IOException {
        // In this sample, we read a PDF from the assets directory.
        /*File file = new File(context.getCacheDir(), FILENAME);
        if (!file.exists()) {
            // Since PdfRenderer cannot handle the compressed asset file directly, we copy it into
            // the cache directory.
            InputStream asset = context.getAssets().open(FILENAME);
            FileOutputStream output = new FileOutputStream(file);
            final byte[] buffer = new byte[1024];
            int size;
            while ((size = asset.read(buffer)) != -1) {
                output.write(buffer, 0, size);
            }
            asset.close();
            output.close();
        } */
        mFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
        // This is the PdfRenderer we use to render the PDF.
        if (mFileDescriptor != null) {
            mPdfRenderer = new PdfRenderer(mFileDescriptor);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void closeRenderer() throws IOException
    {
        try {
            if (null != mCurrentPage) {
                mCurrentPage.close();
            }
        }
        catch (Exception e)
        {}
        try
        {

        }
        catch (Exception e) {
            mPdfRenderer.close();
            mFileDescriptor.close();
        }
    }

    /**
     * Shows the specified page of PDF to the screen.
     *
     * @param index The page index.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showPage(int index) {
        if (mPdfRenderer.getPageCount() <= index) {
            return;
        }
        // Make sure to close the current page before opening another one.
        if (null != mCurrentPage) {
            mCurrentPage.close();
        }
        // Use `openPage` to open a specific page in PDF.
        mCurrentPage = mPdfRenderer.openPage(index);
        // Important: the destination bitmap must be ARGB (not RGB).
        Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(), mCurrentPage.getHeight(),
                Bitmap.Config.ARGB_8888);
        // Here, we render the page onto the Bitmap.
        // To render a portion of the page, use the second and third parameter. Pass nulls to get
        // the default result.
        // Pass either RENDER_MODE_FOR_DISPLAY or RENDER_MODE_FOR_PRINT for the last parameter.
        mCurrentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        // We are ready to show the Bitmap to user.
       // mImageView.setImageBitmap(bitmap);

        try {

            alteredBitmap = Bitmap.createBitmap(bitmap.getWidth()+100,
                    bitmap.getHeight()+100, bitmap.getConfig());

            mImageView.setNewImage(alteredBitmap, bitmap);
        }
        catch (Exception e) {
            Log.v("ERROR", e.toString());
        }

        updateUi();
    }

    /**
     * Updates the state of 2 control buttons in response to the current page index.
     */
    @SuppressLint("StringFormatInvalid")
    private void updateUi() {
        int index = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            index = mCurrentPage.getIndex();
        }
        int pageCount = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            pageCount = mPdfRenderer.getPageCount();
        }
        mButtonPrevious.setEnabled(0 != index);
        mButtonNext.setEnabled(index + 1 < pageCount);
        setTitle(getString(R.string.app_name_with_index, index + 1, pageCount));
    }

    /**
     * Gets the number of pages in the PDF. This method is marked as public for testing.
     *
     * @return The number of pages.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public int getPageCount() {
        return mPdfRenderer.getPageCount();
    }


}
