import java.io.IOException;

import com.webraid.cocproxy.Proxy;
import com.webraid.cocproxy.gui.InterceptGUI;

public class Start {
	public static void main(String[] args) throws IOException {
		InterceptGUI.create();
		Proxy.start();
	}
}
