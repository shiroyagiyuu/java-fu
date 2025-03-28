package shiroyagiyuu.javafu;

public class FuUtils
{
	public static FuCommand createCmdAry(String cmd, Object[] args) {
		FuCommand  fucmd;
		int  n = args.length;

		fucmd = new FuCommand(cmd);

		for (int i=0; i<n; i++) {
			Object    obj = args[i];

			if (obj instanceof Integer) {
				int  val = (int)obj;
				fucmd.add( new FuInteger(val) );
			} else if (obj instanceof String) {
				String val = (String)obj;
				fucmd.add( new FuString(val) );
			}
		}

		return fucmd;
	}

	public static FuCommand createCmd(String cmd, Object... args) {
		return createCmdAry(cmd, args);
	}
}


