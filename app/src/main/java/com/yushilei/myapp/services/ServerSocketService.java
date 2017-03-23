package com.yushilei.myapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class ServerSocketService extends Service {
    public ServerSocketService() {
        Log.i(TAG, "new ServerSocketService");
    }

    private static final String TAG = "ServerSocketService";
    boolean isDestroy = false;

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        new Thread(new TcpServer()).start();
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        isDestroy = true;
        return super.onUnbind(intent);
    }

    private class TcpServer implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
                String hostAddress = getIp();
                Log.i(TAG, "hostAddress:" + hostAddress + " port:" + 8688);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i(TAG, "establish tcp server failed ,port 8688");
                return;
            }
            while (!isDestroy) {
                try {
                    final Socket accept = serverSocket.accept();
                    Log.i(TAG, "serverSocket.accept()");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            response(accept);
                        }

                        private void response(Socket accept) {
                            //
                            try {
                                //接受信息
                                BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                                PrintWriter out = new PrintWriter(new OutputStreamWriter(accept.getOutputStream()));
                                out.println("Server: 欢迎启动ServerSocket");
                                out.flush();

                                while (!isDestroy) {
                                    String s = in.readLine();
                                    Log.i(TAG, "client: " + s);
                                    if (TextUtils.isEmpty(s)) {
                                        break;
                                    }
                                }
                                in.close();
                                out.close();
                                accept.close();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private String getIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference", ex.toString());
        }
        return null;
    }
}
