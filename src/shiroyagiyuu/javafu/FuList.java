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

	public void addAll(Object[] objs)
	{
		int  len = objs.length;
		for (int i=0; i<len; i++)
		{
			Object    obj = objs[i];

			if (obj instanceof Integer) {
				int  val = (int)obj;
				l.add( new FuInteger(val) );
			} else if (obj instanceof String) {
				String val = (String)obj;
				l.add( new FuString(val) );
			} else if (obj instanceof FuObject) {
				FuObject  val = (FuObject)obj;
				l.add( val );
			}
		}
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

	public static FuList create(Object... o) {
		FuList  l = new FuList();
		l.addAll(o);

		return l;
	}
}

