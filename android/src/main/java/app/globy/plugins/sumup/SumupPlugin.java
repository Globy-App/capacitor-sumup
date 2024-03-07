package app.globy.plugins.sumup;

import com.getcapacitor.ActivityCallback;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Sumup")
public class SumupPlugin extends Plugin {

    private Sumup implementation = new Sumup();

    @Override
    public void load() {
        SumupState.init(this);
    }

    @PluginMethod
    public void login(PluginCall call) {
        // Get the affiliateKey from the options

        SumupLogin sumupLogin = SumUpLogin.builder()
    }

    @PluginMethod(returntype = PluginMethod.RETURN_NONE)
    public void logout(PluginCall call) {
        call.unimplemented("Not implemented on Android.");
    }

    @ActivityCallback
    public void handleAuthResponse(PluginCall call, ActivityResult result) {
        if (call == null) {
            return;
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

    @PluginMethod(returntype = PluginMethod.RETURN_NONE)
    public void prepareForCheckout(PluginCall call) {
        call.unimplemented("Not implemented on Android.");
    }

    @PluginMethod(returnType = PluginMethod.RETURN_NONE)
    public void openCardReaderPage(PluginCall call, ActivityResult result) {
        call.unimplemented("Not implemented on Android.");
    }
}
