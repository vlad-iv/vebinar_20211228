package server;

import com.sun.net.httpserver.HttpHandler;

public interface PathHandler extends HttpHandler {
	String getPah();
}
