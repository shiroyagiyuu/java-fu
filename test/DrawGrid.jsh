import shiroyagiyuu.javafu.*;
FuList		res;

GimpClient  cli = new GimpClient();

//res = cli.call("gimp-image-new", 512, 512, "RGB");
res = cli.call("gimp-image-list");
System.out.println("Script="+ res.getScript());
System.out.println("obj=" + res);
System.out.println("value="+res.get(0));

res = cli.call("gimp-brushes-get-list", "");
System.out.println("Script="+res.getScript());

/*
res = cli.call("gimp-display-new", res.get(0));
System.out.println("Script="+res.getScript());
*/

