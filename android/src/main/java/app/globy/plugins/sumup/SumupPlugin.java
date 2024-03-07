package app.globy.plugins.sumup;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.sumup.merchant.reader.api.SumUpAPI;
import com.sumup.merchant.reader.api.SumUpState;

@CapacitorPlugin(name = "SumUp")
public class SumupPlugin extends Plugin {

    @Override
    public void load() {
        SumUpState.init(this.getContext());
    }

    @PluginMethod
    public void login(PluginCall call) {
        // Get the affiliateKey from the options
        String key = call.getData().getString("affiliatekey");

        if (key == null) {
            throw new IllegalArgumentException("affiliatekey is not set");
        }


        // DEV NOTE: The SumupLogin Builder is not used as we can't get the intent from it later.
        //  We require this intent to get the result and give it back to capacitor.
        Intent LoginIntent = new Intent();
        LoginIntent.putExtra("affiliate-key", key);

        startActivityForResult(call, LoginIntent, "handleAuthResponse");
    }

    @PluginMethod(returnType = PluginMethod.RETURN_NONE)
    public void logout(PluginCall call) {
        call.unimplemented("Not implemented on Android.");
    }

    @ActivityCallback
    public void handleAuthResponse(PluginCall call, ActivityResult result) {
        if (call == null) {
            return;
        }

        Intent data = result.getData();
        if (data == null) {
            return;
        }

        Bundle extras = data.getExtras();
        if (extras == null) {
            return;
        }

        int resultCode = extras.getInt(SumUpAPI.Response.RESULT_CODE);
        String resultMessage = extras.getString(SumUpAPI.Response.MESSAGE);

        // Sumup has handled the call successfully if the resultcode is positive
        if (resultCode > 0) {
            JSObject res = new JSObject();
            res.put("ResultCode", resultCode);
            res.put("Message", resultMessage);

            call.resolve(res);
        } else {
            call.reject(resultMessage, String.valueOf(resultCode));
        }
    }

    @PluginMethod
    public void makePayment(PluginCall call) {
        call.unimplemented("Not implemented on Android.");
    }

    @ActivityCallback
    public void handlePaymentResponse(PluginCall call, ActivityResult result) {
        if (call == null) {
            return;
        }
    }

    @PluginMethod(returnType = PluginMethod.RETURN_NONE)
    public void prepareForCheckout(PluginCall call) {
        call.unimplemented("Not implemented on Android.");
    }

    @PluginMethod(returnType = PluginMethod.RETURN_NONE)
    public void openCardReaderPage(PluginCall call, ActivityResult result) {
        call.unimplemented("Not implemented on Android.");
    }
}
