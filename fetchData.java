package labrador.cse.usf.parsetest2;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;

import static labrador.cse.usf.parsetest2.MainActivity.zip;

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed ="";
    String singleParsed ="";

    //String icon_restaurant="";
//String zip=get().getExtra("zip");



    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //dataParsed=dataParsed+zip;
            String url1="https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+";
            String url2="&key=AIzaSyBDSrahseWZ95JmdeyfIDmGAuB6Aaodiz0";
            URL url = new URL(url1+zip+url2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            dataParsed=dataParsed+"URL used:  " +url1+zip+url2+"\n";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject parentObject = new JSONObject(data);

            JSONArray resultsArray= parentObject.getJSONArray("results");
            for(int i =0 ;i <resultsArray.length(); i++){

                JSONObject JO = resultsArray.getJSONObject(i);

                JSONObject address=resultsArray.getJSONObject(i);
                String address_restaurant= address.getString("formatted_address");
                JSONObject locationObj=JO.getJSONObject("geometry").getJSONObject("location");
                String lat= locationObj.getString("lat");
                String lng= locationObj.getString("lng");

                JSONObject iconObject=resultsArray.getJSONObject(i);
                String icon_restaurant= iconObject.getString("icon");
                JSONObject nameObject=resultsArray.getJSONObject(i);
                String name_restaurant= nameObject.getString("name");
                JSONObject idObject=resultsArray.getJSONObject(i);
                String id_restaurant= idObject.getString("id");
                singleParsed =  "Name:" + name_restaurant + "\n"+
                        "Address:" + address_restaurant + "\n"+
                        "Icon Link: "+ icon_restaurant + "\n"+
                        "Lat and longitude: " + lat +" " + lng +"\n"+
                        "ID: "+ id_restaurant+"\n";



                dataParsed = dataParsed + singleParsed +"\n" ;


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(this.dataParsed);
        //MainActivity.url.concat(this.icon_restaurant);

    }
}