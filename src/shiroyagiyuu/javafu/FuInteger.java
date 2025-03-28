package shiroyagiyuu.javafu;

public class FuInteger implements FuObject
{
	int  value;

	public FuInteger(int i) {
		this.value = i;
	}

	public int get() {
		return value;
	}

	public void set(int i) {
		this.value = i;
	}

	public String getScript() {
		return Integer.toString(value);
	}
}

