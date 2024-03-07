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
import com.sumup.merchant.reader.api.SumUpPayment;
import com.sumup.merchant.reader.api.SumUpState;
import com.sumup.merchant.reader.identitylib.ui.activities.LoginActivity;
import com.sumup.merchant.reader.models.TransactionInfo;
import com.sumup.merchant.reader.ui.activities.CardReaderPageActivity;
import com.sumup.merchant.reader.ui.activities.CardReaderPaymentAPIDrivenPageActivity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

@CapacitorPlugin(name = "SumUp")
public class SumUpPlugin extends Plugin {

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
        Intent LoginIntent = new Intent(this.getActivity(), LoginActivity.class);
        LoginIntent.putExtra("isAffiliate", true);
        LoginIntent.putExtra("affiliate-key", key);

        startActivityForResult(call, LoginIntent, "handleResponse");
    }

    @PluginMethod(returnType = PluginMethod.RETURN_NONE)
    public void logout(PluginCall call) {
        if (SumUpAPI.isLoggedIn()) {
            SumUpAPI.logout();
        }

        call.resolve();
    }

    @ActivityCallback
    public void handleResponse(PluginCall call, ActivityResult result) {
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
        JSObject data = call.getData();

        if (data == null) {
            call.reject("options cannot be empty");
        }

        // We reject if its not, but this does not get recognized
        assert data != null;

        if (!data.has("total")) {
            call.reject("missing total");
        }

        if (!data.has("currency")) {
            call.reject("missing currency");
        }
        SumUpPayment.Currency currency = SumUpPayment.Currency.valueOf(data.getString("currency"));

        Intent CheckoutIntent = new Intent(this.getActivity(), CardReaderPaymentAPIDrivenPageActivity.class);
        CheckoutIntent.putExtra(SumUpAPI.Param.TOTAL, new BigDecimal(data.getString("total")));
        CheckoutIntent.putExtra(SumUpAPI.Param.CURRENCY, currency.getIsoCode());

        if (data.has("title")) {
            CheckoutIntent.putExtra(SumUpAPI.Param.TITLE, data.getString("title"));
        }

        if (data.has("receiptEmail")) {
            CheckoutIntent.putExtra(SumUpAPI.Param.RECEIPT_EMAIL, data.getString("receiptEmail"));
        }

        if (data.has("receiptSMS")) {
            CheckoutIntent.putExtra(SumUpAPI.Param.RECEIPT_PHONE, data.getString("receiptSMS"));
        }

        if (data.has("foreignTransactionId")) {
            CheckoutIntent.putExtra(SumUpAPI.Param.FOREIGN_TRANSACTION_ID, data.getString("foreignTransactionId"));
        } else {
            CheckoutIntent.putExtra(SumUpAPI.Param.FOREIGN_TRANSACTION_ID, UUID.randomUUID().toString());
        }

        if (data.has("skipSuccessScreen")) {
            CheckoutIntent.putExtra(SumUpAPI.Param.SKIP_SUCCESS_SCREEN, true);
        }

        if (data.has("skipFailedScreen")) {
            CheckoutIntent.putExtra(SumUpAPI.Param.SKIP_FAILED_SCREEN, true);
        }

        JSObject info = data.getJSObject("additionalInfo");
        if (info != null) {
            HashMap<String, String> infoObject = new HashMap<>();
            for (Iterator<String> it = info.keys(); it.hasNext(); ) {
                String key = it.next();
                infoObject.put(key, info.getString(key));
            }

            CheckoutIntent.putExtra(SumUpAPI.Param.ADDITIONAL_INFO, infoObject);
        }

        startActivityForResult(call, CheckoutIntent, "handlePaymentResponse");
    }

    @ActivityCallback
    public void handlePaymentResponse(PluginCall call, ActivityResult result) {
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

        // Error during execution of the SumUp Request
        if (resultCode <= 0) {
            call.reject(resultMessage, String.valueOf(resultCode));
        }

        JSObject res = new JSObject();
        res.put("ResultCode", resultCode);
        res.put("Message", resultMessage);

        // Sumup has handled the call successfully if the resultcode is positive
        if (resultCode != SumUpAPI.Response.ResultCode.SUCCESSFUL) {
            // Checkout was not successful
            call.resolve(res);
        }

        // Payment was successful, gather some more data about it.
        String TxCode = extras.getString(SumUpAPI.Response.TX_CODE);
        Boolean ReceiptSent = extras.getBoolean(SumUpAPI.Response.RECEIPT_SENT);

        JSObject JsTxInfo = new JSObject();
        TransactionInfo TxInfo = extras.getParcelable(SumUpAPI.Response.TX_INFO);
        if (TxInfo != null) {
            JsTxInfo.put("TransactionCode", TxInfo.getTransactionCode());
            JsTxInfo.put("MerchantCode", TxInfo.getMerchantCode());
            JsTxInfo.put("Amount", TxInfo.getAmount());
            JsTxInfo.put("Tip", TxInfo.getTipAmount());
            JsTxInfo.put("VAT", TxInfo.getVatAmount());
            JsTxInfo.put("Currency", TxInfo.getCurrency());
            JsTxInfo.put("PaymentStatus", TxInfo.getStatus());
            JsTxInfo.put("PaymentType", TxInfo.getPaymentType());
            JsTxInfo.put("EntryMode", TxInfo.getEntryMode());
            JsTxInfo.put("Installments", TxInfo.getInstallments());
            JsTxInfo.put("CardType", TxInfo.getCard().getType());
            JsTxInfo.put("ForeignTransactionId", TxInfo.getForeignTransactionId());
        }

        res.put("TransactionCode", TxCode);
        res.put("TransactionInfo", JsTxInfo);
        res.put("ReceiptSent", ReceiptSent);

        call.resolve(res);
    }

    @PluginMethod(returnType = PluginMethod.RETURN_NONE)
    public void prepareForCheckout(PluginCall call) {
        call.unimplemented("Not implemented on Android.");
    }

    @PluginMethod
    public void openCardReaderPage(PluginCall call) {
        Intent viewSettingsIntent = new Intent(this.getActivity(), CardReaderPageActivity.class);
        startActivityForResult(call, viewSettingsIntent, "handleResponse");
    }
}
