package classes;

import android.util.Log;

import org.jetbrains.annotations.Contract;
import org.json.JSONException;
import org.json.JSONObject;

public class GeoJson {
    private static final String TAG;

    static {
        TAG = "CLASS - GeoJson";
    }

    private String inData;

    public GeoJson() {
        inData = null;
    }

    @Contract(pure = true)
    private String getInData() {
        return inData;
    }

    private void setInData(String inData) {
        Log.d(TAG, "setInData: " + inData);
        this.inData = inData;
    }

    public GeoJson deffInData(String inData){
        this.setInData(inData);

        return this;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject(getInData());
        Log.d(TAG, "toJson: " + json);

        return json;
    }
}
