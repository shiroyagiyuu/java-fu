package shiroyagiyuu.javafu;

import java.io.*;

public class DrawGrid
{
	public static void main(String[] args)
	{
		GimpClient	cli;
		FuList		res;

		try {
			cli = new GimpClient();

			cli.open();

			//res = cli.call("gimp-image-new", 512, 512, "RGB");
			res = cli.call("gimp-image-list");
			System.out.println("Script="+ res.getScript());
			System.out.println("obj=" + res.toString());
			System.out.println("value="+res.get(0).toString());

			/*
			res = cli.call("gimp-display-new", res.get(0));
			System.out.println("Script="+res.getScript());
			*/
			cli.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
