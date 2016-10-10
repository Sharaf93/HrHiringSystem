package pagecode;

public class Test {
	
	String s1,s2;
	Test obj;
	public String getS1() {
		return s1;
	}
	public void setS1(String s1) {
		this.s1 = s1;
	}
	public String getS2() {
		return s2;
	}
	public void setS2(String s2) {
		this.s2 = s2;
	}
	public Test getObj() {
		if (obj == null)
			obj = new Test();
		return obj;
	}
	public void setObj(Test obj) {
		this.obj = obj;
	}

}
