package app.globy.plugins.sumup;

import android.util.Log;

public class SumupPlugin {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
