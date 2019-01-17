package ui;

import android.content.Context;

public class WebService {
    /*private RequestQueue mRequestQueue;
    private static WebService apiRequests = null;
    private String url = "http://hacaton2018app.herokuapp.com/events";

    public static WebService getInstance() {
        if (apiRequests == null) {
            apiRequests = new WebService();
            return apiRequests;
        }
        return apiRequests;
    }*/

    public void updateProfile(Context context, String discription, String title, String address, String koord /*File file,*/ /*Response.Listener<String> listener, Response.ErrorListener errorListener*/) {
        /*SimpleMultiPartRequest events = new SimpleMultiPartRequest(Request.Method.POST, url, listener, errorListener);
//        request.setParams(data);
        mRequestQueue = RequestManager.getnstance(context);
        events.addMultipartParam("discription", "text", discription);
        events.addMultipartParam("title", "text", title);
        events.addMultipartParam("address", "text", address);
        events.addMultipartParam("koord", "text", koord);
        //request.addFile("document_file", file.getPath());

        events.setFixedStreamingMode(true);
        mRequestQueue.add(events);*/
    }
}
