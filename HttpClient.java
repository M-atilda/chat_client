import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
public class HttpClient {
    static String m_url_str;
    static int m_port_num;
    
    public HttpClient(String url_str, int port_num)
    {
	HttpClient.m_url_str = url_str;
	HttpClient.m_port_num = port_num;
    }
    
    public static String request(String data) {
		Socket socket = null;
		BufferedReader in = null;
		BufferedWriter out = null;
        StringBuilder result = new StringBuilder();
		try {
			socket = new Socket();
			// ここが違う。送信したいサーバのIPアドレスとポート番号を定義
			socket.connect(new InetSocketAddress(m_url_str, m_port_num));
 
			out = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			out.write(data);
			out.newLine();
			out.flush();
        
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
        return result.toString();
	}
}
