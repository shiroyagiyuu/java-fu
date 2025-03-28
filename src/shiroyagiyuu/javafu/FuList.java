package shiroyagiyuu.javafu;

import java.util.*;

public class FuList implements FuObject
{
	ArrayList<FuObject>	l;

	public FuList() {
		l = new ArrayList<FuObject>();
	}

	public void add(FuObject obj) {
		l.add(obj);
	}

	public void add(String str) {
		l.add(new FuString(str));
	}
	
	public void add(int i) {
		l.add(new FuInteger(i));
	}

	public FuObject get(int i) {
		return l.get(i);
	}

	public int getFuInt(int i) {
		FuObject  o = l.get(i);
		if (o instanceof FuInteger) {
			FuInteger  fi = (FuInteger)o;
			return fi.get();
		}
		return -1; //return exception
	}

	public String getFuString(int i) {
		FuObject  o = l.get(i);
		if (o instanceof FuString) {
			FuString  fs = (FuString)o;
			return fs.get();
		}
		return null; //return exception
	}

	public String getScript() {
		StringBuilder sb = new StringBuilder();
		String   s;

		sb.append("'(");
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

}

