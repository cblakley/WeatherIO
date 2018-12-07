package colin.blakley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadActivity extends AppCompatActivity {

    URL url1;
    int k = 0;
    String[] fileName;
    ImageView mImageView;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        File file = new File("/data/data/colin.blakley/files/DownLoad/Humber_Black_Cen.gif/");
        if(file.exists()){
            displayImage();
        }

        try {
            url1  = new URL("http://humber.ca/brand/sites/default/files/styles/300px/public/images/Humber_Black_Cen.gif?itok=WS2QVCnS");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mImageView = (ImageView) findViewById(R.id.colin06);
    }
    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

        @Override
        protected Long doInBackground(URL... urls) {
            int count = urls.length;

            // set the size of the array that holds the file names
            fileName = new String[count];

            long totalSize = 0;
            for (int i = 0; i < count; i++) {
                totalSize += DownloadFile(urls[i], i);
                publishProgress((int) ((i / (float) count) * 100));
                // Escape early if cancel() is called
                if (isCancelled()) break;
            }
            return totalSize;
        }

        private long DownloadFile(URL url, int i) {
            // TODO Auto-generated method stub
            int total = 0;
            try {


                URLConnection conn = url.openConnection();
                if (!(conn instanceof HttpURLConnection))
                    throw new IOException("Not an HTTP connection");


                HttpURLConnection httpConn  = (HttpURLConnection) conn;

                //httpConn.setAllowUserInteraction(false);
                //httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                int response = httpConn.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK) {
                    InputStream input = httpConn.getInputStream();

                    // download the file
                    // each portion will stored into an index
                    String[] path = url.getPath().split("/");
                    // we are interested only in the last index
                    String imageName = path[path.length - 1];
                    String PATH = getFilesDir() + "/DownLoad/" ;
                    File folder = new File(PATH);
                    folder.mkdirs();
                    fileName[i] = folder + "/" + imageName;
                    OutputStream output = new FileOutputStream(fileName[i]);

                    byte data[] = new byte[1024];

                    int count;
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return total;
        }



        protected void onPostExecute(Long result) {
            Toast.makeText(DownloadActivity.this, "Downloaded " + result + " bytes", Toast.LENGTH_LONG).show();
        }


        protected void onProgressUpdate(Integer... values) {
            Log.d("MyAsyncTask","onProgressUpdate - " + values[0]);
            int i = values.length;

            for (int ii = 1; ii< i;ii++){
                k= k+ values[ii];
            }
            //tv = (TextView) findViewById(R.id.colin07);
            //tv.setText(String.valueOf(k));
            Bitmap myBitmap = BitmapFactory.decodeFile(fileName[0]);
            mImageView.setImageBitmap(myBitmap);


        }


        protected void onCancelled() {
            // stop the progress
            tv.setText("Download stopped!!");
            Toast.makeText(DownloadActivity.this, "on Cancelled called", Toast.LENGTH_LONG).show();

        }
    }


    public void CancelDownload(View view){
        Toast.makeText(DownloadActivity.this, "CancelDownload", Toast.LENGTH_LONG).show();
        new DownloadActivity.DownloadFilesTask().cancel(true);

    }

    public void downloadImage(View view) {


        new DownloadActivity.DownloadFilesTask().execute(url1);
        //dw.execute(url1,url2,url3);


    }

    public void displayImage(){
        ImageView mImageView = (ImageView) findViewById(R.id.colin06);
        Bitmap myBitmap = BitmapFactory.decodeFile("/data/data/colin.blakley/files/DownLoad/Humber_Black_Cen.gif/");
        mImageView.setImageBitmap(myBitmap);
    }
}
