package org.devgirl.calendar;
 
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class Calendar extends CordovaPlugin {
    public static final String ACTION_ADD_CALENDAR_ENTRY = "addCalendarEntry";
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            if (ACTION_ADD_CALENDAR_ENTRY.equals(action)) { 
               LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
               LocationListener locationListener = new LocationListener() {
                 public void onLocationChanged(Location location) {
                   // Called when a new location is found by the network location provider.
                   HttpClient httpclient = new DefaultHttpClient();
                   HttpPost httppost = new HttpPost("192.168.0.109:8099/postLoc");
               
                   try {
                       // Add your data
                       List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                       nameValuePairs.add(new BasicNameValuePair("id", "12345"));
                       nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
                       httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
               
                       // Execute HTTP Post Request
                       HttpResponse response = httpclient.execute(httppost);
                       
                   } catch (ClientProtocolException e) {
                       // TODO Auto-generated catch block
                   } catch (IOException e) {
                       // TODO Auto-generated catch block
                   }
                   
                 }
             
                 public void onStatusChanged(String provider, int status, Bundle extras) {}
             
                 public void onProviderEnabled(String provider) {}
             
                 public void onProviderDisabled(String provider) {}
               };
           
           // Register the listener with the Location Manager to receive location updates
               locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
               callbackContext.success("Diwakar");
               return true;
            }
            callbackContext.error("Invalid action");
            return false;
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        } 
    }
}
