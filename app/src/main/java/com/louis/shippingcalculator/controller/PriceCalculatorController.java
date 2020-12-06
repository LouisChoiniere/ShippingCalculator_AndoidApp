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
import com.louis.shippingcalculator.model.PriceQuote;
import com.louis.shippingcalculator.util.AsyncResponse;
import com.louis.shippingcalculator.util.MySingleton;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

                        List<PriceQuote> priceQuotes = new ArrayList<>();

                        try {
                            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                            Document doc = dBuilder.parse(new InputSource(new StringReader((String) response)));
                            doc.getDocumentElement().normalize();

                            NodeList elements = doc.getElementsByTagName("price-quote");

                            for (int i = 0; i < elements.getLength(); i++) {
                                Element element = (Element) elements.item(i);

                                PriceQuote priceQuote = new PriceQuote();
                                priceQuote.setName(element.getElementsByTagName("service-name").item(0).getTextContent());
                                priceQuote.setPrice(element.getElementsByTagName("base").item(0).getTextContent());

                                priceQuotes.add(priceQuote);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        asyncResponse.onFinished(priceQuotes);
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
                Map<String, String> params = new HashMap<String, String>();
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
                                "    <origin-postal-code>" + priceCalculator.getFrom().getPostalCode().replaceAll("\\s+", "") + "</origin-postal-code>\n" +
                                "    <destination>\n" +
                                "        <domestic>\n" +
                                "            <postal-code>" + priceCalculator.getTo().getPostalCode().replaceAll("\\s+", "") + "</postal-code>\n" +
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
