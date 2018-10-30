package com.beyondsot.latte.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.beyondsot.latte.app.Latte;
import com.beyondsot.latte.net.callback.IRequest;
import com.beyondsot.latte.net.callback.ISuccess;
import com.beyondsot.latte.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 *  文件異步緩存
 */
public class SaveFileTask extends AsyncTask<Object,Void,File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }
    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0]; // 文件路徑
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2]; // 請求體
        final String name = (String) params[3]; //文件名稱
        final InputStream is = body.byteStream(); // 輸入流
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension); // 寫入到內存 卡
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name); //默認帶文件名稱
        }

    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    /**
     *  安裝apk
     * @param file
     */
    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }

}
