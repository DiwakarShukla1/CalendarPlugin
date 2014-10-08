package org.devgirl.calendar;

import LocationUpdateService; 
 
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;

public class Calendar extends CordovaPlugin {
    public static final String ACTION_ADD_CALENDAR_ENTRY = "addCalendarEntry";
    
    private Intent updateServiceIntent;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Activity activity = this.cordova.getActivity();
        try {
            if (ACTION_ADD_CALENDAR_ENTRY.equals(action)) { 
              updateServiceIntent = new Intent(activity, LocationUpdateService.class);
              activity.startService(updateServiceIntent);
           
           // Register the listener with the Location Manager to receive location updates
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
