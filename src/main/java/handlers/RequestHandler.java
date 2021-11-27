package handlers;

import model.Request;

import java.util.LinkedList;
import java.util.Queue;

public final class RequestHandler {
    private static final Queue<Request> requestQueue = new LinkedList<>();

    public Request getRequest() {
        return requestQueue.poll();
    }

    public void addRequest(Request request) {
        requestQueue.add(request);
    }
}
