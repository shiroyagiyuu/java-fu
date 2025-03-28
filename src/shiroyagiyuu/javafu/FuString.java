package shiroyagiyuu.javafu;

public class FuString implements FuObject
{
	String  value;

	public FuString(String s) {
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
