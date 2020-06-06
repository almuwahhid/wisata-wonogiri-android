package id.ac.akakom.bayu.wisatawonogiri.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UtilWisata {
    public static boolean isPreLolipop(){
        return Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP;
    }

    public static Bitmap getBitmap(int drawableRes, Activity activity) {
        Drawable drawable = activity.getResources().getDrawable(drawableRes);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    private static class BitmapFromURLTask extends AsyncTask<String, Void, Bitmap> {
        OnBitmapOk onBitmapOK;

        public BitmapFromURLTask(OnBitmapOk onBitmapOK) {
            this.onBitmapOK = onBitmapOK;
        }

        @Override
        protected Bitmap doInBackground(String[] params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        interface OnBitmapOk{
            void onOk(Bitmap bitmap);
        }

        @Override
        protected void onPostExecute(Bitmap bmp) {
            onBitmapOK.onOk(bmp);
        }
    }

    public static void hideSoftKeyboard(Context context, EditText ettext){
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(ettext.getWindowToken(), 0);
    }
}
