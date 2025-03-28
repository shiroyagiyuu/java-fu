package shiroyagiyuu.javafu;

public class FuSymbol implements FuObject
{
	String  value;

	public FuSymbol(String s) {
		this.value = s;
	}

	public String get() {
		return this.value;
	}

	public void set(String s) {
		this.value = s;
	}

	public String getScript() {
		return this.value;
	}
}
