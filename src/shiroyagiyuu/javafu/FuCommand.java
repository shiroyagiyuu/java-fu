package shiroyagiyuu.javafu;

import java.util.*;

public class FuCommand extends FuList
{
	public FuCommand(String cmd) {
		add(new FuSymbol(cmd));
	}

	public String getScript() {
		StringBuilder sb = new StringBuilder();
		String   s;

		sb.append("(");
		s = l.get(0).getScript();
		sb.append(s);
		for (int i=1;i<l.size();i++) {
			sb.append(" ");
			s = l.get(i).getScript();
			sb.append(s);
		}
		sb.append(")");

		return sb.toString();
	}

	public static FuCommand create(String com, Object... args) {
		FuCommand  fucom = new FuCommand(com);
		fucom.addAll(args);

		return fucom;
	}
}

