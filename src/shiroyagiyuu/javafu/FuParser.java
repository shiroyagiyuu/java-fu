package shiroyagiyuu.javafu;

public class FuParser
{
	String	str;
	int	n;

	final int TK_NONE=0;
	final int TK_SYMBOL=1;
	final int TK_INTEGER=2;
	final int TK_FLOAT=3;
	final int TK_STRING=4;

	public FuParser(String str) {
		this.str = str;
		this.n = 0;
	}

	private FuObject createToken(StringBuilder tk, int tktype) {
		System.out.println("token:"+tk.toString());

		if (tktype == TK_INTEGER) {
			int  n = Integer.parseInt(tk.toString());
			return new FuInteger(n);
		} else if (tktype == TK_SYMBOL) {
			return new FuString(tk.toString());
		} else {
			System.out.println("unknown type=" + tktype);
			return null;
		}
	}

	public FuObject parse(FuList l) {
		char  c;
		StringBuilder   tk;
		int		tktype = TK_NONE;

		System.out.println("str=" + str.substring(n));
		//first (
		while(str.charAt(n)!='(') {
			n++;
		}
		n++;

		tk = new StringBuilder();
		tktype = TK_NONE;
		for (;n<str.length();) {
			c = str.charAt(n);
			n++;
			switch(c) {
			case ' ':
				if (tktype != TK_NONE) {
					l.add(createToken(tk, tktype));
					tk = new StringBuilder();
					tktype = TK_NONE;
				}
				break;
			case '#':
				c = str.charAt(n);

				System.out.println("vector=" + str.substring(n-1));
				if (c=='(') {
					l.add(parse(new FuVector()));
				} else {
					System.out.println("unknown vector??");
				}
				break;
			case '\'':
				c = str.charAt(n);
				if (c=='(') {
					l.add(parse(new FuList()));
				} else {
					System.out.println("unknown list??");
				}
				break;
			case '(':
				n--;
				l.add(parse(new FuList()));
				break;
			case ')':
				if (tktype != TK_NONE) {
					l.add(createToken(tk, tktype));
				}
				return l;
			case '"':
				c = str.charAt(n);
				while (c!='"') {
					tk.append(c);
					n++;
					c = str.charAt(n);
				}
				l.add(new FuString(tk.toString()));
				tk = new StringBuilder();
				n++;
				tktype = TK_NONE;
				if (str.charAt(n)==' ') {
					n++;
				}
				break;
			default:
				tk.append(c);
				if (c<'0' || '9'<c) {
					tktype = TK_SYMBOL;
				} else if (tktype==TK_INTEGER && c=='.') {
					tktype = TK_FLOAT;
				} else {
					tktype = TK_INTEGER;
				}
			}
		}
		System.out.println("Malformat List: "+str);
		return l;
	}

	public FuObject parseList() {
		return parse(new FuList());
	}

	public static FuObject parseList(String str) {
		FuParser  parser = new FuParser(str);
		return parser.parseList();
	}
}

