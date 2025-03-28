package shiroyagiyuu.javafu;

import java.io.*;
import java.net.*;

public class GimpClient
{
	String	host;
	int	port;

	Socket	soc;
	OutputStream	out_st;
	InputStream	in_st;

	int  result;

	public GimpClient(String host, int port)
	{
		this.host = host;
		this.port = port;
	}

	public GimpClient() {
		this("127.0.0.1", 10008);
	}

	public void open() throws IOException {
		soc = new Socket(host, port);

		out_st = soc.getOutputStream();
		in_st = soc.getInputStream();
	}

	public void send(String msg) throws IOException {
		int	msglen = msg.length();
		int	cmdlen = msglen + 3;
		byte[]	buf = new byte[cmdlen];

		buf[0] = 0x47;
		buf[1] = (byte)(msglen / 256);
		buf[2] = (byte)(msglen % 256);
		byte[] msgbyte = msg.getBytes();

		for (int i=0; i<msglen; i++) {
			buf[i+3] = msgbyte[i];
		}

		out_st.write(buf, 0, cmdlen);
		out_st.flush();
	}

	public String recv() throws IOException {
		byte[]  hdr = new byte[4];
		int l=0,off=0;

		l = in_st.read(hdr,0,4);
		while (off+l < 4) {
			off += l;
			l = in_st.read(hdr,off,4-off);
		}

		System.out.println("hdrlen=" + (off+l) + ",hdr=" + hdr[0]);

		result = hdr[1];
		System.out.println("result=" + result + ": " + ((result==0)?"OK":"Error"));

		int     len = hdr[2]*256 + hdr[3];
		System.out.println("len=" + len);
		byte[]  buf = new byte[len];

		l = off = 0;
		while (off+l < len) {
			off += l;
			l = in_st.read(buf,off,len-off);
		}
		for (int i=0; i<buf.length; i++) {
			System.out.println(Integer.toHexString(buf[i]));
		}
		return new String(buf,0,len);
	}

	public String runmsg(String msg) throws IOException {
		send(msg);
		return recv();
	}

	public FuList runmsg(FuCommand msg) throws IOException {
		result = 0;
		send(msg.getScript());
		String    res = recv();

		if (result==0) {
			FuObject  obj = FuParser.parseList(res);

			if (obj instanceof FuList) {
				return (FuList)obj;
			} else {
				System.out.println("Unknown Result: "+res);
				return null;
			}
		} else {
			System.out.println("Error Return: " + res);
			return null;
		}
	}

	public FuList call(String cmd, Object... args) throws IOException {
		return runmsg(FuUtils.createCmd(cmd, args));
	}

	public void close() {
		try {
			out_st.close();
			in_st.close();
			soc.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GimpClient	cli;
		FuList	cmd;
		FuList	res;

		try {
			cli = new GimpClient();

			cli.open();

			res = cli.call("gimp-image-new", 512, 512, "RGB");
			System.out.println("Script="+ res.getScript());
			System.out.println("obj=" + res.toString());
			System.out.println("value="+res.get(0).toString());

			FuList cmd2 = new FuList();
			res = cli.call("gimp-display-new", res.get(0));
			System.out.println("Script="+res.getScript());
			cli.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

