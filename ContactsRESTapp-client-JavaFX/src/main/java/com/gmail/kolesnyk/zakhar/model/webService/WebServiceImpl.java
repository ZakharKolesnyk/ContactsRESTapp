package com.gmail.kolesnyk.zakhar.model.webService;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebServiceImpl implements WebService {

    public String sendRequest(String request, String method, String json) throws IOException {
        StringBuilder output = new StringBuilder("");
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        conn.setRequestMethod(method);
        if (json != null) {
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json);
            wr.flush();
            wr.close();
        }
        BufferedReader br;
        String line;
        try (InputStream is = conn.getInputStream()) {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                output.append(line);
            }
        }
        return output.toString();
    }
}
