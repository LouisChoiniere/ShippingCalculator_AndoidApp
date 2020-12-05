package com.louis.shippingcalculator.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.louis.shippingcalculator.model.PriceCalculator;
import com.louis.shippingcalculator.util.AsyncResponse;
import com.louis.shippingcalculator.util.MySingleton;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PriceCalculatorController {
    private static final String TAG = "PriceCalculatorCtrl";

    private Context context;
    private RequestQueue requestQueue;

    public PriceCalculatorController(Context context) {
        this.context = context;

        requestQueue = MySingleton.getInstance(context.getApplicationContext()).getRequestQueue();
    }

    public void getShippingPrice(PriceCalculator priceCalculator, final AsyncResponse asyncResponse) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "https://ct.soa-gw.canadapost.ca/rs/ship/price",
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.d(TAG, "onResponse: ");
                        asyncResponse.onFinished(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Accept", "application/vnd.cpc.ship.rate-v4+xml");
                params.put("Content-Type", "application/vnd.cpc.ship.rate-v4+xml");
                params.put("Authorization", "Basic NmU5M2Q1Mzk2ODg4MTcxNDowYmZhOWZjYjk4NTNkMWY1MWVlNTdh");
                params.put("Accept-language", "en-CA");

                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=" +
                        getParamsEncoding();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                String postData =
                        "<mailing-scenario xmlns=\"http://www.canadapost.ca/ws/ship/rate-v4\">\n" +
                        "    <customer-number>2004381</customer-number>\n" +
                        "    <parcel-characteristics>\n" +
                        "        <weight>" + priceCalculator.getWeight() + "</weight>\n" +
                        "    </parcel-characteristics>\n" +
                        "    <origin-postal-code>" + priceCalculator.getFrom().getPostalCode().replaceAll("\\s+","") + "</origin-postal-code>\n" +
                        "    <destination>\n" +
                        "        <domestic>\n" +
                        "            <postal-code>" + priceCalculator.getTo().getPostalCode().replaceAll("\\s+","") + "</postal-code>\n" +
                        "        </domestic>\n" +
                        "    </destination>\n" +
                        "</mailing-scenario>";
                try {
                    return postData == null ? null :
                            postData.getBytes(getParamsEncoding());
                } catch (UnsupportedEncodingException uee) {
                    // TODO consider if some other action should be taken
                    return null;
                }
            }

        };

        requestQueue.add(stringRequest);
    }
}
